package com.proyectoHotel.repository;

import com.proyectoHotel.model.Habitaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HabitacionesRepository extends JpaRepository<Habitaciones,Long> {

    @Query(value = "SELECT p FROM Habitaciones p WHERE p.nroHabitacion LIKE ?1")
    Optional<Habitaciones> findByNrRoom (int nrRoom);

}
