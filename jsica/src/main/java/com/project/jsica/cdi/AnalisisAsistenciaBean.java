/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.jsica.cdi;

import com.project.algoritmo.AnalisisFinalLocal;
import com.project.jsica.ejb.dao.EmpleadoFacadeLocal;
import com.project.jsica.ejb.dao.RegistroAsistenciaFacadeLocal;
import com.project.jsica.ejb.dao.RegistroAsistenciaFinalFacadeLocal;
import com.project.jsica.ejb.dao.UtilitarioAsistenciaLocal;
import com.project.jsica.ejb.entidades.Empleado;
import com.project.jsica.ejb.entidades.RegistroAsistencia2;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author RyuujiMD
 */
@Named(value = "analisisAsistenciaBean")
@SessionScoped
public class AnalisisAsistenciaBean implements Serializable {
    private static final Logger LOG = Logger.getLogger(AnalisisAsistenciaBean.class.getName());

    @EJB
    private AnalisisFinalLocal analisis;

    @EJB
    private UtilitarioAsistenciaLocal utilitario;

    @EJB
    private EmpleadoFacadeLocal empleadoDAO;
    
    @EJB
    private RegistroAsistenciaFinalFacadeLocal registroDAO;

    public void recargar() {
        LOG.info("INICIAMOS RECARGA");
        utilitario.crearEspejo();
        Date fechaInicio = utilitario.getFechaPartida();
        Date fechaFin = utilitario.getFechaLlegada();
        Date horaInicio = utilitario.getHoraPartida();
        Date horaFin = utilitario.getHoraLlegada();

        List<Empleado> empleados = empleadoDAO.search("SELECT e FROM Empleado e");

//        analisis.setListaEmpleados(empleados);
        analisis.iniciarAnalisis(empleados, fechaInicio, horaInicio, fechaFin, horaFin);

    }
    
    public List<RegistroAsistencia2> buscarXFecha(Date fechaInicio, Date fechaFin){
        LOG.info("BUSCAR REGISTROS");
        return registroDAO.busquedaXFecha(fechaInicio, fechaFin);
    }

}
