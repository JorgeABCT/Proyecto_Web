package com.DosChingones.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name="rol")
public class Rol implements Serializable{

    public Rol(String nombre, Long idUsuario) {
        this.nombre = nombre;
        this.idUsuario = idUsuario;
    }
    
    private static final Long serialVersionUID= 1L;

    public Rol() {
    }
    
    public Rol(Long idRol, String nombre, Long idUsuario) {
        this.idRol = idRol;
        this.nombre = nombre;
        this.idUsuario = idUsuario;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id_rol")
    private Long idRol;
    private String nombre;
    @Column (name = "id_usuario")
    private Long idUsuario;
    
    public void setNombre(String role_user) {
        this.nombre = role_user;
    }

    public void setIdUsuario(Long id_usuario) {
        this.idUsuario = id_usuario;
    }
}
