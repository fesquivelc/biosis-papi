/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.GrupoHorario;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface GrupoHorarioFacadeLocal {

    void create(GrupoHorario grupoHorario);

    void edit(GrupoHorario grupoHorario);

    void remove(GrupoHorario grupoHorario);

    GrupoHorario find(Object id);

    List<GrupoHorario> findAll();
    
    List<GrupoHorario> search(String namedQuery);
    
    List<GrupoHorario> search(String namedQuery, Map<String, Object> parametros);
    
    List<GrupoHorario> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    List<GrupoHorario> findRange(int[] range);

    int count();
    
}
