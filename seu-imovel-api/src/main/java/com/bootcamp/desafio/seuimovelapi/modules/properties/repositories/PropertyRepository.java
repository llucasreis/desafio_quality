package com.bootcamp.desafio.seuimovelapi.modules.properties.repositories;

import com.bootcamp.desafio.seuimovelapi.modules.properties.domain.Property;

public interface PropertyRepository {
    boolean createProperty(Property property);
    Property findById(Long id);
}
