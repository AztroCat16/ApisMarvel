package com.challenge.apismarvel.exceptions;

import com.challenge.apismarvel.dto.ApiResponseDTO;
import com.challenge.apismarvel.dto.ResponseErrorDTO;
import com.challenge.apismarvel.util.AppMessages;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import static com.challenge.apismarvel.util.GetResponseError.getResponseError;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = { ResponseStatusException.class })
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
        int statusCode = ex.getStatusCode().value();

        ApiResponseDTO<Object> apiResponse = new ApiResponseDTO<>();
        if (statusCode >= 400 && statusCode < 500) {
            apiResponse.setType(AppMessages.ERROR_TYPE);
            apiResponse.setAction(AppMessages.CANCEL);
            apiResponse.setCode(AppMessages.CLIENT_ERROR);
            apiResponse.setMessage(ex.getReason());
        } else if (statusCode >= 500 && statusCode < 600) {
            apiResponse.setType(AppMessages.ERROR_TYPE);
            apiResponse.setAction(AppMessages.CANCEL);
            apiResponse.setCode(AppMessages.ERROR);
            apiResponse.setMessage(ex.getReason());
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<>(apiResponse, httpHeaders, ex.getStatusCode());
    }

    @ExceptionHandler(value = {HttpClientErrorException.class})
    public ResponseEntity<ApiResponseDTO<Object>> handleHttpClientErrorException(HttpClientErrorException ex) {
        ApiResponseDTO<Object> apiResponse = new ApiResponseDTO<>();
        apiResponse.setType(AppMessages.ERROR_TYPE);
        apiResponse.setAction(AppMessages.CANCEL);

        ResponseErrorDTO meta = getResponseError(ex);

        if (meta.getCode() != null && meta.getStatus() != null) {
            if ((int) meta.getCode() == 404) {
                apiResponse.setCode(AppMessages.NOT_FOUND);
            } else if (meta.getCode() instanceof String) {
                apiResponse.setCode(meta.getCode().toString());
            } else {
                apiResponse.setCode((ex.getStatusText()));
            }
            apiResponse.setMessage(meta.getStatus());
            return new ResponseEntity<>(apiResponse, ex.getStatusCode());
        } else {
            apiResponse.setCode(ex.getStatusText());
            apiResponse.setMessage(ex.getMessage());
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new  ResponseEntity<>(apiResponse, httpHeaders, ex.getStatusCode());
    }

}
