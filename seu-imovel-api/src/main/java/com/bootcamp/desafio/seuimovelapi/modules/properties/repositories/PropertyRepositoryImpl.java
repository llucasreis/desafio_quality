package com.bootcamp.desafio.seuimovelapi.modules.properties.repositories;

import com.bootcamp.desafio.seuimovelapi.modules.properties.domain.Property;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PropertyRepositoryImpl implements PropertyRepository {

    private final List<Property> propertyList;

    public PropertyRepositoryImpl() {
        this.propertyList = new ArrayList<>();
    }

    private Long getNextId() {
        if (this.propertyList.size() == 0) return 1L;

        Property lastProperty = this.propertyList.get(this.propertyList.size() - 1);

        return lastProperty.getId()+1;
    }

    @Override
    public Property createProperty(Property property) {
        property.setId(this.getNextId());

        this.propertyList.add(property);

        return property;
    }

    @Override
    public Property findById(Long id) {
        return this.propertyList.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
}
