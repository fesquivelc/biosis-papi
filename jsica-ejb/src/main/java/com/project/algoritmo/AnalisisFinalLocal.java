/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.algoritmo;

import com.project.jsica.ejb.entidades.Empleado;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fesquivelc
 */
@Local
public interface AnalisisFinalLocal {

    void iniciarAnalisis(List<Empleado> empleados, Date fechaInicio, Date horaInicio, Date fechaFin, Date horaFin);
    
}
