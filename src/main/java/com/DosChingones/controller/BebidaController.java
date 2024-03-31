/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DosChingones.controller;

import com.DosChingones.domain.Bebida;
import com.DosChingones.domain.Platillo;
import com.DosChingones.service.BebidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author XPC
 */
@Controller
@RequestMapping("/bebida")
public class BebidaController {
    
    @Autowired
    private BebidaService bebidaService;
    
    @GetMapping("{id_bebida}")
    public String verPlatillo(Model model, Bebida bebida){
        var BebidaVer = bebidaService.getBebida(bebida);
        model.addAttribute("bebida", BebidaVer);
        return "/carrito/verBebida";
    }
}
