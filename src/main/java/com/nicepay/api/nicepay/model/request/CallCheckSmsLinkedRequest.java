package com.nicepay.api.nicepay.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * CallCheckSmsLinkedRequest
 * Desc : Client -> Server SMS 내역 조회
 *
 * @author : Sung Ho Cho
 * @version : 1.0
 * Date : 2025-05-19
 */

@Getter
@Setter
public class CallCheckSmsLinkedRequest {

    /** 전문 ID , 업무별 정의된 ID 입력
     * NICE링크결제 요청: 0501001, NICE링크결제 내역조회: 0501002 (required) */
    private String sid;
    /** API 전송 일시 (YYYYMMDDHHMISS) (required) */
    private String trDtm;
    /** 상점 로그인 ID (required) */
    private String usrId;
    /** 암호화 key (hex(sha256(sid + usrId + trDtm + MerchantKey))) (required) */
    private String encKey;
    /** 가맹점 ID (required) */
    private String mid;
    /** NICE링크결제 등록 시 응답으로 받은 reqId (required) */
    private String reqId;

}
