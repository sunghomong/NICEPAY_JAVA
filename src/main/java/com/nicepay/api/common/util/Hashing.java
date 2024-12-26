package com.nicepay.api.common.util;


import com.nicepay.api.common.exception.NicePayCode;
import com.nicepay.api.common.exception.NicePayException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * Hashing
 * Desc : comment
 *
 * @author : Sung Ho Cho
 * @version : 1.0
 * Date : 2024-12-18
 */

@UtilityClass
@Slf4j
public class Hashing {

    public static String hmacSha256(String key, String data) throws NicePayException {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            mac.init(secretKeySpec);
            byte[] hash = mac.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new NicePayException(NicePayCode.HASHING_ERROR);
        }
    }

    public String encrypt(byte[] password) throws NicePayException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");    // SHA-256 해시함수를 사용
            md.update(password);
            password = md.digest();

            return byteToString(password);
        } catch (Exception e) {
            log.warn("해싱 에러", e);
            throw new NicePayException(NicePayCode.HASHING_ERROR);
        }
    }

    String byteToString(byte[] temp) throws NicePayException {
        StringBuilder sb = new StringBuilder();
        for (byte a : temp) {
            sb.append(String.format("%02x", a));
        }
        return sb.toString();
    }

}
