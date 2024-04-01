/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DosChingones.service.impl;

import com.DosChingones.dao.RolDao;
import com.DosChingones.domain.Rol;
import com.DosChingones.service.RolService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author XPC
 */
@Service
public class RolServiceImpl implements RolService{

    @Autowired
    private RolDao rolDao;
    
    @Override
    public void save(Rol rol) {
        rolDao.save(rol);
    }

    @Override
    public void delete(Rol rol) {
        rolDao.delete(rol);
    }

    @Override
    public List<Rol> getRoles() {
        return rolDao.findAll();
    }
    
}
