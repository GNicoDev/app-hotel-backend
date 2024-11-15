package com.proyectoHotel.services;

import com.proyectoHotel.model.Customer;
import com.proyectoHotel.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository clienteRepository) {

        this.customerRepository = clienteRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> findByLastName(String surname) {
        List<Customer> customers = customerRepository.findAll().stream()
                .filter(customer -> Objects.equals(customer.getLastName(), surname)).collect(Collectors.toList());
        return customers;
    }

    @Override
    public Customer findByPassport(String passport) {
        Optional<Customer> oCustomer = customerRepository.findAll().stream()
                .filter(customerPassport -> Objects.equals(customerPassport.getPassport(), passport)).findFirst();
        if (oCustomer.isPresent())
            return oCustomer.get();
        return null;
    }

    @Override
    public Customer save(Customer c) {

        return customerRepository.save(c);
    }

    @Override
    public boolean deleteById(Long id) {
        customerRepository.deleteById(id);
        return true;
    }
}
