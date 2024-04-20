/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DosChingones.service.impl;

import com.DosChingones.dao.DetalleDao;
import com.DosChingones.dao.FacturaDao;
import com.DosChingones.dao.PlatilloDao;
import com.DosChingones.domain.Detalle;
import com.DosChingones.domain.Item;
import com.DosChingones.domain.Usuario;
import com.DosChingones.domain.Factura;
import com.DosChingones.domain.Platillo;
import com.DosChingones.service.ItemService;
import com.DosChingones.service.PlatilloService;
import com.DosChingones.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author Laboratorios
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Override
    public Item getNoEsp(Item item) {
        for (Item i : listaItems) {
            if (i.getId_platillo() == item.getId_platillo() && i.getDetalle().equals(item.getDetalle())) {
                return i;
            }
        }
        return null;
    }

    @Override
    public List<Item> gets() {
        return listaItems;
    }

    //Busca en array, si lo encuentra lo devuelve, sino retorna null
    @Override
    public Item get(Item item) {
        for (Item i : listaItems) {
            if (i.getIDitem() == item.getIDitem()) {
                return i;
            }
        }
        return null;
    }

    @Override
    public void save(Item item) {
        var existe = false;
        for (Item i : listaItems) {
            if (this.get(item) == i) {
                i.setCantidad(i.getCantidad() + 1);
                existe = true;
                break;
            }
        }
        if (!existe) {
            item.setCantidad(1);
            item.setIDitem(Long.valueOf(this.gets().size() + 1));
            listaItems.add(item);
        }
    }

    @Override
    public void delete(Item item) {
        var posicion = -1;
        var existe = false;
        for (Item i : listaItems) {
            posicion++;
            if (this.get(item) == i) {
                existe = true;
                break;
            }
        }
        if (existe) {
            listaItems.remove(posicion);
        }
    }

    @Override
    public void update(Item item, String detalle, int Cantidad) {
        for (Item i : listaItems) {
            if (this.get(item) == i) {
                i.setDetalle(detalle);
                i.setCantidad(Cantidad);
                break;
            }
        }
    }

    @Autowired
    private UsuarioService uuarioService;
    @Autowired
    private FacturaDao facturaDao;
    @Autowired
    private DetalleDao detalleDao;
    @Autowired
    private PlatilloDao platilloDao;
    @Autowired
    private PlatilloService platilloService;

    public void facturar() {
        System.out.println("Facturando");

        //Se obtiene el usuario autenticado
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }

        if (username.isBlank()) {
            return;
        }
        Usuario uuario = uuarioService.getUsuarioPorUsername(username);

        if (uuario == null) {
            return;
        }

        Factura factura = new Factura(uuario);
        facturaDao.save(factura);

        double total = 0;
        for (Item i : listaItems) {
            System.out.println("Producto: " + i.getNombre()
                    + " Cantidad: " + i.getCantidad()
                    + " Total: " + i.getPrecio() * i.getCantidad());

            Platillo getPlatillo = platilloService.getPlatilloPorID(i.getId_platillo());
            Detalle venta = new Detalle(factura, getPlatillo, i.getPrecio(), i.getCantidad());
            detalleDao.save(venta);
            total += i.getPrecio() * i.getCantidad();
        }
        factura.setTotal(total);
        facturaDao.save(factura);
        listaItems.clear();
    }
}
