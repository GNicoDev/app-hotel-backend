package com.proyectoHotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column(unique = true)
    private int dni;

    @Column
    private String telefono;
                              //orphanRemoval sirve para indicarle a la base de datos que
                              //si yo elimino un cliente, automaticamente se eliminan las habitaciones
    @OneToMany(mappedBy = "clientes", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Habitaciones> habitaciones = new ArrayList<>();

}
