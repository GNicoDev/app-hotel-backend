package com.proyectoHotel.controller.dto;

import com.proyectoHotel.model.Clientes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HabitacionesDTO {
    private Long id;

    private int cantHuespedes;

    private String tipoHabitacion;

    private int nroHabitacion;

    private LocalDateTime fechaDeIngreso;

    private LocalDateTime fechaDeEgreso;

    private BigDecimal precio;

    private Clientes clientes;
}
