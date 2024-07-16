package com.example.challenge.challenge_literatura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> Clase) {
        try {
            return mapper.readValue(json.toString(), Clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
