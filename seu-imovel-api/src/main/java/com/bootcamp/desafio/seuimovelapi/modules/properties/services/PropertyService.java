package com.bootcamp.desafio.seuimovelapi.modules.properties.services;

import com.bootcamp.desafio.seuimovelapi.modules.properties.domain.Property;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.PropertyFormDTO;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.RoomSquareMetersDTO;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.TotalSquareMetersDTO;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.TotalValueDTO;

import java.util.List;

public interface PropertyService {
    boolean createProperty(PropertyFormDTO formDTO);
    Property findById(Long id);
    TotalSquareMetersDTO getTotalSquareMeters(Long id);
    TotalValueDTO getTotalValue(Long id);
    RoomSquareMetersDTO getBiggestRoom(Long id);
    List<RoomSquareMetersDTO> getRoomsSquareMeters(Long id);
}
