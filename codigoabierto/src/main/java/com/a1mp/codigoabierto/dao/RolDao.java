package com.a1mp.codigoabierto.dao;

import com.a1mp.codigoabierto.domain.Rol;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RolDao extends JpaRepository<Rol, Long>{
    Optional<Rol> findByNombre(String nombre);
}
