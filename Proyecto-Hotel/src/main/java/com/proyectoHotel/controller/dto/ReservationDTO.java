package com.proyectoHotel.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDTO {
    private int roomNumber;
    private int guestCount;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
}
