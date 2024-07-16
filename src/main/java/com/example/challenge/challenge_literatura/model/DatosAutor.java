package com.example.challenge.challenge_literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
                        @JsonAlias("name") String nombre,
                        @JsonAlias("birth_year") Long fechaDenacimiento,
                        @JsonAlias("death_year") Long fechaDeMuerte ) {
}
