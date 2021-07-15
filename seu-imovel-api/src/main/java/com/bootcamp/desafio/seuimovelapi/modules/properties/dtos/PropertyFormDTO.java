package com.bootcamp.desafio.seuimovelapi.modules.properties.dtos;

import com.bootcamp.desafio.seuimovelapi.modules.districts.domain.District;
import com.bootcamp.desafio.seuimovelapi.modules.properties.domain.Property;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

public class PropertyFormDTO {

    @NotBlank(message = "O nome da propriedade não pode estar vazio.")
    @Pattern(regexp = "^[A-Z][A-Za-z0-9_ ]*$", message = "O nome da propriedade deve começar com uma letra maiúscula.")
    @Size(max = 30, message = "O comprimento do nome não pode exceder 30 caracteres.")
    private String prop_name;

    @NotNull(message = "O bairro não pode estar vazio.")
    private Long prop_district_id;

    @Valid
    @Size(min = 1, message = "Uma propriedade precisa ter pelo menos um cômodo.")
    private List<RoomFormDTO> rooms;

    public PropertyFormDTO() {
    }

    public PropertyFormDTO(String prop_name, Long prop_district_id, List<RoomFormDTO> rooms) {
        this.prop_name = prop_name;
        this.prop_district_id = prop_district_id;
        this.rooms = rooms;
    }

    public Property convert(District district) {
        return new Property(this.prop_name, district, RoomFormDTO.convert(this.rooms));
    }

    public String getProp_name() {
        return prop_name;
    }

    public void setProp_name(String prop_name) {
        this.prop_name = prop_name;
    }

    public Long getProp_district_id() {
        return prop_district_id;
    }

    public void setProp_district_id(Long prop_district_id) {
        this.prop_district_id = prop_district_id;
    }

    public List<RoomFormDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomFormDTO> rooms) {
        this.rooms = rooms;
    }
}
