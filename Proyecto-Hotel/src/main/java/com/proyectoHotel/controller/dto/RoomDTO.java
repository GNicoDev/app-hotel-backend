package com.proyectoHotel.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
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

    @Min(value = 0, message = "Guest count must be at least 0")
    @Max(value = 4, message = "Guest count must be at most 4")
    private int guestCount;

    @NotEmpty(message = "Room type cannot be empty")
    private String roomType;

    @Min(value = 1, message = "Room number must be greater than 0")
    private int roomNumber;

    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;

    @Min(value = 0, message = "Price per night must be positive")
    private double pricePerNight;
}