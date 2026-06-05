package com.projetFe.Event.restcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.beans.PropertyEditorSupport;
import java.io.IOException;

public class JsonEditor<T> extends PropertyEditorSupport {
    private final Class<T> clazz;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonEditor (Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            T obj = objectMapper.readValue(text, clazz);
            setValue(obj);
        } catch (IOException e) {
            throw new IllegalArgumentException("Impossible de lire l'objet JSON", e);
        }
    }
}

