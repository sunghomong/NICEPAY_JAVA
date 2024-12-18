package com.nicepay.api.nicepay.model.request;

import lombok.Getter;
import lombok.Setter;

/**
 * TransactionStatusRequest
 * Desc : comment
 *
 * @author : Sung Ho Cho
 * @version : 1.0
 * Date : 2024-12-18
 */

@Getter
@Setter
public class TransactionStatusRequest {

    /** 거래 구분 TID */
    private String tid;
    /** 상정 ID */
    private String mid;
    /** 생성일시 */
    private String ediDate;
    /** 위변조 검증 Data, Hex(SHA256(TID + MID + EdiDate + 상점키)) */
    private String signData;
    /** 인코딩 방식 */
    private String charSet;
    /** 응답전문 유형 */
    private String ediType;

}
