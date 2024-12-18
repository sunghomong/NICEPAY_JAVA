package com.nicepay.api.common.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicepay.api.common.exception.NicePayCode;
import com.nicepay.api.common.exception.NicePayException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * Connection
 * Desc : comment
 *
 * @author : Sung Ho Cho
 * @version : 1.0
 * Date : 2024-12-18
 */
@UtilityClass
@Slf4j
public class Connection {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 전송하는 유틸리티 메서드
     *
     * @param url 요청 URL
     * @param request 요청 데이터
     * @param headers 요청 헤더
     * @param responseType 응답 타입
     * @param <T> 응답 타입
     * @return 응답 데이터
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static <T> T sendRequest(String url, MultiValueMap<String, String> request, HttpHeaders headers, Class<T> responseType) throws NicePayException {
        try {
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(request, headers);
            RestTemplate restTemplate = new RestTemplate();

            log.info("nice pay 호출 url : {}", url);
            log.info("nice pay 호출 data : {}", requestEntity);

            // API 호출
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            String responseBody = responseEntity.getBody();
            log.info("nice pay 응답 값 : {}", responseBody);

            // 응답 데이터 파싱
            return objectMapper.readValue(responseBody, responseType);
        } catch (Exception e) {
            log.error("요청 중 에러 발생: ", e);
            throw new NicePayException(NicePayCode.SERVER_ERROR);
        }
    }

}
