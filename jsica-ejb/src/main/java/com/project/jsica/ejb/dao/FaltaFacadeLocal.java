/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.Falta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface FaltaFacadeLocal {

    void create(Falta falta);

    void edit(Falta falta);

    void remove(Falta falta);

    Falta find(Object id);

    List<Falta> findAll();

    List<Falta> findRange(int[] range);

    int count();
    
}
