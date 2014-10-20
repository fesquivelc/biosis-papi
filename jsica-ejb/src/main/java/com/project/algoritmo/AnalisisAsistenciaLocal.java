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
 * @author RyuujiMD
 */
@Local
public interface AnalisisAsistenciaLocal {

    void setListaEmpleados(List<Empleado> listaEmpleados);

    void iniciarAnalisis(Date fechaInicio, Date horaInicio, Date fechaFin, Date horaFin, List<Empleado> listaEmpleados);
    
}
