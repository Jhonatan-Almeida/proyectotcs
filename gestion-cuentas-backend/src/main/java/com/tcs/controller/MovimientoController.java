package com.tcs.controller;


import com.tcs.exception.SaldoNoDisponibleException;
import com.tcs.model.Cuenta;
import com.tcs.model.Movimiento;
import com.tcs.request.MovimientoRequest;
import com.tcs.service.CuentaService;
import com.tcs.service.MovimientoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@Tag(name = "Movimientos para una cuenta")
public class MovimientoController {

    @Autowired
    private final MovimientoService movimientoService;
    private final CuentaService cuentaService;

    @Autowired
    public MovimientoController(MovimientoService movimientoService, CuentaService cuentaService) {
        this.movimientoService = movimientoService;
        this.cuentaService = cuentaService;
    }
    @Operation(summary = "Crear movimiento")
    @PostMapping("/crear")
    public ResponseEntity<?> crearMovimiento(@RequestBody MovimientoRequest movimientoRequest) {
        try {
            Cuenta cuenta = cuentaService.buscarCuentaPorNumero(movimientoRequest.getNumeroCuenta());
            if (cuenta == null) {
                return ResponseEntity.badRequest().body("La cuenta no existe");
            }

            Movimiento movimiento = new Movimiento();
            movimiento.setCuenta(cuenta);
            movimiento.setFecha(new Date());
            movimiento.setTipoMovimiento(movimientoRequest.getTipo());
            movimiento.setValor(movimientoRequest.getMovimiento());
            movimiento.setSaldo(movimientoRequest.getSaldoDisponible());

            movimiento = movimientoService.crearMovimiento(movimiento);

            return ResponseEntity.ok(movimiento);
        } catch (SaldoNoDisponibleException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocurrió un error interno");
        }
    }
    @Operation(summary = "Obtener movimientos")
    @GetMapping
    public List<Movimiento> getAllMovimientos() {
        return movimientoService.getAllMovimientos();
    }

    @Operation(summary = "Obtener movimiento por id")
    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> getMovimientoById(@PathVariable Long id) {
        Movimiento movimiento = movimientoService.getMovimientoById(id);
        if (movimiento != null) {
            return ResponseEntity.ok(movimiento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Crear movimiento")
    @PostMapping
    public Movimiento createMovimiento(@RequestBody Movimiento movimiento) {
        return movimientoService.createMovimiento(movimiento);
    }
    @Operation(summary = "Actualizar movimiento")
    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> updateMovimiento(@PathVariable Long id, @RequestBody Movimiento movimientoDetails) {
        Movimiento updatedMovimiento = movimientoService.updateMovimiento(id, movimientoDetails);
        if (updatedMovimiento != null) {
            return ResponseEntity.ok(updatedMovimiento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "Eliminar movimiento")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id) {
        boolean isDeleted = movimientoService.deleteMovimiento(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}

