/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.Rol;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface RolFacadeLocal {

    void create(Rol rol);

    void edit(Rol rol);

    void remove(Rol rol);

    Rol find(Object id);

    List<Rol> findAll();

    List<Rol> findRange(int[] range);
    
    List<Rol> search(String namedQuery);
    
    List<Rol> search(String namedQuery, Map<String, Object> parametros);
    
    List<Rol> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();
    
}
