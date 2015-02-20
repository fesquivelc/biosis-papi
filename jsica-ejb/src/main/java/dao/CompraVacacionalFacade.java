/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.project.jsica.ejb.entidades.CompraVacacional;
import com.project.jsica.ejb.entidades.Empleado;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fesquivelc
 */
@Stateless
public class CompraVacacionalFacade extends AbstractFacade<CompraVacacional> implements CompraVacacionalFacadeLocal {
    @PersistenceContext(unitName = "biosis-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompraVacacionalFacade() {
        super(CompraVacacional.class);
    }
    
    @Override
    public List<CompraVacacional> buscarXEmpleado(Empleado empleado) {
        String jpql = "SELECT v FROM CompraVacacional v WHERE v.empleado = :empleado ";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("empleado", empleado);
        
        return this.search(jpql, parametros);
    }

    @Override
    public List<CompraVacacional> buscarXEmpleado(Empleado empleado, Date fechaInicio, Date fechaFin) {
        String jpql = "SELECT v FROM CompraVacacional v WHERE v.empleado = :empleado "
                + "AND (v.fechaRegistro BETWEEN :fechaInicio AND :fechaFin)";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("empleado", empleado);
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        
        return this.search(jpql, parametros);
    }
    
}
