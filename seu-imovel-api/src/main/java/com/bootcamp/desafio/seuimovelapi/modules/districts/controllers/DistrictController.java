package com.bootcamp.desafio.seuimovelapi.modules.districts.controllers;

import com.bootcamp.desafio.seuimovelapi.modules.districts.domain.District;
import com.bootcamp.desafio.seuimovelapi.modules.districts.dtos.DistrictFormDTO;
import com.bootcamp.desafio.seuimovelapi.modules.districts.services.DistrictService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ApiOperation("Criar um bairro")
    public ResponseEntity<District> createDistrict(@Valid @RequestBody DistrictFormDTO formDTO) {
        District newDistrict = this.districtService.createDistrict(formDTO);

        return new ResponseEntity<>(newDistrict, HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation("Retornar todos os bairros")
    public List<District> findAll() {
        return this.districtService.findAll();
    }
}
