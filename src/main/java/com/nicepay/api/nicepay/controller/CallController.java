package com.nicepay.api.nicepay.controller;

import com.nicepay.api.common.exception.NicePayCode;
import com.nicepay.api.common.exception.NicePayException;
import com.nicepay.api.common.model.NicePayResponse;
import com.nicepay.api.common.util.Hashing;
import com.nicepay.api.nicepay.model.request.CancelPayRequest;
import com.nicepay.api.nicepay.model.request.NetCancelRequest;
import com.nicepay.api.nicepay.model.request.TransactionStatusRequest;
import com.nicepay.api.nicepay.model.response.CancelPayResponse;
import com.nicepay.api.nicepay.model.response.TransactionStatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * CallController
 * Desc : NicePayController 호출 ex
 *
 * @author : Sung Ho Cho
 * @version : 1.1
 * Date : 2024-12-27
 */
@RestController
@RequestMapping("/testController")
@Slf4j
public class CallController {

    /**
     * 나이스 페이 상태 값 조회 후 업데이트 처리
     * */
    public void updateNicePayStatus() throws NicePayException {
        /* 내부 데이터 valid 체크 필요 */

        String plainText = "TID" + "MID" + "EdiDate" + "상점키"; // TID + MID + EdiDate + 상점키
        String hashingText = Hashing.encrypt(plainText.getBytes());

        TransactionStatusRequest request = new TransactionStatusRequest();
        request.setTid("TID");
        request.setMid("MID");
        request.setEdiDate("EdiDate");
        request.setSignData(hashingText);

        NicePayResponse<TransactionStatusResponse> response = sendRequest(
                "/nicePay/getNicePaySt",
                request,
                new ParameterizedTypeReference<>() {}
        );

        if (!response.getResultCode().equals(NicePayCode.OK.getCode())) {
            throw new NicePayException(response.getResultCode(),response.getResultMessage());
        }

//      TransactionStatusResponse statusResponse = response.getData();
        /* 내부 데이터 업데이트 가능 */
//        switch (statusResponse.getStatus()) {
//            case "0" : nicePaySt = "70";
//                break;
//            case "1" : nicePaySt = "10";
//                break;
//            case "9" :
//                log.error("거래 내역 조회 에러 발생 nice pay err msg : {}", response.getResultMsg());
//                nicePaySt = "90";
//                break;
//            default : nicePaySt = "20";
//        }

//        updateNicePaySt(vo.getPAY_NO(),nicePaySt);
    }


    public NicePayResponse<Void> netCancel(String pk) {
        NetCancelRequest request = new NetCancelRequest();

        request.setNetCancelPK(pk);

        return sendRequest(
                "/nicePay/netCancel",
                request,
                new ParameterizedTypeReference<NicePayResponse<Void>>() {}
        );
    }

    /**
     * nice pay 승인 취소
     * @apiNote : 결제건은 존재하지만 예약자가 없는 케이스
     * */
    @PostMapping("/cancelNicePay")
    public void cancelNicePay() throws NicePayException {
        CancelPayRequest req = new CancelPayRequest();
        req.setTid("TID");
        req.setMid("MID");
        req.setMoid("내부 고유 값(PK)");
        req.setCancelAmt("100");
        req.setCancelMsg("환불 요청에 따른 취소");
        req.setPartialCancelCode("1");
        req.setEdiDate("승인 요청 시 보낸 생성일시");

        String plainText = req.getTid() + req.getCancelAmt() + req.getEdiDate() + "상점키"; // (MID + CancelAmt + EdiDate + 상점키)
        String hashingText = Hashing.encrypt(plainText.getBytes());

        req.setSignData(hashingText);

        /* 나머지 필요에 따라 추가 가능 ex) */
        req.setRefundAcctNm("계좌 번호");
        req.setRefundAcctNo("은행 코드");
        req.setRefundBankCd("계좌주명");

        NicePayResponse<CancelPayResponse> response = sendRequest(
                "/nicePay/cancelNicePay",
                req,
                new ParameterizedTypeReference<>() {}
        );

        if (!response.getResultCode().equals(NicePayCode.OK.getCode())) {
            throw new NicePayException(response.getResultCode(),response.getResultMessage());
        }

        // TODO 필요에 따라 내부 데이터 업데이트 가능 CancelPayResponse cancelPayResponse = response.getData();...
    }



    /**
     * 전송하는 유틸리티 메서드
     *
     * @param url 요청 URL
     * @param request 요청 객체
     * @param responseType 응답 타입 (ParameterizedTypeReference<NicePayResponse<T>>) 고정
     * @param <T> 응답 타입
     * @return NicePayResponse<T>
     */
    private <M, T> NicePayResponse<T> sendRequest(String url, M request, ParameterizedTypeReference<NicePayResponse<T>> responseType) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<M> entity = new HttpEntity<>(request, headers);

        log.info("[nice pay] request url: {}", url);
        log.info("[nice pay] request entity: {}", entity);

        try {
            ResponseEntity<NicePayResponse<T>> responseEntity = restTemplate.exchange(
                    "localhost:38080" + "/" + url,
                    HttpMethod.POST,
                    entity,
                    responseType
            );

            log.info("[nice pay] response: {}", responseEntity);

            return responseEntity.getBody();
        } catch (Exception e) {
            log.error("[nice pay] api 요청 실패 : ", e);
            return new NicePayResponse<>(NicePayCode.SERVER_ERROR, null);
        }
    }
}
