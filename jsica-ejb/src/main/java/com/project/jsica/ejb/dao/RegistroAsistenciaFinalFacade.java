/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.jsica.ejb.dao;

import com.project.algoritmo.AnalisisAsistenciaLocal;
import com.project.algoritmo.AnalisisFinalLocal;
import static com.project.jsica.ejb.dao.AbstractFacade.biosis_PU;
import com.project.jsica.ejb.entidades.Area;
import com.project.jsica.ejb.entidades.Empleado;
import com.project.jsica.ejb.entidades.RegistroAsistencia2;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

/**
 *
 * @author fesquivelc
 */
@Stateless
public class RegistroAsistenciaFinalFacade extends AbstractFacade<RegistroAsistencia2> implements RegistroAsistenciaFinalFacadeLocal {
    @PersistenceContext(unitName = biosis_PU)
    private EntityManager em;
    private static final Logger LOG = Logger.getLogger(RegistroAsistenciaFinalFacade.class.getName());
    
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
        LOG.info("TERMINO EL ANALISIS");
    }

    @Override
    public List<RegistroAsistencia2> busquedaXFecha(Date fechaInicio, Date fechaFin) {
        LOG.info("INICIANDO BUSQUEDA POR FECHA");
//        this.analizar();
        String jpql = "SELECT r FROM RegistroAsistencia2 r WHERE r.fecha BETWEEN :fechaInicio AND :fechaFin";
        
        Map<String, Object> variables = new HashMap<>();
        variables.put("fechaInicio", fechaInicio);
        variables.put("fechaFin", fechaFin);
        
        return this.search(jpql, variables);
    }

    @Override
    public List<RegistroAsistencia2> buscarXEmpleadoHE(Empleado empleado, Date fechaDesde, Date fechaHasta) {
        String jpql = "SELECT r FROM RegistroAsistencia2 r "
                + "WHERE r.milisegundosExtra > 0 AND r.empleado = :empleado AND r.fecha BETWEEN :fechaDesde AND :fechaHasta";
        Map<String, Object> variables = new HashMap<>();
        variables.put("empleado", empleado);
        variables.put("fechaDesde", fechaDesde);
        variables.put("fechaHasta", fechaHasta);
        
        return this.search(jpql, variables);
        
    }

    @Override
    public List<RegistroAsistencia2> buscarXArea(Area area, Date fechaDesde, Date fechaHasta) {
        String jpql = "SELECT r FROM RegistroAsistencia2 r "
                + "WHERE r.empleado.servicioId.areaId = :area AND r.fecha BETWEEN :fechaDesde AND :fechaHasta";
        Map<String, Object> variables = new HashMap<>();
        variables.put("area", area);
        variables.put("fechaDesde", fechaDesde);
        variables.put("fechaHasta", fechaHasta);
        
        return this.search(jpql, variables);
        
    }

    @Override
    public List<RegistroAsistencia2> buscarXEmpleado(Empleado empleado, Date fechaDesde, Date fechaHasta) {
        String jpql = "SELECT r FROM RegistroAsistencia2 r "
                + "WHERE r.empleado = :empleado AND r.fecha BETWEEN :fechaDesde AND :fechaHasta";
        Map<String, Object> variables = new HashMap<>();
        variables.put("empleado", empleado);
        variables.put("fechaDesde", fechaDesde);
        variables.put("fechaHasta", fechaHasta);
        
        return this.search(jpql, variables);
    }
    
    
    
    
    
    
    
    
}
