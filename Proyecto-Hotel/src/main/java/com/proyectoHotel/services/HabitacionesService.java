package com.proyectoHotel.services;

import com.proyectoHotel.controller.dto.HabitacionesDTO;
import com.proyectoHotel.model.Clientes;
import com.proyectoHotel.model.Habitaciones;

import java.util.List;
import java.util.Optional;

public interface HabitacionesService {

    List<Habitaciones> findAll();

    Optional<Habitaciones> findById(Long id);

    Optional<Habitaciones> findByNrRoom(int nrRoom);

    boolean possibleRoomCapacity(int nroRoom, int quantGuests);

    List<Habitaciones> listRoomsAvailable(int cantGuess);

    boolean occupiedRoom (int nroRoom);

    Habitaciones save(Habitaciones h);

    boolean deleteById (Long id);
    Clientes returnClientRoom(Long idRoom);

}
