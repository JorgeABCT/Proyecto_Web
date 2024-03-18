/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DosChingones.service;

/**
 *
 * @author XPC
 */
import com.DosChingones.domain.Bebida;
import java.util.List;

public interface BebidaService {
    
    //Se obtiene un array List con todas las categorias de la tabla
    public List<Bebida> getBebidas(boolean activo);
    
    //Se obtiene una Categoria segun el Id pasado por parametro
    public Bebida getBebida(Bebida bebida);
            
    //Se actualiza una categoria o se inserta una nueva... (Si no hay id es un insert)
    public void save(Bebida bebida);
    
    //Se elimina una categoria segun el id pasado
    
    public void delete(Bebida bebida);
}
