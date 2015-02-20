/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import com.project.jsica.ejb.entidades.Area;
import com.project.jsica.ejb.entidades.Empleado;
import com.project.jsica.ejb.entidades.RegistroAsistencia;
import java.util.Date;
import java.util.HashMap;
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
    @PersistenceContext(unitName = biosis_PU)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegistroAsistenciaFacade() {
        super(RegistroAsistencia.class);
    }
    
    /**
     *
     * @param lista
     */
    @Override
    public void guardarLote(List<RegistroAsistencia> lista){
        for(RegistroAsistencia r : lista){
            this.create(r);
        }
    }

    @Override
    public List<RegistroAsistencia> busquedaXFecha(Date fechaInicio, Date fechaFin) {
        String jpql = "SELECT r FROM RegistroAsistencia r WHERE r.fecha BETWEEN :fechaInicio AND :fechaFin";
        
        Map<String, Object> map = new HashMap<>();
        map.put("fechaInicio", fechaInicio);
        map.put("fechaFin", fechaFin);
        
        return search(jpql, map);
    }

    @Override
    public List<RegistroAsistencia> buscarXArea(Area area, Date fechaDesde, Date fechaHasta) {
        String jpql = "SELECT r FROM RegistroAsistencia r WHERE "
                + "r.empleado.area = :area AND "
                + "r.fecha BETWEEN :fechaDesde AND :fechaHasta";
        
        Map<String, Object> map = new HashMap<>();
        map.put("area", area);
        map.put("fechaDesde", fechaDesde);
        map.put("fechaHasta", fechaHasta);
        
        return search(jpql, map);
        
    }

    @Override
    public List<RegistroAsistencia> buscarXEmpleado(Empleado empleado, Date fechaDesde, Date fechaHasta) {
        String jpql = "SELECT r FROM RegistroAsistencia r WHERE "
                + "r.empleado = :empleado AND "
                + "r.fecha BETWEEN :fechaDesde AND :fechaHasta";
        
        Map<String, Object> map = new HashMap<>();
        map.put("empleado", empleado);
        map.put("fechaDesde", fechaDesde);
        map.put("fechaHasta", fechaHasta);
        
        return search(jpql, map);
    }
    
}
