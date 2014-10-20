/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.CambioTurno;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface CambioTurnoFacadeLocal {

    void create(CambioTurno cambioTurno);

    void edit(CambioTurno cambioTurno);

    void remove(CambioTurno cambioTurno);

    CambioTurno find(Object id);

    List<CambioTurno> findAll();

    List<CambioTurno> findRange(int[] range);
    
    List<CambioTurno> search(String namedQuery);
    
    List<CambioTurno> search(String namedQuery, Map<String, Object> parametros);
    
    List<CambioTurno> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();
    
}
