package com.challenge.apismarvel.services.impl;

import com.challenge.apismarvel.dto.CharactersDTO;
import com.challenge.apismarvel.dto.marvelresponse.MarvelResponseDTO;
import com.challenge.apismarvel.services.CharactersService;
import com.challenge.apismarvel.util.ApiClientMarvel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static com.challenge.apismarvel.util.AppMessages.ERROR_DATA;

@Service
public class CharactersServiceImpl implements CharactersService {

    @Autowired
    ApiClientMarvel apiClientMarvel;

    @Override
    public List<CharactersDTO> getCharacters() {
        List<CharactersDTO> charactersList = new ArrayList<>();

        List<MarvelResponseDTO> marvelResponse = apiClientMarvel.getResponseMarvel();

        if (!marvelResponse.isEmpty()) {

            for (MarvelResponseDTO object : marvelResponse) {
                CharactersDTO character = new CharactersDTO();

                character.setId(object.getId());
                character.setName(object.getName());
                character.setDescription(object.getDescription());
                character.setImage(object.getThumbnail().getPath() + "." + object.getThumbnail().getExtension());
                charactersList.add(character);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_DATA);
        }

        return charactersList;
    }
}
