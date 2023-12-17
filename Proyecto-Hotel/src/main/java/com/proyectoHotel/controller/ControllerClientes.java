package com.proyectoHotel.controller;

import com.proyectoHotel.controller.dto.ClientesDTO;
import com.proyectoHotel.model.Clientes;
import com.proyectoHotel.services.ClientesService;
import com.proyectoHotel.services.ClientesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel/clientes")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
public class ControllerClientes {
    @Autowired
    ClientesService clientesService;

    // FIND CLIENT BY ID
    @GetMapping("buscar/{id}")
    public ResponseEntity<?> findClientById(@PathVariable Long id) {

        Optional<Clientes> optionalClientes = clientesService.findById(id);
        if (optionalClientes.isPresent()) {
            Clientes cliente = optionalClientes.get();

            ClientesDTO clientesDTO = ClientesDTO.builder()
                    .id(cliente.getId())
                    .nombre(cliente.getNombre())
                    .apellido(cliente.getApellido())
                    .dni(cliente.getDni())
                    .telefono(cliente.getTelefono())
                    .habitaciones(cliente.getHabitaciones())
                    .build();
            return ResponseEntity.ok(clientesDTO);
        }
        return ResponseEntity.notFound().build();
    }


    //List all clients
    @GetMapping("listar")
    public ResponseEntity<?> listAllClients() {
        List<Clientes> clientesList = clientesService.findAll();
        List<ClientesDTO> clientesDTOList = new ArrayList<>();
        for (Clientes cliente : clientesList) {
            ClientesDTO clientesDTO = ClientesDTO.builder()
                    .id(cliente.getId())
                    .nombre(cliente.getNombre())
                    .apellido(cliente.getApellido())
                    .dni(cliente.getDni())
                    .telefono(cliente.getTelefono())
                    .habitaciones(cliente.getHabitaciones())
                    .build();
            clientesDTOList.add(clientesDTO);
        }

        return ResponseEntity.ok(clientesDTOList);
    }

    //LIST CLIENTS BY SURNAME
    @GetMapping("listarporapellido/{surname}")
    public ResponseEntity<?> listBySurname(@PathVariable String surname) {
        if (surname != null) {
            List<Clientes> clientesList = clientesService.findBySurname(surname);
            if (!clientesList.isEmpty()) {
                return ResponseEntity.ok(clientesList);
            } else return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }

    //FIND CLIENTE BY DNI
    @GetMapping("buscarpordni/{dni}")
    public ResponseEntity<?> findClienteByDdni(@PathVariable int dni) {
        return ResponseEntity.ok(clientesService.findByDni(dni));
    }


    // SAVE CLIENT
    @PostMapping("guardar")
    public ResponseEntity<?> saveClient(@RequestBody ClientesDTO clientesDTO) throws URISyntaxException {
        String nombre = clientesDTO.getNombre();
        if (nombre != null) {
            Clientes cliente = Clientes.builder()
                    .dni(clientesDTO.getDni())
                    .nombre(clientesDTO.getNombre())
                    .apellido(clientesDTO.getApellido())
                    .telefono(clientesDTO.getTelefono())
                    .build();

            return ResponseEntity.ok(clientesService.save(cliente));
        }
        return ResponseEntity.badRequest().build();
    }

    //UPDATE CLIENTS
    @PostMapping("actualizar/{id}")
    public ResponseEntity<?> updateClient(@RequestBody ClientesDTO clientDTO, @PathVariable Long id) {
        Optional<Clientes> optionalClientes = clientesService.findById(id);
        if (optionalClientes.isPresent()) {
            Clientes cliente = optionalClientes.get();
            cliente.setNombre(clientDTO.getNombre());
            cliente.setApellido(clientDTO.getApellido());
            cliente.setDni(clientDTO.getDni());
            cliente.setTelefono(clientDTO.getTelefono());
            return ResponseEntity.ok(clientesService.save(cliente));
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE CLIENT
    @PostMapping("/borrar/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        Optional<Clientes> oCliente = clientesService.findById(id);
        if (oCliente.isPresent()) {
            return ResponseEntity.ok(clientesService.deleteById(id));
        }
        return ResponseEntity.ok(false);
    }


}
