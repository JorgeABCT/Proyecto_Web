/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DosChingones.service.impl;


import com.DosChingones.domain.Item;
import com.DosChingones.service.ItemService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Laboratorios
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Override
    public List<Item> gets() {
        return listaItems;
    }

    //Busca en array, si lo encuentra lo devuelve, sino retorna null
    @Override
    public Item get(Item item) {
        for (Item i : listaItems) {
            if (i.getId_platillo() == item.getId_platillo() && i.getDetalle().equals(item.getDetalle())) {
                return i;
            }
        }
        return null;
    }

    @Override
    public void save(Item item) {
        var existe = false;
        for (Item i : listaItems) {
            if (i.getId_platillo() == item.getId_platillo()  && i.getDetalle().equals(item.getDetalle())) {
                i.setCantidad(i.getCantidad()+1);
                existe = true;
                break;
            }
        }
        if (!existe) {
            item.setCantidad(1);
            listaItems.add(item);
        }
    }

    @Override
    public void delete(Item item) {
        var posicion = -1;
        var existe = false;
        for (Item i : listaItems) {
            posicion++;
            if (i.getId_platillo() == item.getId_platillo()  && i.getDetalle().equals(item.getDetalle())) {
                existe = true;
                break;
            }
        }
        if (existe) {
            listaItems.remove(posicion);
        }
    }

    @Override
    public void update(Item item) {
        for (Item i : listaItems) {
            if (i.getId_platillo() == item.getId_platillo() && i.getDetalle().equals(item.getDetalle())) {
                i.setCantidad(item.getCantidad());
                break;
            }
        }
    }

    @Override
    public void facturar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
