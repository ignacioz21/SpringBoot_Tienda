package com.a1mp.codigoabierto.service;

import com.a1mp.codigoabierto.domain.*;
import com.a1mp.codigoabierto.dao.*;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {  

    private final UsuarioDao usuarioRepository;
    private final RolDao rolRepository;
    private final UsuarioRolDao usuarioRolDao;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioDao usuarioRepository, RolDao rolRepository, 
                         UsuarioRolDao usuarioRolDao, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.usuarioRolDao = usuarioRolDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Usuario registrarUsuario(Usuario usuario, String nombreRol) {
        System.out.println("Registrando usuario: " + usuario.getEmail());
        
        // Encriptar contraseña
        usuario.setContrsena(passwordEncoder.encode(usuario.getContrsena()));
        
        // Guardar usuario primero
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        System.out.println("Usuario guardado con ID: " + usuarioGuardado.getId());

        // Buscar el rol
        Rol rol = rolRepository.findByNombre(nombreRol)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + nombreRol));
        
        System.out.println("Rol encontrado - ID: " + rol.getId());

        // Crear la relación usuario-rol
        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setIdUsuario(usuarioGuardado.getId());
        usuarioRol.setIdRol(rol.getId());
        usuarioRolDao.save(usuarioRol);
        
        System.out.println("Relación usuario-rol creada");
        
        return usuarioGuardado;
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    public List<Long> obtenerRolesDeUsuario(Long idUsuario) {
        return usuarioRolDao.findRolesByUsuarioId(idUsuario);
    }
}