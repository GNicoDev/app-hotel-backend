package com.proyectoHotel.services;

import com.proyectoHotel.model.Clientes;
import com.proyectoHotel.model.Habitaciones;
import com.proyectoHotel.repository.ClienteRepository;
import com.proyectoHotel.repository.HabitacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {
    HabitacionesRepository habitacionesRepository;

    @Override
    public Habitaciones checkIn(Clientes cliente, Habitaciones habitacion) {
        habitacion.setClientes(cliente);
        return habitacion;
    }

    @Override
    public void checkOut(Clientes cliente, Habitaciones habitacion) {
        habitacion.setClientes(null);
    }


}
