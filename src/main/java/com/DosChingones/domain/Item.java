/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DosChingones.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author XPC
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class Item extends Platillo {

    private String detalle;
    private int cantidad;

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
}
