/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DosChingones.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
    @Column(name = "id_detalle")
    private Long idDetalle;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_factura")
    private Factura factura;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_platillo")
    private Platillo platillo;
    
    @Column(name = "precio")
    private Double precio;
    
    @Column(name = "cantidad")
    private int Cantidad; // Cambiado a minúscula, para seguir convención
    
    public Detalle(){
        
    }

    public Detalle(Factura factura, Platillo platillo, Double precio, int Cantidad) {
        this.factura = factura;
        this.platillo = platillo;
        this.precio = precio;
        this.Cantidad = Cantidad;
    }
    
}
