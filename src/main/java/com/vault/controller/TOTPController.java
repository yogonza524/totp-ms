package com.vault.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vault.dto.TOTPGenericResponse;
import com.vault.dto.TOTPRequestPayload;
import com.vault.service.TOTPService;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class TOTPController {

    @Autowired
    private TOTPService totpService;

    @PostMapping(value = "/v1/totp/keys/{client_id}/{key}", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity create(@Valid @RequestBody TOTPRequestPayload payload
        , @PathVariable("client_id") String clientId,
                          @PathVariable("key") String key) throws JsonProcessingException {
        ResponseEntity result = this.totpService.createTOPT(payload, clientId, key);
        System.out.println("Response ----> " + result.getBody());

        return new ResponseEntity(result.getBody(), result.getStatusCode());
    }

    @GetMapping(value = "/v1/totp/keys/{client_id}/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TOTPGenericResponse> getKey(@PathVariable("client_id") String clientId,
                                               @PathVariable("key") String key) {
        ResponseEntity<TOTPGenericResponse> result = this.totpService.getKey(clientId, key);

        return new ResponseEntity(result.getBody(), result.getStatusCode());
    }

    @GetMapping(value = "/v1/totp/code/{client_id}/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity getCode(@PathVariable("client_id") String clientId,
                                                       @PathVariable("key") String key) {
        ResponseEntity result = this.totpService.getCode(clientId, key);

        return new ResponseEntity(result.getBody(), result.getStatusCode());
    }

    @GetMapping(value = "/v1/totp/code/{client_id}/{key}/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TOTPGenericResponse> validateCode(@PathVariable("client_id") String clientId,
                                                       @PathVariable("key") String key,
                                                            @PathVariable("code") String code) {
        ResponseEntity<TOTPGenericResponse> result = this.totpService.validate(clientId, key, code);

        return new ResponseEntity(result.getBody(), result.getStatusCode());
    }

    @RequestMapping(value = "/v1/totp/keys/{client_id}/{key}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity deleteKey(@PathVariable("client_id") String clientId,
                           @PathVariable("key") String key) {
        ResponseEntity result = this.totpService.deleteKey(clientId, key);

        return new ResponseEntity(result.getBody(), result.getStatusCode());
    }
}
