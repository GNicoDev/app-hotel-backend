package com.proyectoHotel.services;

import com.proyectoHotel.model.Clientes;
import com.proyectoHotel.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientesServiceImpl implements ClientesService {

    private final ClienteRepository clienteRepository;

    public ClientesServiceImpl(ClienteRepository clienteRepository) {

        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Clientes> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Clientes> findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public List<Clientes> findBySurname(String surname) {
        List<Clientes> clientes = clienteRepository.findAll().stream()
                .filter(clientes1 -> clientes1.getApellido() == surname).collect(Collectors.toList());
        return clientes;
    }

    @Override
    public Clientes findByDni(int dni) {
        Optional<Clientes> oCliente = clienteRepository.findAll().stream()
                .filter(clientedni -> clientedni.getDni()== dni).findFirst();
        Clientes cliente = oCliente.get();
        return cliente;
    }

    @Override
    public Clientes save(Clientes c) {

        return clienteRepository.save(c);
    }

    @Override
    public boolean deleteById(Long id) {
        clienteRepository.deleteById(id);
        return true;
    }
}
