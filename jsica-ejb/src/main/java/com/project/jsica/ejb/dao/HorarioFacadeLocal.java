/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.Horario;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface HorarioFacadeLocal {

    void create(Horario horario);

    void edit(Horario horario);

    void remove(Horario horario);

    Horario find(Object id);

    List<Horario> findAll();

    List<Horario> findRange(int[] range);
    
    List<Horario> search(String namedQuery);
    
    List<Horario> search(String namedQuery, Map<String, Object> parametros);
    
    List<Horario> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();
    
}
