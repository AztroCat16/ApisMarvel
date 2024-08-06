package com.challenge.apismarvel.services;

import com.challenge.apismarvel.dto.CharactersDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CharactersService {

    CharactersDTO getCharacter(int id);

    List<CharactersDTO> getCharacters(Integer limit);

}
