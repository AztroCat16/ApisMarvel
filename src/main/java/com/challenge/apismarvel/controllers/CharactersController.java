package com.challenge.apismarvel.controllers;

import com.challenge.apismarvel.dto.ApiResponseDTO;
import com.challenge.apismarvel.dto.CharactersDTO;
import com.challenge.apismarvel.dto.ResponseCharactersDTO;
import com.challenge.apismarvel.services.CharactersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.challenge.apismarvel.util.AppMessages.SUCCESS;
import static com.challenge.apismarvel.util.AppMessages.CONTINUE;

@RestController
@RequestMapping("characters")
public class CharactersController {

    @Autowired
    private CharactersService charactersService;

    @GetMapping
    public ApiResponseDTO<ResponseCharactersDTO> getCharacters()  {
        List<CharactersDTO> list = charactersService.getCharacters();

        return new ApiResponseDTO<>(SUCCESS, CONTINUE, new ResponseCharactersDTO(list.size(), list));
    }

    

}
