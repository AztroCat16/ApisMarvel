package com.challenge.apismarvel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CharactersDTO {

    private Long id;

    private String name;

    private String description;

    private String image;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private List<ComicsDTO> comics;

}
