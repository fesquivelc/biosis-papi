/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.DetalleContrato;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface DetalleContratoFacadeLocal {

    void create(DetalleContrato detalleContrato);

    void edit(DetalleContrato detalleContrato);

    void remove(DetalleContrato detalleContrato);

    DetalleContrato find(Object id);

    List<DetalleContrato> findAll();        

    List<DetalleContrato> findRange(int[] range);
    
    List<DetalleContrato> search(String namedQuery);
    
    List<DetalleContrato> search(String namedQuery, Map<String, Object> parametros);
    
    List<DetalleContrato> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();
    
}
