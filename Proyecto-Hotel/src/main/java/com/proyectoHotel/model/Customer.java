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
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column(unique = true)
    private String passport;

    @Column
    private String phone;
                              //orphanRemoval sirve para indicarle a la base de datos que
                              //si yo elimino un cliente, automaticamente se eliminan las habitaciones
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   // @JsonIgnore
    private List<Room> rooms = new ArrayList<>();

}
