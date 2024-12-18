package com.nicepay.api.common.exception;

import com.nicepay.api.common.model.NicePayResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * NicePayExceptionHandler
 * Desc : comment
 *
 * @author : Sung Ho Cho
 * @version : 1.0
 * Date : 2024-12-18
 */

@RestControllerAdvice
public class NicePayExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(NicePayExceptionHandler.class);

    /** Exception : 500 */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<NicePayResponse<?>> handleException(Exception ex) {
        logger.error("[ NicePayExceptionHandler Error ] : ", ex);
        return new ResponseEntity<>(
                new NicePayResponse<>(NicePayCode.SERVER_ERROR.getCode(), NicePayCode.SERVER_ERROR.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
