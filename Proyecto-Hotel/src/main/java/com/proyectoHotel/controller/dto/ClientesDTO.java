package com.proyectoHotel.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proyectoHotel.model.Habitaciones;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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
public class ClientesDTO {
    private Long id;

    private String nombre;

    private String apellido;

    private int dni;

    private String telefono;

    private List<Habitaciones> habitaciones = new ArrayList<>();
}
