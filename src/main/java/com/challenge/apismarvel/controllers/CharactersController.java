package com.challenge.apismarvel.controllers;

import com.challenge.apismarvel.config.AppConfig;
import com.challenge.apismarvel.dto.ApiResponseDTO;
import com.challenge.apismarvel.dto.CharactersDTO;
import com.challenge.apismarvel.dto.ResponseCharactersDTO;
import com.challenge.apismarvel.services.CharactersService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.challenge.apismarvel.util.AppMessages.*;

@RestController
@RequestMapping("characters")
public class CharactersController {

    @Autowired
    private CharactersService charactersService;

    @Autowired
    private AppConfig appConfig;

    // Endpoint para usar con limit o sin limit con valor por defecto en 25
    @GetMapping
    public ResponseEntity<ApiResponseDTO<ResponseCharactersDTO>> getCharacters(@RequestParam(value = "limit", required = false, defaultValue = "25") Integer limit )  {
        if (limit < appConfig.getMinLimit() || limit > appConfig.getMaxLimit()) {
            return new ResponseEntity<>(new ApiResponseDTO<>(ERROR_TYPE, CANCEL, INVALID_LIMIT, ERROR_LIMIT), HttpStatus.BAD_REQUEST);
        } else {
            List<CharactersDTO> list = charactersService.getCharacters(limit);

            return new ResponseEntity<>(new ApiResponseDTO<>(SUCCESS, CONTINUE, new ResponseCharactersDTO(list.size(), list)), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<CharactersDTO>> getCharacter(@PathVariable("id") int id)  {
        CharactersDTO character = charactersService.getCharacter(id);

        return new ResponseEntity<>(new ApiResponseDTO<>(SUCCESS, CONTINUE, character), HttpStatus.OK);
    }

}
