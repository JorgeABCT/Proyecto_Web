/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DosChingones.service.impl;

import com.DosChingones.dao.DetalleDao;
import com.DosChingones.dao.FacturaDao;
import com.DosChingones.domain.Factura;
import com.DosChingones.service.DetalleService;
import com.DosChingones.service.FacturaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author XPC
 */
@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaDao facturaDao;

    @Autowired
    private DetalleService detalleService;

    @Override
    public List<Factura> getFacturas() {
        return facturaDao.findAll();
    }

    @Override
    public List<Factura> getFacturasActivas() {
        var facturas = facturaDao.findAll();
        //Si son solo activas (activo = true) debo filtrar las inactivas...
        
        facturas.removeIf(e -> e.getEstado() == 3);
        return facturas;
    }

    @Override
    public List<Factura> getFacturasTerminadas() {
        var facturas = facturaDao.findAll();
        //Si son solo activas (activo = true) debo filtrar las inactivas...
        
        facturas.removeIf(e -> e.getEstado() != 3);
        return facturas;
    }

    @Override
    public Factura getFactura(Factura factura) {
        return facturaDao.findById(factura.getIdFactura()).orElse(null);
    }

    @Override
    public List<Factura> getFacturasDeUser(Long ID) {
        var facturas = facturaDao.findAll();

        List<Factura> encontrarFactura = new ArrayList<Factura>();
        for (int i = 0; i < facturas.size(); i++) {
            if (facturas.get(i).getUsuario().getId_usuario() == ID) {
                encontrarFactura.add(facturas.get(i));
            }
        }

        return encontrarFactura;
    }
    
    @Override
    public List<Factura> getFacturasDeUserActivas(Long ID) {
        var facturas = facturaDao.findAll();

        List<Factura> encontrarFactura = new ArrayList<Factura>();
        for (int i = 0; i < facturas.size(); i++) {
            if (facturas.get(i).getUsuario().getId_usuario() == ID) {
                encontrarFactura.add(facturas.get(i));
            }
        }
        encontrarFactura.removeIf(e -> e.getEstado() == 3);
        return encontrarFactura;
    }

    @Override
    public List<Factura> getFacturasDeUserTerminadas(Long ID) {
        var facturas = facturaDao.findAll();

        List<Factura> encontrarFactura = new ArrayList<Factura>();
        for (int i = 0; i < facturas.size(); i++) {
            if (facturas.get(i).getUsuario().getId_usuario() == ID) {
                encontrarFactura.add(facturas.get(i));
            }
        }
        encontrarFactura.removeIf(e -> e.getEstado() != 3);
        return encontrarFactura;
    }

    @Override
    public Factura getFacturaPorID(Long id) {
        var facturas = facturaDao.findAll();

        Factura encontrarFactura = null;
        for (int i = 0; i < facturas.size(); i++) {
            if (facturas.get(i).getIdFactura() == id) {
                encontrarFactura = facturas.get(i);
            }
        }

        return encontrarFactura;
    }

    @Override
    public void save(Factura factura) {
        facturaDao.save(factura);
    }

    @Override
    public void delete(Factura factura) {
        var listaDetalles = detalleService.getDetalles();
        for (int i = 0; i < listaDetalles.size(); i++) {
            if (Objects.equals(factura.getIdFactura(), listaDetalles.get(i).getFactura().getIdFactura())) {
                detalleService.delete(listaDetalles.get(i));
            }
        }
        facturaDao.delete(factura);
    }

}
