package com.nicepay.api.nicepay.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nicepay.api.nicepay.model.SmsLinkedDetail;
import com.nicepay.api.nicepay.model.request.GetSmsLinkedDetailRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * GetSmsLinkedDetailResponse
 * Desc : SMS 링크 내역 조회 response
 *
 * @author : Sung Ho Cho
 * @version : 1.0
 * Date : 2025-06-20
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetSmsLinkedDetailResponse {

    /** 요청 일시 (YYYYMMDDHHMMSS) */
    @JsonProperty("reqDt")
    private String reqDt;

    /** 사용자 ID별 조회 권한 표기 (2: MID, 3: GID, 4: AID)  */
    @JsonProperty("authCl")
    private String authCl;

    /** 데이터 개수 */
    @JsonProperty("dataCnt")
    private int dataCnt;

    /** 요청 정보 (요청 헤더 + 바디) */
    @JsonProperty("reqInfo")
    private GetSmsLinkedDetailRequest reqInfo;

    /** return data */
    @JsonProperty("data")
    private List<SmsLinkedDetail> data;

}
