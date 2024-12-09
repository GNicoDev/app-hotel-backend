package com.proyectoHotel.mapper;

import com.proyectoHotel.controller.dto.CustomerDTO;
import com.proyectoHotel.model.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomerMapper {

    public CustomerDTO toDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .lastName(customer.getLastName())
                .passport(customer.getPassport())
                .phone(customer.getPhone())
                .rooms(customer.getRooms() != null ? customer.getRooms() : new ArrayList<>())
                .build();
    }

    public Customer fromDTO(CustomerDTO customerDTO) {
        return Customer.builder()
                .id(customerDTO.getId())
                .name(customerDTO.getName())
                .lastName(customerDTO.getLastName())
                .passport(customerDTO.getPassport())
                .phone(customerDTO.getPhone())
                .rooms(customerDTO.getRooms())
                .build();
    }

    // METHOD FOR UPDATING AN EXISTING ENTITY WITH VALUES FROM THE DTO
    public void updateCustomerFromDTO(CustomerDTO customerDTO, Customer customer) {
        customer.setName(customerDTO.getName());
        customer.setLastName(customerDTO.getLastName());
        customer.setPassport(customerDTO.getPassport());
        customer.setPhone(customerDTO.getPhone());
        customer.setRooms(customerDTO.getRooms());
    }
}

