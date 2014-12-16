/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.Empleado;
import com.project.jsica.ejb.entidades.TCAnalisis;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fesquivelc
 */
@Local
public interface TCAnalisisFacadeLocal {

    void create(TCAnalisis tCAnalisis);

    void edit(TCAnalisis tCAnalisis);

    void remove(TCAnalisis tCAnalisis);

    TCAnalisis find(Object id);

    List<TCAnalisis> findAll();

    List<TCAnalisis> findRange(int[] range);

    int count();

    TCAnalisis buscarXEmpleado(Empleado empleado);
    
}
