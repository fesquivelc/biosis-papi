/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.Papeleta;
import java.util.List;
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

    int count();
    
}
