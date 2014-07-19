/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.NivelEducativo;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface NivelEducativoFacadeLocal {

    void create(NivelEducativo nivelEducativo);

    void edit(NivelEducativo nivelEducativo);

    void remove(NivelEducativo nivelEducativo);

    NivelEducativo find(Object id);

    List<NivelEducativo> findAll();

    List<NivelEducativo> findRange(int[] range);
    
    List<NivelEducativo> search(String namedQuery);
    
    List<NivelEducativo> search(String namedQuery, Map<String, Object> parametros);
    
    List<NivelEducativo> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();
    
}
