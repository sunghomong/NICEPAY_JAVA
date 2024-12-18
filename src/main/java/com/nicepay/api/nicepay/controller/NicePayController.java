package com.nicepay.api.nicepay.controller;

import com.nicepay.api.common.exception.NicePayCode;
import com.nicepay.api.common.exception.NicePayException;
import com.nicepay.api.common.model.NicePayResponse;
import com.nicepay.api.nicepay.model.request.TransactionStatusRequest;
import com.nicepay.api.nicepay.service.NicePayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * NicePayController
 * Desc : comment
 *
 * @author : Sung Ho Cho
 * @version : 1.0
 * Date : 2024-12-18
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
     * @return TransactionStatusResponse 거래 내역
     * */
    @PostMapping(value = "/getTransactionStatus")
    public NicePayResponse<?> getTransactionStatus(@RequestBody TransactionStatusRequest request) throws NicePayException {
        return new NicePayResponse<>(NicePayCode.OK,nicePayService.getTransactionStatus(request));
    }
}
