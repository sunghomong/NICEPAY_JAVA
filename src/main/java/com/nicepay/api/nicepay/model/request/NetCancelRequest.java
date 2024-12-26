package com.nicepay.api.nicepay.model.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 망취소 요청 객체
 *
 * @author : Sung Ho Cho
 * @version : 1.0
 * Date : 2024-12-26
 */

@Getter
@Setter
public class NetCancelRequest {

    /** 거래 구분 TID (required) */
    private String tid;
    /** 토큰 (required) */
    private String authToken;
    /** 상점 ID (required) */
    private String mid;
    /** 결제금액 (required) */
    private String amt;
    /** 생성일시 (required) */
    private String ediDate;
    /** 망취소 여부, 망취소 시 “1” 설정 (required) */
    private String netCancel;
    /** 위변조 검증 Data, Hex(SHA256(TID + MID + EdiDate + 상점키)) (required) */
    private String signData;
    /** 인코딩 방식 */
    private String charSet;
    /** 응답전문 유형 */
    private String ediType;
    /** 상점 정보 전달용 예비필드 */
    private String mailReserved;
    /** 망취소 요청 url 승인시 전달 받은 url (required) */
    private String netCancelURL;
    /** 내부 데이터 pk 값 */
    private String netCancelPK;

}
