package com.bootcamp.desafio.seuimovelapi.modules.properties.services;

import com.bootcamp.desafio.seuimovelapi.modules.properties.domain.District;
import com.bootcamp.desafio.seuimovelapi.modules.properties.domain.Property;
import com.bootcamp.desafio.seuimovelapi.modules.properties.domain.Room;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.PropertyFormDTO;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.TotalSquareMetersDTO;
import com.bootcamp.desafio.seuimovelapi.modules.properties.repositories.DistrictRepository;
import com.bootcamp.desafio.seuimovelapi.modules.properties.repositories.PropertyRepository;
import com.bootcamp.desafio.seuimovelapi.shared.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final DistrictRepository districtRepository;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository, DistrictRepository districtRepository) {
        this.propertyRepository = propertyRepository;
        this.districtRepository = districtRepository;
    }

    @Override
    public boolean createProperty(PropertyFormDTO formDTO) {
        District district = this.districtRepository.findByName(formDTO.getProp_district());

        if (district == null) throw new NotFoundException("Bairro não encontrado");

        return this.propertyRepository.createProperty(formDTO.convert(district));
    }

    @Override
    public Property findById(Long id) {
        Property property = this.propertyRepository.findById(id);

        if (property == null) throw new NotFoundException("Propriedade não encontrada");

        return property;
    }

    @Override
    public TotalSquareMetersDTO getTotalSquareMeters(Long id) {
        Property property = this.findById(id);

        double totalSquareMeters = property.getRooms().stream().mapToDouble(Room::squareMeters).sum();

        return new TotalSquareMetersDTO(property.getId(), property.getProp_name(), totalSquareMeters);
    }
}
