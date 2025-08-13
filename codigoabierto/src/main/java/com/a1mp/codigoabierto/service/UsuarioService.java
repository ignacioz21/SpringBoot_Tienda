package com.a1mp.codigoabierto.service;

import com.a1mp.codigoabierto.domain.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {  

    public List<Usuario> getUsuarios();

    public Usuario getUsuario(Usuario usuario);

    public Optional<Usuario> getUsuarioLogin(String email);

    public void save(Usuario usuario);

    public void delete(Usuario usuario);

}
