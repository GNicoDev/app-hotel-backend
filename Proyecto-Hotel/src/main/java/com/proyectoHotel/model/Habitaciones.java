package com.proyectoHotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Habitaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int cantHuespedes;

    @Column(length = 20)
    private String tipoHabitacion;
    //simple , doble, triple, cuadruple, compartida + de 6

    @Column(unique = true)
    private int nroHabitacion;

    @Column
    private LocalDateTime fechaDeIngreso;

    @Column
    private LocalDateTime fechaDeEgreso;

    @Column
    private double precio;

    @ManyToOne
    @JsonIgnore
    private Clientes clientes;

}
