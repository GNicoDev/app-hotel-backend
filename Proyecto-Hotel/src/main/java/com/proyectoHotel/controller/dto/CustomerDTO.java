package com.proyectoHotel.controller.dto;

import com.proyectoHotel.model.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {
    private Long id;

    private String name;

    private String lastName;

    private String passport;

    private String phone;

    private List<Room> rooms = new ArrayList<>();
}
