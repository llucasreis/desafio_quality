package com.bootcamp.desafio.seuimovelapi.modules.properties.dtos;

import java.math.BigDecimal;

public class TotalValueDTO {

    private Long id;
    private String prop_name;
    private BigDecimal total_value;

    public TotalValueDTO() {
    }

    public TotalValueDTO(Long id, String prop_name, BigDecimal total_value) {
        this.id = id;
        this.prop_name = prop_name;
        this.total_value = total_value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProp_name() {
        return prop_name;
    }

    public void setProp_name(String prop_name) {
        this.prop_name = prop_name;
    }

    public BigDecimal getTotal_value() {
        return total_value;
    }

    public void setTotal_value(BigDecimal total_value) {
        this.total_value = total_value;
    }
}
