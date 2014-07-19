/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.Vacacion;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface VacacionFacadeLocal {

    void create(Vacacion vacacion);

    void edit(Vacacion vacacion);

    void remove(Vacacion vacacion);

    Vacacion find(Object id);

    List<Vacacion> findAll();

    List<Vacacion> findRange(int[] range);
    
    List<Vacacion> search(String namedQuery);
    
    List<Vacacion> search(String namedQuery, Map<String, Object> parametros);
    
    List<Vacacion> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();
    
}
