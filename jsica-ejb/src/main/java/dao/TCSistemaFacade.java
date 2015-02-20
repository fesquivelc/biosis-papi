/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.project.jsica.ejb.entidades.TCSistema;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fesquivelc
 */
@Stateless
public class TCSistemaFacade extends AbstractFacade<TCSistema> implements TCSistemaFacadeLocal {
    @PersistenceContext(unitName = "biosis-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TCSistemaFacade() {
        super(TCSistema.class);
    }
    
}
