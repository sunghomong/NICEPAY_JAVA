package com.nicepay.api.nicepay.service;

import com.nicepay.api.common.exception.NicePayCode;
import com.nicepay.api.common.exception.NicePayException;
import com.nicepay.api.common.util.Connection;
import com.nicepay.api.common.util.ValidCheck;
import com.nicepay.api.nicepay.model.request.TransactionStatusRequest;
import com.nicepay.api.nicepay.model.response.TransactionStatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.nio.charset.Charset;

/**
 * NicePayService
 * Desc : comment
 *
 * @author : Sung Ho Cho
 * @version : 1.0
 * Date : 2024-12-18
 */

@Service
@Slf4j
public class NicePayService {

    @Value("${nicepay.transactionStatus}")
    private String transactionStatusUrl;


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

        TransactionStatusResponse response = Connection.sendRequest(transactionStatusUrl,requestData,createFormHeaders(),TransactionStatusResponse.class);

        if (!response.getResultCode().equals("0000")){
            throw new NicePayException(NicePayCode.BAD_REQUEST);
        }

        return response;
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
}
