package com.tcs.service;

import com.tcs.exception.ResourceNotFoundException;
import com.tcs.model.Cliente;
import com.tcs.repository.ClienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(String clienteid) {
        return clienteRepository.findById(clienteid);
    }

    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(String clienteid, Cliente clienteDetails) {
        Cliente cliente = clienteRepository.findById(clienteid).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + clienteid));
        cliente.setNombre(clienteDetails.getNombre());
        cliente.setGenero(clienteDetails.getGenero());
        cliente.setEdad(clienteDetails.getEdad());
        cliente.setDireccion(clienteDetails.getDireccion());
        cliente.setTelefono(clienteDetails.getTelefono());
        cliente.setContrasena(clienteDetails.getContrasena());
        cliente.setEstado(clienteDetails.getEstado());
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(String clienteid) {
        Cliente cliente = clienteRepository.findById(clienteid).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + clienteid));
        clienteRepository.delete(cliente);
    }
}
