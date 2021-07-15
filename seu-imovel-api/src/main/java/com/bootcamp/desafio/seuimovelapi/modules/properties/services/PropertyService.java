package com.bootcamp.desafio.seuimovelapi.modules.properties.services;

import com.bootcamp.desafio.seuimovelapi.modules.properties.domain.Property;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.PropertyFormDTO;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.RoomSquareMeterDTO;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.TotalSquareMetersDTO;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.TotalValueDTO;

public interface PropertyService {
    boolean createProperty(PropertyFormDTO formDTO);
    Property findById(Long id);
    TotalSquareMetersDTO getTotalSquareMeters(Long id);
    TotalValueDTO getTotalValue(Long id);
    RoomSquareMeterDTO getBiggestRoom(Long id);
}
