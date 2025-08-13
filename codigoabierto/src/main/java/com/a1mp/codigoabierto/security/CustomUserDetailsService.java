package com.a1mp.codigoabierto.security;

import com.a1mp.codigoabierto.domain.Usuario;
import com.a1mp.codigoabierto.dao.UsuarioDao;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioDao usuarioDao;

    public CustomUserDetailsService(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario u = usuarioDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        String roleName = u.getRoles().getFirst().getNombre();

        List<SimpleGrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority(roleName)
        );

        return new org.springframework.security.core.userdetails.User(
                u.getEmail(),
                u.getContrsena(),
                authorities
        );
    }
}
