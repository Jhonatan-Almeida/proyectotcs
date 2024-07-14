package com.tcs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Cliente extends Persona {

    private String clienteid;
    private String contrasena;
    private String estado;

}
