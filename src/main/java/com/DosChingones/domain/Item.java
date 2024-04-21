/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DosChingones.domain;

import com.DosChingones.service.ItemService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author XPC
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class Item extends Platillo {
    
    private Long IDitem;
    private String detalle;
    private int cantidad;

    public Item(Platillo p, Long ID) {
        this.IDitem = ID;
        super.setId_platillo(p.getId_platillo());
        super.setNombre(p.getNombre());
        super.setDescripcion(p.getDescripcion());
        super.setPrecio(p.getPrecio());
        super.setRutaImagen(p.getRutaImagen());
        super.setVegano(p.isVegano());
        super.setActivo(p.isActivo());
        this.cantidad = 0;
        this.detalle = "";
    }
    
    public Item(Platillo p) {
        super.setId_platillo(p.getId_platillo());
        super.setNombre(p.getNombre());
        super.setDescripcion(p.getDescripcion());
        super.setPrecio(p.getPrecio());
        super.setRutaImagen(p.getRutaImagen());
        super.setVegano(p.isVegano());
        super.setActivo(p.isActivo());
        this.cantidad = 0;
        this.detalle = "";
    }
    
    public Item(Long ID, int cantidad, String detalle){
        this.cantidad = cantidad;
        this.detalle = detalle;
        this.IDitem = ID;
    }
}
