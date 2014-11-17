/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.DetalleHorario;
import com.project.jsica.ejb.entidades.Empleado;
import com.project.jsica.ejb.entidades.EmpleadoHorario;
import com.project.util.FechaUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author RyuujiMD
 */
@Stateless
public class DetalleHorarioFacade extends AbstractFacade<DetalleHorario> implements DetalleHorarioFacadeLocal {

    @PersistenceContext(unitName = biosis_PU)
    private EntityManager em;

    @EJB
    private EmpleadoHorarioFacadeLocal empleadoHorarioDAO;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleHorarioFacade() {
        super(DetalleHorario.class);
    }

    @Override
    public List<DetalleHorario> buscarXEmpleado(Empleado empleado, Integer mes, Integer anio) {
        int primero = 1;
        int ultimo = FechaUtil.ultimoDiaMes(mes, anio);

        String fechaI = "{d '" + anio + "-" + mes + "-" + primero + "'}";
        String fechaF = "{d '" + anio + "-" + mes + "-" + ultimo + "'}";

        String sql;

        Map<String, Object> parametros = new HashMap<>();

        if (empleado.getGrupoHorarioId() != null) {
            sql = "SELECT eh FROM EmpleadoHorario eh "
                    + "WHERE (eh.porGrupo = TRUE AND eh.grupoHorarioId = :grupo) "
                    + "AND (eh.horarioId.porFecha = FALSE "
                    + "OR (eh.horarioId.porFecha = TRUE AND eh.horarioId.fecha BETWEEN " + fechaI + " AND " + fechaF + ")) ";
            parametros.put("grupo", empleado.getGrupoHorarioId());
        } else {
            sql = "SELECT eh FROM EmpleadoHorario eh "
                    + "WHERE (eh.porGrupo = FALSE AND eh.empleadoId.docIdentidad = :dni) "
                    + "AND (eh.horarioId.porFecha = FALSE "
                    + "OR (eh.horarioId.porFecha = TRUE AND eh.horarioId.fecha BETWEEN " + fechaI + " AND " + fechaF + ")) ";
            parametros.put("dni", empleado.getDocIdentidad());
        }

        List<EmpleadoHorario> empleadoHorarios = empleadoHorarioDAO.search(sql, parametros);
        List<DetalleHorario> lista = new ArrayList<>();
        for (EmpleadoHorario empleadoHorario : empleadoHorarios) {
            List<DetalleHorario> horarioJornadas = empleadoHorario.getHorarioId().getDetalleHorarioList();
            lista.addAll(horarioJornadas);
        }
        return lista;
    }

}
