package com.bootcamp.desafio.seuimovelapi.modules.properties.dtos;

import com.bootcamp.desafio.seuimovelapi.modules.properties.domain.District;
import com.bootcamp.desafio.seuimovelapi.modules.properties.domain.Property;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

public class PropertyFormDTO {

    @NotBlank(message = "O nome da propriedade não pode estar vazio.")
    @Pattern(regexp = "^[A-Z][A-Za-z0-9_ ]*$", message = "O nome da propriedade deve começar com uma letra maiúscula.")
    @Size(max = 30, message = "O comprimento do nome não pode exceder 30 caracteres.")
    private String prop_name;

    @NotBlank(message = "O bairro não pode estar vazio.")
    @Size(max = 45, message = "O comprimento o bairro não pode exceder 45 caracteres.")
    private String prop_district;

//    @NotNull(message = "O valor do metro quadrado no bairro não pode estar vazio.")
//    @DecimalMin(value = "0.0", inclusive = false, message = "O valor do metro quadrado no bairro precisa ser maior que zero.")
//    @Digits(integer = 13, fraction = 2, message = "O valor do metro quadrado precisa ser até 13 dígitos.")
//    private BigDecimal value_district_m2;

    @Valid
    @Size(min = 1, message = "Uma propriedade precisa ter pelo menos um cômodo.")
    private List<RoomFormDTO> rooms;

    public PropertyFormDTO() {
    }

    public PropertyFormDTO(String prop_name, String prop_district, BigDecimal value_district_m2, List<RoomFormDTO> rooms) {
        this.prop_name = prop_name;
        this.prop_district = prop_district;
//        this.value_district_m2 = value_district_m2;
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

    public String getProp_district() {
        return prop_district;
    }

    public void setProp_district(String prop_district) {
        this.prop_district = prop_district;
    }

//    public BigDecimal getValue_district_m2() {
//        return value_district_m2;
//    }
//
//    public void setValue_district_m2(BigDecimal value_district_m2) {
//        this.value_district_m2 = value_district_m2;
//    }

    public List<RoomFormDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomFormDTO> rooms) {
        this.rooms = rooms;
    }
}
