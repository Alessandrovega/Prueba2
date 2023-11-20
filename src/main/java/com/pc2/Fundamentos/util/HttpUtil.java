package com.pc2.Fundamentos.util;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpUtil {

    private final RestTemplate restTemplate;

    public HttpUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> realizarGET(String url) {
        // Realizar la solicitud GET y obtener la respuesta como String
        return restTemplate.getForEntity(url, String.class);
    }
}