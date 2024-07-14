package com.tcs.model;

import jakarta.persistence.*;
import java.util.Date;
import lombok.Data;


@Entity
@Data
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cuentaId", nullable = false)
    private Cuenta cuenta;
    private Date fecha;
    private String tipoMovimiento;
    private double valor;
    private double saldo;

}
