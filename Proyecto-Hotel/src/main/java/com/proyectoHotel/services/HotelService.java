package com.proyectoHotel.services;

import com.proyectoHotel.model.Clientes;
import com.proyectoHotel.model.Habitaciones;

public interface HotelService {
    Habitaciones checkIn (Clientes cliente, Habitaciones habitacion);

    void checkOut (Clientes cliente, Habitaciones habitacion);


}
