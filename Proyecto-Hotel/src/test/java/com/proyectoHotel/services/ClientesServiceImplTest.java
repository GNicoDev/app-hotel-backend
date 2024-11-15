package com.proyectoHotel.services;

import com.proyectoHotel.model.Customer;
import com.proyectoHotel.repository.CustomerRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import  org.junit.Assert;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class CustomerServiceImplTest {
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
        Mockito.when(customerRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Customer(1L,"Nicolas","Alvarez","30050605","2966 489009",null),
                        new Customer(2L,"Jimena","Alvarez","35050789","2494 4858732",null),
                        new Customer(3L,"Agustina","Sazatornil","33789053","2494 647867",null)
                )
        );

        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    void return_customer_by_surname() {


        List<Customer> customers = customerService.findByLastName("Alvarez");

        List<Long> customerIDs = customers.stream().map(customer -> customer.getId()).collect(Collectors.toList());

        Assert.assertThat(customerIDs, CoreMatchers.is(Arrays.asList(1L,2L)));

    }

    @Test
    void return_customer_by_dni() {

        Customer customer = customerService.findByPassport("30050605");

        Long customerId = customer.getId();

        Long customerIdExpected = 1L;

        Assert.assertEquals(customerIdExpected, customerId);

    }

}