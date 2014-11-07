/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.algoritmo;

import com.project.jsica.ejb.entidades.CambioTurno;
import com.project.jsica.ejb.entidades.DetalleHorario;
import com.project.jsica.ejb.entidades.DetalleRegistroAsistencia;
import com.project.jsica.ejb.entidades.Empleado;
import com.project.jsica.ejb.entidades.EmpleadoPermiso;
import com.project.jsica.ejb.entidades.Feriado;
import com.project.jsica.ejb.entidades.RegistroAsistencia2;
import com.project.jsica.ejb.entidades.Vista;
import com.project.util.FechaUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author fesquivelc
 */
public class AlgoritmoFinal {

    private final int MINUTOS_ANTES_DE_MARCAR = 120;
    private final int MINUTOS_DESPUES_DE_HORA_SALIDA = 240;
    private final int MINUTOS_ANTES_DE_MARCAR_PERMISO = 0;
    private final int MINUTOS_DESPUES_DE_INICIO_PERMISO = 30;

    private Date horaInicio;
    private Date horaFin;
    private Date fechaInicio;
    private Date fechaFin;

    private Empleado empleado;
    private List<RegistroAsistencia2> registrosXMes; // lo que se retornara
    private List<Feriado> feriadosXAnio;
    private static final Logger LOG = Logger.getLogger(AlgoritmoFinal.class.getName());

    public List<RegistroAsistencia2> analizar(
            Empleado empleado,
            Date fechaInicio,
            Date fechaFin,
            Date horaInicio,
            Date horaFin,
            List<Feriado> feriados,
            List<DetalleHorario> turnosXMes,
            List<EmpleadoPermiso> permisosXMes,
            List<Vista> marcacionesXMes,
            List<CambioTurno> cambiosTurnoXMes) {

        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;

        this.feriadosXAnio = feriados;

        LOG.info("PARAMETROS DE INICIO:"
                + " FI y HI: " + fechaInicio.toString() + " " + horaInicio.toString()
                + " FF Y HF: " + fechaFin.toString() + " " + horaFin.toString());
        //////////////////////////////////////////////////////////////////////////////

        registrosXMes = new ArrayList<>();

        for (DetalleHorario turno : turnosXMes) {
            if (turno.getHorarioId().getPorFecha()) {
                LOG.info("ANALIZANDO HORARIO DE EMERGENCIA");
                //ANALIZAR CAMBIOS DE TURNO

            } else {
                LOG.info("ANALIZANDO HORARIO ADMINISTRATIVO");
                registrosXMes.addAll(analizarTurnoAdministrativo(empleado, fechaInicio, horaInicio, fechaFin, horaFin, turno, permisosXMes, marcacionesXMes));
            }
        }

        return registrosXMes;
    }

    private List<RegistroAsistencia2> analizarTurnoAdministrativo(
            Empleado empleado,
            Date fechaDesde,
            Date horaDesde,
            Date fechaHasta,
            Date horaHasta,
            DetalleHorario turno,
            List<EmpleadoPermiso> permisosXMes,
            List<Vista> marcacionesXMes) {

        List<RegistroAsistencia2> registros = new ArrayList<>();

        Calendar inicio = Calendar.getInstance();

        inicio.setTime(fechaDesde);

        Calendar fin = Calendar.getInstance();

        fin.setTime(fechaHasta);

        Calendar hora = Calendar.getInstance();
        hora.setTime(horaDesde);

        int contador = 0;
        for (Date fecha = inicio.getTime(); !fecha.after(fechaHasta); inicio.add(Calendar.DAY_OF_MONTH, 1)) {
            LOG.info("RECORRIENDO DIA A DIA " + fecha.toString());
            Date horaAnalisis = hora.getTime();

            RegistroAsistencia2 registro = analizarRegistro(empleado, turno, fecha, horaAnalisis, fechaHasta, horaHasta, marcacionesXMes, permisosXMes);

            if (registro != null) {
                registros.add(registro);
            }

            hora.set(Calendar.HOUR_OF_DAY, hora.getMinimum(Calendar.HOUR_OF_DAY));
            contador++;
        }

        return registros;
    }

