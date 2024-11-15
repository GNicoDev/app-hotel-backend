package com.proyectoHotel.controller;

import com.proyectoHotel.controller.dto.RoomDTO;
import com.proyectoHotel.model.Room;
import com.proyectoHotel.services.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel/")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})

public class RoomController {
    @Autowired
    RoomsService roomsService;

    // FIND ROOM BY ID
    @GetMapping("rooms/{id}")
    public ResponseEntity<?> findRoomById(@PathVariable Long id) {

        Optional<Room> optionalRoom = roomsService.findById(id);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();

            RoomDTO roomDTO = RoomDTO.builder()
                    .id(room.getId())
                    .roomNumber(room.getRoomNumber())
                    .roomType(room.getRoomType())
                    .guestCount(room.getGuestCount())
                    .pricePerNight(room.getPricePerNight())
                    .checkInDate(room.getCheckInDate())
                    .checkOutDate(room.getCheckOutDate())
                    .build();
            return ResponseEntity.ok(roomDTO);
        }
        return ResponseEntity.notFound().build();
    }

    //List all rooms
    @GetMapping("rooms")
    public ResponseEntity<?> listAllRooms() {
        List<Room> roomList = roomsService.findAll();
        List<RoomDTO> roomDTOList = new ArrayList<>();
        for (Room room : roomList) {
            RoomDTO roomDTO = RoomDTO.builder()
                    .id(room.getId())
                    .roomNumber(room.getRoomNumber())
                    .roomType(room.getRoomType())
                    .guestCount(room.getGuestCount())
                    .pricePerNight(room.getPricePerNight())
                    .checkInDate(room.getCheckInDate())
                    .checkOutDate(room.getCheckOutDate())
                    .build();
            roomDTOList.add(roomDTO);
        }

        return ResponseEntity.ok(roomDTOList);
    }

    //SHOW ROOM BY NUMBER
    @GetMapping("rooms/number/{number}")
    public ResponseEntity showRoomByNumber (@PathVariable int number) {
        Optional<Room> optionalRoom = roomsService.findByNrRoom(number);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            RoomDTO roomDTO = RoomDTO.builder()
                    .id(room.getId())
                    .roomNumber(room.getRoomNumber())
                    .roomType(room.getRoomType())
                    .guestCount(room.getGuestCount())
                    .pricePerNight(room.getPricePerNight())
                    .checkInDate(room.getCheckInDate())
                    .checkOutDate(room.getCheckOutDate())
                    .build();
            return ResponseEntity.ok(roomDTO);
        }
        return ResponseEntity.badRequest().build();
    }

    //SHOW AVAILABLE ROOMS ACCORDING GUESS QUANTITY
    @GetMapping("rooms/available/{guestCount}")
    public ResponseEntity<?> listRoomsAvailable(@PathVariable int guestCount) {
        List<Room> roomList = roomsService.listRoomsAvailable(guestCount);
        if (roomList != null) {
            List<RoomDTO> roomDTOList = new ArrayList<>();
            for (Room room : roomList) {
                RoomDTO roomDTO = RoomDTO.builder()
                        .id(room.getId())
                        .roomNumber(room.getRoomNumber())
                        .roomType(room.getRoomType())
                        .guestCount(room.getGuestCount())
                        .pricePerNight(room.getPricePerNight())
                        .checkInDate(room.getCheckInDate())
                        .checkOutDate(room.getCheckOutDate())
                        .build();
                roomDTOList.add(roomDTO);
            }
            return ResponseEntity.ok(roomDTOList);
        }
        else
            return ResponseEntity.noContent().build();
    }


    // SAVE ROOM
    @PostMapping("rooms")
    public ResponseEntity<?> saveRoom(@RequestBody RoomDTO roomDTO) throws URISyntaxException {
        if (roomDTO.getRoomNumber()!= 0 && !roomDTO.getRoomType().isEmpty() && roomDTO.getPricePerNight() != 0 ) {
            if (roomsService.findByNrRoom(roomDTO.getRoomNumber()).isEmpty()) {
                Room newRoom = roomsService.save(Room.builder()
                        .roomNumber(roomDTO.getRoomNumber())
                        .roomType(roomDTO.getRoomType())
                        .pricePerNight(roomDTO.getPricePerNight())
                        .build());
                RoomDTO roomDTOcreated = RoomDTO.builder()
                        .id(newRoom.getId())
                        .roomNumber(roomDTO.getRoomNumber())
                        .roomType(roomDTO.getRoomType())
                        .pricePerNight(roomDTO.getPricePerNight())
                        .build();
                return ResponseEntity.created(new URI("/hotel/rooms")).body(roomDTOcreated);
            }
            else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    //UPDATE ROOM
    @PutMapping("rooms/{id}")
    public ResponseEntity<?> updateRoom(@RequestBody RoomDTO roomDTO, @PathVariable Long id) {
        Optional<Room> optionalRoom = roomsService.findById(id);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            room.setRoomNumber(roomDTO.getRoomNumber());
            room.setRoomType(roomDTO.getRoomType());
            room.setPricePerNight(roomDTO.getPricePerNight());

            return ResponseEntity.ok(roomsService.save(room));
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE ROOM
    @DeleteMapping("rooms/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long id) {
        Optional<Room> optionalRoom = roomsService.findById(id);
        if (optionalRoom.isPresent()) {
            return ResponseEntity.ok(roomsService.deleteById(id));
        }
        return ResponseEntity.notFound().build();
    }
}