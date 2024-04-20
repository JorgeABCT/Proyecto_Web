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
            item.setIDitem(Long.valueOf(this.gets().size()+1));
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

    @Override
    public void facturar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private Long generarProximoId() {
        // Lógica para generar un nuevo ID único, por ejemplo, puedes utilizar un contador
        // O puedes considerar usar una estrategia más robusta, como UUID.randomUUID()
        // En este ejemplo, simplemente devuelvo el tamaño actual del carrito más uno
        return Long.valueOf(this.gets().size() + 1);
    }
}
