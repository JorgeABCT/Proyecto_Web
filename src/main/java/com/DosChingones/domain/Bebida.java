/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DosChingones.domain;

/**
 *
 * @author XPC
 */
import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name="bebidas")
public class Bebida implements Serializable{
    
    private static final Long serialVersionUID= 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id_bebida")
    private Long id_bebida;
    private String nombre;
    private String descripcion;
    private double precio;
    private String rutaImagen;
    private boolean naturales;
    private boolean alcohol;
    private boolean activo;
    
}
