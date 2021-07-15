package com.bootcamp.desafio.seuimovelapi.modules.properties.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bootcamp.desafio.seuimovelapi.modules.properties.domain.District;
import com.bootcamp.desafio.seuimovelapi.modules.properties.domain.Property;
import com.bootcamp.desafio.seuimovelapi.modules.properties.domain.Room;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.PropertyFormDTO;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.RoomFormDTO;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.TotalSquareMetersDTO;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.TotalValueDTO;
import com.bootcamp.desafio.seuimovelapi.modules.properties.repositories.DistrictRepository;
import com.bootcamp.desafio.seuimovelapi.modules.properties.repositories.PropertyRepository;
import com.bootcamp.desafio.seuimovelapi.modules.properties.repositories.PropertyRepositoryImpl;
import com.bootcamp.desafio.seuimovelapi.modules.properties.services.PropertyService;
import com.bootcamp.desafio.seuimovelapi.modules.properties.services.PropertyServiceImpl;
import com.bootcamp.desafio.seuimovelapi.shared.exceptions.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.math.BigDecimal;
import java.util.List;

public class PropertyServiceImplTest {

    private DistrictRepository districtRepository;
    private PropertyRepository propertyRepository;
    private PropertyService propertyService;

    @BeforeEach
    public void init() {
        propertyRepository = Mockito.mock(PropertyRepositoryImpl.class);
        districtRepository = Mockito.mock(DistrictRepository.class);

        propertyService = new PropertyServiceImpl(propertyRepository, districtRepository);
    }

    @Test
    public void shouldBeAbleToCreateAProperty() {
        District mockDistrict = new District(1L, "Japiim 2", BigDecimal.valueOf(2933.5));

        Mockito.when(districtRepository.findByName(ArgumentMatchers.any(String.class))).thenReturn(mockDistrict);
        Mockito.when(propertyRepository.createProperty(ArgumentMatchers.any(Property.class))).thenReturn(true);

        PropertyFormDTO formDTO = new PropertyFormDTO("Casa1", "Japiim 2", List.of(
                new RoomFormDTO("Quarto", 25.0, 25.0)
        ));
        boolean response = this.propertyService.createProperty(formDTO);

        Assertions.assertThat(response).isEqualTo(true);
    }

    @Test
    public void shouldNotBeAbleToCreateAPropertyIfDistrictDoesNotExist() {
        String expected = "Bairro não encontrado";
        PropertyFormDTO formDTO = new PropertyFormDTO("", "Japiim 2", List.of(
                new RoomFormDTO("Quarto", 25.0, 25.0)
        ));

        Exception exception = assertThrows(NotFoundException.class, () -> this.propertyService.createProperty(formDTO));

        Assertions.assertThat(exception.getMessage()).isEqualTo(expected);
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

        Assertions.assertThat(response.getTotalSquareMeters()).isEqualTo(expected);
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

        Assertions.assertThat(response.getTotalValue()).isEqualTo(expected);
    }
}
