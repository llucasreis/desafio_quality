package com.bootcamp.desafio.seuimovelapi.modules.properties.domain;

import com.bootcamp.desafio.seuimovelapi.modules.districts.domain.District;

import java.util.List;

public class Property {
    private Long id;
    private String prop_name;
    private District district;
    private List<Room> rooms;

    public Property() {
    }

    public Property(String prop_name, District district, List<Room> rooms) {
        this.prop_name = prop_name;
        this.district = district;
        this.rooms = rooms;
    }

    public Property(Long id, String prop_name, District district, List<Room> rooms) {
        this.id = id;
        this.prop_name = prop_name;
        this.district = district;
        this.rooms = rooms;
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

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
