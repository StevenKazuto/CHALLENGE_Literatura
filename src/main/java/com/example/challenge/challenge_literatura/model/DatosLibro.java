package com.example.challenge.challenge_literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
                          @JsonAlias("title") String titulo,

                          @JsonAlias("authors") List<DatosAutor> autor,

                          @JsonAlias("languages") List<String> idiomasList,

                          @JsonAlias("download_count") Double numeroDeDescargas) {

}
