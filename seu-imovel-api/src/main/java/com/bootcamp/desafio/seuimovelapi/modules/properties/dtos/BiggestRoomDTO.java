package com.bootcamp.desafio.seuimovelapi.modules.properties.dtos;

import com.bootcamp.desafio.seuimovelapi.modules.properties.domain.Room;

public class BiggestRoomDTO {
    private String room_name;
    private double square_meters;

    public BiggestRoomDTO() {
    }

    public BiggestRoomDTO(String room_name, double square_meters) {
        this.room_name = room_name;
        this.square_meters = square_meters;
    }

    public static BiggestRoomDTO convert(Room room) {
        return new BiggestRoomDTO(room.getRoom_name(), room.squareMeters());
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public double getSquare_meters() {
        return square_meters;
    }

    public void setSquare_meters(double square_meters) {
        this.square_meters = square_meters;
    }
}
