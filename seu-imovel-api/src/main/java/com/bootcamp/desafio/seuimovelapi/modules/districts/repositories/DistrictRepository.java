package com.bootcamp.desafio.seuimovelapi.modules.districts.repositories;

import com.bootcamp.desafio.seuimovelapi.modules.districts.domain.District;

public interface DistrictRepository {
    District findById(Long id);
}
