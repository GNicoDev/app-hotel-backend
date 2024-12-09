package com.proyectoHotel.controller;

import com.proyectoHotel.controller.dto.RoomDTO;
import com.proyectoHotel.mapper.RoomMapper;
import com.proyectoHotel.model.Room;
import com.proyectoHotel.services.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;

@RestController
@RequestMapping("/hotel/")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})

public class RoomController {

    private final RoomsService roomsService;
    private final RoomMapper roomMapper;
    @Autowired
    public RoomController(RoomsService roomsService, RoomMapper roomMapper) {
        this.roomsService = roomsService;
        this.roomMapper = roomMapper;
    }

    //LIST ALL ROOMS
    @GetMapping("rooms")
    public ResponseEntity<?> listAllRooms() {
        List<Room> roomList = roomsService.findAll();
        List<RoomDTO> roomDTOList = roomList.stream()
                .map(roomMapper::toDTO).collect(Collectors.toList());

        return ResponseEntity.ok(roomDTOList);
    }

    //SHOW ROOM BY NUMBER
    @GetMapping("rooms/number/{number}")
    public ResponseEntity showRoomByNumber (@PathVariable int number) {
        Optional<Room> optionalRoom = roomsService.findByNrRoom(number);
        if (optionalRoom.isPresent()) {
            RoomDTO roomDTO = roomMapper.toDTO(optionalRoom.get());
            return ResponseEntity.ok(roomDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found with number: " + number);
    }

    //SHOW AVAILABLE ROOMS ACCORDING GUESS QUANTITY
    @GetMapping("rooms/available/{guestCount}")
    public ResponseEntity<?> listRoomsAvailable(@PathVariable int guestCount) {
        List<Room> roomList = roomsService.listRoomsAvailable(guestCount);
        if (!roomList.isEmpty()) {
            List<RoomDTO> roomDTOList = roomList.stream()
                    .map(roomMapper::toDTO).collect(Collectors.toList());
            return ResponseEntity.ok(roomDTOList);
        }
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No rooms available for guest count: " + guestCount);
    }

    // FIND ROOM BY ID
    @GetMapping("rooms/{id}")
    public ResponseEntity<?> findRoomById(@PathVariable Long id) {

        Optional<Room> optionalRoom = roomsService.findById(id);
        if (optionalRoom.isPresent()) {
            RoomDTO roomDTO = roomMapper.toDTO(optionalRoom.get());
            return ResponseEntity.ok(roomDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found with ID: " + id);
    }


    // SAVE ROOM
    @PostMapping("rooms")
    public ResponseEntity<?> saveRoom(@Valid @RequestBody RoomDTO roomDTO) throws URISyntaxException {
        if (roomDTO.getRoomNumber()!= 0 && !roomDTO.getRoomType().isEmpty() && roomDTO.getPricePerNight() != 0 ) {
            if (roomsService.findByNrRoom(roomDTO.getRoomNumber()).isEmpty()) {
                Room newRoom = roomsService.save(roomMapper.fromDTO(roomDTO));
                RoomDTO roomDTOcreated = roomMapper.toDTO(newRoom);
                return ResponseEntity.created(new URI("/hotel/rooms")).body(roomDTOcreated);
            }
            else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Room number already exists");            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid room data");
    }

    //UPDATE ROOM
    @PutMapping("rooms/{id}")
    public ResponseEntity<?> updateRoom(@Valid @RequestBody RoomDTO roomDTO, @PathVariable Long id) {
        Optional<Room> optionalRoom = roomsService.findById(id);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            roomMapper.updateRoomFromDTO(roomDTO,room);

            Room updatedRoom = roomsService.save(room);

            RoomDTO updatedRoomDTO = roomMapper.toDTO(updatedRoom);
            return ResponseEntity.ok(updatedRoomDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found with ID: " + id);
    }

    // DELETE ROOM
    @DeleteMapping("rooms/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long id) {
        Optional<Room> optionalRoom = roomsService.findById(id);
        if (optionalRoom.isPresent()) {
            return ResponseEntity.ok(roomsService.deleteById(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found with ID: " + id);
    }

}