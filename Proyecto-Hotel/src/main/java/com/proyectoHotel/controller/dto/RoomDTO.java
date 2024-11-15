package com.proyectoHotel.controller.dto;

import com.proyectoHotel.model.Customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDTO {
    private Long id;

    private int guestCount;

    private String roomType;

    private int roomNumber;

    private LocalDateTime checkInDate;

    private LocalDateTime checkOutDate;

    private double pricePerNight;

}
