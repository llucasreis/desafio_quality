package com.bootcamp.desafio.seuimovelapi.modules.properties.controllers;

import com.bootcamp.desafio.seuimovelapi.modules.properties.domain.Property;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.RoomSquareMetersDTO;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.PropertyFormDTO;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.TotalSquareMetersDTO;
import com.bootcamp.desafio.seuimovelapi.modules.properties.dtos.TotalValueDTO;
import com.bootcamp.desafio.seuimovelapi.modules.properties.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/property")
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    public ResponseEntity<Property> createProperty(@Valid @RequestBody PropertyFormDTO formDTO) {
        Property newProperty = this.propertyService.createProperty(formDTO);

        return new ResponseEntity<>(newProperty, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/totalSquareMeters")
    public ResponseEntity<TotalSquareMetersDTO> getTotalSquareMeters(@PathVariable Long id) {
        TotalSquareMetersDTO totalSquareMetersDTO = this.propertyService.getTotalSquareMeters(id);

        return ResponseEntity.ok(totalSquareMetersDTO);
    }

    @GetMapping("/{id}/totalValue")
    public ResponseEntity<TotalValueDTO> getTotalValue(@PathVariable Long id) {
        TotalValueDTO totalValueDTO = this.propertyService.getTotalValue(id);

        return ResponseEntity.ok(totalValueDTO);
    }

    @GetMapping("/{id}/biggestRoom")
    public ResponseEntity<RoomSquareMetersDTO> getBiggestRoom(@PathVariable Long id) {
        RoomSquareMetersDTO roomSquareMetersDTO = this.propertyService.getBiggestRoom(id);

        return ResponseEntity.ok(roomSquareMetersDTO);
    }

    @GetMapping("/{id}/rooms")
    public ResponseEntity<List<RoomSquareMetersDTO>> getRoomsSquareMeters(@PathVariable Long id) {
        List<RoomSquareMetersDTO> roomSquareMetersDTOS = this.propertyService.getRoomsSquareMeters(id);

        return ResponseEntity.ok(roomSquareMetersDTOS);
    }
}
