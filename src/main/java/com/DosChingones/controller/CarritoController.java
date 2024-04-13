/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DosChingones.controller;

import com.DosChingones.domain.Item;
import com.DosChingones.domain.Platillo;
import com.DosChingones.service.ItemService;
import com.DosChingones.service.PlatilloService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author XPC
 */
@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private PlatilloService platilloService;

    /*@GetMapping("/agregar/{idPlatillo}")
    public ModelAndView agregarCarrito(Model model, Item item){
        return new ModelAndView("/carrito/fragmentos_C :: agregadoCarrito");
    }*/
    @GetMapping("/listado")
    public String listado(Model model) {
        var items = itemService.gets();
        model.addAttribute("items", items);

        var carritoTotalVenta = 0;
        var totalCarrito = 0;

        for (Item i : items) {
            totalCarrito += i.getCantidad();
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        model.addAttribute("carritoTotal", carritoTotalVenta);
        model.addAttribute("listaTotal", totalCarrito);
        String nombre = "Dos Chingones - Carrito";
        model.addAttribute("title", nombre);
        return "/carrito/listadoCarrito";
    }

    @GetMapping("/agregar/{id_platillo}")
    public ModelAndView agregarItem(Model model, @PathVariable("id_platillo") Long id_platillo) {
        System.out.println("Llego aca por lo menos");
        Platillo getPlatillo = new Platillo();
        getPlatillo.setId_platillo(id_platillo);
        Item item = new Item(getPlatillo);
        Item item2 = itemService.get(item);
        if (item2 == null) {//No existe el producto en el carrito
            Platillo platillo = platilloService.getPlatillo(item);
            item2 = new Item(platillo);
        }

        itemService.save(item2);

        var lista = itemService.gets();
        var totalCarrito = 0;
        var carritoTotalVenta = 0;

        for (Item i : lista) {
            totalCarrito += i.getCantidad();
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        model.addAttribute("listaItems", lista);
        model.addAttribute("listaTotal", totalCarrito);
        model.addAttribute("carritoTotal", carritoTotalVenta);

        return new ModelAndView("/menu/fragmentos_M :: overlayAgregadoCarrito");
    }

    @PostMapping("/agregarV/{id_platillo}")
    public String agregarAlCarrito(@PathVariable("id_platillo") Long idPlatillo,
            @RequestParam("CambiosPlatillo") String cambiosPlatillo,
            HttpServletRequest request, Model model) {
        /*System.out.println("ID del platillo: " + idPlatillo);
        System.out.println("Cambios en el platillo: " + cambiosPlatillo);*/
        Platillo getPlatillo = new Platillo();
        getPlatillo.setId_platillo(idPlatillo);
        Item item = new Item(getPlatillo);
        item.setDetalle(cambiosPlatillo);
        Item item2 = itemService.get(item);
        if (item2 == null) {//No existe el producto en el carrito
            Platillo platillo = platilloService.getPlatillo(item);
            item2 = new Item(platillo);
            item2.setDetalle(cambiosPlatillo);
        }

        itemService.save(item2);

        var lista = itemService.gets();
        var totalCarrito = 0;
        var carritoTotalVenta = 0;

        for (Item i : lista) {
            totalCarrito += i.getCantidad();
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        boolean mostrarModal = true;
        model.addAttribute("mostrarModal", mostrarModal);
        model.addAttribute("listaItems", lista);
        model.addAttribute("listaTotal", totalCarrito);
        model.addAttribute("carritoTotal", carritoTotalVenta);
        
        return "redirect/platillo/"+idPlatillo;
    }
}
