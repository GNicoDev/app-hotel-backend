package com.proyectoHotel.services;

import com.proyectoHotel.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> findAll();

    Optional<Customer> findById(Long id);

    List<Customer> findByLastName(String surname);

    Customer findByPassport(String passport);

    Customer save(Customer c);

    boolean deleteById (Long id);


}
