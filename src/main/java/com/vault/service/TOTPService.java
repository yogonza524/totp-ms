package com.vault.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.vault.dto.TOTPGenericResponse;
import com.vault.dto.TOTPRequestPayload;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Optional;

@Service
public class TOTPService {

    @Value("${vault.host}")
    private String host;

    @Value("${vault.token}")
    private String token;

    @Autowired
    private RestTemplate restTemplate;

    private final ObjectMapper mapper = new ObjectMapper();

    public ResponseEntity<TOTPGenericResponse> createTOPT(TOTPRequestPayload payload, String clientId, String key)
            throws JsonProcessingException {
        HttpHeaders headers = getHeaders();

        HttpEntity<String> request = new HttpEntity<>(mapper.writeValueAsString(payload), headers);

        ResponseEntity<TOTPGenericResponse> response = restTemplate.exchange(
                host + "/v1/totp/keys/" + clientId + "-" + key,
                HttpMethod.POST, request, TOTPGenericResponse.class);

        if (response.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
            return this.getCode(clientId, key);
        }
        return response;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "*/*");
        headers.add("User-Agent", "PostmanRuntime/7.20.1");
        headers.add("Connection", "keep-alive");
        headers.add("Content-Type", "application/json");
        headers.add("X-Vault-Token", token);

        return headers;
    }

    public ResponseEntity<TOTPGenericResponse> getCode(String clientId, String key) {
        HttpHeaders headers = getHeaders();

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<TOTPGenericResponse> response = restTemplate.exchange(host + "/v1/totp/code/" + clientId + "-" + key,
                HttpMethod.GET, request, TOTPGenericResponse.class);

        return response;
    }

    public ResponseEntity<TOTPGenericResponse> getKey(String clientId, String key) {
        HttpHeaders headers = getHeaders();

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<TOTPGenericResponse> response = restTemplate.exchange(host + "/v1/totp/keys/" + clientId + "-" + key,
                HttpMethod.GET, request, TOTPGenericResponse.class);

        if (response.getStatusCode().equals(HttpStatus.OK)) {
            ResponseEntity<TOTPGenericResponse> code = this.getCode(clientId, key);

            if (code.getStatusCode().equals(HttpStatus.OK)) {
                response.getBody().getData().put("code", code.getBody().getData().get("code"));
            }
        }

        return response;
    }

    public ResponseEntity validate(String clientId, String key, String code) {
        HttpHeaders headers = getHeaders();

        HttpEntity<String> request = new HttpEntity<>(new Gson().toJson(Collections.singletonMap("code", code)), headers);

        try {
            ResponseEntity<TOTPGenericResponse> response = restTemplate.exchange(
                    host + "/v1/totp/code/" + clientId + "-" + key,
                    HttpMethod.POST, request, TOTPGenericResponse.class);

            return response;
        }
        catch (HttpClientErrorException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(
                    Collections.singletonMap("data",
                            Collections.singletonMap("valid", false)));
        }
    }

    public ResponseEntity deleteKey(String clientId, String key) {
        HttpHeaders headers = getHeaders();

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<TOTPGenericResponse> response = restTemplate.exchange(host + "/v1/totp/keys/" + clientId + "-" + key,
                HttpMethod.DELETE, request, TOTPGenericResponse.class);

        if (response.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
            return ResponseEntity.ok().body(Collections.singletonMap("data", "OTP deleted!"));
        }
        return response;
    }

    private ResponseEntity unirestPost(String clientId, String key, TOTPRequestPayload request) throws JsonProcessingException {
                HttpResponse<JsonNode> r = Unirest.post(host + "/v1/totp/code/" + clientId + "-" + key)
                .header("X-Vault-Token", token)
                .body(mapper.writeValueAsString(request))
                .asJson();

                return new ResponseEntity(r.getBody().toPrettyString(), HttpStatus.valueOf(r.getStatus()));
    }
}
