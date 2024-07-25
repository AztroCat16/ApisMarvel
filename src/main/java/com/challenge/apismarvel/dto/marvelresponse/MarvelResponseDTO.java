package com.challenge.apismarvel.dto.marvelresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarvelResponseDTO {

    private Long id;

    private String name;

    private String description;

    private ThumbnailResponseDTO thumbnail;

    private ComicsResponseDTO comics;

}