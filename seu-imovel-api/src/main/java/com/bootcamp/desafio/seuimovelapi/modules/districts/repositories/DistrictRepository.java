package com.bootcamp.desafio.seuimovelapi.modules.districts.repositories;

import com.bootcamp.desafio.seuimovelapi.modules.districts.domain.District;

import java.util.List;

public interface DistrictRepository {
    District createDistrict(District district);
    District findById(Long id);
    District findByName(String name);
    List<District> findAll();
}
