package com.nicepay.api.common.util;

import com.nicepay.api.common.exception.NicePayCode;
import com.nicepay.api.common.exception.NicePayException;
import io.micrometer.common.util.StringUtils;
import lombok.experimental.UtilityClass;

import java.util.List;

/**
 * ValidCheck
 * Desc : comment
 *
 * @author : Sung Ho Cho
 * @version : 1.0
 * Date : 2024-12-18
 */
@UtilityClass
public class ValidCheck {

    public static void validNullCheck(Object... datas) throws NicePayException {
        for(Object data : datas) {
            if(data == null) {
                throw new NicePayException(NicePayCode.REQUIRED_DATA_NULL);
            }

            if(data instanceof String && StringUtils.isBlank((String)data)) {
                throw new NicePayException(NicePayCode.REQUIRED_DATA_NULL);
            }

            if (data instanceof List && ((List<?>) data).isEmpty()) {
                throw new NicePayException(NicePayCode.REQUIRED_DATA_NULL);
            }

            if (data.getClass().isArray() && ((Object[]) data).length == 0) {
                throw new NicePayException(NicePayCode.REQUIRED_DATA_NULL);
            }
        }
    }
}
