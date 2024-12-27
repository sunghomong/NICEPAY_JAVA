package com.nicepay.api.nicepay.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CancelPayResponse
 * Desc : 승인 취소 요청 응답 파라미터
 *
 * @author : Sung Ho Cho
 * @version : 1.0
 * Date : 2024-12-27
 */

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CancelPayResponse {

    /** 결과 코드
     * 2001,2211 : 성공 / 그외 실패 */
    @JsonProperty("ResultCode")
    private String resultCode;
    /** 결과 메시지 */
    @JsonProperty("ResultMsg")
    private String resultMsg;
    /** 에러코드 */
    @JsonProperty("ErrorCD")
    private String errorCd;
    /** 에러메시지 */
    @JsonProperty("ErrorMsg")
    private String errorMsg;
    /** 금액 */
    @JsonProperty("CancelAmt")
    private String cancelAmt;
    /** 상점 ID */
    @JsonProperty("MID")
    private String mid;
    /** 주문번호 */
    @JsonProperty("Moid")
    private String moid;
    @JsonProperty(value = "Signature")
    private String signature;
    /** 결제수단 코드
     * CARD: 신용카드(SSG페이포함) / BANK: 계좌이체 / VBANK: 가상계좌 / CELLPHONE: 휴대폰결제 /
     * SSG_BANK: SSG 은행계좌 / GIFT_SSG: SSG머니 / GIFT_CULT: 컬쳐캐쉬(컬쳐랜드 문화상품권)
     * */
    @JsonProperty(value = "PayMethod")
    private String payMethod;
    /** 결과 구분 transaction ID */
    @JsonProperty("TID")
    private String tid;
    /** 휴대폰 부분취소/부분환불일 경우 응답됨.
     * 2회차 이상의 휴대폰 부분취소/부분환불 요청 시, 응답전문 내 OTID 로 취소요청 되어야 함. */
    @JsonProperty("OTID")
    private String otid;
    /** 취소일자, YYYYMMDD */
    @JsonProperty("CancelDate")
    private String cancelDate;
    /** 취소시간, HHmmss */
    @JsonProperty("CancelTime")
    private String cancelTime;
    /** 취소번호 */
    @JsonProperty("CancelNum")
    private String cancelNum;
    /** 취소 후 잔액 -> 000000001000 */
    @JsonProperty("RemainAmt")
    private String remainAmt;
    /** 상점 정보 전달용 예비필드 */
    @JsonProperty("MallReserved")
    private String mallReserved;

    /* ==================================================
     * 신용카드 건 추가 응답 파라미터 */

    /** 쿠폰금액 */
    @JsonProperty("CouponAmt")
    private String couponAmt;
    /** 간편결제 서비스명
     * 6: SKPAY / 8: SAMSUNGPAY (구버전 사용 시) / 15: PAYCO /
     * 16: KAKAOPAY / 20: NAVERPAY / 21: SAMSUNGPAY / 22: APPLEPAY
     * */
    @JsonProperty("ClickpayCl")
    private String clickpayCl;

    /* 페이코, 카카오 결제 시에만 응답 */
    /** 신용카드 금액 */
    @JsonProperty("MultiCardAcquAmt")
    private String multiCardAcquAmt;
    /** 포인트 금액 */
    @JsonProperty("MultiPointAmt")
    private String multiPointAmt;
    /** 쿠폰 금액 */
    @JsonProperty("MultiCouponAmt")
    private String multiCouponAmt;

    /* 페이코 결제 시에만 응답 */
    /** 현금영수증 발급 취소 대상 금액 (null able) */
    @JsonProperty("MultiRcptAmt")
    private String multiRcptAmt;

    /* ================================================= */
}
