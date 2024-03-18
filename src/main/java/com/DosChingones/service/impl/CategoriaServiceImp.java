/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DosChingones.service.impl;

/**
 *
 * @author XPC
 */
import com.DosChingones.dao.CategoriaDao;
import com.DosChingones.domain.Categoria;
import com.DosChingones.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaServiceImp implements CategoriaService{

    
    @Autowired
    private CategoriaDao categoriaDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Categoria> getCategorias(boolean activo) {  
        var categorias = categoriaDao.findAll();
        //Si son solo activas (activo = true) debo filtrar las inactivas...
        if (activo) {
            categorias.removeIf(e -> !e.getActivo());
        }
        return categorias;
    }
    
    //Se obtiene una Categoria segun el Id pasado por parametro
    @Transactional(readOnly=true)
    public Categoria getCategoria(Categoria categoria) {
        return categoriaDao.findById(categoria.getIdCategoria()).orElse(null);  
    }
            
    //Se actualiza una categoria o se inserta una nueva... (Si no hay id es un insert)
    public void save(Categoria categoria) {
        categoriaDao.save(categoria);
    }
    
    //Se elimina una categoria segun el id pasado
    public void delete(Categoria categoria) {
        categoriaDao.delete(categoria);
    }
}