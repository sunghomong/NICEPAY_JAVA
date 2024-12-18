package com.nicepay.api.common.exception;

import lombok.Getter;

/**
 * NicePayException
 * Desc : comment
 *
 * @author : Sung Ho Cho
 * @version : 1.0
 * Date : 2024-12-18
 */

@Getter
public class NicePayException extends Exception{

    private static final long serialVersionUID = -4864185459566844601L;

    private String code;
    private String message;

    public NicePayException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public NicePayException(NicePayCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }
}
