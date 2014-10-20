/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.Logs;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface LogsFacadeLocal {

    void create(Logs logs);

    void edit(Logs logs);

    void remove(Logs logs);

    Logs find(Object id);

    List<Logs> findAll();

    List<Logs> findRange(int[] range);
    
    List<Logs> search(String namedQuery);
    
    List<Logs> search(String namedQuery, Map<String, Object> parametros);
    
    List<Logs> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();
    
}
