package com.proyectoHotel.mapper;

import com.proyectoHotel.controller.dto.RoomDTO;
import com.proyectoHotel.model.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    public RoomDTO toDTO(Room room) {
        return RoomDTO.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .roomType(room.getRoomType())
                .guestCount(room.getGuestCount())
                .pricePerNight(room.getPricePerNight())
                .checkInDate(room.getCheckInDate())
                .checkOutDate(room.getCheckOutDate())
                .build();
    }

    public Room fromDTO(RoomDTO roomDTO) {
        return Room.builder()
                .id(roomDTO.getId())
                .roomNumber(roomDTO.getRoomNumber())
                .roomType(roomDTO.getRoomType())
                .guestCount(roomDTO.getGuestCount())
                .pricePerNight(roomDTO.getPricePerNight())
                .checkInDate(roomDTO.getCheckInDate())
                .checkOutDate(roomDTO.getCheckOutDate())
                .build();
    }

    // METHOD FOR UPDATING AN EXISTING ENTITY WITH VALUES FROM THE DTO
    public void updateRoomFromDTO(RoomDTO roomDTO, Room room) {
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setRoomType(roomDTO.getRoomType());
        room.setPricePerNight(roomDTO.getPricePerNight());
        room.setGuestCount(roomDTO.getGuestCount());
        room.setCheckInDate(roomDTO.getCheckInDate());
        room.setCheckOutDate(roomDTO.getCheckOutDate());
    }

}
