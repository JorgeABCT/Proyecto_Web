/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DosChingones.controller;

import com.DosChingones.domain.Categoria;
import com.DosChingones.domain.Platillo;
import com.DosChingones.service.BebidaService;
import com.DosChingones.service.CategoriaService;
import com.DosChingones.service.FirebaseStorageService;
import com.DosChingones.service.ImagenService;
import com.DosChingones.service.PlatilloService;
import com.DosChingones.service.RolService;
import com.DosChingones.service.UsuarioService;
import com.google.firebase.internal.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author XPC
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

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
    private RolService rolService;
    
    @Autowired
    private FirebaseStorageService firebaseService;

    @GetMapping("/listadoCategorias")
    private String listadoCategorias(Model model, Categoria categoria) {
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        return "/admin/listadoCategoria";
    }

    @GetMapping("/categoria/eliminar/{idCategoria}")
    public String eliminarCategoria(Categoria categoria) {
        categoriaService.delete(categoria);
        return "redirect:/admin/listadoCategorias";
    }

    @PostMapping("/categoria/guardar")
    public String guardarCategoria(Categoria categoria) {
        categoriaService.save(categoria);
        return "redirect:/admin/listadoCategorias";
    }

    @GetMapping("/categoria/modificar/{idCategoria}")
    public String modificarCategoria(Categoria categoria, Model model) {
        categoria = categoriaService.getCategoria(categoria);
        model.addAttribute("categoria", categoria);
        return "/admin/modificarCategoria";
    }

    @GetMapping("/listadoPlatillos")
    private String listadoPlatillos(Model model, Platillo platillo) {
        var platillos = platilloService.getPlatillos(false);
        model.addAttribute("platillos", platillos);
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        return "/admin/listadoPlatillos";
    }

    @GetMapping("/platillo/eliminar/{id_platillo}")
    public String eliminarPlatillo(Platillo platillo) {
        platilloService.delete(platillo);
        return "redirect:/admin/listadoCategorias";
    }

    @PostMapping("/platillo/guardar")
    public String guardarPlatillo(Platillo platillo, @RequestParam("imagenFile") MultipartFile imagenFile) {
        
        if(!imagenFile.isEmpty()) {
            platilloService.save(platillo);
            String ruta=firebaseService.cargaImagen(imagenFile, "producto", platillo.getId_platillo());
            platillo.setRutaImagen(ruta);
        }
        platilloService.save(platillo);
        return "redirect:/admin/listadoPlatillos";
    }
    

    @GetMapping("/platillo/modificar/{id_platillo}")
    public String modificarPlatillo(Platillo platillo, Model model) {
        var categorias = categoriaService.getCategorias(false);
        platillo = platilloService.getPlatillo(platillo);
        model.addAttribute("platillo", platillo);
        model.addAttribute("categorias", categorias);
        return "/admin/modificarPlatillo";
    }
}
