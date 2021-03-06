/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.project.jsica.ejb.entidades.Feriado;
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
public class FeriadoFacade extends AbstractFacade<Feriado> implements FeriadoFacadeLocal {

    @PersistenceContext(unitName = biosis_PU)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FeriadoFacade() {
        super(Feriado.class);
    }

    @Override
    public List<Feriado> buscarXAnio(Integer anio) {
        String jpql = "SELECT f FROM Feriado f WHERE f.anioId.anio = :anio";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("anio", anio + "");

        return this.search(jpql, parametros);
    }

    @Override
    public Feriado buscarXDia(Date fecha) {
        String jpql = "SELECT f FROM Feriado f WHERE "
                + ":fecha BETWEEN f.fechaInicio AND f.fechaFin";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fecha", fecha);

        List<Feriado> lista = this.search(jpql, parametros, -1, 1);
        
        return lista.isEmpty() ? null : lista.get(0);
    }

}
