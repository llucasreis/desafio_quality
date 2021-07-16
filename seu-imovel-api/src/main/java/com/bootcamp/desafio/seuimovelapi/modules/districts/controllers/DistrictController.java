package com.bootcamp.desafio.seuimovelapi.modules.districts.controllers;

import com.bootcamp.desafio.seuimovelapi.modules.districts.domain.District;
import com.bootcamp.desafio.seuimovelapi.modules.districts.dtos.DistrictFormDTO;
import com.bootcamp.desafio.seuimovelapi.modules.districts.services.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/district")
public class DistrictController {

    private final DistrictService districtService;

    @Autowired
    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @PostMapping
    public ResponseEntity<?> createDistrict(@Valid @RequestBody DistrictFormDTO formDTO) {
        this.districtService.createDistrict(formDTO);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<District> findAll() {
        return this.districtService.findAll();
    }
}
