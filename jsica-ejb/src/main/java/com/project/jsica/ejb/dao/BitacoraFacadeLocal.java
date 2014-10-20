/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.Bitacora;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author juvitec
 */
@Local
public interface BitacoraFacadeLocal {

    void create(Bitacora bitacora);

    void edit(Bitacora bitacora);

    void remove(Bitacora bitacora);

    Bitacora find(Object id);

    List<Bitacora> findAll();

    List<Bitacora> findRange(int[] range);
    List<Bitacora> search(String namedQuery);
    
    List<Bitacora> search(String namedQuery, Map<String, Object> parametros);
    
    List<Bitacora> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();
    
}
