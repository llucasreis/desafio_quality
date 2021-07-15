package com.bootcamp.desafio.seuimovelapi.modules.properties.services;

import com.bootcamp.desafio.seuimovelapi.modules.properties.domain.District;
import com.bootcamp.desafio.seuimovelapi.modules.properties.domain.Property;
import com.bootcamp.desafio.seuimovelapi.modules.properties.domain.Room;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.PropertyFormDTO;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.RoomSquareMetersDTO;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.TotalSquareMetersDTO;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.TotalValueDTO;
import com.bootcamp.desafio.seuimovelapi.modules.properties.repositories.DistrictRepository;
import com.bootcamp.desafio.seuimovelapi.modules.properties.repositories.PropertyRepository;
import com.bootcamp.desafio.seuimovelapi.shared.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

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

    private double calculateTotalSquareMeters(List<Room> rooms) {
        return rooms.stream().mapToDouble(Room::squareMeters).sum();
    }

    @Override
    public TotalSquareMetersDTO getTotalSquareMeters(Long id) {
        Property property = this.findById(id);

        double totalSquareMeters = this.calculateTotalSquareMeters(property.getRooms());

        return new TotalSquareMetersDTO(property.getId(), property.getProp_name(), totalSquareMeters);
    }

    @Override
    public TotalValueDTO getTotalValue(Long id) {
        Property property = this.findById(id);

        double totalSquareMeters = this.calculateTotalSquareMeters(property.getRooms());
        BigDecimal districtValue = property.getDistrict().getValue_district_m2();

        BigDecimal totalValue = districtValue.multiply(BigDecimal.valueOf(totalSquareMeters));

        return new TotalValueDTO(property.getId(), property.getProp_name(), totalValue);
    }

    @Override
    public RoomSquareMetersDTO getBiggestRoom(Long id) {
        Property property = this.findById(id);

        Room biggestRoom = property.getRooms().stream().max(Comparator.comparingDouble(Room::squareMeters)).get();

        return RoomSquareMetersDTO.convert(biggestRoom);
    }

    @Override
    public List<RoomSquareMetersDTO> getRoomsSquareMeters(Long id) {
        Property property = this.findById(id);

        return RoomSquareMetersDTO.convert(property.getRooms());
    }
}
