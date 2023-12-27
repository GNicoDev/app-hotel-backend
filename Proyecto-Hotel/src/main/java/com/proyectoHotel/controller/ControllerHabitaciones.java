package com.proyectoHotel.controller;

import com.proyectoHotel.controller.dto.ClientesDTO;
import com.proyectoHotel.controller.dto.HabitacionesDTO;
import com.proyectoHotel.model.Clientes;
import com.proyectoHotel.model.Habitaciones;
import com.proyectoHotel.services.ClientesServiceImpl;
import com.proyectoHotel.services.HabitacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel/habitaciones")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})

public class ControllerHabitaciones {
    @Autowired
    HabitacionesService habitacionesService;

    // FIND ROOM BY ID
    @GetMapping("buscar/{id}")
    public ResponseEntity<?> findRoomById(@PathVariable Long id) {

        Optional<Habitaciones> optionalHabitaciones = habitacionesService.findById(id);
        if (optionalHabitaciones.isPresent()) {
            Habitaciones habitaciones = optionalHabitaciones.get();

            HabitacionesDTO habitacionesDTO = HabitacionesDTO.builder()
                    .id(habitaciones.getId())
                    .nroHabitacion(habitaciones.getNroHabitacion())
                    .tipoHabitacion(habitaciones.getTipoHabitacion())
                    .cantHuespedes(habitaciones.getCantHuespedes())
                    .precio(habitaciones.getPrecio())
                    .fechaDeIngreso(habitaciones.getFechaDeIngreso())
                    .fechaDeEgreso(habitaciones.getFechaDeEgreso())
                    .clientes(habitaciones.getClientes())
                    .build();
            return ResponseEntity.ok(habitacionesDTO);
        }
        return ResponseEntity.notFound().build();
    }

    //List all rooms
    @GetMapping("listar")
    public ResponseEntity<?> listAllRooms() {
        List<Habitaciones> habitacionesList = habitacionesService.findAll();
        List<HabitacionesDTO> habitacionesDTOList = new ArrayList<>();
        for (Habitaciones habitaciones : habitacionesList) {
            HabitacionesDTO habitacionesDTO = HabitacionesDTO.builder()
                    .id(habitaciones.getId())
                    .nroHabitacion(habitaciones.getNroHabitacion())
                    .tipoHabitacion(habitaciones.getTipoHabitacion())
                    .cantHuespedes(habitaciones.getCantHuespedes())
                    .precio(habitaciones.getPrecio())
                    .fechaDeIngreso(habitaciones.getFechaDeIngreso())
                    .fechaDeEgreso(habitaciones.getFechaDeEgreso())
                    .clientes(habitaciones.getClientes())
                    .build();
            habitacionesDTOList.add(habitacionesDTO);
        }

        return ResponseEntity.ok(habitacionesList);
    }

    //LIST ROOM BY NUMBER
    @GetMapping("buscarhabitacion/{nro}")
    public ResponseEntity listByNrRoom(@PathVariable int nro) {
        Optional<Habitaciones> oHabitacion = habitacionesService.findByNrRoom(nro);
        if (!oHabitacion.isEmpty()) {
            Habitaciones habitacion = oHabitacion.get();
            HabitacionesDTO habitacionesDTO = HabitacionesDTO.builder()
                    .id(habitacion.getId())
                    .nroHabitacion(habitacion.getNroHabitacion())
                    .tipoHabitacion(habitacion.getTipoHabitacion())
                    .cantHuespedes(habitacion.getCantHuespedes())
                    .precio(habitacion.getPrecio())
                    .fechaDeIngreso(habitacion.getFechaDeIngreso())
                    .fechaDeEgreso(habitacion.getFechaDeEgreso())
                    .clientes(habitacion.getClientes())
                    .build();
            return ResponseEntity.ok(habitacionesDTO);
        }
        return ResponseEntity.badRequest().build();
    }

    //LIST AVAILABLE ROOMS ACCORDING GUESS QUANTITY
    @GetMapping("listardisponibles/{cantHuespedes}")
    public ResponseEntity<?> listRoomsAvailable(@PathVariable int cantHuespedes) {
        List<Habitaciones> habitacionesList = habitacionesService.listRoomsAvailable(cantHuespedes);
        if (habitacionesList != null) {
            List<HabitacionesDTO> habitacionesDTOList = new ArrayList<>();
            for (Habitaciones habitaciones : habitacionesList) {
                HabitacionesDTO habitacionesDTO = HabitacionesDTO.builder()
                        .id(habitaciones.getId())
                        .nroHabitacion(habitaciones.getNroHabitacion())
                        .tipoHabitacion(habitaciones.getTipoHabitacion())
                        .cantHuespedes(habitaciones.getCantHuespedes())
                        .precio(habitaciones.getPrecio())
                        .fechaDeIngreso(habitaciones.getFechaDeIngreso())
                        .fechaDeEgreso(habitaciones.getFechaDeEgreso())
                        .clientes(habitaciones.getClientes())
                        .build();
                habitacionesDTOList.add(habitacionesDTO);
            }
            return ResponseEntity.ok(habitacionesDTOList);
        }
        else
            return ResponseEntity.ok("Null");
    }


    // SAVE ROOM
    @PostMapping("guardar")
    public ResponseEntity<?> saveRoom(@RequestBody HabitacionesDTO habitacionDTO) throws URISyntaxException {
        int nroHabitacion = habitacionDTO.getNroHabitacion();
        if (nroHabitacion != 0) {
            habitacionesService.save(Habitaciones.builder()
                    .nroHabitacion(habitacionDTO.getNroHabitacion())
                    .tipoHabitacion(habitacionDTO.getTipoHabitacion())
                    .precio(habitacionDTO.getPrecio())
                    .build());
            return ResponseEntity.created(new URI("/hotel/habitaciones/guardar")).build();
        }
        return ResponseEntity.badRequest().build();
    }

    //UPDATE ROOM
    @PostMapping("actualizar/{id}")
    public ResponseEntity<?> updateRoom(@RequestBody HabitacionesDTO habitDTO, @PathVariable Long id) {
        Optional<Habitaciones> oHabit = habitacionesService.findById(id);
        if (oHabit.isPresent()) {
            Habitaciones habitacion = oHabit.get();
            habitacion.setNroHabitacion(habitDTO.getNroHabitacion());
            habitacion.setTipoHabitacion(habitDTO.getTipoHabitacion());
            habitacion.setPrecio(habitDTO.getPrecio());

            return ResponseEntity.ok(habitacionesService.save(habitacion));
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE ROOM
    @PostMapping("/borrar/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long id) {
        Optional<Habitaciones> oHabit = habitacionesService.findById(id);
        if (oHabit.isPresent()) {
            return ResponseEntity.ok(habitacionesService.deleteById(id));
        }
        return ResponseEntity.ok(false);
    }
}