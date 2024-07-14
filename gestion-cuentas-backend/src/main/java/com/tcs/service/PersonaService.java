package com.tcs.service;


import com.tcs.model.Persona;
import com.tcs.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    public Persona getPersonaById(Long id) {
        return personaRepository.findById(id).orElse(null);
    }

    public Persona createPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    public Persona updatePersona(Long id, Persona personaDetails) {
        Persona persona = getPersonaById(id);
        if (persona != null) {
            persona.setNombre(personaDetails.getNombre());
            persona.setGenero(personaDetails.getGenero());
            persona.setEdad(personaDetails.getEdad());
            persona.setIdentificacion(personaDetails.getIdentificacion());
            persona.setDireccion(personaDetails.getDireccion());
            persona.setTelefono(personaDetails.getTelefono());
            return personaRepository.save(persona);
        }
        return null;
    }

    public boolean deletePersona(Long id) {
        if (getPersonaById(id) != null) {
            personaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
