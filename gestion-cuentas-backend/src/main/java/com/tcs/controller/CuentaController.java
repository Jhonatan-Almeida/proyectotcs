package com.tcs.controller;


import com.tcs.model.Cuenta;
import com.tcs.service.CuentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
@Tag(name = "Gestion Cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @Operation(summary = "Obtener cuentas")
    @GetMapping
    public List<Cuenta> getAllCuentas() {
        return cuentaService.getAllCuentas();
    }

    @Operation(summary = "Obtener cuenta por id")
    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<Cuenta> getCuentaById(@PathVariable Long cuentaId) {
        Cuenta cuenta = cuentaService.getCuentaById(cuentaId);
        if (cuenta != null) {
            return ResponseEntity.ok(cuenta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Crear cuenta")
    @PostMapping
    public Cuenta createCuenta(@RequestBody Cuenta cuenta) {
        return cuentaService.createCuenta(cuenta);
    }

    @Operation(summary = "Actualizar cuenta")
    @PutMapping("/{numeroCuenta}")
    public ResponseEntity<Cuenta> updateCuenta(@PathVariable Long cuentaId, @RequestBody Cuenta cuentaDetails) {
        Cuenta updatedCuenta = cuentaService.updateCuenta(cuentaId, cuentaDetails);
        if (updatedCuenta != null) {
            return ResponseEntity.ok(updatedCuenta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar cuenta")
    @DeleteMapping("/{numeroCuenta}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Long cuentaId) {
        boolean isDeleted = cuentaService.deleteCuenta(cuentaId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

