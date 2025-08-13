package com.a1mp.codigoabierto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.a1mp.codigoabierto.service.UsuarioService;
import com.a1mp.codigoabierto.domain.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //Login page
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.registrarUsuario(usuario, "ROLE_USER"); //Valor por defecto
        
        return "redirect:/login?registrado";
    }

    @GetMapping("/usuario")
    public String usuarioHome() {
        return "usuario";
    }

    @GetMapping("/admin")
    public String adminHome() {
        return "admin";
    }
    
    
    
    

}
