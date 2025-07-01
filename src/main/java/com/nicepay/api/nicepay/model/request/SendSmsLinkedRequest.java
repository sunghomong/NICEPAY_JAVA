package com.nicepay.api.nicepay.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * SendSmsLinkedRequest
 * Desc : SMS 링크 발송 request
 *
 * @author : Sung Ho Cho
 * @version : 1.0
 * Date : 2025-05-19
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendSmsLinkedRequest {

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

        /** 상품명 (required) */
        @JsonProperty("goodsNm")
        private String goodsNm;

        /** 상품가격 (required) */
        @JsonProperty("goodsAmt")
        private String goodsAmt;

        /** 상품 주문번호 (required) */
        @JsonProperty("moid")
        private String moid;

        /** 구매자명 (required) */
        @JsonProperty("ordNm")
        private String ordNm;

        /** 구매자 이메일 (sendType이 1일 경우 필수) (required) */
        @JsonProperty("ordEmail")
        private String ordEmail;

        /** 구매자 휴대폰 번호 (하이픈 없이) (required) */
        @JsonProperty("ordHpNo")
        private String ordHpNo;

        /** 발송 내용 구분: 0=기본, 1=추가 (특정 조건 시 반드시 '0'으로 설정) (required) */
        @JsonProperty("type")
        private String type;

        /** 링크결제 페이지 내 로고 이미지 URL (https 프로토콜 허용, 최대 255자) */
        @JsonProperty("logoImageUrl")
        private String logoImageUrl;

        /** 페이지 색상 타입: blue(기본), green, purple, darkgray, red */
        @JsonProperty("skinType")
        private String skinType;

        /** 사업자 유형: 0=개인, 1=법인 */
        @JsonProperty("ordBusType")
        private String ordBusType;

        /** 사업자번호(개인: 생년월일(YYMMDD), 법인: 사업자번호(10자리)) */
        @JsonProperty("ordBusNo")
        private String ordBusNo;

        /** 결제 링크 발송 수단: 0=SMS(기본), 1=Email, 2=KAKAO */
        @JsonProperty("sendType")
        private String sendType;

        /** 결제링크 유효기간(YYYYMMDD) */
        @JsonProperty("payLimitDt")
        private String payLimitDt;

        /** 할부개월수 지정: 00=일시불, 02=2개월, 03=3개월... */
        @JsonProperty("multiSelectQuota")
        private String multiSelectQuota;

        /** 가맹점 여분필드 (결제통보 응답 시 그대로 전달됨) */
        @JsonProperty("mallReserved")
        private String mallReserved;
    }
}
