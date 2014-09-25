/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.jsica.ejb.dao;

import com.project.algoritmo.AnalisisAsistenciaLocal;
import com.project.jsica.ejb.entidades.Empleado;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author RyuujiMD
 * @param <T>
 */
public abstract class AbstractFacade<T> {

    protected static final String jsica_PU = "jsica-postgresql-PU";

    @EJB
    private UtilitarioAsistenciaLocal utilitarioAsistencia;
    @EJB
    private AnalisisAsistenciaLocal analisisAsistencia;
    @EJB
    private EmpleadoFacadeLocal empleadoDAO;

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {

        getEntityManager().persist(entity);

    }

    public void edit(T entity) {
        getEntityManager().merge(entity);

    }
    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(AbstractFacade.class.getName());

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        utilitarioAsistencia.crearEspejo();
        Date fechaInicio = utilitarioAsistencia.getFechaPartida();
        Date fechaFin = utilitarioAsistencia.getFechaLlegada();
        Date horaInicio = utilitarioAsistencia.getHoraPartida();
        Date horaFin = utilitarioAsistencia.getHoraLlegada();
        LOG.log(Level.INFO, "--FECHA INICIO: {0}", fechaInicio.toString());
        LOG.log(Level.INFO, "--FECHA FIN: {0}", fechaFin.toString());
        LOG.log(Level.INFO, "--HORA INICIO: {0}", horaInicio.toString());
        LOG.log(Level.INFO, "--HORA FIN: {0}", horaFin.toString());
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(empleadoDAO.find(Long.parseLong("1")));
        analisisAsistencia.setListaEmpleados(empleados);
        analisisAsistencia.iniciarAnalisis(fechaInicio, horaInicio, fechaFin, horaFin);

        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));

        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<T> search(String namedQuery) {
        return this.search(namedQuery, null, -1, -1);
    }

    public List<T> search(String namedQuery, Map<String, Object> parametros) {
        return this.search(namedQuery, parametros, -1, -1);
    }

    public List<T> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        Query query = getEntityManager().createQuery(namedQuery);

        if (parametros != null) {
            for (Map.Entry<String, Object> entry : parametros.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }

        if (inicio != -1) {
            query.setFirstResult(inicio);
        }

        if (tamanio != -1) {
            query.setMaxResults(tamanio);
        }

        List<T> lista = query.getResultList();

        return lista;
    }

}
