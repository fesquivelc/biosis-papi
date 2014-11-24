/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.InterrupcionVacacional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fesquivelc
 */
@Stateless
public class InterrupcionVacacionalFacade extends AbstractFacade<InterrupcionVacacional> implements InterrupcionVacacionalFacadeLocal {
    @PersistenceContext(unitName = "biosis-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InterrupcionVacacionalFacade() {
        super(InterrupcionVacacional.class);
    }
    
}
