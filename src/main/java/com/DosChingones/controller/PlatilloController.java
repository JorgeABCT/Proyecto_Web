/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DosChingones.controller;

import com.DosChingones.domain.Item;
import com.DosChingones.domain.Platillo;
import com.DosChingones.service.ItemService;
import com.DosChingones.service.PlatilloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author XPC
 */
@Controller
@RequestMapping("/platillo")
public class PlatilloController {

    @Autowired
    private ItemService itemService;
    
    @Autowired
    private PlatilloService platilloService;

    @GetMapping("{id_platillo}")
    public String verPlatillo(Model model, Platillo platillo) {
        var platilloVer = platilloService.getPlatillo(platillo);
        model.addAttribute("platillo", platilloVer);
        if (platilloVer.getCategoria().getNombre().equals("Bebidas")) {
            return "/carrito/verBebida";
        }
        String nombre = "Dos Chingones - ";
        nombre += platilloVer.getNombre();
        model.addAttribute("title", nombre);
        return "/carrito/verPlatillo";
    }
}
