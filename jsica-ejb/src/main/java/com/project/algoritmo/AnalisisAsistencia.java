/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.algoritmo;

import com.project.jsica.ejb.dao.BiometricoFacadeLocal;
import com.project.jsica.ejb.dao.CambioTurnoFacadeLocal;
import com.project.jsica.ejb.dao.EmpleadoFacadeLocal;
import com.project.jsica.ejb.dao.EmpleadoHorarioFacadeLocal;
import com.project.jsica.ejb.dao.EmpleadoPermisoFacadeLocal;
import com.project.jsica.ejb.dao.FeriadoFacadeLocal;
import com.project.jsica.ejb.dao.RegistroAsistenciaFacadeLocal;
import com.project.jsica.ejb.dao.VistaFacadeLocal;
import com.project.jsica.ejb.entidades.CambioTurno;
import com.project.jsica.ejb.entidades.DetalleHorario;
import com.project.jsica.ejb.entidades.Empleado;
import com.project.jsica.ejb.entidades.EmpleadoHorario;
import com.project.jsica.ejb.entidades.EmpleadoPermiso;
import com.project.jsica.ejb.entidades.Feriado;
import com.project.jsica.ejb.entidades.Permiso;
import com.project.jsica.ejb.entidades.RegistroAsistencia;
import com.project.jsica.ejb.entidades.Vista;
import com.project.util.FechaUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author RyuujiMD
 */
@Stateless
public class AnalisisAsistencia implements AnalisisAsistenciaLocal {

    @EJB
    private BiometricoFacadeLocal biometricoDAO;
    @EJB
    private RegistroAsistenciaFacadeLocal registroAsistenciaDAO;
    @EJB
    private CambioTurnoFacadeLocal cambioTurnoDAO;
    @EJB
    private FeriadoFacadeLocal feriadoDAO;
    @EJB
    private EmpleadoPermisoFacadeLocal empleadoPermisoDAO;
    @EJB
    private EmpleadoHorarioFacadeLocal empleadoHorarioDAO;
    @EJB
    private VistaFacadeLocal vistaDAO;
    @EJB
    private EmpleadoFacadeLocal empleadoDAO;

    private Date horaInicio;
    private Date fechaInicio;
    private Date horaFin;
    private Date fechaFin;

    private List<Empleado> empleados;
    private final List<RegistroAsistencia> registroxMes = new ArrayList<>();
    private List<Feriado> feriadosXAnio;

    private final int MINUTOS_MAX_MARCACION_REGULAR = 5;
    private final int MINUTOS_MAX_MARCACION_TARDANZA = 15;
    private final int MINUTOS_ANTES_MARCACION_ENTRADA = 60;
    private final int MINUTOS_ANTES_MARCACION_SALIDA = 0;
    private final int MINUTOS_MAX_MARCACION_SALIDA = 120;

    private void analizarRegistroAdministrativo(Empleado empleado, DetalleHorario turno, List<Vista> marcacionesXMes, List<Permiso> permisosXMes) {
        //CASOS
        if (fechaInicio.compareTo(fechaFin) == 0) {

        }
    }

