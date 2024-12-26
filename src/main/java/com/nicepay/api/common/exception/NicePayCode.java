package com.nicepay.api.common.exception;

import lombok.Getter;

/**
 * NicePayCode
 * Desc : comment
 *
 * @author : Sung Ho Cho
 * @version : 1.0
 * Date : 2024-12-18
 */
@Getter
public enum NicePayCode {

    OK("2000","성공(Success)"),
    NET_CANCEL_FAIL("2015","망 취소에 실패하였습니다."),
    BAD_REQUEST("4000","잘못된 요청입니다. 요청 형식을 확인해 주세요. (Bad Request)"),
    INVALID_DATA_ERROR("4001", "유효하지 않은 데이터입니다. (Invalid Data)"),
    DUPLICATE_ERROR("4002", "중복된 값입니다. (Duplicate Data error)"),
    REQUIRED_DATA_NULL("4003", "필수 입력 파라미터가 누락되었습니다. (required data is null)"),
    SERVER_ERROR("5000", "서버 처리중 에러가 발생 했습니다. 관리자에게 문의하세요. (Internal Server Error)"),
    CODE_ERROR("6000", "미등록된 에러코드입니다. 관리자에게 문의하세요. (Code Error)"),
    FORMAT_ERROR("9996", "데이터 포맷 오류"),
    PARSING_ERROR("9997", "파싱 오류"),
    HASHING_ERROR("9998", "해싱 에러");

    private final String code;
    private final String message;

    NicePayCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static NicePayCode fromCode(String code) {

        for (NicePayCode constant : values()) {
            if (constant.code.equals(code)) {
                return constant;
            }
        }

        /* 해당하는 코드가 없을 경우 */
        return NicePayCode.CODE_ERROR;
    }
}
