/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.Contrato;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface ContratoFacadeLocal {

    void create(Contrato contrato);

    void edit(Contrato contrato);

    void remove(Contrato contrato);

    Contrato find(Object id);

    List<Contrato> findAll();

    List<Contrato> findRange(int[] range);
    
    List<Contrato> search(String namedQuery);
    
    List<Contrato> search(String namedQuery, Map<String, Object> parametros);
    
    List<Contrato> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();
    
}
