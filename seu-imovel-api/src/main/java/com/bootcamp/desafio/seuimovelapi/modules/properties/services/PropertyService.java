package com.bootcamp.desafio.seuimovelapi.modules.properties.services;

import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.PropertyFormDTO;

public interface PropertyService {
    void createProperty(PropertyFormDTO formDTO);
}
