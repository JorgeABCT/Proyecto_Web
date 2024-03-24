/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DosChingones.dao;

import com.DosChingones.domain.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author XPC
 */
public interface ImagenDao extends JpaRepository<Imagen, Long> {
    
    Imagen findByActivo(boolean activo);
}
