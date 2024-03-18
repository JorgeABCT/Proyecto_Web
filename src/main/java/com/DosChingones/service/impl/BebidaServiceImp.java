/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DosChingones.service.impl;

/**
 *
 * @author XPC
 */
import com.DosChingones.dao.BebidaDao;
import com.DosChingones.domain.Bebida;
import com.DosChingones.service.BebidaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BebidaServiceImp implements BebidaService{

    
    @Autowired
    private BebidaDao bebidaDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Bebida> getBebidas(boolean activo) {  
        var bebidas = bebidaDao.findAll();
        //Si son solo activas (activo = true) debo filtrar las inactivas...
        if (activo) {
            bebidas.removeIf(e -> !e.isActivo());
        }
        return bebidas;
    }
    
    //Se obtiene una Bebida segun el Id pasado por parametro
    @Transactional(readOnly=true)
    public Bebida getBebida(Bebida bebida) {
        return bebidaDao.findById(bebida.getId_bebida()).orElse(null);  
    }
            
    //Se actualiza una bebida o se inserta una nueva... (Si no hay id es un insert)
    public void save(Bebida bebida) {
        bebidaDao.save(bebida);
    }
    
    //Se elimina una bebida segun el id pasado
    public void delete(Bebida bebida) {
        bebidaDao.delete(bebida);
    }
}
