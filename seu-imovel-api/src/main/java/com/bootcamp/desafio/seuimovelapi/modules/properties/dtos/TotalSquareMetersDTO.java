package com.bootcamp.desafio.seuimovelapi.modules.properties.dtos;

public class TotalSquareMetersDTO {

    private Long id;
    private String prop_name;
    private double totalSquareMeters;

    public TotalSquareMetersDTO() {
    }

    public TotalSquareMetersDTO(Long id, String prop_name, double totalSquareMeters) {
        this.id = id;
        this.prop_name = prop_name;
        this.totalSquareMeters = totalSquareMeters;
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

    public double getTotalSquareMeters() {
        return totalSquareMeters;
    }

    public void setTotalSquareMeters(double totalSquareMeters) {
        this.totalSquareMeters = totalSquareMeters;
    }
}