    private RegistroAsistencia2 analizarRegistro(
            Empleado empleado,
            DetalleHorario turno,
            Date fecha,
            Date horaAnalisis,
            Date fechaHasta,
            Date horaHasta,
            List<Vista> marcacionesXMes,
            List<EmpleadoPermiso> permisosXMes) {

        LOG.info("----------------- ANALISIS DEL TURNO " + turno.getJornadaCodigo().getNombre() + " EN LA FECHA " + fecha.toString() + "-----------------");

        Calendar fechaSalida = Calendar.getInstance();
        fechaSalida.setTime(fecha);
        if (turno.getJornadaCodigo().isTerminaDiaSiguiente()) {
            fechaSalida.add(Calendar.DAY_OF_MONTH, 1);
        }

        Calendar horaSalida = Calendar.getInstance();
        horaSalida.setTime(turno.getJornadaCodigo().getHSalida());
        horaSalida.add(Calendar.MINUTE, MINUTOS_DESPUES_DE_HORA_SALIDA);

        if (FechaUtil.compararFechaHora(fechaInicio, horaInicio, fechaSalida.getTime(), horaSalida.getTime()) <= 0
                && FechaUtil.compararFechaHora(fechaFin, horaFin, fechaSalida.getTime(), horaSalida.getTime()) >= 0) {

            LOG.info("SE COMPARO LAS FECHAS Y HORAS, SE DA INICIO AL ANALISIS");
            RegistroAsistencia2 registro = new RegistroAsistencia2();
            String tipo;
            //SE PROCEDE A ANALIZAR CADA DETALLE 
            EmpleadoPermiso permisoXFecha = tienePermisoXFecha(fecha, permisosXMes);

            if (permisoXFecha != null) {
                LOG.info("PERMISO POR FECHA");
                String codigo = permisoXFecha.getPermisoId().getMotivoPermisoCodigo().getCodigo();

                if (codigo.equals("VAC")) {
                    tipo = "V";
                } else if (codigo.equals("LIC")) {
                    tipo = "L";
                } else {
                    tipo = "P";
                }
                registro.setPermiso(permisoXFecha);

            } else if (isOnomastico(fecha, empleado) && !turno.getHorarioId().getPorFecha()) {
                LOG.info("ES ONOMASTICO");

            } else if (isDiaLaboral(fecha, turno) || turno.getHorarioId().getPorFecha()) {
                Feriado feriado = isFeriado(fecha, feriadosXAnio);

                if (feriado != null && !turno.getHorarioId().getPorFecha()) {
                    LOG.info("ES FERIADO");
                    tipo = "F";
                    registro.setFeriado(feriado);
                } else {
                    List<EmpleadoPermiso> permisosXHora = isPermisoPorhora(fecha, fechaSalida.getTime(), turno, permisosXMes);

                    boolean permisoHoraEntrada = false;
                    boolean permisoHoraSalida = false;
                    boolean permisoRefrigerio = false;

                    Date horaEntrada = null;

                    List<DetalleRegistroAsistencia> detalle = new ArrayList<>();

                    for (EmpleadoPermiso permiso : permisosXHora) {
//                        DetalleRegistroAsistencia detalle = new DetalleRegistroAsistencia();

                        Date horaPermiso = permiso.getPermisoId().getHoraInicio();

                        if (horaPermiso.compareTo(turno.getJornadaCodigo().getHEntrada()) == 0) {
                            permisoHoraEntrada = true;
                        }

                        detalle.addAll(
                                analizarPermiso(
                                        permiso,
                                        registro,
                                        fecha,
                                        horaPermiso,
                                        turno.getJornadaCodigo().getHSalida(),
                                        MINUTOS_ANTES_DE_MARCAR_PERMISO,
                                        MINUTOS_DESPUES_DE_INICIO_PERMISO,
                                        marcacionesXMes
                                ));

                    }

                }

            } else {
                return null;
            }

            return registro;
        } else {
            LOG.warn("NO SE ANALIZA EL TURNO");
            return null;
        }
    }

    private boolean isOnomastico(Date fecha, Empleado empleado) {
        Calendar onomastico = Calendar.getInstance();
        Calendar comparacion = Calendar.getInstance();

        onomastico.setTime(empleado.getFechaNacimiento());
        comparacion.setTime(fecha);

        return onomastico.get(Calendar.DAY_OF_MONTH) == comparacion.get(Calendar.DAY_OF_MONTH)
                && onomastico.get(Calendar.MONTH) == comparacion.get(Calendar.MONTH);
    }

    private boolean isDiaLaboral(Date fecha, DetalleHorario turno) {
        Calendar dia = Calendar.getInstance();
        dia.setTime(fecha);

        int comparacion = dia.get(Calendar.DAY_OF_MONTH);

        return diasLaborables(turno.getHorarioId().getLunes(), turno.getHorarioId().getMartes(), turno.getHorarioId().getMiercoles(), turno.getHorarioId().getJueves(), turno.getHorarioId().getViernes(), turno.getHorarioId().getSabado(), turno.getHorarioId().getDomingo()).contains(comparacion);
    }

    private List<EmpleadoPermiso> isPermisoPorhora(Date fInicio, Date fFin, DetalleHorario turno, List<EmpleadoPermiso> permisosXMes) {
        List<EmpleadoPermiso> permisosXHora = new ArrayList<>();

        for (EmpleadoPermiso permiso : permisosXMes) {
            if (!permiso.getPermisoId().getPorFecha()) {
                Date hEntrada = turno.getJornadaCodigo().getHEntrada();
                Date hSalida = turno.getJornadaCodigo().getHSalida();
                Date fechaPermiso = permiso.getPermisoId().getFechaInicio();
                Date horaPermiso = permiso.getPermisoId().getHoraInicio();

                if (FechaUtil.compararFechaHora(fInicio, hEntrada, fechaPermiso, horaPermiso) <= 0
                        && FechaUtil.compararFechaHora(fFin, hSalida, fechaPermiso, horaPermiso) >= 0) {
                    permisosXHora.add(permiso);
                }
            }
        }

        return permisosXHora;
    }

