/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.RegistroAsistencia;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface RegistroAsistenciaFacadeLocal {

    void create(RegistroAsistencia registroAsistencia);

    void edit(RegistroAsistencia registroAsistencia);

    void remove(RegistroAsistencia registroAsistencia);

    RegistroAsistencia find(Object id);

    List<RegistroAsistencia> findAll();

    List<RegistroAsistencia> findRange(int[] range);
    
    List<RegistroAsistencia> search(String namedQuery);
    
    List<RegistroAsistencia> search(String namedQuery, Map<String, Object> parametros);
    
    List<RegistroAsistencia> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();
    
}
