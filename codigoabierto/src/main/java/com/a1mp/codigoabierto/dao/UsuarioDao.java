package com.a1mp.codigoabierto.dao;

import com.a1mp.codigoabierto.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}   
