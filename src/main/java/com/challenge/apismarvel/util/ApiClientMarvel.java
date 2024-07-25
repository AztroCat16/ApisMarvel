package com.challenge.apismarvel.util;

import com.challenge.apismarvel.config.AppConfig;

import com.challenge.apismarvel.dto.marvelresponse.MarvelResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;

import static com.challenge.apismarvel.util.AppMessages.ERROR_MD5_KEYS;

@Data
@Configuration
@PropertySource("classpath:application.yml")
@Component
public class ApiClientMarvel {

    @Autowired
    private AppConfig appConfig;

    private final RestTemplate restTemplate;

    private final HttpHeaders headers;

    private final ObjectMapper objectMapper;

    @Autowired
    public ApiClientMarvel() {
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
        this.headers.setContentType(MediaType.APPLICATION_JSON);
        this.objectMapper = new ObjectMapper();
    }

    private String getCharacters() {

        String ts = String.valueOf(System.currentTimeMillis());
        String apikey = appConfig.getPublicKey();

        String toHash = ts + appConfig.getPrivateKey() + apikey;
        String sb = getMD5(toHash);

        ResponseEntity<String> response = restTemplate.exchange(
                appConfig.getMarvelApi() + "characters?limit=25&ts={ts}&apikey={apikey}&hash={sb}",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                },
                ts,
                apikey,
                sb);

        return response.getBody();
    }

    private String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(str.getBytes());
            byte[] digest = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }

            return String.valueOf(sb);
        } catch (NoSuchAlgorithmException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_MD5_KEYS);
        }
    }

    public List<MarvelResponseDTO> getResponseMarvel() {
        final String response = getCharacters();

        try {
            JSONObject responseJSON = new JSONObject(response);
            if (responseJSON.has("data")) {
                String dataString = responseJSON.getJSONObject("data").get("results").toString();
                return objectMapper.readValue(dataString, new TypeReference<>() {
                });
            }
        } catch (JSONException | JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return Collections.emptyList();
    }

}
