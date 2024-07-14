package com.tcs.controller;


import com.tcs.exception.ResourceNotFoundException;
import com.tcs.model.Persona;
import com.tcs.repository.PersonaRepository;
import com.tcs.service.PersonaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/api/personas")
@Tag(name = "Gestion Personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @Operation(summary = "Obtener personas")
    @GetMapping
    public List<Persona> getAllPersonas() {
        return personaService.getAllPersonas();
    }

    @Operation(summary = "Crear persona")
    @PostMapping
    public Persona createPersona(@RequestBody Persona persona) {
        return personaService.createPersona(persona);
    }

    @Operation(summary = "Obtener persona por id")
    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable(value = "id") Long personaId) {
        Persona persona = personaService.getPersonaById(personaId);
        if (persona != null) {
            return ResponseEntity.ok(persona);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Actualizar persona")
    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable(value = "id") Long personaId,
            @RequestBody Persona personaDetails) {
        Persona updatedPersona = personaService.updatePersona(personaId, personaDetails);
        if (updatedPersona != null) {
            return ResponseEntity.ok(updatedPersona);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar persona")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable(value = "id") Long personaId) {
        boolean isDeleted = personaService.deletePersona(personaId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

