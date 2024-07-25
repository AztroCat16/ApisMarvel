package com.challenge.apismarvel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseCharactersDTO {

    private int items;

    private List<CharactersDTO> characters;

}
