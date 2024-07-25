package com.challenge.apismarvel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponseDTO<T> {

    private String type;

    private String action;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String code;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private T data;

    public ApiResponseDTO(String type, String action, T data) {
        this.type = type;
        this.action = action;
        this.data = data;
    }

    public ApiResponseDTO(String type, String action, String code, String message) {
        this.type = type;
        this.action = action;
        this.code = code;
        this.message = message;
    }

}
