/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.CambioTurno;
import com.project.util.FechaUtil;
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
public class CambioTurnoFacade extends AbstractFacade<CambioTurno> implements CambioTurnoFacadeLocal {
    @PersistenceContext(unitName = biosis_PU)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CambioTurnoFacade() {
        super(CambioTurno.class);
    }

    @Override
    public List<CambioTurno> buscarXEmpleado(String dni, Integer mes, Integer anio) {
        int primero = 1;
        int ultimo = FechaUtil.ultimoDiaMes(mes, anio);

        String fechaI = "{d '" + anio + "-" + mes + "-" + primero + "'}";
        String fechaF = "{d '" + anio + "-" + mes + "-" + ultimo + "'}";

        String jpql = "SELECT ct FROM CambioTurno ct WHERE "
                + "(ct.empleado1Id.docIdentidad = :dni AND ct.detalleHorarioOriginal.fecha BETWEEN " + fechaI + " AND " + fechaF + " ) "
                + "OR "
                + "(ct.empleado2Id.docIdentidad = :dni AND ct.detalleHorarioReemplazo.fecha BETWEEN " + fechaI + " AND " + fechaF + " ) "
                + "ORDER BY ct.detalleHorarioOriginal.fecha, ct.detalleHorarioReemplazo.fecha ASC ";

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("dni", dni);

        return this.search(jpql, parametros);
    }
    
    
}
