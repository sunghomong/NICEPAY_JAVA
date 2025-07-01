package com.nicepay.api.nicepay.controller;

import com.nicepay.api.common.exception.NicePayCode;
import com.nicepay.api.common.exception.NicePayException;
import com.nicepay.api.common.model.NicePayResponse;
import com.nicepay.api.nicepay.model.request.*;
import com.nicepay.api.nicepay.service.NicePayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Desc : 나이스 페이 api
 *
 * @author : Sung Ho Cho
 * @version : 1.2
 * Date : 2024-12-27
 */

@RestController
@RequestMapping("/nicePay")
@Slf4j
public class NicePayController {

    private final NicePayService nicePayService;

    public NicePayController(NicePayService nicePayService) {
        this.nicePayService = nicePayService;
    }

    /**
     * TID 거래 상태 조회 요청 API
     *
     * @param request 거래 내역 조회 request
     * @return NicePayResponse<TransactionStatusResponse>
     * */
    @PostMapping(value = "/getTransactionStatus")
    public NicePayResponse<?> getTransactionStatus(@RequestBody TransactionStatusRequest request) {
        try {
            return new NicePayResponse<>(NicePayCode.OK,nicePayService.getTransactionStatus(request));
        }catch (NicePayException ne) {
            return new NicePayResponse<>(ne.getCode(),ne.getMessage());
        }catch (Exception e) {
            log.error("[nice pay] 거래 내역 조회 오류 : ", e);
            return new NicePayResponse<>(NicePayCode.SERVER_ERROR,null);
        }
    }

    /**
     * 승인 취소 요청 API
     *
     * @param request 승인 요청 취소 request
     * @return NicePayResponse<CancelPayResponse>
     * */
    @PostMapping(value = "/cancelNicePay")
    public NicePayResponse<?> cancelNicePay(@RequestBody CancelPayRequest request) {
        try {
            return new NicePayResponse<>(NicePayCode.OK,nicePayService.cancelNicePay(request));
        }catch (NicePayException ne) {
            return new NicePayResponse<>(ne.getCode(),ne.getMessage());
        }catch (Exception e) {
            log.error("[nice pay] 거래 내역 조회 오류 : ", e);
            return new NicePayResponse<>(NicePayCode.SERVER_ERROR,null);
        }
    }

    /**
     * nice pay 망 취소 요청
     * @apiNote : 결제 후 10 분 후 내부 데이터 비교 후 망 취소 요청
     *
     * @param request payNo
     * @return NicePayResponse<Void>
     * */
    @PostMapping("/netCancel")
    public NicePayResponse<?> netCancel(@RequestBody NetCancelRequest request) {
        try {
            nicePayService.scheduleNetCancel(request);

            return new NicePayResponse<>(NicePayCode.OK,null);
        }catch (NicePayException ne) {
            return new NicePayResponse<>(ne.getCode(), ne.getMessage(), null);
        } catch (Exception e) {
            log.error("[nice pay] 망 취소 중 내부 에러 발생 :", e);
            return new NicePayResponse<>(NicePayCode.SERVER_ERROR,null);
        }
    }

    /**
     * 결제 문자 발송
     *
     * @return NicePayResponse<Void>
     * */
    @PostMapping("/sendSmsLinked")
    public NicePayResponse<?> sendSmsLinked(@RequestBody CallSendSmsLinkedRequest request) {
        try {
            nicePayService.sendSmsLinked(request);

            return new NicePayResponse<>(NicePayCode.OK,null);
        }catch (NicePayException ne) {
            return new NicePayResponse<>(ne.getCode(),ne.getMessage());
        }catch (Exception e) {
            log.error("[nice pay] 결제문자 발송 실패 err : ", e);
            return new NicePayResponse<>(NicePayCode.SERVER_ERROR,null);
        }
    }

    /**
     * sms 링크 내역 조회
     * @return NicePayResponse<Void>
     * */
    @PostMapping("/checkSmsLinked")
    public NicePayResponse<?> checkSmsLinked(@RequestBody CallCheckSmsLinkedRequest request) {
        try {
            return new NicePayResponse<>(NicePayCode.OK,nicePayService.getSmsLinkedDetail(request));
        }catch (NicePayException ne) {
            return new NicePayResponse<>(ne.getCode(),ne.getMessage());
        }catch (Exception e) {
            log.error("[nice pay] 결제문자 발송 실패 err : ", e);
            return new NicePayResponse<>(NicePayCode.SERVER_ERROR,null);
        }
    }

}
