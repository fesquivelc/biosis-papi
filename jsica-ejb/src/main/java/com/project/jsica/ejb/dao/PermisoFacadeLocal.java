/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.Permiso;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface PermisoFacadeLocal {

    void create(Permiso permiso);

    void edit(Permiso permiso);

    void remove(Permiso permiso);

    Permiso find(Object id);

    List<Permiso> findAll();

    List<Permiso> findRange(int[] range);
    
    List<Permiso> search(String namedQuery);
    
    List<Permiso> search(String namedQuery, Map<String, Object> parametros);
    
    List<Permiso> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();
    
}
