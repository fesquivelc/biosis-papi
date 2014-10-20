/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.Vista;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface VistaFacadeLocal {

    void create(Vista vista);

    void edit(Vista vista);

    void remove(Vista vista);

    Vista find(Object id);

    List<Vista> findAll();

    List<Vista> findRange(int[] range);
    
    List<Vista> search(String namedQuery);
    
    List<Vista> search(String namedQuery, Map<String, Object> parametros);
    
    List<Vista> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();
    
}
