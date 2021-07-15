package com.bootcamp.desafio.seuimovelapi.modules.properties.services;

import com.bootcamp.desafio.seuimovelapi.modules.properties.domain.Property;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.PropertyFormDTO;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.TotalSquareMetersDTO;

public interface PropertyService {
    void createProperty(PropertyFormDTO formDTO);
    Property findById(Long id);
    TotalSquareMetersDTO getTotalSquareMeters(Long id);
}
