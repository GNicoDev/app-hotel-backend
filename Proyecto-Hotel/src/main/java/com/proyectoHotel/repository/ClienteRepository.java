package com.proyectoHotel.repository;

import com.proyectoHotel.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository <Clientes, Long>{

    @Query(value = "SELECT p FROM Clientes p WHERE p.apellido LIKE %:apellido%") //% % lo que se escribe entre % tiene que ser de tipo string, entero va sin %%
    List<Clientes> findClientBySurname (@Param("apellido") String surname);

    @Query(value = "SELECT p FROM Clientes p WHERE p.dni LIKE ?1")
    Clientes findClienteByDni (int dni );
}
