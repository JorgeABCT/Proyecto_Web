/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DosChingones.service;

import com.DosChingones.domain.Imagen;

/**
 *
 * @author XPC
 */
public interface ImagenService {
    
    public Imagen getLogo(boolean activo);
    
    public void save(Imagen imagen);
    
    public void delete(Imagen imagen);
}
