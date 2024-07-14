package com.tcs.service;

import com.tcs.model.Cuenta;
import com.tcs.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    public Cuenta getCuentaById(Long cuentaId) {
        return cuentaRepository.findById(cuentaId).orElse(null);
    }

    public Cuenta createCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public Cuenta updateCuenta(Long cuentaId, Cuenta cuentaDetails) {
        Cuenta cuenta = getCuentaById(cuentaId);
        if (cuenta != null) {
            cuenta.setNumeroCuenta(cuentaDetails.getNumeroCuenta());
            cuenta.setTipoCuenta(cuentaDetails.getTipoCuenta());
            cuenta.setSaldoInicial(cuentaDetails.getSaldoInicial());
            cuenta.setEstado(cuentaDetails.getEstado());
            return cuentaRepository.save(cuenta);
        }
        return null;
    }

    public boolean deleteCuenta(Long cuentaId) {
        if (getCuentaById(cuentaId) != null) {
            cuentaRepository.deleteById(cuentaId);
            return true;
        }
        return false;
    }

    public Cuenta buscarCuentaPorNumero(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta);
    }
}

