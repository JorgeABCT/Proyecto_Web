/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DosChingones.service.impl;

/**
 *
 * @author XPC
 */
import com.DosChingones.dao.PlatilloDao;
import com.DosChingones.domain.Platillo;
import com.DosChingones.service.PlatilloService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlatilloServiceImp implements PlatilloService{

    
    @Autowired
    private PlatilloDao platilloDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Platillo> getPlatillos(boolean activo) {  
        var platillos = platilloDao.findAll();
        //Si son solo activas (activo = true) debo filtrar las inactivas...
        if (activo) {
            platillos.removeIf(e -> !e.isActivo());
        }
        return platillos;
    }
    
    //Se obtiene una Platillo segun el Id pasado por parametro
    @Transactional(readOnly=true)
    public Platillo getPlatillo(Platillo platillo) {
        return platilloDao.findById(platillo.getId_platillo()).orElse(null);  
    }
            
    //Se actualiza una platillo o se inserta una nueva... (Si no hay id es un insert)
    public void save(Platillo platillo) {
        platilloDao.save(platillo);
    }
    
    //Se elimina una platillo segun el id pasado
    public void delete(Platillo platillo) {
        platilloDao.delete(platillo);
    }
}
