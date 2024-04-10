/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DosChingones.controller;

import com.DosChingones.domain.Bebida;
import com.DosChingones.domain.Item;
import com.DosChingones.domain.Platillo;
import com.DosChingones.domain.Usuario;
import com.DosChingones.service.BebidaService;
import com.DosChingones.service.CategoriaService;
import com.DosChingones.service.ImagenService;
import com.DosChingones.service.ItemService;
import com.DosChingones.service.PlatilloService;
import com.DosChingones.service.UsuarioService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author XPC
 */
@Controller
public class IndexController {

    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ImagenService imagenService;

    @Autowired
    private PlatilloService platilloService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private BebidaService bebidaService;
    
    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    private String listado(Model model) {
        var platillos = platilloService.getPlatillos(true);
        var categorias = categoriaService.getCategorias(true);
        List<Platillo> lista = new ArrayList<Platillo>();

        for (int i = 1; i < categorias.size(); i++) {
            for (int j = 0; j < platillos.size(); j++) {
                Platillo plato = platillos.get(j);
                if (plato.getCategoria().getIdCategoria() == i) {
                    lista.add(platillos.get(j));
                }
            }
        }

        var imagen = imagenService.getLogo(true);
        model.addAttribute("categorias", categorias);
        model.addAttribute("platillos", lista);
        model.addAttribute("imagen", imagen);
        model.addAttribute("title", "Dos Chingones - Inicio");
        return "/index";
    }

    @GetMapping("/menu_regular")
    private String Menu_regular(Model model) {
        var platillos = platilloService.getPlatillos(true);
        List<Platillo> lista = new ArrayList<Platillo>();

        for (int j = 0; j < platillos.size(); j++) {
            Platillo plato = platillos.get(j);
            if (!plato.isVegano() && plato.isActivo() && !plato.getCategoria().getNombre().equals("Bebidas")) {
                lista.add(plato);
            }
        }
        model.addAttribute("platillos", lista);
        String nombre = "Dos Chingones - Menu Regular";
        model.addAttribute("title", nombre);
        return "/menu/menu";
    }

    @GetMapping("/menu_vegano")
    private String Menu_vegano(Model model) {
        var platillos = platilloService.getPlatillos(true);
        List<Platillo> lista = new ArrayList<Platillo>();

        for (int j = 0; j < platillos.size(); j++) {
            Platillo plato = platillos.get(j);
            if (plato.isVegano() && plato.isActivo() && !plato.getCategoria().getNombre().equals("Bebidas")) {
                lista.add(plato);
            }
        }
        model.addAttribute("platillos", lista);
        String nombre = "Dos Chingones - Menu Vegano";
        model.addAttribute("title", nombre);
        return "/menu/menu";
    }

    @GetMapping("/bebidas")
    private String bebidas(Model model) {
        var platillos = platilloService.getPlatillos(true);
        List<Platillo> lista = new ArrayList<Platillo>();

        for (int j = 0; j < platillos.size(); j++) {
            Platillo plato = platillos.get(j);
            if (plato.isActivo() && plato.getCategoria().getNombre().equals("Bebidas")) {
                lista.add(plato);
            }
        }
        model.addAttribute("platillos", lista);
        String nombre = "Dos Chingones - Bebidas";
        model.addAttribute("title", nombre);
        return "/menu/bebidas";
    }
    
    @GetMapping("/login")
    public String Login(Model model, Usuario usuario){
        model.addAttribute("usuario", usuario);
        String nombre = "Dos Chingones - Inicio de sesión";
        model.addAttribute("title", nombre);
        return "/usuario/inicioU";
    }

    @PostMapping("/login")
    public String confirmarLogin(Model model, Usuario usuario) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            Usuario usuarioU = usuarioService.getUsuarioPorUsername(userDetails.getUsername());
            if (usuarioU.isActivo()) {
                return "redirect:/";
            }
            return "redirect:/login";
        } else {
            return "redirect:/login";
        }
    }
}
