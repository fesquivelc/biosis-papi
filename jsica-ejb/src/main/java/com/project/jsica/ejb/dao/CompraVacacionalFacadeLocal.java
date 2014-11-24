/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.CompraVacacional;
import com.project.jsica.ejb.entidades.Empleado;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fesquivelc
 */
@Local
public interface CompraVacacionalFacadeLocal {

    void create(CompraVacacional compraVacacional);

    void edit(CompraVacacional compraVacacional);

    void remove(CompraVacacional compraVacacional);

    CompraVacacional find(Object id);

    List<CompraVacacional> findAll();

    List<CompraVacacional> findRange(int[] range);

    int count();

    List<CompraVacacional> buscarXEmpleado(Empleado empleado);
    
    List<CompraVacacional> buscarXEmpleado(Empleado empleado, Date fechaInicio, Date fechaFin);
    
    
}
