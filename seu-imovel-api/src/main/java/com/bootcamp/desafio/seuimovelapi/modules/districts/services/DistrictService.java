package com.bootcamp.desafio.seuimovelapi.modules.districts.services;

import com.bootcamp.desafio.seuimovelapi.modules.districts.domain.District;

public interface DistrictService {
    District findById(Long id);
}
