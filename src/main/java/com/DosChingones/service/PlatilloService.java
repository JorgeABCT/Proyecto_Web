/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DosChingones.service;

/**
 *
 * @author XPC
 */
import com.DosChingones.domain.Platillo;
import java.util.List;

public interface PlatilloService {
    
    //Se obtiene un array List con todas las categorias de la tabla
    public List<Platillo> getPlatillos(boolean activo);
    
    //Se obtiene una Categoria segun el Id pasado por parametro
    public Platillo getPlatillo(Platillo platillo);
    
    public Platillo getPlatilloPorNombre(String nombre);
            
    //Se actualiza una categoria o se inserta una nueva... (Si no hay id es un insert)
    public void save(Platillo platillo);
    
    //Se elimina una categoria segun el id pasado
    
    public void delete(Platillo platillo);
}
