package com.proyectoHotel.services;

import com.proyectoHotel.model.Clientes;
import com.proyectoHotel.repository.ClienteRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import  org.junit.Assert;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class ClientesServiceImplTest {
    private ClientesServiceImpl clientesService;

    @BeforeEach
    void setUp() {
        ClienteRepository clienteRepository = Mockito.mock(ClienteRepository.class);
        Mockito.when(clienteRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Clientes(1L,"Nicolas","Alvarez",30050605,"2966 489009",null),
                        new Clientes(2L,"Jimena","Alvarez",35050789,"2494 4858732",null),
                        new Clientes(3L,"Agustina","Sazatornil",33789053,"2494 647867",null)
                )
        );

        clientesService = new ClientesServiceImpl(clienteRepository);
    }

    @Test
    void return_customer_by_surname() {


        List<Clientes> clientes = clientesService.findBySurname("Alvarez");

        List<Long> clientesId = clientes.stream().map(cliente -> cliente.getId()).collect(Collectors.toList());

        Assert.assertThat(clientesId, CoreMatchers.is(Arrays.asList(1L,2L)));

    }

    @Test
    void return_customer_by_dni() {

        Clientes cliente = clientesService.findByDni(30050605);

        Long clienteFoundId = cliente.getId();

        Long clienteIdExpected = 1L;

        Assert.assertEquals(clienteIdExpected,clienteFoundId);

    }

}