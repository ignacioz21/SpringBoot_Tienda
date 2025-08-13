package com.a1mp.codigoabierto.service.impl;

import org.springframework.stereotype.Service;

import com.a1mp.codigoabierto.dao.UsuarioDao;
import com.a1mp.codigoabierto.domain.Usuario;
import com.a1mp.codigoabierto.service.UsuarioService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        return usuarioDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuario(Usuario usuario) {
        return usuarioDao.findById(usuario.getId()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> getUsuarioLogin(String email) {
        return usuarioDao.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public void save(Usuario usuario){
        usuarioDao.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public void delete(Usuario usuario){
        usuarioDao.delete(usuario);
    }


}
