package com.bootcamp.desafio.seuimovelapi.modules.properties.dtos;

import java.math.BigDecimal;

public class TotalValueDTO {

    private Long id;
    private String prop_name;
    private BigDecimal totalValue;

    public TotalValueDTO() {
    }

    public TotalValueDTO(Long id, String prop_name, BigDecimal totalValue) {
        this.id = id;
        this.prop_name = prop_name;
        this.totalValue = totalValue;
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

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }
}
