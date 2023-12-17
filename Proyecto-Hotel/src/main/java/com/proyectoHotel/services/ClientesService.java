package com.proyectoHotel.services;

import com.proyectoHotel.model.Clientes;

import java.util.List;
import java.util.Optional;

public interface ClientesService {

    List<Clientes> findAll();

    Optional<Clientes> findById(Long id);

    List<Clientes> findBySurname(String surname);

    Clientes findByDni(int dni);

    Clientes save(Clientes c);

    boolean deleteById (Long id);


}
