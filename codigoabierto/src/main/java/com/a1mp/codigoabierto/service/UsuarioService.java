package com.a1mp.codigoabierto.service;

import com.a1mp.codigoabierto.domain.*;
import com.a1mp.codigoabierto.dao.*;
import java.util.List;
import java.util.Optional;
import java.util.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {  

    private final UsuarioDao usuarioRepository;
    private final RolDao rolRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioDao usuarioRepository, RolDao rolRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrarUsuario(Usuario usuario, String nombreRol) {
        usuario.setContrsena(passwordEncoder.encode(usuario.getContrsena()));

        Rol rol = rolRepository.findByNombre(nombreRol)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        List<Rol> roles = new ArrayList<>();
        roles.add(rol);
        usuario.setRoles(roles);

        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

}
