/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.Empleado;
import com.project.jsica.ejb.entidades.TCAnalisis;
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
public class TCAnalisisFacade extends AbstractFacade<TCAnalisis> implements TCAnalisisFacadeLocal {
    @PersistenceContext(unitName = biosis_PU)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TCAnalisisFacade() {
        super(TCAnalisis.class);
    }

    @Override
    public TCAnalisis buscarXEmpleado(Empleado empleado) {
        String jpql = "SELECT ta FROM TCAnalisis ta WHERE ta.empleado = :empleado";
        Map<String, Object> variables = new HashMap<>();
        variables.put("empleado", empleado);
        
        List<TCAnalisis> lista= search(jpql, variables);
        
        if(lista.isEmpty()){
            return null;
        }else{
            return lista.get(0);
        }
        
    }
    
    
    
}
