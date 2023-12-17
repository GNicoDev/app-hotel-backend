package com.proyectoHotel.services;

import com.proyectoHotel.model.Clientes;
import com.proyectoHotel.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        return clienteRepository.findClientBySurname(surname);
    }

    @Override
    public Clientes findByDni(int dni) {
        return clienteRepository.findClienteByDni(dni);
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
