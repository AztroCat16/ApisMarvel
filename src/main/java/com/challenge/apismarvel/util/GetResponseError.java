package com.challenge.apismarvel.util;

import com.challenge.apismarvel.dto.ResponseErrorDTO;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

public class GetResponseError {

    private GetResponseError() { throw new IllegalStateException("Utility class"); }

    public static ResponseErrorDTO getResponseError(HttpClientErrorException ex) {
        final String responseString = ex.getResponseBodyAsString();
        return parseMetaFromResponse(responseString);
    }

    private static ResponseErrorDTO parseMetaFromResponse(String responseString) {
        try {
            JSONObject responseJSON = new JSONObject(responseString);

            ResponseErrorDTO errorDTO = new ResponseErrorDTO();

            if (responseJSON.has("code")) {
                Object code = responseJSON.get("code");
                if (code instanceof Integer) {
                    errorDTO.setCode(code);
                } else {
                    errorDTO.setCode(code.toString());
                }
            }

            if (responseJSON.has("status")) {
                errorDTO.setStatus(responseJSON.getString("status"));
            } else if (responseJSON.has("message")) {
                errorDTO.setStatus(responseJSON.getString("message"));
            }

            return errorDTO;
        } catch (JSONException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
