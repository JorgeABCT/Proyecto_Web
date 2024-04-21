package com.DosChingones.service.impl;

import com.DosChingones.dao.DetalleDao;
import com.DosChingones.domain.Detalle;
import com.DosChingones.service.DetalleService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author XPC
 */
@Service
public class DetalleServiceImpl implements DetalleService {

    @Autowired
    private DetalleDao detalleDao;

    @Override
    public List<Detalle> getDetalles() {
        return detalleDao.findAll();
    }

    @Override
    public Detalle getDetalle(Detalle detalle) {
        return detalleDao.findById(detalle.getIdDetalle()).orElse(null);
    }

    @Override
    public List<Detalle> getDetallesDeFactura(Long ID) {
        var detalles = detalleDao.findAll();

        List<Detalle> encontrarDetalles = new ArrayList<Detalle>();
        for (int i = 0; i < detalles.size(); i++) {
            if (detalles.get(i).getFactura().getIdFactura() == ID) {
                encontrarDetalles.add(detalles.get(i));
            }
        }
        return encontrarDetalles;
    }

    @Override
    public Detalle getDetallePorID(Long id) {
        var detalles = detalleDao.findAll();

        Detalle encontrarDetalle = null;
        for (int i = 0; i < detalles.size(); i++) {
            if (detalles.get(i).getFactura().getIdFactura() == id) {
                encontrarDetalle = detalles.get(i);
            }
        }
        return encontrarDetalle;
    }

    @Override
    public void save(Detalle detalle) {
        detalleDao.save(detalle);
    }

    @Override
    public void delete(Detalle detalle) {
        detalleDao.delete(detalle);
    }

}
