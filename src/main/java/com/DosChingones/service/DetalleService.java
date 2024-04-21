/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DosChingones.service;

import com.DosChingones.domain.Detalle;
import java.util.List;

/**
 *
 * @author XPC
 */
public interface DetalleService {
    
    //Se obtiene un array List con todas las categorias de la tabla
    public List<Detalle> getDetalles();
    
    //Se obtiene una Categoria segun el Id pasado por parametro
    public Detalle getDetalle(Detalle detalle);
    
    public List<Detalle> getDetallesDeFactura(Long ID);
    
    public Detalle getDetallePorID(Long id);
            
    //Se actualiza una categoria o se inserta una nueva... (Si no hay id es un insert)
    public void save(Detalle detalle);
    
    //Se elimina una categoria segun el id pasado
    
    public void delete(Detalle detalle);
}
