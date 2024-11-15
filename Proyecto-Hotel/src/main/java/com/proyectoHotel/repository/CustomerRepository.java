package com.proyectoHotel.repository;

import com.proyectoHotel.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository <Customer, Long>{

    @Query(value = "SELECT p FROM Customer p WHERE p.lastName LIKE %:lastName%") //% % lo que se escribe entre % tiene que ser de tipo string, entero va sin %%
    List<Customer> customerByLastName (@Param("lastName") String surname);

    @Query(value = "SELECT p FROM Customer p WHERE p.passport LIKE ?1")
    Customer findCustomerByPassport (String passport );
}
