/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.jsica.ejb.dao;

import com.project.algoritmo.AnalisisAsistenciaLocal;
import com.project.algoritmo.AnalisisFinalLocal;
import static com.project.jsica.ejb.dao.AbstractFacade.jsica_PU;
import com.project.jsica.ejb.entidades.Empleado;
import com.project.jsica.ejb.entidades.RegistroAsistencia2;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fesquivelc
 */
@Stateless
public class RegistroAsistenciaFinalFacade extends AbstractFacade<RegistroAsistencia2> implements RegistroAsistenciaFinalFacadeLocal {
    @PersistenceContext(unitName = jsica_PU)
    private EntityManager em;
    
    @EJB
    private UtilitarioAsistenciaLocal utilitario;
    
    @EJB
    private EmpleadoFacadeLocal empleadoDAO;
    
    @EJB
    private AnalisisFinalLocal analisis;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegistroAsistenciaFinalFacade() {
        super(RegistroAsistencia2.class);
    }
    
    
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void analizar() {
        utilitario.crearEspejo();
        Date fechaInicio = utilitario.getFechaPartida();
        Date fechaFin = utilitario.getFechaLlegada();
        Date horaInicio = utilitario.getHoraPartida();
        Date horaFin = utilitario.getHoraLlegada();
        
        List<Empleado> empleados = empleadoDAO.search("SELECT e FROM Empleado e");        
        
        analisis.iniciarAnalisis(empleados, fechaInicio, horaInicio, fechaFin, horaFin);
    }
    
    
}
