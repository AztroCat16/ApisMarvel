package com.challenge.apismarvel.services;

import com.challenge.apismarvel.dto.CharactersDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CharactersService {

    List<CharactersDTO> getCharacters();

    CharactersDTO getCharacter(int id);

}
