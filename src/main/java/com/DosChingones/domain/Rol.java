package com.DosChingones.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name="rol")
public class Rol implements Serializable{
    
    private static final Long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id_rol")
    private Long idRol;
    private String nombre;
    
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
    
    public void setNombre(String role_user) {
        this.nombre = role_user;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    public Rol() {
    }
    
    public Rol(Long idRol, String nombre, Usuario usuario) {
        this.idRol = idRol;
        this.nombre = nombre;
        this.usuario = usuario;
    }
    
    public Rol(String nombre, Usuario usuario) {
        this.nombre = nombre;
        this.usuario = usuario;
    }
}
