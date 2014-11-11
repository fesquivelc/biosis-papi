/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.RegistroAsistencia2;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author fesquivelc
 */
@Local
public interface RegistroAsistenciaFinalFacadeLocal {

    void create(RegistroAsistencia2 registroAsistencia);

    void edit(RegistroAsistencia2 registroAsistencia);

    void remove(RegistroAsistencia2 registroAsistencia);

    RegistroAsistencia2 find(Object id);

    List<RegistroAsistencia2> findAll();

    List<RegistroAsistencia2> findRange(int[] range);
    
    List<RegistroAsistencia2> search(String namedQuery);
    
    List<RegistroAsistencia2> search(String namedQuery, Map<String, Object> parametros);
    
    List<RegistroAsistencia2> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);
    
    void analizar();
    
}
