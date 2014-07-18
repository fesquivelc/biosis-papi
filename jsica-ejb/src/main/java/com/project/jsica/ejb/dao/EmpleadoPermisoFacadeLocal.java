/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.EmpleadoPermiso;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface EmpleadoPermisoFacadeLocal {

    void create(EmpleadoPermiso empleadoPermiso);

    void edit(EmpleadoPermiso empleadoPermiso);

    void remove(EmpleadoPermiso empleadoPermiso);

    EmpleadoPermiso find(Object id);

    List<EmpleadoPermiso> findAll();

    List<EmpleadoPermiso> findRange(int[] range);

    int count();
    
}
