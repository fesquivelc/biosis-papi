/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.Vista;
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
public class VistaFacade extends AbstractFacade<Vista> implements VistaFacadeLocal {
    @PersistenceContext(unitName = biosis_PU)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VistaFacade() {
        super(Vista.class);
    }

    @Override
    public List<Vista> buscarXEmpleado(String dni, Integer mes, Integer anio) {
        int primero = 1;
        int ultimo = FechaUtil.ultimoDiaMes(mes, anio);

        String fechaI = "{d '" + anio + "-" + mes + "-" + primero + "'}";
        String fechaF = "{d '" + anio + "-" + mes + "-" + ultimo + "'}";

        String jpql = "SELECT v FROM Vista v WHERE v.dni = :dni AND v.fecha BETWEEN " + fechaI + " AND " + fechaF;

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("dni", Integer.parseInt(dni));

        return this.search(jpql, parametros);
    }
    
    
    
}
