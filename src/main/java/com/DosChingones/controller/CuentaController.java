/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DosChingones.controller;

import com.DosChingones.domain.Usuario;
import com.DosChingones.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author XPC
 */

@Controller
@RequestMapping("/cuenta")
public class CuentaController {

    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/login")
    private String login(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "/usuario/inicioU";
    }
    
    @GetMapping("/nuevo")
    private String nuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "/usuario/nuevoU";
    }
    
    @GetMapping("/modifica")
    private String modificarU(Model model, Usuario usuario){
        usuario = usuarioService.getUsuarioPorUsernameYPassword(usuario.getUsername(), usuario.getPassword());
        model.addAttribute("usuario", usuario);
        return "/usuario/modificaU";
    }
    
    @PostMapping("/guardar")
    private String actualizarU(@ModelAttribute("usuario") Usuario usuario){
        usuarioService.save(usuario, false);
        return "redirect:/";
    }
    
    @PostMapping("/login")
    private String login(@ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult, Model model) {
        Usuario credencialesValidas = usuarioService.getUsuarioPorUsernameYPassword(usuario.getUsername(), usuario.getPassword());
        
        if (credencialesValidas!=null) {
            Usuario usuario2 = usuarioService.getUsuarioPorUsernameYPassword(usuario.getUsername(), usuario.getPassword());
             return modificarU(model, usuario2);
        } else {
            bindingResult.reject("error.login", "Usuario o contraseña incorrectos.");
            return "/usuario/inicioU";
        }
    }
    
    @PostMapping("/nuevo")
    public String nuevo(@ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult, Model model) {
        Usuario usuarioComprobar = usuarioService.getUsuarioPorUsernameOCorreo(usuario.getUsername(), usuario.getCorreo());
        if (usuarioComprobar==null) {
            usuarioService.save(usuario, true);
            return "redirect:/";
        } else {
            bindingResult.reject("error.crear", "Ya existe un usuario con este nombre, o correo ya vinculado a una cuenta.");

            return "/usuario/nuevoU";
        }
    }

}

   
