package com.tcs.service;

import com.tcs.exception.SaldoNoDisponibleException;
import com.tcs.model.Cuenta;
import com.tcs.model.Movimiento;
import com.tcs.repository.CuentaRepository;
import com.tcs.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MovimientoService {
    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    @Autowired
    public MovimientoService(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
    }

    @Transactional
    public Movimiento crearMovimiento(Movimiento movimiento) throws SaldoNoDisponibleException {
        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuenta().getCuentaId())
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

        double saldoActual = cuenta.getSaldoInicial();
        if (movimiento.getTipoMovimiento().equals("retiro") && saldoActual < movimiento.getValor()) {
            throw new SaldoNoDisponibleException("Saldo no disponible");
        }

        movimiento.setFecha(new Date());

        // Actualizar saldo de la cuenta y del movimiento
        if (movimiento.getTipoMovimiento().equals("retiro")) {
            saldoActual -= movimiento.getValor();
        } else if (movimiento.getTipoMovimiento().equals("deposito")) {
            saldoActual += movimiento.getValor();
        }
        cuenta.setSaldoInicial(saldoActual);
        movimiento.setSaldo(saldoActual);
        cuentaRepository.save(cuenta);

        return movimientoRepository.save(movimiento);
    }

    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    public Movimiento getMovimientoById(Long id) {
        return movimientoRepository.findById(id).orElse(null);
    }

    public Movimiento createMovimiento(Movimiento movimiento) {
        return movimientoRepository.save(movimiento);
    }

    public Movimiento updateMovimiento(Long id, Movimiento movimientoDetails) {
        Movimiento movimiento = getMovimientoById(id);
        if (movimiento != null) {
            movimiento.setFecha(movimientoDetails.getFecha());
            movimiento.setTipoMovimiento(movimientoDetails.getTipoMovimiento());
            movimiento.setValor(movimientoDetails.getValor());
            movimiento.setSaldo(movimientoDetails.getSaldo());
            return movimientoRepository.save(movimiento);
        }
        return null;
    }

    public boolean deleteMovimiento(Long id) {
        if (getMovimientoById(id) != null) {
            movimientoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Cuenta buscarCuentaPorNumero(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta);
    }
}