    private EmpleadoPermiso tienePermisoXFecha(Date fecha, List<EmpleadoPermiso> permisosXMes) {
        for (EmpleadoPermiso permiso : permisosXMes) {
            if (permiso.getPermisoId().getPorFecha()) {
                Date fInicio = permiso.getPermisoId().getFechaInicio();
                Date fFin = permiso.getPermisoId().getFechaFin();

                if (fInicio.compareTo(fecha) <= 0 && fFin.compareTo(fecha) >= 0) {
                    return permiso;
                }
            }
        }
        return null;
    }

    private Feriado isFeriado(Date fecha, List<Feriado> feriadosXAnio) {
        for (Feriado feriado : feriadosXAnio) {
            if (feriado.getFechaInicio().compareTo(fecha) <= 0
                    && feriado.getFechaFin().compareTo(fecha) >= 0) {
                return feriado;
            }
        }
        return null;
    }

    private List<Integer> diasLaborables(boolean lunes, boolean martes, boolean miercoles, boolean jueves, boolean viernes, boolean sabado, boolean domingo) {
        List<Integer> diasLaborables = new ArrayList<>();
        if (domingo) {
            diasLaborables.add(1);
        }
        if (lunes) {
            diasLaborables.add(2);
        }
        if (martes) {
            diasLaborables.add(3);
        }
        if (miercoles) {
            diasLaborables.add(4);
        }
        if (jueves) {
            diasLaborables.add(5);
        }
        if (viernes) {
            diasLaborables.add(6);
        }
        if (sabado) {
            diasLaborables.add(7);
        }

        return diasLaborables;
    }

    private List<DetalleRegistroAsistencia> analizarPermiso(EmpleadoPermiso permiso, RegistroAsistencia2 registro,Date fecha, Date horaInicio, Date horaMaxima, int minutosAntes, int minutosDespues, List<Vista> marcaciones) {
        
        List<DetalleRegistroAsistencia> lista = new ArrayList<>();
        
        //OBTENEMOS MARCACION DE INICIO DEL PERMISO
        Calendar cal = Calendar.getInstance();
        cal.setTime(horaInicio);
        
        cal.add(Calendar.MINUTE, -minutosAntes);
        Date desde = cal.getTime();
        
        cal.add(Calendar.MINUTE, minutosAntes + minutosDespues);
        
        Date hasta = cal.getTime();
        
        Vista marcacionInicio = obtenerMarcacion(fecha, desde, hasta, marcaciones);
        
        Vista marcacionFinal = obtenerMarcacion(fecha, hasta, horaMaxima, marcaciones);
        
        int cargaInicio;
        int cargaFin;
        Date horaI = null;
        Date horaF = null;
        
        String tipoRegistro = "P";
        
        if(marcacionInicio == null){
            cargaInicio = 23;
        }else{
            cargaInicio = 1;
            horaI = marcacionInicio.getHora();
        }
        
        if(marcacionFinal == null){
            cargaFin = 23;
        }else{
            cargaFin = -1;
            horaF = marcacionFinal.getHora();
        }
        
        DetalleRegistroAsistencia inicioPermiso = new DetalleRegistroAsistencia();
        DetalleRegistroAsistencia finPermiso = new DetalleRegistroAsistencia();
        
        inicioPermiso.setFecha(fecha);
        inicioPermiso.setCarga(cargaInicio);
        inicioPermiso.setHora(horaI);
        inicioPermiso.setRegistroAsistencia(registro);
        inicioPermiso.setTipoEvento("S");
        inicioPermiso.setTipoRegistro(tipoRegistro);
        
        finPermiso.setFecha(fecha);
        finPermiso.setCarga(cargaFin);
        finPermiso.setHora(horaF);
        finPermiso.setRegistroAsistencia(registro);
        finPermiso.setTipoEvento("E");
        finPermiso.setTipoRegistro(tipoRegistro);
        
        lista.add(inicioPermiso);
        lista.add(finPermiso);
        
        return lista;
    }

    private Vista obtenerMarcacion(Date fecha, Date desde, Date hasta, List<Vista> marcacionesXMes) {
        Vista vista = null;
        long menor = 0;
        long comparacion;

        boolean bandera = true;
        for (Vista v : marcacionesXMes) {
            if (fecha.compareTo(v.getFecha()) == 0) {
                if(desde.compareTo(v.getHora()) <= 0 && hasta.compareTo(v.getHora()) >=0){
                    comparacion = v.getHora().getTime() - desde.getTime();
                    
                    if(bandera){
                        vista = v;
                        menor = comparacion;
                        bandera = false;
                    }else if(menor > comparacion){
                        vista = v;
                        menor = comparacion;
                    }
                    
                }
            }
        }

        return vista;
    }

}
