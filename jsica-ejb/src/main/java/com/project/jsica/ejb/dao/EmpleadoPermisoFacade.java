/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.Empleado;
import com.project.jsica.ejb.entidades.EmpleadoPermiso;
import com.project.jsica.ejb.entidades.Permiso;
import com.project.util.FechaUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author RyuujiMD
 */
@Stateless
public class EmpleadoPermisoFacade extends AbstractFacade<EmpleadoPermiso> implements EmpleadoPermisoFacadeLocal {

    @PersistenceContext(unitName = biosis_PU)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoPermisoFacade() {
        super(EmpleadoPermiso.class);
    }

    @Override
    public List<EmpleadoPermiso> buscarXEmpleado(String dni, Integer mes, Integer anio) {
        int primero = 1;
        int ultimo = FechaUtil.ultimoDiaMes(mes, anio);

        String fechaI = "{d '" + anio + "-" + mes + "-" + primero + "'}";
        String fechaF = "{d '" + anio + "-" + mes + "-" + ultimo + "'}";

        String jpql = "SELECT pe FROM EmpleadoPermiso pe"
                + " WHERE pe.empleadoId.docIdentidad = :dni AND"
                + " (pe.permisoId.porFecha = false AND pe.permisoId.fechaInicio BETWEEN " + fechaI + " AND " + fechaF + ") "
                + " OR "
                + " (pe.permisoId.porFecha = true AND "
                + "    (pe.permisoId.fechaInicio BETWEEN " + fechaI + " AND " + fechaF + " OR pe.permisoId.fechaFin BETWEEN " + fechaI + " AND " + fechaF + "))";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("dni", dni);

        List<EmpleadoPermiso> lista = this.search(jpql, parametros);

        return lista;
    }

    @Override
    public List<EmpleadoPermiso> buscarXEmpleado(Empleado empleado, Date fechaInicio, Date fechaFin, Boolean conGoce) {
        String jpql = "SELECT p FROM EmpleadoPermiso p "
                + "WHERE p.empleadoId = :empleado "
                + "AND "
                + "p.permisoId.motivoPermisoCodigo.conGoce = :goce"
                + "AND "
                + "p.permisoId.fechaInicio BETWEEN :fechaInicio AND :fechaFin ";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("empleado", empleado);
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("goce", conGoce);

        return this.search(jpql, parametros);
    }

    

}
