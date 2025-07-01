package com.nicepay.api.nicepay.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * GetSmsLinkedDetailRequest
 * Desc : SMS 링크 결제 내역 조회 request
 *
 * @author : Sung Ho Cho
 * @version : 1.0
 * Date : 2025-06-20
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetSmsLinkedDetailRequest {

    @JsonProperty("header")
    private NicePayHeader header;

    @JsonProperty("body")
    private Body body;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Body {

        /** 상점 로그인 ID (required) */
        @JsonProperty("usrId")
        private String usrId;

        /** 암호화 key (hex(sha256(sid + usrId + trDtm + MerchantKey))) (required) */
        @JsonProperty("encKey")
        private String encKey;

        /** 가맹점 ID (required) */
        @JsonProperty("mid")
        private String mid;

        /** NICE링크결제 등록 시 응답으로 받은 reqId */
        @JsonProperty("reqId")
        private String reqId;

    }
}