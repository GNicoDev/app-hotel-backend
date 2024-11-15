package com.proyectoHotel.controller;

import com.proyectoHotel.controller.dto.CustomerDTO;
import com.proyectoHotel.model.Customer;
import com.proyectoHotel.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel/")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
public class CustomerController {
    @Autowired
    CustomerService customerService;

    // FIND CLIENT BY ID
    @GetMapping("customers/{id}")
    public ResponseEntity<?> findClientById(@PathVariable Long id) {

        Optional<Customer> optionalCustomer = customerService.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer cliente = optionalCustomer.get();

            CustomerDTO customerDTO = CustomerDTO.builder()
                    .id(cliente.getId())
                    .name(cliente.getName())
                    .lastName(cliente.getLastName())
                    .passport(cliente.getPassport())
                    .phone(cliente.getPhone())
                    .rooms(cliente.getRooms())
                    .build();
            return ResponseEntity.ok(customerDTO);
        }
        return ResponseEntity.notFound().build();
    }


    //List all clients
    @GetMapping("customers")
    public ResponseEntity<?> listAllClients() {
        List<Customer> customerList = customerService.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer cliente : customerList) {
            CustomerDTO customerDTO = CustomerDTO.builder()
                    .id(cliente.getId())
                    .name(cliente.getName())
                    .lastName(cliente.getLastName())
                    .passport(cliente.getPassport())
                    .phone(cliente.getPhone())
                    .rooms(cliente.getRooms())
                    .build();
            customerDTOList.add(customerDTO);
        }

        return ResponseEntity.ok(customerDTOList);
    }

    //LIST CLIENTS BY SURNAME
    @GetMapping("customers/lastname/{lastname}")
    public ResponseEntity<?> listBySurname(@PathVariable String lastname) {
        if (lastname != null) {
            List<Customer> customerList = customerService.findByLastName(lastname);
            if (!customerList.isEmpty()) {
                List<CustomerDTO> customerDTOList = new ArrayList<>();
                for (Customer customer : customerList) {
                    CustomerDTO customerDTO = CustomerDTO.builder()
                            .id(customer.getId())
                            .name(customer.getName())
                            .lastName(customer.getLastName())
                            .passport(customer.getPassport())
                            .phone(customer.getPhone())
                            .rooms(customer.getRooms())
                            .build();
                    customerDTOList.add(customerDTO);
                }
                return ResponseEntity.ok(customerDTOList);
            } else return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }

    //FIND CLIENTE BY PASSPORT
    @GetMapping("customers/passport/{passport}")
    public ResponseEntity<?> findCustomerByPasport(@PathVariable String passport) {
        Customer customer = customerService.findByPassport(passport);
        if (customer!=null) {
            CustomerDTO customerDTO = CustomerDTO.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .lastName(customer.getLastName())
                    .passport(customer.getPassport())
                    .phone(customer.getPhone())
                    .rooms(customer.getRooms())
                    .build();
            return ResponseEntity.ok(customerDTO);
        }
        else return ResponseEntity.badRequest().build();
    }


    // SAVE CLIENT
    @PostMapping("customers")
    public ResponseEntity<?> saveClient(@RequestBody CustomerDTO customerDTO) throws URISyntaxException {
        if ((customerDTO.getName() != null) && (customerDTO.getLastName() != null) && (customerDTO.getPhone() != null)) {
            Customer customer = Customer.builder()
                    .passport(customerDTO.getPassport())
                    .name(customerDTO.getName())
                    .lastName(customerDTO.getLastName())
                    .phone(customerDTO.getPhone())
                    .build();
            customerService.save(customer);
            return (ResponseEntity<?>) ResponseEntity.ok(customerDTO);
        }
        return ResponseEntity.badRequest().build();
    }

    //UPDATE CLIENTS
    @PutMapping("customers/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Long id) {
        Optional<Customer> optionalCustomer = customerService.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setName(customerDTO.getName());
            customer.setLastName(customerDTO.getLastName());
            customer.setPassport(customerDTO.getPassport());
            customer.setPhone(customerDTO.getPhone());
            return ResponseEntity.ok(customerService.save(customer));
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE CLIENT
    @DeleteMapping("customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        Optional<Customer> optionalCustomer = customerService.findById(id);
        if (optionalCustomer.isPresent()) {
            return ResponseEntity.ok(customerService.deleteById(id));
        }
        return ResponseEntity.notFound().build();
    }


}
