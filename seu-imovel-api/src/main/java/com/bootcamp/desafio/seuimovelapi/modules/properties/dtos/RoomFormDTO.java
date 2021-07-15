package com.bootcamp.desafio.seuimovelapi.modules.properties.dtos;

import com.bootcamp.desafio.seuimovelapi.modules.properties.domain.Room;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

public class RoomFormDTO {

    @NotBlank(message = "O campo não pode estar vazio.")
    @Size(min = 5, max = 30, message = "O nome do cômodo não pode exceder 30 caracteres.")
    private String room_name;

    @NotNull(message = "A largura do cômodo não pode estar vazia.")
    @Max(value = 25, message = "A largura máxima permitida por cômodo é de 25 metros.")
    private double room_width;

    @NotNull(message = "O comprimento do cômodo não pode estar vazio.")
    @Max(value = 33, message = "O comprimento máximo permitido por cômodo é de 33 metros.")
    private double room_length;

    public RoomFormDTO() {
    }

    public RoomFormDTO(String room_name, double room_width, double room_length) {
        this.room_name = room_name;
        this.room_width = room_width;
        this.room_length = room_length;
    }

    public Room convert() {
        return new Room(this.room_name, this.room_width, this.room_length);
    }

    public static List<Room> convert(List<RoomFormDTO> rooms) {
        return rooms.stream().map(RoomFormDTO::convert).collect(Collectors.toList());

    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public double getRoom_width() {
        return room_width;
    }

    public void setRoom_width(double room_width) {
        this.room_width = room_width;
    }

    public double getRoom_length() {
        return room_length;
    }

    public void setRoom_length(double room_length) {
        this.room_length = room_length;
    }
}