    private void analizarRegistroAsistencial(Empleado empleado, DetalleHorario turnoOriginal, DetalleHorario turnoReemplazo, List<Vista> marcacionesXMes, List<Permiso> permisosXMes, List<CambioTurno> cambiosTurnoXMes) {

        DetalleHorario turno;

        if (turnoReemplazo != null) {
            turno = turnoReemplazo;
        } else {
            turno = turnoOriginal;
        }

        Calendar cal = Calendar.getInstance();

        cal.setTime(turno.getJornadaCodigo().getHEntrada());
        cal.add(Calendar.MINUTE, MINUTOS_MAX_MARCACION_TARDANZA);
        //HORA CON LA QUE SE VA A COMPARAR LA HORA DE INICIO
        Date horaEntrada = cal.getTime();

        cal.setTime(turno.getJornadaCodigo().getHSalida());
        cal.add(Calendar.MINUTE, MINUTOS_MAX_MARCACION_SALIDA);
        //HORA CON LA QUE SE VA A COMPARAR LA HORA DE SALIDA
        Date horaSalida = cal.getTime();

        Date fechaEntrada = turno.getFecha();

        Date fechaSalida;

        if (turno.getJornadaCodigo().isTerminaDiaSiguiente()) {
            cal.setTime(fechaEntrada);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            fechaSalida = cal.getTime();
        } else {
            fechaSalida = fechaEntrada;
        }

        RegistroAsistencia registroEntrada;
        RegistroAsistencia registroSalida;

        Permiso permiso = this.isEstaEnPermisoXFecha(turno.getFecha(), permisosXMes);

        if (permiso != null) {
            registroEntrada = new RegistroAsistencia();
            registroEntrada.setTipo("PE");
            registroEntrada.setFecha(turno.getFecha());
            registroEntrada.setTurnoOriginal(turnoOriginal);
            registroEntrada.setEmpleadoId(empleado);
            registroEntrada.setPermisoId(permiso);
            registroxMes.add(registroEntrada);
        } else if (FechaUtil.compararFechaHora(fechaInicio, horaInicio, fechaEntrada, horaEntrada) <= 0) {
            if (FechaUtil.compararFechaHora(fechaFin, horaFin, fechaEntrada, horaEntrada) < 0) {
                LOG.info("NADA");
            } else if (FechaUtil.compararFechaHora(fechaFin, horaFin, fechaSalida, horaSalida) >= 0) {
                LOG.info("ANALIZAMOS HORA DE ENTRADA Y HORA DE SALIDA");

                registroEntrada = obtenerRegistroEntrada(empleado, turno, fechaEntrada, marcacionesXMes);

                if (registroEntrada.getTipo().charAt(0) == 'F') {
                    if (turnoReemplazo != null) {
                        registroEntrada.setTurnoOriginal(turnoOriginal);
                        registroEntrada.setTurnoReemplazo(turnoReemplazo);
                        registroEntrada.setTipo("FC");
                    } else {
                        registroEntrada.setTipo("FT");
                    }
                    registroxMes.add(registroEntrada);
                } else {
                    registroSalida = obtenerRegistroSalida(empleado, turno, fechaEntrada, marcacionesXMes);
                    if (registroSalida == null) {
                        if (turnoReemplazo != null) {
                            registroEntrada.setTurnoOriginal(turnoOriginal);
                            registroEntrada.setTurnoReemplazo(turnoReemplazo);
                            registroEntrada.setTipo("FC");
                        } else {
                            registroEntrada.setTipo("FT");
                        }
                        registroxMes.add(registroEntrada);
                    } else {
                        if (turnoReemplazo != null) {
                            registroEntrada.setTurnoOriginal(turnoOriginal);
                            registroEntrada.setTurnoReemplazo(turnoReemplazo);
                            registroEntrada.setTipo(registroEntrada.getTipo().replace("N", "C"));
                        } else {
                            registroEntrada.setTipo(registroEntrada.getTipo().replace("N", "T"));
                        }
                        registroxMes.add(registroEntrada);
                        registroxMes.add(registroSalida);
                    }
                }

            } else {
                LOG.info("ANALIZANDO HORA DE ENTRADA");

                registroEntrada = obtenerRegistroEntrada(empleado, turno, fechaEntrada, marcacionesXMes);
                if (registroEntrada.getTipo().charAt(0) == 'F') {
                    if (turnoReemplazo != null) {
                        registroEntrada.setTurnoOriginal(turnoOriginal);
                        registroEntrada.setTurnoReemplazo(turnoReemplazo);
                        registroEntrada.setTipo("FC");
                    }
                } else if (turnoReemplazo != null) {
                    registroEntrada.setTurnoOriginal(turnoOriginal);
                    registroEntrada.setTurnoReemplazo(turnoReemplazo);
                }
                registroxMes.add(registroEntrada);
            }
        } else if (FechaUtil.compararFechaHora(fechaInicio, horaInicio, fechaSalida, horaSalida) > 0) {
            LOG.info("NADA");
        } else if (FechaUtil.compararFechaHora(fechaFin, horaFin, fechaSalida, horaSalida) >= 0) {
            LOG.info("SE ANALIZA SALIDA");

            registroEntrada = this.buscarRegistroXTurno(turno);
            if (registroEntrada.getTipo().substring(0, 1).equals("F")) {
                //SE HA DETERMINADO COMO FALTA NO EXISTE NADA QUE SE PUEDA HACER
            } else {
                registroSalida = this.obtenerRegistroSalida(empleado, turno, fechaEntrada, marcacionesXMes);
                if (registroSalida == null) {
                    if (turnoReemplazo == null) {
                        registroEntrada.setTipo("FT");
                    } else {
                        registroEntrada.setTipo("FC");
                    }
                    this.registroxMes.add(registroEntrada);
                } else {
                    if (turnoReemplazo == null) {
                        registroEntrada.setTipo(registroEntrada.getTipo().replace("N", "T"));
                    } else {
                        registroEntrada.setTipo(registroEntrada.getTipo().replace("N", "C"));
                    }
                    this.registroxMes.add(registroEntrada);
                    this.registroxMes.add(registroSalida);
                }

            }

        }
    }//TERMINA METODO ANALIZAR ASISTENCIAL

