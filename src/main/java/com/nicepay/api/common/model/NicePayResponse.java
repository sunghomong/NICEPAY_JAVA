package com.nicepay.api.common.model;

import com.nicepay.api.common.exception.NicePayCode;
import lombok.Getter;
import lombok.Setter;

/**
 * NicePayResponse
 * Desc : comment
 *
 * @author : Sung Ho Cho
 * @version : 1.0
 * Date : 2024-12-18
 */

@Getter
@Setter
public class NicePayResponse<T> {

    /** 결과 코드 */
    private String resultCode;
    /** 결과 메시지 */
    private String resultMessage;
    /** 데이터 */
    private T data;

    public NicePayResponse(String resultCode, String resultMessage, T data) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.data = data;
    }

    public NicePayResponse(String resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public NicePayResponse(NicePayCode code, T data) {
        this.resultCode = code.getCode();
        this.resultMessage = code.getMessage();
        this.data = data;
    }
}
