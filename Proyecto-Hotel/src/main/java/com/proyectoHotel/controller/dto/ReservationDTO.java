package com.proyectoHotel.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDTO {
    @NotNull(message = "Room number cannot be null")
    private int roomNumber;

    @Min(value = 1, message = "Guest count must be at least 1")
    private int guestCount;

    @NotNull(message = "Check-in date cannot be null")
    private LocalDateTime checkInDate;

    @NotNull(message = "Check-out date cannot be null")
    private LocalDateTime checkOutDate;
}
