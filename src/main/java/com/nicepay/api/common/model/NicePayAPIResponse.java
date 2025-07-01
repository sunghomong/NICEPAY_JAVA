package com.nicepay.api.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nicepay.api.common.exception.NicePayCode;
import com.nicepay.api.nicepay.model.request.NicePayHeader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * NicePayAPIResponse
 * Desc : 나이스페이 측 응답 객체
 *
 * @author : Sung Ho Cho
 * @version : 1.0
 * Date : 2025-05-19
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class NicePayAPIResponse<T> {

    @JsonProperty("header")
    private NicePayHeader header;

    @JsonProperty("body")
    private T body;

    public NicePayAPIResponse(String resultCode, String resultMessage, T body) {
        this.header.setResCode(resultCode);
        this.header.setResMsg(resultMessage);
        this.body = body;
    }

    public NicePayAPIResponse(NicePayCode code, T body) {
        this.header.setResCode(code.getCode());
        this.header.setResMsg(code.getMessage());
        this.body = body;
    }
}
