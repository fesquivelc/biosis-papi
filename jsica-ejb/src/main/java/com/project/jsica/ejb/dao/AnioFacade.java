/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.Anio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author RyuujiMD
 */
@Stateless
public class AnioFacade extends AbstractFacade<Anio> implements AnioFacadeLocal {
    //Bitacora-------
       
    
    @PersistenceContext(unitName = biosis_PU)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnioFacade() {
        super(Anio.class);
    }

    @Override
    public void vigenciaFalsa() {
        String sql = "UPDATE Anio a SET a.vigente = FALSE WHERE a.vigente = TRUE";
        this.getEntityManager().createQuery(sql).executeUpdate();
    }
    
    
}
