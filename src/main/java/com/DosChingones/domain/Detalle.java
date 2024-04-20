/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DosChingones.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author XPC
 */

@Data
@Entity
@Table(name = "detalle_factura")
public class Detalle implements Serializable{
    
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long idDetalle;
    
    @JoinColumn(name = "id_factura")
    private Factura factura;
    
    @JoinColumn(name = "id_platillo")
    private Platillo platillo;
    
    private Long precio;
    private int Cantidad; /*Maximo 5*/

    public Detalle(Factura factura, Platillo platillo, Long precio, int Cantidad) {
        this.factura = factura;
        this.platillo = platillo;
        this.precio = precio;
        this.Cantidad = Cantidad;
    }
    
}
