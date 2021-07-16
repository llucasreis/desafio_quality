package com.bootcamp.desafio.seuimovelapi.modules.districts.services;

import com.bootcamp.desafio.seuimovelapi.modules.districts.domain.District;
import com.bootcamp.desafio.seuimovelapi.modules.districts.dtos.DistrictFormDTO;
import com.bootcamp.desafio.seuimovelapi.modules.districts.repositories.DistrictRepository;
import com.bootcamp.desafio.seuimovelapi.shared.exceptions.ConflictException;
import com.bootcamp.desafio.seuimovelapi.shared.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public District createDistrict(DistrictFormDTO formDTO) {
        District districtAlreadyExist = this.districtRepository.findByName(formDTO.getProp_district());

        if (districtAlreadyExist != null) throw new ConflictException("Bairro já existe");

        return this.districtRepository.createDistrict(formDTO.convert());
    }

    @Override
    public District findById(Long id) {
        District district = this.districtRepository.findById(id);

        if (district == null) throw new NotFoundException("Bairro não encontrado");

        return district;
    }

    @Override
    public List<District> findAll() {
        return this.districtRepository.findAll();
    }
}
