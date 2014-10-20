/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.Sucursal;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface SucursalFacadeLocal {

    void create(Sucursal sucursal);

    void edit(Sucursal sucursal);

    void remove(Sucursal sucursal);

    Sucursal find(Object id);

    List<Sucursal> findAll();

    List<Sucursal> findRange(int[] range);
    
    List<Sucursal> search(String namedQuery);
    
    List<Sucursal> search(String namedQuery, Map<String, Object> parametros);
    
    List<Sucursal> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();
    
}
