package com.tcs.controller;

import com.tcs.exception.ResourceNotFoundException;
import com.tcs.model.Cliente;
import com.tcs.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Gestion Clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Obtener clientes")
    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @Operation(summary = "Obtener cliente por id")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable String clienteid) {
        Cliente cliente = clienteService.getClienteById(clienteid).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + clienteid));
        return ResponseEntity.ok(cliente);
    }

    @Operation(summary = "Crear cliente")
    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteService.createCliente(cliente);
    }

    @Operation(summary = "Actualizar cliente")
    @PutMapping("/{clienteid}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable String clienteid, @RequestBody Cliente clienteDetails) {
        Cliente updatedCliente = clienteService.updateCliente(clienteid, clienteDetails);
        return ResponseEntity.ok(updatedCliente);
    }

    @Operation(summary = "Eliminar cliente")
    @DeleteMapping("/{clienteid}")
    public ResponseEntity<Void> deleteCliente(@PathVariable String clienteid) {
        clienteService.deleteCliente(clienteid);
        return ResponseEntity.noContent().build();
    }
}
