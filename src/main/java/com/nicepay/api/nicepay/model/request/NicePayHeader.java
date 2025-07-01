package com.nicepay.api.nicepay.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * NicePayHeader
 * Desc : 나이스페이 공통 헤더
 *
 * @author : Sung Ho Cho
 * @version : 1.0
 * Date : 2025-05-19
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NicePayHeader {

    /** 전문 ID , 업무별 정의된 ID 입력
     * NICE링크결제 요청: 0501001, NICE링크결제 내역조회: 0501002 (required) */
    @JsonProperty("sid")
    private String sid;

    /** API 전송 일시 (YYYYMMDDHHMISS) (required) */
    @JsonProperty("trDtm")
    private String trDtm;

    /** 전문 구분 (요청: ‘S’, 응답: ‘R’) (required) */
    @JsonProperty("gubun")
    private String gubun;

    /** 결과코드 */
    @JsonProperty("resCode")
    private String resCode;

    /** 결과메세지 */
    @JsonProperty("resMsg")
    private String resMsg;
}
