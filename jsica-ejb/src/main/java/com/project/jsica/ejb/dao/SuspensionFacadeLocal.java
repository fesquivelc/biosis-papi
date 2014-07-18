/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.Suspension;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface SuspensionFacadeLocal {

    void create(Suspension suspension);

    void edit(Suspension suspension);

    void remove(Suspension suspension);

    Suspension find(Object id);

    List<Suspension> findAll();

    List<Suspension> findRange(int[] range);

    int count();
    
}
