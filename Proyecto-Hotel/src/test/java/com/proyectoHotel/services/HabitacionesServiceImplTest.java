package com.proyectoHotel.services;

import com.proyectoHotel.model.Clientes;
import com.proyectoHotel.model.Habitaciones;
import com.proyectoHotel.repository.ClienteRepository;
import com.proyectoHotel.repository.HabitacionesRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class HabitacionesServiceImplTest {
    private HabitacionesServiceImpl habitacionesService;
    @BeforeEach
    void setUp() {
        HabitacionesRepository habitacionesRepository = Mockito.mock(HabitacionesRepository.class);
        Mockito.when(habitacionesRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Habitaciones(1L,0,"Doble",124,null,null,19500.0,null),
                        new Habitaciones(2L,0,"Triple",214,null,null,28500.8,null),
                        new Habitaciones(3L,0,"Simple",318,null,null,9500.55,null)
                )
        );
        habitacionesService = new HabitacionesServiceImpl(habitacionesRepository);

    }

    @Test
    void findByNrRoom() {
        Optional<Habitaciones> optionalHabitacion = habitacionesService.findByNrRoom(124);

        Habitaciones habitacion = optionalHabitacion.get();

        Long habitacioId = habitacion.getId();

        Long idHabitacionExpected = 1L;

        assertEquals(idHabitacionExpected,habitacioId);
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