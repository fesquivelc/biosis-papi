/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.TipoEmpleado;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface TipoEmpleadoFacadeLocal {

    void create(TipoEmpleado tipoEmpleado);

    void edit(TipoEmpleado tipoEmpleado);

    void remove(TipoEmpleado tipoEmpleado);

    TipoEmpleado find(Object id);

    List<TipoEmpleado> findAll();

    List<TipoEmpleado> findRange(int[] range);
    
    List<TipoEmpleado> search(String namedQuery);
    
    List<TipoEmpleado> search(String namedQuery, Map<String, Object> parametros);
    
    List<TipoEmpleado> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();
    
}
