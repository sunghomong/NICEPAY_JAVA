package com.nicepay.api.nicepay.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * TransactionStatusResponse
 * Desc : comment
 *
 * @author : Sung Ho Cho
 * @version : 1.0
 * Date : 2024-12-18
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionStatusResponse {

    /** 결과 코드
     * 0000 : 성공 / 그외 실패 */
    @JsonProperty("ResultCode")
    private String resultCode;
    /** 결과 메시지 */
    @JsonProperty("ResultMsg")
    private String resultMsg;
    /** 결과 구분 transaction ID */
    @JsonProperty("TID")
    private String tid;
    /** 거래 상태
     * 0 : 승인상태, 1 : 취소상태, 9 : 승인거래 없음 */
    @JsonProperty("Status")
    private String status;
    /** 승인 번호 */
    @JsonProperty("AuthCode")
    private String authCode;
    /** 승인 날짜 */
    @JsonProperty("AuthDate")
    private String authDate;

}
