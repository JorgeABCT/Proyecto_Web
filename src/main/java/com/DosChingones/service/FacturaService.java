/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DosChingones.service;

import com.DosChingones.domain.Factura;
import java.util.List;

/**
 *
 * @author XPC
 */
public interface FacturaService {
    
    //Se obtiene un array List con todas las categorias de la tabla
    public List<Factura> getFacturas();
    
    public List<Factura> getFacturasActivas();
    
    public List<Factura> getFacturasTerminadas();
    
    //Se obtiene una Categoria segun el Id pasado por parametro
    public Factura getFactura(Factura factura);
    
    public List<Factura> getFacturasDeUser(Long ID);
    
    public List<Factura> getFacturasDeUserActivas(Long ID);
    
    public List<Factura> getFacturasDeUserTerminadas(Long ID);
    
    public Factura getFacturaPorID(Long id);
            
    //Se actualiza una categoria o se inserta una nueva... (Si no hay id es un insert)
    public void save(Factura factura);
    
    //Se elimina una categoria segun el id pasado
    
    public void delete(Factura factura);
}
