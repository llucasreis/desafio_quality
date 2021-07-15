package com.bootcamp.desafio.seuimovelapi.modules.properties.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bootcamp.desafio.seuimovelapi.modules.districts.domain.District;
import com.bootcamp.desafio.seuimovelapi.modules.districts.services.DistrictService;
import com.bootcamp.desafio.seuimovelapi.modules.districts.services.DistrictServiceImpl;
import com.bootcamp.desafio.seuimovelapi.modules.properties.domain.Property;
import com.bootcamp.desafio.seuimovelapi.modules.properties.domain.Room;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.*;
import com.bootcamp.desafio.seuimovelapi.modules.districts.repositories.DistrictRepositoryImpl;
import com.bootcamp.desafio.seuimovelapi.modules.properties.repositories.PropertyRepository;
import com.bootcamp.desafio.seuimovelapi.modules.properties.repositories.PropertyRepositoryImpl;
import com.bootcamp.desafio.seuimovelapi.shared.exceptions.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

public class PropertyServiceImplTest {

    private PropertyService propertyService;
    private PropertyRepository propertyRepository;
    private DistrictService districtService;

    @BeforeEach
    public void init() {
        propertyRepository = Mockito.mock(PropertyRepositoryImpl.class);
        districtService = Mockito.mock(DistrictServiceImpl.class);

        propertyService = new PropertyServiceImpl(propertyRepository, districtService);
    }

    @Test
    public void shouldBeAbleToCreateAProperty() {
        District mockDistrict = new District(1L, "Japiim 2", BigDecimal.valueOf(2933.5));

        Mockito.when(districtService.findById(ArgumentMatchers.any(Long.class))).thenReturn(mockDistrict);
        Mockito.when(propertyRepository.createProperty(ArgumentMatchers.any(Property.class))).thenReturn(true);

        PropertyFormDTO formDTO = new PropertyFormDTO("Casa1", 1L, List.of(
                new RoomFormDTO("Quarto", 25.0, 25.0)
        ));
        boolean response = this.propertyService.createProperty(formDTO);

        Assertions.assertThat(response).isEqualTo(true);
    }

    @Test
    public void shouldBeAbleToFindPropertyById() {
        Property mockProperty = new Property(
                1L, "Casa1",
                new District(1L,"Japiim 2", BigDecimal.valueOf(1200.2)),
                List.of(new Room("Quarto", 25.0, 25.0)));

        Mockito.when(propertyRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(mockProperty);

        Property response = this.propertyService.findById(1L);

        Assertions.assertThat(response.getProp_name()).isEqualTo("Casa1");
    }

    @Test
    public void shouldNotBeAbleToFindPropertyIfDoesntExist() {
        String expected = "Propriedade não encontrada";
        Exception exception = assertThrows(NotFoundException.class, () -> this.propertyService.findById(1L));

        Assertions.assertThat(exception.getMessage()).isEqualTo(expected);
    }

    @Test
    public void shouldBeAbleToCalculateTotalSquareMeters() {
        double expected = 625.0;

        Property mockProperty = new Property(
                1L, "Casa1",
                new District(1L,"Japiim 2", BigDecimal.valueOf(1200.2)),
                List.of(new Room("Quarto", 25.0, 25.0)));

        Mockito.when(propertyRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(mockProperty);

        TotalSquareMetersDTO response = this.propertyService.getTotalSquareMeters(1L);

        Assertions.assertThat(response.getTotal_square_meters()).isEqualTo(expected);
    }

    @Test
    public void shouldBeAbleToCalculateTotalValue() {
        BigDecimal expected = BigDecimal.valueOf(1200.2).multiply(BigDecimal.valueOf(625.0));

        Property mockProperty = new Property(
                1L, "Casa1",
                new District(1L,"Japiim 2", BigDecimal.valueOf(1200.2)),
                List.of(new Room("Quarto", 25.0, 25.0)));

        Mockito.when(propertyRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(mockProperty);

        TotalValueDTO response = this.propertyService.getTotalValue(1L);

        Assertions.assertThat(response.getTotal_value()).isEqualTo(expected);
    }

    @Test
    public void shouldBeAbleToGetBiggestRoom() {
        Property mockProperty = new Property(
                1L, "Casa1",
                new District(1L,"Japiim 2", BigDecimal.valueOf(1200.2)),
                List.of(
                        new Room("Quarto", 25.0, 25.0),
                        new Room("Banheiro", 15.0, 15.0)));

        Mockito.when(propertyRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(mockProperty);

        RoomSquareMetersDTO response = this.propertyService.getBiggestRoom(1L);

        Assertions.assertThat(response.getRoom_name()).isEqualTo("Quarto");
        Assertions.assertThat(response.getSquare_meters()).isEqualTo(625.0);
    }

    @Test
    public void shouldBeAbleToGetRoomsSquareMeters() {
        Property mockProperty = new Property(
                1L, "Casa1",
                new District(1L,"Japiim 2", BigDecimal.valueOf(1200.2)),
                List.of(
                        new Room("Quarto", 25.0, 25.0),
                        new Room("Banheiro", 15.0, 15.0)));

        Mockito.when(propertyRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(mockProperty);

        List<RoomSquareMetersDTO> response = this.propertyService.getRoomsSquareMeters(1L);

        Assertions.assertThat(response).hasSize(2);
        Assertions.assertThat(response.get(0)).usingRecursiveComparison().isEqualTo(
                new RoomSquareMetersDTO("Quarto", 625.0));
        Assertions.assertThat(response.get(1)).usingRecursiveComparison().isEqualTo(
                new RoomSquareMetersDTO("Banheiro", 225.0));
    }
}
