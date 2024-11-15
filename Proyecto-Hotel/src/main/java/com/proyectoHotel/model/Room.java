package com.proyectoHotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int guestCount;

    @Column(length = 20)
    private String roomType;
    //simple , doble, triple, cuadruple, compartida + de 6

    @Column(unique = true)
    private int roomNumber;

    @Column
    private LocalDateTime checkInDate;

    @Column
    private LocalDateTime checkOutDate;

    @Column
    private double pricePerNight;

    @ManyToOne
    @JsonIgnore
    private Customer customer;

}
