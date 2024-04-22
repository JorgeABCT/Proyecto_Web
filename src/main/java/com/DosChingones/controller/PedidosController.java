/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DosChingones.controller;

import com.DosChingones.domain.Detalle;
import com.DosChingones.domain.Factura;
import com.DosChingones.domain.Usuario;
import com.DosChingones.service.DetalleService;
import com.DosChingones.service.FacturaService;
import com.DosChingones.service.UsuarioService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author XPC
 */
@Controller
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private DetalleService detalleService;

    @GetMapping("/listado")
    public String verListado(Model model) {
        String nombre = "Dos Chingones";
        model.addAttribute("title", nombre);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Usuario usuario = usuarioService.getUsuarioPorUsername(username);

        var listaPedidos = facturaService.getFacturasDeUserActivas(usuario.getId_usuario());

        Map<Long, List<Detalle>> detallesPorFactura = new HashMap<>();

        for (Factura factura : listaPedidos) {
            List<Detalle> detalles = detalleService.getDetallesDeFactura(factura.getIdFactura());
            detallesPorFactura.put(factura.getIdFactura(), detalles);
        }

        model.addAttribute("detalles", detallesPorFactura);
        model.addAttribute("totalItems", listaPedidos.size());
        model.addAttribute("items", listaPedidos);
        
        var listaPedidosT = facturaService.getFacturasDeUserTerminadas(usuario.getId_usuario());

        Map<Long, List<Detalle>> detallesPorFacturaT = new HashMap<>();

        for (Factura factura : listaPedidosT) {
            List<Detalle> detalles = detalleService.getDetallesDeFactura(factura.getIdFactura());
            detallesPorFacturaT.put(factura.getIdFactura(), detalles);
        }

        model.addAttribute("detallesT", detallesPorFacturaT);
        model.addAttribute("itemsT", listaPedidosT);

        return "/pedidos/listado";
    }
}
