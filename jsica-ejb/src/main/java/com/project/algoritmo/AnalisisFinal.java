/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.algoritmo;

import com.project.jsica.ejb.dao.CambioTurnoFacadeLocal;
import com.project.jsica.ejb.dao.DetalleHorarioFacadeLocal;
import com.project.jsica.ejb.dao.EmpleadoPermisoFacadeLocal;
import com.project.jsica.ejb.dao.FeriadoFacadeLocal;
import com.project.jsica.ejb.dao.RegistroAsistenciaFinalFacadeLocal;
import com.project.jsica.ejb.dao.VistaFacadeLocal;
import com.project.jsica.ejb.entidades.CambioTurno;
import com.project.jsica.ejb.entidades.DetalleHorario;
import com.project.jsica.ejb.entidades.Empleado;
import com.project.jsica.ejb.entidades.EmpleadoPermiso;
import com.project.jsica.ejb.entidades.Feriado;
import com.project.jsica.ejb.entidades.RegistroAsistencia2;
import com.project.jsica.ejb.entidades.Vista;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.log4j.Logger;

/**
 *
 * @author fesquivelc
 */
@Stateless
public class AnalisisFinal implements AnalisisFinalLocal {

    @EJB
    private RegistroAsistenciaFinalFacadeLocal registroAsistenciaDAO;
    @EJB
    private CambioTurnoFacadeLocal cambioTurnoDAO;
    @EJB
    private FeriadoFacadeLocal feriadoDAO;
    @EJB
    private EmpleadoPermisoFacadeLocal empleadoPermisoDAO;
    @EJB
    private VistaFacadeLocal vistaDAO;

    @EJB
    private DetalleHorarioFacadeLocal turnoDAO;
    private static final Logger LOG = Logger.getLogger(AnalisisFinal.class.getName());

    @Override
    public void iniciarAnalisis(List<Empleado> empleados, Date fechaInicio, Date horaInicio, Date fechaFin, Date horaFin) {
        AlgoritmoFinal algoritmo = new AlgoritmoFinal();

        Calendar calendario = Calendar.getInstance();

        List<DetalleHorario> turnosXMes;
        List<EmpleadoPermiso> permisosXMes;
        List<CambioTurno> cambiosTurnoXMes;
        List<Vista> marcacionesXMes;
        List<Feriado> feriadosXAnio = null;
        List<RegistroAsistencia2> registrosXMes;

        int anio = calendario.get(Calendar.YEAR);
        calendario.setTime(fechaInicio);

        Calendar cal = Calendar.getInstance();
        
        feriadosXAnio = feriadoDAO.buscarXAnio(anio);

        while (fechaInicio.compareTo(fechaFin) <= 0) {
            LOG.info("INICIANDO EL LOOP");
            int mes = calendario.get(Calendar.MONTH) + 1;

            if (anio != calendario.get(Calendar.YEAR)) {
                anio = calendario.get(Calendar.YEAR);
                feriadosXAnio = feriadoDAO.buscarXAnio(anio);
            }

            for (Empleado empleado : empleados) {
                LOG.info(empleado.getApellidos());
                turnosXMes = turnoDAO.buscarXEmpleado(empleado, mes, anio);
                permisosXMes = empleadoPermisoDAO.buscarXEmpleado(empleado.getDocIdentidad(), mes, anio);
                cambiosTurnoXMes = cambioTurnoDAO.buscarXEmpleado(empleado.getDocIdentidad(), mes, anio);
                marcacionesXMes = vistaDAO.buscarXEmpleado(getCodigo(empleado), mes, anio);

                Date finMes = null;
                Date horaFinMes = null;

                cal.setTime(fechaInicio);
                int mesInicio = cal.get(Calendar.MONTH);

                cal.setTime(fechaFin);
                int mesFin = cal.get(Calendar.MONTH);
                
                

                LOG.info("PARAMETROS DE FIN");
                if (mesInicio < mesFin) {
                    cal.setTime(fechaInicio);
                    cal.set(Calendar.DAY_OF_MONTH, cal.getMaximum(Calendar.DAY_OF_MONTH));
                    finMes = cal.getTime();
                    cal.setTime(horaInicio);
                    cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
                    horaFinMes = cal.getTime();
                } else if (mesInicio == mesFin) {
                    finMes = cal.getTime();
                    horaFinMes = horaFin;
                }

                registrosXMes = algoritmo.analizar(empleado, fechaInicio, finMes, horaInicio, horaFinMes, feriadosXAnio, turnosXMes, permisosXMes, marcacionesXMes, cambiosTurnoXMes);

                this.guardarRegistros(registrosXMes);
            }

            calendario.setTime(fechaInicio);
            calendario.add(Calendar.MONTH, 1);
            calendario.set(Calendar.DAY_OF_MONTH, 1);

            fechaInicio = calendario.getTime();
        }
    }

    private String getCodigo(Empleado empleado) {
        //SI SE TRATA DE CAJAMARCA ES EL DOCUMENTO DE IDENTIDAD
        //SI SE TRATA DE ELECTRONORTE ES EL CODIGO DE TRABAJADOR :D
        return empleado.getFichaLaboral().getCodigoTrabajador();
    }

    private void guardarRegistros(List<RegistroAsistencia2> registros) {
        for (RegistroAsistencia2 registro : registros) {
            registroAsistenciaDAO.create(registro);
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
