/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import com.project.jsica.ejb.entidades.FichaGeneralEmpleado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author RyuujiMD
 */
@Stateless
public class FichaGeneralEmpleadoFacade extends AbstractFacade<FichaGeneralEmpleado> implements FichaGeneralEmpleadoFacadeLocal {
    @PersistenceContext(unitName = biosis_PU)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FichaGeneralEmpleadoFacade() {
        super(FichaGeneralEmpleado.class);
    }
    
}
