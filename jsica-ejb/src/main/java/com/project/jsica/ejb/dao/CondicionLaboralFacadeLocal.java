/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.CondicionLaboral;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface CondicionLaboralFacadeLocal {

    void create(CondicionLaboral condicionLaboral);

    void edit(CondicionLaboral condicionLaboral);

    void remove(CondicionLaboral condicionLaboral);

    CondicionLaboral find(Object id);

    List<CondicionLaboral> findAll();

    List<CondicionLaboral> findRange(int[] range);

    int count();
    
}
