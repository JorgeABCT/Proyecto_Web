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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author XPC
 */
@Data
@Entity
@Table(name = "factura")
public class Factura implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private Long idFactura;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private Date Fecha;
    private double total;
    private int estado;

    /*Estado de Factura
1. En preparacion
2. En camino
3. Entregado
4. Cancelado
     */

    public Factura(Usuario usuario) {
        this.usuario = usuario;
        this.Fecha = Calendar.getInstance().getTime();
        this.estado = 1; /*Inicia en preparacion*/
    }
    
    
}
