package com.proyectoHotel.repository;

import com.proyectoHotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomsRepository extends JpaRepository<Room,Long> {

    @Query(value = "SELECT p FROM Room p WHERE p.roomNumber LIKE ?1")
    Optional<Room> findByNrRoom (int nrRoom);

}
