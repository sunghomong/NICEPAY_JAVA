package com.nicepay.api.nicepay.model.response;

import com.nicepay.api.nicepay.model.SmsLinkedDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * SmsLinkedResponse
 * Desc : nice -> inner(set) -> client
 *
 * @author : Sung Ho Cho
 * @version : 1.0
 * Date : 2025-06-20
 */
@Getter
@Setter
@NoArgsConstructor
public class SmsLinkedResponse {

    public SmsLinkedResponse(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    /** 0000: 성공 그외 실패 */
    private String resultCode;
    /** 결과 메시지 */
    private String resultMsg;
    /** 요청 일시 (YYYYMMDDHHMMSS) */
    private String regDt;
    /** 인증 구분 (예: 3) */
    private String authCl;
    /** 데이터 개수 */
    private int dataCnt;
    /** 링크 결제 내역 조회 response detail */
    private List<SmsLinkedDetail> detailList;

}