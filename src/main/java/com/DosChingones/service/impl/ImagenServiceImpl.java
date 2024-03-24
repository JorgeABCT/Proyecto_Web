/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DosChingones.service.impl;

import com.DosChingones.dao.ImagenDao;
import com.DosChingones.domain.Imagen;
import com.DosChingones.service.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author XPC
 */
@Service
public class ImagenServiceImpl implements ImagenService{
    
    @Autowired
    private ImagenDao imagenDao;

    @Override
    public void save(Imagen imagen) {
        imagenDao.save(imagen);
    }

    @Override
    public void delete(Imagen imagen) {
        imagenDao.delete(imagen);
    }

    @Override
    public Imagen getLogo(boolean activo) {
        return imagenDao.findByActivo(activo);
    }
}
