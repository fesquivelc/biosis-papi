/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.algoritmo.AnalisisAsistenciaLocal;
import com.project.jsica.ejb.entidades.Empleado;
import com.project.jsica.ejb.entidades.RegistroAsistencia;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author RyuujiMD
 */
@Stateless
public class RegistroAsistenciaFacade extends AbstractFacade<RegistroAsistencia> implements RegistroAsistenciaFacadeLocal {
    @PersistenceContext(unitName = jsica_PU)
    private EntityManager em;

//    @EJB
//    private UtilitarioAsistenciaLocal utilitario;
//    @EJB
//    private AnalisisAsistenciaLocal analisis;
//    @EJB
//    private EmpleadoFacadeLocal empleadoDAO;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegistroAsistenciaFacade() {
        super(RegistroAsistencia.class);
    }

//    @Override
//    public List<RegistroAsistencia> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
//        utilitario.crearEspejo();
//        Date fechaInicio = utilitario.getFechaPartida();
//        Date fechaFin = utilitario.getFechaLlegada();
//        Date horaInicio = utilitario.getHoraPartida();
//        Date horaFin = utilitario.getHoraLlegada();
//        
//        List<Empleado> empleados = empleadoDAO.search("SELECT e FROM Empleado e");        
//        
//        analisis.setListaEmpleados(empleados);
//        analisis.iniciarAnalisis(fechaInicio, horaInicio, fechaFin, horaFin, empleados);
////        
//        return super.search(namedQuery, parametros, inicio, tamanio); //To change body of generated methods, choose Tools | Templates.
//    }
    
}
