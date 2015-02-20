/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.project.jsica.ejb.entidades.EmpleadoHorario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author RyuujiMD
 */
@Stateless
public class EmpleadoHorarioFacade extends AbstractFacade<EmpleadoHorario> implements EmpleadoHorarioFacadeLocal {

    @PersistenceContext(unitName = biosis_PU)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoHorarioFacade() {
        super(EmpleadoHorario.class);
    }

    @Override
    public List<EmpleadoHorario> getHorariosAsistenciales() {
        String sql = "SELECT eh FROM EmpleadoHorario eh WHERE eh.horarioId.porFecha = TRUE";
        return this.getEntityManager().createQuery(sql).getResultList();

    }

}
