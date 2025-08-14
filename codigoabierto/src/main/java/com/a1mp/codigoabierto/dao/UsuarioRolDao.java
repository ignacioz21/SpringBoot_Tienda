package com.a1mp.codigoabierto.dao;

import com.a1mp.codigoabierto.domain.UsuarioRol;
import com.a1mp.codigoabierto.domain.UsuarioRolId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UsuarioRolDao extends JpaRepository<UsuarioRol, UsuarioRolId> {
    
    @Query("SELECT ur.idRol FROM UsuarioRol ur WHERE ur.idUsuario = :idUsuario")
    List<Long> findRolesByUsuarioId(@Param("idUsuario") Long idUsuario);
    
    void deleteByIdUsuario(Long idUsuario);
}