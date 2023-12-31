package com.proyectoHotel.services;

import com.proyectoHotel.controller.dto.HabitacionesDTO;
import com.proyectoHotel.model.Clientes;
import com.proyectoHotel.model.Habitaciones;
import com.proyectoHotel.repository.HabitacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HabitacionesServiceImpl implements HabitacionesService{
    private final HabitacionesRepository habitacionesRepository;

    public HabitacionesServiceImpl(HabitacionesRepository habitacionesRepository) {
        this.habitacionesRepository = habitacionesRepository;
    }

    @Override
    public List<Habitaciones> findAll() {
        return habitacionesRepository.findAll();
    }

    @Override
    public Optional<Habitaciones> findById(Long id) {
        return habitacionesRepository.findById(id);
    }


    @Override
    public Optional<Habitaciones> findByNrRoom(int nrRoom) {
        Optional<Habitaciones> oHabitaciones = habitacionesRepository.findAll().stream()
                .filter(habitaciones1 -> habitaciones1.getNroHabitacion() == nrRoom).findFirst();

        return oHabitaciones;
       // return habitacionesRepository.findByNrRoom(nrRoom);
    }

    @Override
    public boolean possibleRoomCapacity(int nrRoom, int quantGuests) {
        Optional<Habitaciones> oHab =  habitacionesRepository.findByNrRoom(nrRoom);
        Habitaciones hab= oHab.get();
        String tipoHab = hab.getTipoHabitacion();
        int aux = switch (tipoHab) {
            case "Simple" -> 1;
            case "Doble" -> 2;
            case "Triple" -> 3;
            case "Cuadruple" -> 4;
            default -> 0;
        };

        if (aux>=quantGuests)
            return true;
        else
            return false;
    }

    @Override
    public List<Habitaciones> listRoomsAvailable(int cantGuess) {
        List<Habitaciones> listaHabitaciones = habitacionesRepository.findAll();
        List<Habitaciones> listok = new ArrayList<>();
        String tipoHab;
        int aux;
        for (Habitaciones h: listaHabitaciones) {
            tipoHab = h.getTipoHabitacion();
            aux = switch (tipoHab) {
                case "Simple" -> 1;
                case "Doble" -> 2;
                case "Triple" -> 3;
                case "Cuadruple" -> 4;
                case "Quintuple" -> 5;
                default -> 0;
            };
            if ((aux >= cantGuess) && (h.getCantHuespedes() == 0)) {
                listok.add(h);
            }
        }
        return listok;
    }

    @Override
    public boolean occupiedRoom(int nroRoom) {
        Optional<Habitaciones> oHab =  habitacionesRepository.findByNrRoom(nroRoom);
        Habitaciones hab= oHab.get();
        if (hab.getClientes()!=null)
           return true;
        return false;
    }

    @Override
    public Habitaciones save(Habitaciones h) {
       return habitacionesRepository.save(h);
    }

    @Override
    public boolean deleteById(Long id) {
        habitacionesRepository.deleteById(id);
        return true;
    }

    @Override
    public Clientes returnClientRoom(Long idRoom) {
        Optional<Habitaciones> optionalHabitaciones = habitacionesRepository.findById(idRoom);
        Habitaciones habitacion = optionalHabitaciones.get();
        return habitacion.getClientes();
    }
}
