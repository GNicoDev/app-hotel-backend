package com.proyectoHotel.controller;

import com.proyectoHotel.controller.dto.HabitacionesDTO;
import com.proyectoHotel.model.Clientes;
import com.proyectoHotel.model.Habitaciones;
import com.proyectoHotel.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/hotel")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
public class ControllerHotel {
    @Autowired
    HabitacionesService habitacionesService;
    @Autowired
    ClientesService clientesService;

    @PostMapping("reservar/{idCliente}")
    public ResponseEntity<?> checkIn(@RequestBody HabitacionesDTO habDTO, @PathVariable Long idCliente) {
        Optional<Habitaciones> oHabitacion = habitacionesService.findByNrRoom(habDTO.getNroHabitacion());
        Optional<Clientes> oCliente = clientesService.findById(idCliente);
        if ((oHabitacion.isPresent()) && (oCliente.isPresent())) {
            Habitaciones hab = oHabitacion.get();
            if (!habitacionesService.occupiedRoom(habDTO.getNroHabitacion())) {
                if (habitacionesService.possibleRoomCapacity(habDTO.getNroHabitacion(), habDTO.getCantHuespedes())) {
                    Clientes cliente = oCliente.get();
                    hab.setClientes(cliente);
                    hab.setCantHuespedes(habDTO.getCantHuespedes());
                    hab.setFechaDeIngreso(habDTO.getFechaDeIngreso());
                    hab.setFechaDeEgreso(habDTO.getFechaDeEgreso());
                    habitacionesService.save(hab);
                    return ResponseEntity.ok(hab);
                } else return ResponseEntity.badRequest().build();
            } else return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("checkout/{nroHab}")
    public ResponseEntity<?> checkOut(@PathVariable int nroHab) {
        Optional<Habitaciones> oHabitacion = habitacionesService.findByNrRoom(nroHab);
        if (oHabitacion.isPresent()) {
            Habitaciones hab = oHabitacion.get();
            hab.setClientes(null);
            hab.setCantHuespedes(0);
            hab.setFechaDeIngreso(null);
            hab.setFechaDeEgreso(null);
            habitacionesService.save(hab);
            return ResponseEntity.ok(hab);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("mostrarcliente/{idHabitacion}")
    public ResponseEntity<?> returnClienteOfRoom(@PathVariable Long idHabitacion) {
        return ResponseEntity.ok(habitacionesService.returnClientRomm(idHabitacion));
    }
}
