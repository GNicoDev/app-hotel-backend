package com.proyectoHotel.services;

import com.proyectoHotel.model.Room;
import com.proyectoHotel.repository.RoomsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RoomsServiceImplTest {
    private RoomsServiceImpl roomsService;
    @BeforeEach
    void setUp() {
        RoomsRepository roomsRepository = Mockito.mock(RoomsRepository.class);
        Mockito.when(roomsRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Room(1L,0,"Doble",124,null,null,19500.0,null),
                        new Room(2L,0,"Triple",214,null,null,28500.8,null),
                        new Room(3L,0,"Simple",318,null,null,9500.55,null)
                )
        );
        roomsService = new RoomsServiceImpl(roomsRepository);

    }

    @Test
    void return_the_room_by_rooms_number() {
        Optional<Room> optionalRoom = roomsService.findByNrRoom(124);

        Room room = optionalRoom.get();

        Long roomId = room.getId();

        Long roomIdExpected = 1L;

        assertEquals(roomIdExpected,roomId);
    }

    @Test
    void possibleRoomCapacity() {

    }

    @Test
    void listRoomsAvailable() {
    }

    @Test
    void occupiedRoom() {
    }

    @Test
    void returnClientRoom() {
    }
}