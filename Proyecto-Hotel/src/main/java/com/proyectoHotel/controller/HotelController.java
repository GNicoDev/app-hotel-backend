package com.proyectoHotel.controller;

import com.proyectoHotel.controller.dto.CustomerDTO;
import com.proyectoHotel.controller.dto.ReservationDTO;
import com.proyectoHotel.controller.dto.RoomDTO;
import com.proyectoHotel.model.Customer;
import com.proyectoHotel.model.Room;
import com.proyectoHotel.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/hotel")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
public class HotelController {
    @Autowired
    RoomsService roomsService;
    @Autowired
    CustomerService customerService;

    @PostMapping("rooms/{customerId}/reservation")
    public ResponseEntity<?> checkIn(@RequestBody ReservationDTO reservationDTO, @PathVariable Long customerId) {
        Optional<Room> roomOptional = roomsService.findByNrRoom(reservationDTO.getRoomNumber());
        Optional<Customer> optionalCustomer = customerService.findById(customerId);
        if (roomOptional.isEmpty() || optionalCustomer.isEmpty()) {
            return ResponseEntity.badRequest().body("Room or customer not found");
        }
        Room room = roomOptional.get();
        if (roomsService.occupiedRoom(reservationDTO.getRoomNumber())) {
            return ResponseEntity.badRequest().body("The room is already occupied");
        } if (!roomsService.possibleRoomCapacity(reservationDTO.getRoomNumber(), reservationDTO.getGuestCount())) {
            return ResponseEntity.badRequest().body("Room capacity exceeded");
        }
        Customer customer = optionalCustomer.get();
        room.setCustomer(customer);
        room.setGuestCount(reservationDTO.getGuestCount());
        room.setCheckInDate(reservationDTO.getCheckInDate());
        room.setCheckOutDate(reservationDTO.getCheckOutDate());
        roomsService.save(room);

        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setRoomNumber(room.getRoomNumber());
        roomDTO.setGuestCount(room.getGuestCount());
        roomDTO.setRoomType(room.getRoomType());
        roomDTO.setPricePerNight(room.getPricePerNight());
        roomDTO.setCheckInDate(room.getCheckInDate());
        roomDTO.setCheckOutDate(room.getCheckOutDate());
        return ResponseEntity.ok(roomDTO);
    }

    @PostMapping("rooms/{roomNr}/checkout")
    public ResponseEntity<?> checkOut(@PathVariable int roomNr) {
        Optional<Room> roomOptional = roomsService.findByNrRoom(roomNr);
        if (roomOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Room not Found");
        }
        Room room = roomOptional.get();
        room.setCustomer(null);
        room.setGuestCount(0);
        room.setCheckInDate(null);
        room.setCheckOutDate(null);
        roomsService.save(room);

        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setRoomNumber(room.getRoomNumber());
        roomDTO.setGuestCount(room.getGuestCount());
        roomDTO.setRoomType(room.getRoomType());
        roomDTO.setPricePerNight(room.getPricePerNight());
        roomDTO.setCheckInDate(room.getCheckInDate());
        roomDTO.setCheckOutDate(room.getCheckOutDate());

        return ResponseEntity.ok(roomDTO);
    }

    @GetMapping("rooms/{roomId}/customer")
    public ResponseEntity<?> returnClienteOfRoom(@PathVariable Long roomId) {
        Customer customer = roomsService.returnClientRoom(roomId);
        if (customer != null) {
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
        else
            return ResponseEntity.notFound().build();
    }
}
