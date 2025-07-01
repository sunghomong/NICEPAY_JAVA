package com.nicepay.api.nicepay.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * SmsLinkedDetail
 * Desc : SMS Link 내역 조회 Detail
 *
 * @author : Sung Ho Cho
 * @version : 1.0
 * Date : 2025-06-11
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsLinkedDetail {

    /** 가맹점상호 */
    @JsonProperty("coNm")
    private String coNm;

    /** 가맹점 ID */
    @JsonProperty("mid")
    private String mid;

    /** 결제수단 (신용카드, 계좌이체, 가상계좌, 휴대폰) */
    @JsonProperty("svcNm")
    private String svcNm;

    /** 결제내역 (미완료, 결제완료, 결제실패, 결제중지) */
    @JsonProperty("payStatus")
    private String payStatus;

    /** 발송일자 (YYYYMMDD) */
    @JsonProperty("sendDt")
    private String sendDt;

    /** 결제일자 (YYYYMMDD) */
    @JsonProperty("payDt")
    private String payDt;

    /** 거래금액 */
    @JsonProperty("amt")
    private String amt;

    /** 구매자명 */
    @JsonProperty("ordNm")
    private String ordNm;

    /** 상품 주문번호 */
    @JsonProperty("moid")
    private String moid;

    /** 구매자 이메일 */
    @JsonProperty("ordEmail")
    private String ordEmail;

    /** 구매자 전화번호 */
    @JsonProperty("ordHpNo")
    private String ordHpNo;

    /** 전송 결과 (성공, 실패) */
    @JsonProperty("sentStatus")
    private String sentStatus;

    /** 거래 아이디 (Transaction ID) */
    @JsonProperty("tid")
    private String tid;

    /** 상품명 */
    @JsonProperty("goodsNm")
    private String goodsNm;


}
