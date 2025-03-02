package com.tcs.repository;

import com.tcs.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    Cuenta findByNumeroCuenta(String numeroCuenta);
}
