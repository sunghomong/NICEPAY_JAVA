package com.nicepay.api.nicepay.model.request;

import lombok.Getter;
import lombok.Setter;

/**
 * CancelPayRequest
 * Desc : comment
 *
 * @author : Sung Ho Cho
 * @version : 1.0
 * Date : 2024-12-27
 */
@Getter
@Setter
public class CancelPayRequest {

    /** 거래 ID (required) */
    private String tid;
    /** 상점 ID (required) */
    private String mid;
    /** 상점에서 부여하는 취소 주문번호 (Unique 하게 구성)(required) */
    private String moid;
    /** 취소 금액 (required) */
    private String cancelAmt;
    /** 취소 사유 (required) */
    private String cancelMsg;
    /** 부분취소 여부 (전체취소 :0 / 부분취소 : 1) (required) */
    private String partialCancelCode;
    /** 전문생성일시 (YYYY MM DD HH MISS) */
    private String ediDate;
    /** 위변조 검증 Data, Hex(SHA256(MID + CancelAmt + EdiDate + 상점키)) (required) */
    private String signData;

    /* ------------------------- */
    /* 해당 값 설정 시 각 값의 합이 CancelAmt 와 일치해야 함.(필드 사용 전 영업담당자 협의 필요) */
    /** 별도 공급가액 설정 시 사용 */
    private String supplyAmt;
    /** 별도 부가세 설정 시 사용 */
    private String goodsVat;
    /** 별도 봉사료 설정 시 사용 */
    private String serviceAmt;
    /** 별도 면세금액 설정 시 사용 */
    private String taxFreeAmt;
    /* ------------------------- */

    /** 응답파라메터 인코딩 방식 (utf-8 / euc-kr(default)) */
    private String charSet;
    /** 장바구니 결제 유형 (장바구니 결제: 1 / 그 외:0 ) */
    private String charType;
    /** 응답전문 유형 (default(미설정): JSON / KV(설정): Key= Value 형식 응답) */
    private String ediType;
    /** 상점 정보 전달용 예비필드 (Nice pay 가공없음) 전달한 값 그대로 반환. */
    private String mallReserved;

    /* ------------------------- */
    /* 고객 가상계좌 입금 후 환불 요청 시 사용 */
    /** 환불계좌번호 */
    private String refundAcctNo;
    /** 환불계좌 은행코드  */
    private String refundBankCd;
    /** 환불계좌주명 */
    private String refundAcctNm;
    /* ------------------------- */

}
