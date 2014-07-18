/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.Ubigeo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface UbigeoFacadeLocal {

    void create(Ubigeo ubigeo);

    void edit(Ubigeo ubigeo);

    void remove(Ubigeo ubigeo);

    Ubigeo find(Object id);

    List<Ubigeo> findAll();

    List<Ubigeo> findRange(int[] range);

    int count();
    
}
