package com.nicepay.api.nicepay.service;

import com.nicepay.api.common.exception.NicePayCode;
import com.nicepay.api.common.exception.NicePayException;
import com.nicepay.api.common.util.Connection;
import com.nicepay.api.common.util.ValidCheck;
import com.nicepay.api.nicepay.model.request.NetCancelRequest;
import com.nicepay.api.nicepay.model.request.TransactionStatusRequest;
import com.nicepay.api.nicepay.model.response.NetCancelResponse;
import com.nicepay.api.nicepay.model.response.TransactionStatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * NicePayService
 * Desc : comment
 *
 * @author : Sung Ho Cho
 * @version : 1.1
 * Date : 2024-12-26
 */

@Service
@Slf4j
public class NicePayService {

    @Value("${nicepay.transactionStatus}")
    private String transactionStatusUrl;

    private static final long SCHEDULE_DELAY_10_MS = 10 * 60 * 1000;
    private final ThreadPoolTaskScheduler scheduler;

    public NicePayService(ThreadPoolTaskScheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * 거래 내역 조회
     *
     * @param request 거래 내역 조회 request
     * @return TransactionStatusResponse 거래 내역 상태값
     * */
    public TransactionStatusResponse getTransactionStatus(TransactionStatusRequest request) throws NicePayException {
        MultiValueMap<String, String> requestData = new LinkedMultiValueMap<>();

        /* 유효성 검사 (필수 값) */
        ValidCheck.validNullCheck(
                request.getTid(),
                request.getMid(),
                request.getEdiDate(),
                request.getSignData()
        );

        requestData.add("CharSet", request.getCharSet());
        requestData.add("EdiDate", request.getEdiDate());
        requestData.add("EdiType", request.getEdiType());
        requestData.add("MID", request.getMid());
        requestData.add("SignData", request.getSignData());
        requestData.add("TID", request.getTid());

        TransactionStatusResponse response = Connection.sendRequest(
                transactionStatusUrl,
                requestData,
                createFormHeaders(),
                TransactionStatusResponse.class
        );

        if (!response.getResultCode().equals("0000")){
            throw new NicePayException(NicePayCode.BAD_REQUEST);
        }

        return response;
    }

    /**
     * 망취소 요청
     * @apiNote 1 dept : 인증 요청 (이때 스케쥴러 동작)
     * -> 2 dept : 결제 시 인증 응답
     * -> 3 dept : 응답 때 정보들 내부에 저장 후 데이터 일치 하는 지 여부 판단 후 망 취소 요청
     *
     * @param request 망 취소 요청 파라미터
     */
    public void netCancel(@RequestBody NetCancelRequest request) throws NicePayException {
        log.info("망취소 요청");

        MultiValueMap<String, String> requestData = new LinkedMultiValueMap<>();

        ValidCheck.validNullCheck(
                request.getTid(),
                request.getAuthToken(),
                request.getMid(),
                request.getAmt(),
                request.getEdiDate(),
                request.getSignData(),
                request.getNetCancelURL()
        );

        requestData.add("TID", request.getTid());
        requestData.add("AuthToken", request.getAuthToken());
        requestData.add("MID", request.getMid());
        requestData.add("Amt", request.getAmt());
        requestData.add("EdiDate", request.getEdiDate());
        requestData.add("SignData", request.getSignData());
        requestData.add("NetCancel", "1");
        requestData.add("CharSet", request.getCharSet());
        requestData.add("EdiType", request.getEdiType());
        requestData.add("MallReserved", request.getMailReserved());

        NetCancelResponse response = Connection.sendRequest(
                request.getNetCancelURL(),
                requestData,
                createFormHeaders(),
                NetCancelResponse.class
        );

        if (!response.getResultCode().equals("2001")){
            log.error("[nice pay] 망 취소 실패 error code : {}, error msg : {}",response.getErrorCd(),response.getErrorMsg());
            throw new NicePayException(NicePayCode.NET_CANCEL_FAIL.getCode(),response.getErrorMsg());
        }
    }

    /**
     * EUC-KR x-www-form-urlencoded 헤더 세팅
     *
     * @return HttpHeaders
     */
    public static HttpHeaders createFormHeaders() {
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(new MediaType("application", "x-www-form-urlencoded",  Charset.forName("EUC-KR")));

        return headers;
    }

    public void scheduleNetCancel(NetCancelRequest request) throws NicePayException {
        ValidCheck.validNullCheck(
                request.getNetCancelPK()
        );

        scheduler.schedule(() -> {
            try {
                /* 내부 데이터 조회 후 request 세팅 필요 */
                /* ex) 상태 코드 값 오류 시 망 취소 요청 아닐 시 pass */
                if (request.getNetCancelPK().equals("PK")){

                    /* request 세팅 후 전달 */
                    request.setTid("~~");
                    request.setAuthToken("~~");
                    request.setMid("~~");
                    request.setAmt("~~");
                    request.setEdiDate("~~");
                    request.setNetCancel("1");
                    request.setSignData("~~");
                    request.setNetCancelURL("~~");
                }

                netCancel(request);
            } catch (Exception e) {
                log.error("[nice pay] net cancel fail cause : ", e);
            }
        },new Date(System.currentTimeMillis() + SCHEDULE_DELAY_10_MS)); // 10분
    }
}
