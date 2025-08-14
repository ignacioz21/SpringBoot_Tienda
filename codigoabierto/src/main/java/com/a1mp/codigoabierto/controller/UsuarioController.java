package com.a1mp.codigoabierto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.a1mp.codigoabierto.service.UsuarioService;
import com.a1mp.codigoabierto.domain.*;

@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Login page
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Registro page - GET
    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    // Registro - POST
    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {
        try {
            usuarioService.registrarUsuario(usuario, "ROLE_USER");
            return "redirect:/login?registrado";
        } catch (Exception e) {
            return "redirect:/registro?error";
        }
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