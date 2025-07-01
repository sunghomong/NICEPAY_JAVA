package com.nicepay.api.nicepay.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nicepay.api.nicepay.model.request.SendSmsLinkedRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * SendSmsLinkedResponse
 * Desc : NICE 링크결제 응답
 *
 * @author : Sung Ho Cho
 * @version : 1.0
 * Date : 2025-05-19
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendSmsLinkedResponse {

    /** 요청 일시 (YYYYMMDDHHMMSS) */
    @JsonProperty("reqDt")
    private String reqDt;

    /** 인증 구분 (예: 3) */
    @JsonProperty("authCl")
    private String authCl;

    /** 데이터 개수 */
    @JsonProperty("dataCnt")
    private int dataCnt;

    /** 요청 ID 목록 */
    @JsonProperty("data")
    private List<DataItem> data;

    /** 요청 정보 (요청 헤더 + 바디) */
    @JsonProperty("reqInfo")
    private SendSmsLinkedRequest reqInfo;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataItem {
        /** 요청 식별자 */
        @JsonProperty("reqId")
        private String reqId;
    }
}
