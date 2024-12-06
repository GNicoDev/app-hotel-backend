package com.proyectoHotel.controller;

import com.proyectoHotel.controller.dto.CustomerDTO;
import com.proyectoHotel.controller.dto.RoomDTO;
import com.proyectoHotel.mapper.CustomerMapper;
import com.proyectoHotel.model.Customer;
import com.proyectoHotel.model.Room;
import com.proyectoHotel.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hotel/")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @Autowired

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    //LIST ALL CUSTOMERS
    @GetMapping("customers")
    public ResponseEntity<?> listAllCustomers() {
        List<Customer> customerList = customerService.findAll();
        List<CustomerDTO> customerDTOList = customerList.stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(customerDTOList);
    }

    // FIND CUSTOMER BY ID
    @GetMapping("customers/{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable Long id) {

        Optional<Customer> optionalCustomer = customerService.findById(id);
        if (optionalCustomer.isPresent()) {
            CustomerDTO customerDTO = customerMapper.toDTO(optionalCustomer.get());
            return ResponseEntity.ok(customerDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found with ID: " + id);
    }



    //LIST CUSTOMERS BY SURNAME
    @GetMapping("customers/lastname/{lastname}")
    public ResponseEntity<?> listBySurname(@PathVariable String lastname) {
        if (lastname == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Last name cannot be null");
        }
        List<Customer> customerList = customerService.findByLastName(lastname);
        if (!customerList.isEmpty()) {
            List<CustomerDTO> customerDTOList = customerList.stream()
                    .map(customerMapper::toDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(customerDTOList);
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No customers found with the given surname");

    }

    //FIND CUSTOMER BY PASSPORT
    @GetMapping("customers/passport/{passport}")
    public ResponseEntity<?> findCustomerByPasport(@PathVariable String passport) {
        Customer customer = customerService.findByPassport(passport);
        if (customer != null) {
            CustomerDTO customerDTO = customerMapper.toDTO(customer);
            return ResponseEntity.ok(customerDTO);
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No customer found with the given passport");
    }


    // SAVE CUSTOMER
    @PostMapping("customers")
    public ResponseEntity<?> saveClient(@Valid @RequestBody CustomerDTO customerDTO) throws URISyntaxException {
        if ((customerDTO.getName() != null) && (customerDTO.getLastName() != null) && (customerDTO.getPhone() != null)) {
            Customer newCustomer = customerService.save(customerMapper.fromDTO(customerDTO));
            CustomerDTO customerDTOCreated = customerMapper.toDTO(newCustomer);

            return ResponseEntity.created(new URI("/hotel/customers")).body(customerDTOCreated);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid customer data");
    }

    //UPDATE CUSTOMER
    @PutMapping("customers/{id}")
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO, @PathVariable Long id) {
        Optional<Customer> optionalCustomer = customerService.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customerMapper.updateRoomFromDTO(customerDTO,customer);

            Customer updatedCustomer = customerService.save(customer);

            CustomerDTO updatedCustomerDTO = customerMapper.toDTO(updatedCustomer);
            return ResponseEntity.ok(updatedCustomerDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found with ID: " + id);
    }

    // DELETE CUSTOMER
    @DeleteMapping("customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        Optional<Customer> optionalCustomer = customerService.findById(id);
        if (optionalCustomer.isPresent()) {
            return ResponseEntity.ok(customerService.deleteById(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found with ID: " + id);
    }

}
