package com.bootcamp.desafio.seuimovelapi.modules.districts.services;

import com.bootcamp.desafio.seuimovelapi.modules.districts.domain.District;
import com.bootcamp.desafio.seuimovelapi.modules.districts.dtos.DistrictFormDTO;

import java.util.List;

public interface DistrictService {
    boolean createDistrict(DistrictFormDTO formDTO);
    District findById(Long id);
    List<District> findAll();
}
