package com.bootcamp.desafio.seuimovelapi.unit.districts.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bootcamp.desafio.seuimovelapi.modules.districts.domain.District;
import com.bootcamp.desafio.seuimovelapi.modules.districts.dtos.DistrictFormDTO;
import com.bootcamp.desafio.seuimovelapi.modules.districts.repositories.DistrictRepository;
import com.bootcamp.desafio.seuimovelapi.modules.districts.repositories.DistrictRepositoryImpl;
import com.bootcamp.desafio.seuimovelapi.modules.districts.services.DistrictService;
import com.bootcamp.desafio.seuimovelapi.modules.districts.services.DistrictServiceImpl;
import com.bootcamp.desafio.seuimovelapi.shared.exceptions.ConflictException;
import com.bootcamp.desafio.seuimovelapi.shared.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class DistrictServiceImplTest {

    private DistrictRepository districtRepository;
    private DistrictService districtService;

    @BeforeEach
    public void init() {
        districtRepository = Mockito.mock(DistrictRepositoryImpl.class);

        districtService = new DistrictServiceImpl(districtRepository);
    }

    @Test
    public void shouldBeAbleToCreateADistrict() {
        Mockito.when(districtRepository.findByName(ArgumentMatchers.any(String.class))).thenReturn(null);
        Mockito.when(districtRepository.createDistrict(ArgumentMatchers.any(District.class))).thenReturn(true);

        DistrictFormDTO formDTO = new DistrictFormDTO("Japiim", BigDecimal.valueOf(2910.7));

        boolean response = this.districtService.createDistrict(formDTO);

        Assertions.assertThat(response).isEqualTo(true);
    }

    @Test
    public void shouldNotBeAbleToCreateADistrictIfAlreadyExist() {
        String expected = "Bairro já existe";

        District mockDistrict = new District(1L, "Japiim", BigDecimal.valueOf(2910.7));
        Mockito.when(districtRepository.findByName(ArgumentMatchers.any(String.class))).thenReturn(mockDistrict);

        DistrictFormDTO formDTO = new DistrictFormDTO("Japiim", BigDecimal.valueOf(2910.7));

        Exception exception = assertThrows(ConflictException.class, () -> {
            this.districtService.createDistrict(formDTO);
        });

        Assertions.assertThat(exception.getMessage()).isEqualTo(expected);
    }

    @Test
    public void shouldBeAbleToFindADistrictById() {
        District mockDistrict = new District(1L, "Japiim 2", BigDecimal.valueOf(2933.5));

        Mockito.when(districtRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(mockDistrict);

        District response = this.districtService.findById(1L);

        Assertions.assertThat(response).usingRecursiveComparison().isEqualTo(mockDistrict);
    }

    @Test
    public void shouldNotBeAbleToFindADistrictIfDoesntExist() {
        String expected = "Bairro não encontrado";
        Exception exception = assertThrows(NotFoundException.class, () -> this.districtService.findById(1L));

        Assertions.assertThat(exception.getMessage()).isEqualTo(expected);
    }
}
