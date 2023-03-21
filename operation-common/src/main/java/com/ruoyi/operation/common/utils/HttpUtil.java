package com.ruoyi.operation.common.utils;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

/**
 * @author xujianhu
 * @date 2023-03-17 14:04
 * @Description: http请求工具类
 */
public class HttpUtil {

    public static String urlHttp(String url, String path, String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> formEntity = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url + path, formEntity, String.class);

        if (responseEntity.getBody() != null) {
            Map bodyMap = JSON.parseObject(responseEntity.getBody(), Map.class);
            return JSON.toJSONString(bodyMap.get("body"));
        }
        return null;
    }
}