    private RegistroAsistencia buscarRegistroXTurno(DetalleHorario turno) {
        String jpql = "SELECT r FROM RegistroAsistencia r WHERE r.turnoOriginal = :turno OR r.turnoReemplazo = :turno";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("turno", turno);

        List<RegistroAsistencia> lista = this.registroAsistenciaDAO.search(jpql, parametros, -1, 1);

        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }
    }

    public List<CambioTurno> cambiosTurnoXEmpleado(String dni, int mes, int anio) {
        int primero = 1;
        int ultimo = this.ultimoDiaMes(mes, anio);

        String fechaI = "{d '" + anio + "-" + mes + "-" + primero + "'}";
        String fechaF = "{d '" + anio + "-" + mes + "-" + ultimo + "'}";

        String jpql = "SELECT ct FROM CambioTurno ct WHERE "
                + "(ct.empleado1Id.docIdentidad = :dni AND ct.detalleHorarioOriginal.fecha BETWEEN " + fechaI + " AND " + fechaF + " ) "
                + "OR "
                + "(ct.empleado2Id.docIdentidad = :dni AND ct.detalleHorarioReemplazo.fecha BETWEEN " + fechaI + " AND " + fechaF + " ) "
                + "ORDER BY ct.detalleHorarioOriginal.fecha, ct.detalleHorarioReemplazo.fecha ASC ";

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("dni", dni);

        return this.cambioTurnoDAO.search(jpql, parametros);

    }

    private List<Feriado> cargarFeriados(int anio) {
        String jpql = "SELECT f FROM Feriado f WHERE f.anioId.anio = :anio";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("anio", anio + "");

        return feriadoDAO.search(jpql, parametros);
    }

    public Vista filtrarMarcacion(java.util.Date fecha, java.util.Date hora, int minimo, int maximo, List<Vista> marcacionesXMes) {
        Vista menor = null;
        long resta = 0;
        long comp;
        boolean bandera = true;
        Calendar cal = Calendar.getInstance();
        cal.setTime(hora);
        cal.add(Calendar.MINUTE, maximo);

        LOG.log(Level.INFO, "FECHA PARA EL FILTRADO: {0}", fecha.toString());
        
        java.util.Date horaMaxima = cal.getTime();
        LOG.log(Level.INFO, "HORA MAXIMA: {0}", horaMaxima.toString());

        cal.add(Calendar.MINUTE, -maximo - minimo);
        java.util.Date horaMinima = cal.getTime();
        LOG.log(Level.INFO, "HORA MINIMA: {0}", horaMinima.toString());
        for (Vista marcacion : marcacionesXMes) {
            LOG.log(Level.INFO, "COMPARACION DE LA FECHA DE MARCACION CON LA FECHA: {0}", marcacion.getFecha().compareTo(fecha));
            if (marcacion.getFecha().compareTo(fecha) == 0) {
                if (marcacion.getHora().compareTo(horaMaxima) <= 0 && marcacion.getHora().compareTo(horaMinima) >= 0) {
                    comp = Math.abs(marcacion.getHora().getTime() - hora.getTime());
                    if (bandera) {
                        resta = comp;
                        menor = marcacion;
                        bandera = false;
                    } else if (resta > comp) {
                        resta = comp;
                        menor = marcacion;
                    }
                }
            }
        }
        return menor;
    }

    private CambioTurno getCambioTurno(DetalleHorario turno, List<CambioTurno> cambiosTurnoXMes) {
        for (CambioTurno cambioTurno : cambiosTurnoXMes) {
            if (cambioTurno.getDetalleHorarioOriginal().equals(turno)) {
                return cambioTurno;
            } else if (cambioTurno.getDetalleHorarioReemplazo().equals(turno)) {
                return cambioTurno;
            }
        }
        return null;
    }

    //LOS PERMISOS SE CARGAN POR MES ASI QUE SOLO PERTENECEN A UN USUARIO A MENOS QUE ESTE USUARIO CAMBIE
    //POR LO TANTO SOLO SE PREGUNTA SI SE ENCUENTRA EN USUARIO POR FECHA
    private Permiso isEstaEnPermisoXFecha(Date fecha, List<Permiso> permisos) {
        for (Permiso permiso : permisos) {
            if (permiso.getPorFecha() && permiso.getFechaFin().compareTo(fecha) >= 0 && permiso.getFechaInicio().compareTo(fecha) <= 0) {
                return permiso;
            }
        }
        return null;
    }

    private boolean isFeriado(Date fecha) {
        for (Feriado feriado : feriadosXAnio) {
            if (fecha.compareTo(feriado.getFechaInicio()) >= 0 && fecha.compareTo(feriado.getFechaFin()) <= 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isTardanza(java.util.Date horaMarcacion, java.util.Date horaEntrada) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(horaEntrada);
        cal.add(Calendar.MINUTE, MINUTOS_MAX_MARCACION_REGULAR);
        java.util.Date horaRegular = cal.getTime();

        return horaMarcacion.compareTo(horaRegular) >= 0;

    }
    private static final Logger LOG = Logger.getLogger(AnalisisAsistencia.class.getName());

    @Override
    public void iniciarAnalisis(Date fechaInicio, Date horaInicio, Date fechaFin, Date horaFin) {
        Calendar calendario = Calendar.getInstance();

        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;

        this.fechaFin = fechaFin;
        this.horaFin = horaFin;

        List<DetalleHorario> turnosXMes;
        List<Permiso> permisosXMes;
        List<CambioTurno> cambiosTurnoXMes;
        List<Vista> marcacionesXMes;

        int anio = calendario.get(Calendar.YEAR);
        calendario.setTime(fechaInicio);
        feriadosXAnio = this.cargarFeriados(anio);

        while (this.fechaInicio.compareTo(fechaFin) <= 0) {
            int mes = calendario.get(Calendar.MONTH) + 1;

            if (anio != calendario.get(Calendar.YEAR)) {
                anio = calendario.get(Calendar.YEAR);
                feriadosXAnio = this.cargarFeriados(anio);
            }

            for (Empleado empleado : this.empleados) {
                turnosXMes = this.turnosXEmpleado(empleado.getDocIdentidad(), mes, anio);
                permisosXMes = this.permisosXEmpleado(empleado.getDocIdentidad(), mes, anio);
                cambiosTurnoXMes = this.cambiosTurnoXEmpleado(empleado.getDocIdentidad(), mes, anio);
                marcacionesXMes = this.vistaXEmpleado(empleado.getDocIdentidad(), mes, anio);

                for (DetalleHorario turno : turnosXMes) {
                    if (turno.getHorarioId().getPorFecha()) {
                        //HORARIO ASISTENCIAL
                        CambioTurno cambioTurno = this.getCambioTurno(turno, cambiosTurnoXMes);
                        DetalleHorario turnoReemplazo = null;
                        if (cambioTurno != null) {
                            if (cambioTurno.getDetalleHorarioOriginal().equals(turno)) {
                                turnoReemplazo = cambioTurno.getDetalleHorarioReemplazo();
                            } else {
                                turnoReemplazo = cambioTurno.getDetalleHorarioOriginal();
                            }
                        }
                        analizarRegistroAsistencial(empleado, turno, turnoReemplazo, marcacionesXMes, permisosXMes, cambiosTurnoXMes);
                    } else {
                        //HORARIO ADMINISTRATIVO
                        analizarRegistroAdministrativo(empleado, turno, marcacionesXMes, permisosXMes);
                    }
                }

                this.guardarRegistros(registroxMes);
            }

            calendario.setTime(this.fechaInicio);
            calendario.add(Calendar.MONTH, 1);
            calendario.set(Calendar.DAY_OF_MONTH, 1);

            this.fechaInicio = calendario.getTime();
        }
    }

    private Vista obtenerMarcacion(Date fecha, Date hora, boolean terminaDiaSiguiente, boolean entrada_salida, List<Vista> marcacionesXMes) {
        Vista vista;

        if (entrada_salida) {
            vista = this.filtrarMarcacion(fecha, hora, MINUTOS_ANTES_MARCACION_ENTRADA, MINUTOS_MAX_MARCACION_TARDANZA, marcacionesXMes);

            LOG.info("VISTA ENTRADA: " + vista);
        } else {

            Calendar cal = Calendar.getInstance();
            java.util.Date fechaSalida;
            if (terminaDiaSiguiente) {
                cal.setTime(fecha);
                cal.add(Calendar.DAY_OF_MONTH, 1);

                fechaSalida = cal.getTime();
            } else {
                fechaSalida = fecha;
            }

            vista = this.filtrarMarcacion(fechaSalida, hora, MINUTOS_ANTES_MARCACION_SALIDA, MINUTOS_MAX_MARCACION_SALIDA, marcacionesXMes);

            LOG.info("VISTA SALIDA: " + vista);
        }

        return vista;
    }

    private RegistroAsistencia obtenerRegistroEntrada(Empleado empleado, DetalleHorario turno, Date fechaEntrada, List<Vista> marcaciones) {
        RegistroAsistencia registro = new RegistroAsistencia();
        Vista vistaEntrada = obtenerMarcacion(fechaEntrada, turno.getJornadaCodigo().getHEntrada(), turno.getJornadaCodigo().isTerminaDiaSiguiente(), true, marcaciones);

        if (vistaEntrada == null) {
            LOG.info("ES UN REGISTRO DE FALTA");
            registro.setEmpleadoId(empleado);
            registro.setFecha(turno.getFecha());
            registro.seteOS(true);
            registro.setTurnoOriginal(turno);
            registro.setTipo("FT");
        } else {
            LOG.info("NO ES UN REGISTRO DE FALTA");
            registro.setBiometricoId(biometricoDAO.searchByIp(vistaEntrada.getEquipoIp()));
            registro.setFecha(vistaEntrada.getFecha());
            registro.seteOS(true);
            registro.setEmpleadoId(empleado);
            registro.setTurnoOriginal(turno);
            registro.setHora(vistaEntrada.getHora());
            if (isTardanza(vistaEntrada.getHora(), turno.getJornadaCodigo().getHEntrada())) {
                registro.setTipo("TN");
            } else {
                registro.setTipo("AN");
            }
        }

        return registro;
    }

    private RegistroAsistencia obtenerRegistroSalida(Empleado empleado, DetalleHorario turno, Date fechaSalida, List<Vista> marcaciones) {
        RegistroAsistencia registro = new RegistroAsistencia();
        Vista vistaSalida = obtenerMarcacion(fechaSalida, turno.getJornadaCodigo().getHSalida(), turno.getJornadaCodigo().isTerminaDiaSiguiente(), false, marcaciones);

        if (vistaSalida == null) {
            registro = null;
        } else {
            registro.setBiometricoId(biometricoDAO.searchByIp(vistaSalida.getEquipoIp()));
            registro.setFecha(vistaSalida.getFecha());
            registro.seteOS(true);
            registro.setEmpleadoId(empleado);
            registro.setTurnoOriginal(turno);
            registro.setHora(vistaSalida.getHora());
            registro.setTipo("--");
        }
        return registro;
    }

    private List<Permiso> permisosXEmpleado(String dni, int mes, int anio) {
        int primero = 1;
        int ultimo = this.ultimoDiaMes(mes, anio);

        String fechaI = "{d '" + anio + "-" + mes + "-" + primero + "'}";
        String fechaF = "{d '" + anio + "-" + mes + "-" + ultimo + "'}";

        String jpql = "SELECT pe FROM EmpleadoPermiso pe"
                + " WHERE pe.empleadoId.docIdentidad = :dni AND"
                + " (pe.permisoId.porFecha = false AND pe.permisoId.fechaInicio BETWEEN " + fechaI + " AND " + fechaF + ") "
                + " OR "
                + " (pe.permisoId.porFecha = true AND "
                + "    (pe.permisoId.fechaInicio BETWEEN " + fechaI + " AND " + fechaF + " OR pe.permisoId.fechaFin BETWEEN " + fechaI + " AND " + fechaF + "))";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("dni", dni);

        List<EmpleadoPermiso> lista = empleadoPermisoDAO.search(jpql, parametros);
        List<Permiso> permisos = new ArrayList<>();
        for (EmpleadoPermiso pe : lista) {
            permisos.add(pe.getPermisoId());
        }

        return permisos;
    }

    @Override
    public void setListaEmpleados(List<Empleado> listaEmpleados) {
        this.empleados = listaEmpleados;
    }

    public List<DetalleHorario> turnosXEmpleado(String dni, int mes, int anio) {

        int primero = 1;
        int ultimo = this.ultimoDiaMes(mes, anio);

        String fechaI = "{d '" + anio + "-" + mes + "-" + primero + "'}";
        String fechaF = "{d '" + anio + "-" + mes + "-" + ultimo + "'}";

        String sql = "SELECT eh FROM EmpleadoHorario eh"
                + " WHERE"
                + " eh.empleadoId.docIdentidad = :dni"
                + " AND (eh.horarioId.porFecha = false"
                + " OR (eh.horarioId.porFecha = true AND eh.horarioId.fecha BETWEEN " + fechaI + " AND " + fechaF + "))";

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("dni", dni);

        List<EmpleadoHorario> empleadoHorarios = empleadoHorarioDAO.search(sql, parametros);
        List<DetalleHorario> lista = new ArrayList<>();
        for (EmpleadoHorario empleadoHorario : empleadoHorarios) {
            List<DetalleHorario> horarioJornadas = empleadoHorario.getHorarioId().getDetalleHorarioList();
            lista.addAll(horarioJornadas);
        }
        return lista;
    }

    private int ultimoDiaMes(int mes, int anio) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(anio, mes - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    private List<Vista> vistaXEmpleado(String dni, int mes, int anio) {
        int primero = 1;
        int ultimo = this.ultimoDiaMes(mes, anio);

        String fechaI = "{d '" + anio + "-" + mes + "-" + primero + "'}";
        String fechaF = "{d '" + anio + "-" + mes + "-" + ultimo + "'}";

        String jpql = "SELECT v FROM Vista v WHERE v.dni = :dni AND v.fecha BETWEEN " + fechaI + " AND " + fechaF;

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("dni", Integer.parseInt(dni));

        return this.vistaDAO.search(jpql, parametros);
    }

    private void guardarRegistros(List<RegistroAsistencia> registros) {
        for (RegistroAsistencia registroAsistencia : registros) {
            LOG.info(registroAsistencia.toString());
            registroAsistenciaDAO.edit(registroAsistencia);
        }
        registros.clear();
    }

}
