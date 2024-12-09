package com.proyectoHotel.controller;

import com.proyectoHotel.controller.dto.CustomerDTO;
import com.proyectoHotel.controller.dto.ReservationDTO;
import com.proyectoHotel.controller.dto.RoomDTO;
import com.proyectoHotel.mapper.CustomerMapper;
import com.proyectoHotel.mapper.RoomMapper;
import com.proyectoHotel.model.Customer;
import com.proyectoHotel.model.Room;
import com.proyectoHotel.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
public class HotelController {
    private final RoomsService roomsService;
    private final CustomerService customerService;
    private final RoomMapper roomMapper;
    private final CustomerMapper customerMapper;

    @Autowired
    public HotelController(RoomsService roomsService, CustomerService customerService, RoomMapper roomMapper, CustomerMapper customerMapper) {
        this.roomsService = roomsService;
        this.customerService = customerService;
        this.roomMapper = roomMapper;
        this.customerMapper = customerMapper;
    }

    @PostMapping("rooms/{customerId}/reservation")
    public ResponseEntity<?> checkIn(@Valid @RequestBody ReservationDTO reservationDTO, @PathVariable Long customerId) {
        Optional<Room> roomOptional = roomsService.findByNrRoom(reservationDTO.getRoomNumber());
        Optional<Customer> optionalCustomer = customerService.findById(customerId);
        if (roomOptional.isEmpty() || optionalCustomer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room or customer not found");
        }

        Room room = roomOptional.get();
        if (roomsService.occupiedRoom(reservationDTO.getRoomNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("The room is already occupied");
        }

        if (!roomsService.possibleRoomCapacity(reservationDTO.getRoomNumber(), reservationDTO.getGuestCount())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room capacity exceeded");
        }

        Customer customer = optionalCustomer.get();
        roomMapper.updateRoomFromReservationDTO(reservationDTO,room,customer);
        roomsService.save(room);

        //add room to the cusltomer's listroom
        if (customer.getRooms().isEmpty())
            customer.setRooms(new ArrayList<>());
        customer.getRooms().add(room);
        customerService.save(customer);

        RoomDTO roomDTO = roomMapper.toDTO(room);
        return ResponseEntity.ok(roomDTO);
    }

    @PostMapping("rooms/{roomNr}/checkout")
    public ResponseEntity<?> checkOut(@PathVariable int roomNr) {
        Optional<Room> roomOptional = roomsService.findByNrRoom(roomNr);
        if (roomOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room not found");
        }
        Room room = roomOptional.get();

        Customer customer = roomsService.returnClientRoom(room.getId());
        customer.getRooms().remove(room);

        roomsService.roomReset(room);

        RoomDTO roomDTO = roomMapper.toDTO(room);

        return ResponseEntity.ok(roomDTO);
    }

    @GetMapping("rooms/{roomId}/customer")
    public ResponseEntity<?> returnClienteOfRoom(@PathVariable Long roomId) {
        Customer customer = roomsService.returnClientRoom(roomId);
        if (customer != null) {
            CustomerDTO customerDTO = customerMapper.toDTO(customer);
            return ResponseEntity.ok(customerDTO);
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No customer found for the given room ID");    }
}
