package com.proyectoHotel.services;

import com.proyectoHotel.model.Customer;
import com.proyectoHotel.model.Room;
import com.proyectoHotel.repository.RoomsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomsServiceImpl implements RoomsService {
    private final RoomsRepository roomsRepository;

    public RoomsServiceImpl(RoomsRepository roomsRepository) {
        this.roomsRepository = roomsRepository;
    }

    @Override
    public List<Room> findAll() {
        return roomsRepository.findAll();
    }

    @Override
    public Optional<Room> findById(Long id) {
        return roomsRepository.findById(id);
    }


    @Override
    public Optional<Room> findByNrRoom(int nrRoom) {
        Optional<Room> optionalRoom = roomsRepository.findAll().stream()
                .filter(room -> room.getRoomNumber() == nrRoom).findFirst();

        return optionalRoom;
       // return habitacionesRepository.findByNrRoom(nrRoom);
    }

    // This method return if the room's capacity is possible based on the
    // number of guests passed as a parameter
    @Override
    public boolean possibleRoomCapacity(int nrRoom, int guestsCount) {
        Optional<Room> optionalRoom =  roomsRepository.findByNrRoom(nrRoom);
        Room room = optionalRoom.get();
        String roomType = room.getRoomType();
        int res = switch (roomType) {
            case "Simple" -> 1;
            case "Doble" -> 2;
            case "Triple" -> 3;
            case "Cuadruple" -> 4;
            default -> 0;
        };

        if (res >=guestsCount)
            return true;
        else
            return false;
    }

    @Override
    public List<Room> listRoomsAvailable(int guestCount) {
        List<Room> roomList = roomsRepository.findAll();
        List<Room> responseList = new ArrayList<>();
        String roomType;
        int res;
        for (Room room : roomList) {
            roomType = room.getRoomType();
            res = switch (roomType) {
                case "Simple" -> 1;
                case "Doble" -> 2;
                case "Triple" -> 3;
                case "Cuadruple" -> 4;
                case "Quintuple" -> 5;
                default -> 0;
            };
            if ((res >= guestCount) && (room.getGuestCount() == 0)) {
                responseList.add(room);
            }
        }
        return responseList;
    }

    @Override
    public boolean occupiedRoom(int nrRoom) {
        Optional<Room> oRoom =  roomsRepository.findByNrRoom(nrRoom);
        Room room= oRoom.get();
        if (room.getCustomer()!=null)
           return true;
        return false;
    }

    @Override
    public Room save(Room h) {
       return roomsRepository.save(h);
    }

    @Override
    public boolean deleteById(Long id) {
        roomsRepository.deleteById(id);
        return true;
    }

    @Override
    public Customer returnClientRoom(Long idRoom) {
        Optional<Room> optionalRoom = roomsRepository.findById(idRoom);
        Room room = optionalRoom.get();
        return room.getCustomer();
    }
}
