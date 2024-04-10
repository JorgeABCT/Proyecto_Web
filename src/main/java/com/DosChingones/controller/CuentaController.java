/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DosChingones.controller;

import com.DosChingones.domain.Usuario;
import com.DosChingones.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author XPC
 */
@Controller
@RequestMapping("/cuenta")
public class CuentaController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession session;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/nuevo")
    private String nuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        String nombre = "Dos Chingones - Nueva Cuenta";
        model.addAttribute("title", nombre);
        return "/usuario/nuevoU";
    }

    /*@GetMapping("/modifica")
    private String modificarU(Model model, Usuario usuario) {
        usuario = usuarioService.getUsuarioPorUsernameYPassword(usuario.getUsername(), usuario.getPassword());
        model.addAttribute("usuario", usuario);
        return "/usuario/modificaU";
    }*/
    @GetMapping("/modifica")
    public String modificarCuenta(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            Usuario usuario = usuarioService.getUsuarioPorUsername(userDetails.getUsername());
            model.addAttribute("usuario", usuario);
            String nombre = "Dos Chingones - Modificar";
            model.addAttribute("title", nombre);
            return "/usuario/modificaU";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/guardar")
    public String actualizarU(@ModelAttribute("usuario") Usuario usuario) {
        var codigo = new BCryptPasswordEncoder();
        usuario.setPassword(codigo.encode(usuario.getPassword()));
        usuarioService.save(usuario, false);
        return "redirect:/";
    }

    @PostMapping("/nuevo")
    public String nuevo(@ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult, Model model) {
        var codigo = new BCryptPasswordEncoder();
        usuario.setPassword(codigo.encode(usuario.getPassword()));
        Usuario usuarioComprobar = usuarioService.getUsuarioPorUsernameOCorreo(usuario.getUsername(), usuario.getCorreo());
        if (usuarioComprobar == null) {
            usuarioService.save(usuario, true);
            Usuario usuarioVer = usuarioService.getUsuarioPorUsername(usuario.getUsername());
            return "redirect:/";
        } else {
            bindingResult.reject("error.crear", "Ya existe un usuario con este nombre, o correo ya vinculado a una cuenta.");
            return "/usuario/nuevoU";
        }
    }

    @GetMapping("/eliminar")
    public String desactivarCuenta(Model model, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            Usuario usuario = usuarioService.getUsuarioPorUsername(userDetails.getUsername());
            usuario.setActivo(false);
            request.getSession().invalidate();
            usuarioService.save(usuario);
            return "redirect:/login";
        } else {
            return "redirect:/login";
        }
    }

}
