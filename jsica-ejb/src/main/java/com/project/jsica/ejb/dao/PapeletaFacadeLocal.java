/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.Papeleta;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface PapeletaFacadeLocal {

    void create(Papeleta papeleta);

    void edit(Papeleta papeleta);

    void remove(Papeleta papeleta);

    Papeleta find(Object id);

    List<Papeleta> findAll();

    List<Papeleta> findRange(int[] range);
    
    List<Papeleta> search(String namedQuery);
    
    List<Papeleta> search(String namedQuery, Map<String, Object> parametros);
    
    List<Papeleta> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();
    
}
