package com.bootcamp.desafio.seuimovelapi.modules.properties.domain;

import java.math.BigDecimal;

public class District {
    private Long id;
    private String prop_district;
    private BigDecimal value_district_m2;

    public District() {
    }

    public District(Long id, String prop_district, BigDecimal value_district_m2) {
        this.id = id;
        this.prop_district = prop_district;
        this.value_district_m2 = value_district_m2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
