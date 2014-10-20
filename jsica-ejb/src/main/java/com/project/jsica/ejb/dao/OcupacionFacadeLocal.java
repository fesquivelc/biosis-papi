/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.Ocupacion;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface OcupacionFacadeLocal {

    void create(Ocupacion ocupacion);

    void edit(Ocupacion ocupacion);

    void remove(Ocupacion ocupacion);

    Ocupacion find(Object id);

    List<Ocupacion> findAll();

    List<Ocupacion> findRange(int[] range);
    
    List<Ocupacion> search(String namedQuery);
    
    List<Ocupacion> search(String namedQuery, Map<String, Object> parametros);
    
    List<Ocupacion> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();
    
}
