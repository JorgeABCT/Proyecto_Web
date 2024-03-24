/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DosChingones.controller;

import com.DosChingones.domain.Bebida;
import com.DosChingones.domain.Platillo;
import com.DosChingones.service.BebidaService;
import com.DosChingones.service.CategoriaService;
import com.DosChingones.service.ImagenService;
import com.DosChingones.service.PlatilloService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author XPC
 */
@Controller
public class IndexController {
    
    @Autowired
    private ImagenService imagenService;

    @Autowired
    private PlatilloService platilloService;
    
    @Autowired
    private CategoriaService categoriaService;
    
    @Autowired
    private BebidaService bebidaService;

    @GetMapping("/")
    private String listado(Model model) {
        var platillos = platilloService.getPlatillos(true);
        var categorias = categoriaService.getCategorias(true);
        List<Platillo> lista = new ArrayList<Platillo>();
        
        for (int i = 1; i < categorias.size(); i++) {
            for (int j = 0; j < platillos.size(); j++) {
                Platillo plato = platillos.get(j);
                if (plato.getCategoria().getIdCategoria()==i) {
                    lista.add(platillos.get(j));
                }
            }
        }
        
        var imagen = imagenService.getLogo(true);
        model.addAttribute("categorias", categorias);
        model.addAttribute("platillos", lista);
        model.addAttribute("imagen", imagen);
        return "/index";
    }
    
    @GetMapping("/menu_regular")
    private String Menu_regular(Model model) {
        var platillos = platilloService.getPlatillos(true);
        List<Platillo> lista = new ArrayList<Platillo>();
        
            for (int j = 0; j < platillos.size(); j++) {
                Platillo plato = platillos.get(j);
                if(!plato.isVegano() && plato.isActivo()){
                    lista.add(plato);
                }
            }
        model.addAttribute("platillos", lista);
        return "/menu/menu_R";
    }
    
    @GetMapping("/menu_vegano")
    private String Menu_vegano(Model model) {
        var platillos = platilloService.getPlatillos(true);
        List<Platillo> lista = new ArrayList<Platillo>();
        
            for (int j = 0; j < platillos.size(); j++) {
                Platillo plato = platillos.get(j);
                if(plato.isVegano() && plato.isActivo()){
                    lista.add(plato);
                }
            }
        model.addAttribute("platillos", lista);
        return "/menu/menu_V";
    }
    
    @GetMapping("/bebidas")
    private String bebidas(Model model) {
        var bebidas = bebidaService.getBebidas(true);
        
        model.addAttribute("platillos", bebidas);
        return "/menu/bebidas";
    }
}
