package com.challenge.apismarvel.services.impl;

import com.challenge.apismarvel.dto.CharactersDTO;
import com.challenge.apismarvel.dto.ComicsDTO;
import com.challenge.apismarvel.dto.marvelresponse.ItemsResponseDTO;
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
import static com.challenge.apismarvel.util.AppMessages.ERROR_FOUND;

@Service
public class CharactersServiceImpl implements CharactersService {

    @Autowired
    ApiClientMarvel apiClientMarvel;

    @Override
    public List<CharactersDTO> getCharacters() {
        List<CharactersDTO> charactersList = new ArrayList<>();

        List<MarvelResponseDTO> marvelResponse = apiClientMarvel.getCharacters();

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

    @Override
    public CharactersDTO getCharacter(int id) {
        CharactersDTO character = new CharactersDTO();

        List<MarvelResponseDTO> marvelResponse = apiClientMarvel.getCharactersId(id);

        if (!marvelResponse.isEmpty()) {
            MarvelResponseDTO object = marvelResponse.get(0);

            character.setId(object.getId());
            character.setName(object.getName());
            character.setDescription(object.getDescription());
            character.setImage(object.getThumbnail().getPath() + "." + object.getThumbnail().getExtension());

            List<ComicsDTO> comics = new ArrayList<>();
            for (ItemsResponseDTO comic : object.getComics().getItems()) {
                ComicsDTO comicD = new ComicsDTO();
                comicD.setName(comic.getName());
                comics.add(comicD);
            }

            character.setComics(comics);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ERROR_FOUND);
        }

        return character;
    }

    @Override
    public List<CharactersDTO> getCharacters(Integer limit) {
        List<CharactersDTO> charactersList = new ArrayList<>();

        List<MarvelResponseDTO> marvelResponse = apiClientMarvel.getCharacters(limit);

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
