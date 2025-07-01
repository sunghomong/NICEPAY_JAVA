package com.nicepay.api.common.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicepay.api.common.exception.NicePayCode;
import com.nicepay.api.common.exception.NicePayException;
import com.nicepay.api.common.model.NicePayAPIResponse;
import com.nicepay.api.nicepay.model.request.NicePayHeader;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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

    /**
     * 전송하는 유틸리티 메서드
     *
     * @param url 요청 URL
     * @param request 요청 객체
     * @param <T> 응답 타입
     * @return NicePayAPIResponse<T>
     */
    public  <M, T> NicePayAPIResponse<T> sendJSONRequest(String url, M request,  Class<T> clazz) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            /* null 일경우 필드 x */
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            String jsonRequest = mapper.writeValueAsString(request);
            HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);

            log.info("[nice pay] request url: {}", url);
            log.info("[nice pay] request entity: {}", entity);

            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            log.info("[nice pay] responseEntity: {}", responseEntity);
            String responseBody = responseEntity.getBody();

            JsonNode root = mapper.readTree(responseBody);

            NicePayAPIResponse<T> response = new NicePayAPIResponse<>();
            response.setHeader(mapper.treeToValue(root.get("header"), NicePayHeader.class));
            response.setBody(mapper.treeToValue(root.get("body"), clazz));

            log.debug("[nice pay] read value response: {}", response);

            return response;
        } catch (Exception e) {
            log.error("[nice pay] api 요청 실패 : ", e);
            return new NicePayAPIResponse<>(NicePayCode.SERVER_ERROR, null);
        }
    }

}
