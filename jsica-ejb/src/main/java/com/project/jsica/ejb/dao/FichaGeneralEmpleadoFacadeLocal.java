/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.FichaGeneralEmpleado;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface FichaGeneralEmpleadoFacadeLocal {

    void create(FichaGeneralEmpleado fichaGeneralEmpleado);

    void edit(FichaGeneralEmpleado fichaGeneralEmpleado);

    void remove(FichaGeneralEmpleado fichaGeneralEmpleado);

    FichaGeneralEmpleado find(Object id);

    List<FichaGeneralEmpleado> findAll();

    List<FichaGeneralEmpleado> findRange(int[] range);

    int count();
    
}
