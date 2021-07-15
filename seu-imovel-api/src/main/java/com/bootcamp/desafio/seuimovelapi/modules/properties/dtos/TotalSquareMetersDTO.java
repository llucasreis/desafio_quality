package com.bootcamp.desafio.seuimovelapi.modules.properties.dtos;

public class TotalSquareMetersDTO {

    private Long id;
    private String prop_name;
    private double total_square_meters;

    public TotalSquareMetersDTO() {
    }

    public TotalSquareMetersDTO(Long id, String prop_name, double total_square_meters) {
        this.id = id;
        this.prop_name = prop_name;
        this.total_square_meters = total_square_meters;
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

    public double getTotal_square_meters() {
        return total_square_meters;
    }

    public void setTotal_square_meters(double total_square_meters) {
        this.total_square_meters = total_square_meters;
    }
}
