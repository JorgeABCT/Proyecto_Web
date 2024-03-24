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
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name="usuario")
public class Usuario implements Serializable{
    
    private static final Long serialVersionUID= 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id_usuario")
    private Long id_usuario;
    private String username;
    private String password;
    private String nombre;
    private String apellidos;
    private String correo;
    private boolean activo;
    
    @OneToMany
    @JoinColumn(name="id_usuario")
    private List<Rol> roles;
}
