package com.proyectoHotel.services;

import com.proyectoHotel.model.Customer;
import com.proyectoHotel.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomsService {

    List<Room> findAll();

    Optional<Room> findById(Long id);

    Optional<Room> findByNrRoom(int nrRoom);

    boolean possibleRoomCapacity(int nroRoom, int quantGuests);

    List<Room> listRoomsAvailable(int cantGuess);

    boolean occupiedRoom (int nroRoom);

    Room save(Room h);

    boolean deleteById (Long id);
    Customer returnClientRoom(Long idRoom);

    void roomReset(Room room);

}
