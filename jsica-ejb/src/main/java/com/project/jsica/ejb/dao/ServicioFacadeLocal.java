/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.Servicio;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface ServicioFacadeLocal {

    void create(Servicio servicio);

    void edit(Servicio servicio);

    void remove(Servicio servicio);

    Servicio find(Object id);

    List<Servicio> findAll();

    List<Servicio> findRange(int[] range);
    
    List<Servicio> search(String namedQuery);
    
    List<Servicio> search(String namedQuery, Map<String, Object> parametros);
    
    List<Servicio> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();
    
}
