package com.bootcamp.desafio.seuimovelapi.modules.districts.dtos;

import com.bootcamp.desafio.seuimovelapi.modules.districts.domain.District;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class DistrictFormDTO {

    @NotBlank(message = "O bairro não pode estar vazio.")
    @Size(max = 45, message = "O comprimento do bairro não pode exceder 45 caracteres.")
    private String prop_district;

    @NotNull(message = "O valor do metro quadrado não pode estar vazio.")
    @DecimalMin(value = "0.0", inclusive = false, message = "O valor do metro quadrado no bairro precisa ser maior que zero.")
    @Digits(integer = 13, fraction = 2, message = "O valor do metro quadrado não pode exceder 13 dígitos.")
    private BigDecimal value_district_m2;

    public DistrictFormDTO() {
    }

    public DistrictFormDTO(String prop_district, BigDecimal value_district_m2) {
        this.prop_district = prop_district;
        this.value_district_m2 = value_district_m2;
    }

    public District convert() {
        return new District(this.prop_district, this.value_district_m2);
    }

    public String getProp_district() {
        return prop_district;
    }

    public void setProp_district(String prop_district) {
        this.prop_district = prop_district;
    }

    public BigDecimal getValue_district_m2() {
        return value_district_m2;
    }

    public void setValue_district_m2(BigDecimal value_district_m2) {
        this.value_district_m2 = value_district_m2;
    }
}
