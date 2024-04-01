/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DosChingones.service;

import com.DosChingones.domain.Rol;
import java.util.List;

/**
 *
 * @author XPC
 */
public interface RolService {
    public List<Rol> getRoles();
    
    public void save(Rol rol);
    
    public void delete(Rol rol);
}
