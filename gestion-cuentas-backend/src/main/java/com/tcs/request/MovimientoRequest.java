package com.tcs.request;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovimientoRequest {
    private Date fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipo;
    private double saldoInicial;
    private boolean estado;
    private double movimiento;
    private double saldoDisponible;
}
