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
import com.project.jsica.ejb.entidades.Jornada;
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
                CambioTurno cambioTurno = getCambioTurno(turno, cambiosTurnoXMes);
                DetalleHorario turnoAnalizar;
                DetalleHorario turnoCambio = null;

                if (cambioTurno != null) {
                    if (cambioTurno.getDetalleHorarioOriginal().equals(turno)) {
                        turnoAnalizar = cambioTurno.getDetalleHorarioReemplazo();
                        turnoCambio = cambioTurno.getDetalleHorarioOriginal();
                    } else {
                        turnoAnalizar = cambioTurno.getDetalleHorarioOriginal();
                        turnoCambio = cambioTurno.getDetalleHorarioReemplazo();
                    }
                } else {
                    turnoAnalizar = turno;
                }

                RegistroAsistencia2 registro = analizarTurnoAsistencial(empleado, fechaInicio, horaInicio, fechaFin, horaFin, turnoAnalizar, turnoCambio, permisosXMes, marcacionesXMes);

                if (registro != null) {
                    registrosXMes.add(registro);
                }

            } else {
                LOG.info("ANALIZANDO HORARIO ADMINISTRATIVO");
                registrosXMes.addAll(analizarTurnoAdministrativo(empleado, fechaInicio, horaInicio, fechaFin, horaFin, turno, permisosXMes, marcacionesXMes));
            }
        }

        return registrosXMes;
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
        Date fecha = inicio.getTime();
        while (!fecha.after(fechaHasta)) {
            LOG.info("RECORRIENDO DIA A DIA " + fecha.toString());
            Date horaAnalisis = hora.getTime();

            RegistroAsistencia2 registro = analizarRegistro(empleado, turno, fecha, horaAnalisis, fechaHasta, horaHasta, marcacionesXMes, permisosXMes);

            if (registro != null) {
                registros.add(registro);
            }

            hora.set(Calendar.HOUR_OF_DAY, hora.getMinimum(Calendar.HOUR_OF_DAY));

            inicio.add(Calendar.DAY_OF_MONTH, 1);

            contador++;
            fecha = inicio.getTime();
        }
//        for (Date fecha = inicio.getTime(); !fecha.after(fechaHasta); inicio.add(Calendar.DAY_OF_MONTH, 1)) {
//            LOG.info("RECORRIENDO DIA A DIA " + fecha.toString());
//            Date horaAnalisis = hora.getTime();
//
//            RegistroAsistencia2 registro = analizarRegistro(empleado, turno, fecha, horaAnalisis, fechaHasta, horaHasta, marcacionesXMes, permisosXMes);
//
//            if (registro != null) {
//                registros.add(registro);
//            }
//
//            hora.set(Calendar.HOUR_OF_DAY, hora.getMinimum(Calendar.HOUR_OF_DAY));
//            contador++;
//        }

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

        boolean falta = false;

        if (FechaUtil.compararFechaHora(fechaInicio, horaInicio, fechaSalida.getTime(), horaSalida.getTime()) <= 0
                && FechaUtil.compararFechaHora(fechaFin, horaFin, fechaSalida.getTime(), horaSalida.getTime()) >= 0) {

            LOG.info("SE COMPARO LAS FECHAS Y HORAS, SE DA INICIO AL ANALISIS");
            RegistroAsistencia2 registro = new RegistroAsistencia2();
            registro.setEmpleado(empleado);
            registro.setFecha(fecha);

            String tipo;
            //SE PROCEDE A ANALIZAR CADA DETALLE 
            EmpleadoPermiso permisoXFecha = tienePermisoXFecha(fecha, permisosXMes);

            if (permisoXFecha != null) {
                LOG.info("PERMISO POR FECHA");
                String codigo = permisoXFecha.getPermisoId().getMotivoPermisoCodigo().getCodigo();

                switch (codigo) {
                    case "VAC":
                        tipo = "V";
                        break;
                    case "LIC":
                        tipo = "L";
                        break;
                    default:
                        tipo = "P";
                        break;
                }
                registro.setPermiso(permisoXFecha);

            } else if (isOnomastico(fecha, empleado) && !turno.getHorarioId().getPorFecha()) {
                LOG.info("ES ONOMASTICO");

            } else if (isDiaLaboral(fecha, turno) || turno.getHorarioId().getPorFecha()) {
                LOG.info("ES DIA LABORAL");
                Feriado feriado = isFeriado(fecha, feriadosXAnio);

                if (feriado != null && !turno.getHorarioId().getPorFecha()) {
                    LOG.info("ES FERIADO");
                    tipo = "F";
                    registro.setFeriado(feriado);
                    registro.setTipo(tipo);
                } else {
                    List<EmpleadoPermiso> permisosXHora = isPermisoPorhora(fecha, fechaSalida.getTime(), turno, permisosXMes);

                    boolean permisoHoraEntrada = false;
                    boolean permisoHoraSalida = false;
                    boolean permisoRefrigerio = false;

                    Date registroEntrada = null;
                    Date registroSalida = null;

                    long tardanza = 0;
                    long extra = 0;
                    int cargaTotal = 0;

                    List<DetalleRegistroAsistencia> detalle = new ArrayList<>();
                    Jornada jornada = turno.getJornadaCodigo();
                    for (EmpleadoPermiso permiso : permisosXHora) {
//                        DetalleRegistroAsistencia detalle = new DetalleRegistroAsistencia();

                        Date horaPermiso = permiso.getPermisoId().getHoraInicio();

                        List<DetalleRegistroAsistencia> permisos = analizarPermiso(
                                permiso,
                                registro,
                                fecha,
                                horaPermiso,
                                turno.getJornadaCodigo().getHSalida(),
                                MINUTOS_ANTES_DE_MARCAR_PERMISO,
                                MINUTOS_DESPUES_DE_INICIO_PERMISO,
                                marcacionesXMes
                        );

                        detalle.addAll(permisos);

                        for (DetalleRegistroAsistencia dra : permisos) {

                            if (dra.getHora().compareTo(jornada.getHEntrada()) == 0) {
                                permisoHoraEntrada = true;
                                registroEntrada = dra.getHora();
                            }

                            if (dra.getHora().compareTo(jornada.getHSalida()) == 0) {
                                permisoHoraSalida = true;
                                registroSalida = dra.getHora();
                            }
                        }

                        DetalleRegistroAsistencia dra1 = permisos.get(0);
                        DetalleRegistroAsistencia dra2 = permisos.get(1);

                        if (jornada.getJornadaConRefrigerio()) {
                            if (dra1.getHora().compareTo(horaPermiso) <= 0 && dra2.getHora().compareTo(horaPermiso) >= 0) {
                                permisoRefrigerio = true;
                            }
                        }

                    }

                    //ANALIZAMOS LOS OTROS COMPONENTES                    
                    if (permisoHoraEntrada) {
                        DetalleRegistroAsistencia detalleRegistroAsistencia = new DetalleRegistroAsistencia();
                        detalleRegistroAsistencia.setCarga(1);
                        detalleRegistroAsistencia.setFecha(fecha);
                        detalleRegistroAsistencia.setHora(registroEntrada);
                        detalleRegistroAsistencia.setRegistroAsistencia(registro);
                        detalleRegistroAsistencia.setTipoEvento("E");
                        detalleRegistroAsistencia.setTipoRegistro("T");

                        detalle.add(detalleRegistroAsistencia);
                    } else {
                        DetalleRegistroAsistencia draEntrada
                                = analizarEvento(
                                        fecha,
                                        jornada.getHEntrada(),
                                        MINUTOS_ANTES_DE_MARCAR,
                                        jornada.getMinutosToleranciaRegularEntradaJornada() + jornada.getMinutosToleranciaTardanzaEntradaJornada(),
                                        "E",
                                        "T",
                                        registro,
                                        marcacionesXMes
                                );
                        if (draEntrada.getCarga() != 23) {
                            Long tardanzaEntrada
                                    = this.milisegundosDiferencia(jornada.getHEntrada(), draEntrada.getHora(), jornada.getMinutosToleranciaRegularEntradaJornada());
                            draEntrada.setMilisegundosTardanza(tardanza);
                            tardanza += tardanzaEntrada;

                        } else {
                            falta = true;
                        }

                        detalle.add(draEntrada);
                    }

                    if (permisoHoraSalida) {
                        DetalleRegistroAsistencia detalleRegistroAsistencia = new DetalleRegistroAsistencia();
                        detalleRegistroAsistencia.setCarga(-1);
                        detalleRegistroAsistencia.setFecha(fechaSalida.getTime());
                        detalleRegistroAsistencia.setHora(registroSalida);
                        detalleRegistroAsistencia.setRegistroAsistencia(registro);
                        detalleRegistroAsistencia.setTipoEvento("S");
                        detalleRegistroAsistencia.setTipoRegistro("T");

                        detalle.add(detalleRegistroAsistencia);
                    } else {
                        DetalleRegistroAsistencia draSalida
                                = analizarEvento(
                                        fecha,
                                        jornada.getHSalida(),
                                        0,
                                        MINUTOS_DESPUES_DE_HORA_SALIDA,
                                        "S",
                                        "T",
                                        registro,
                                        marcacionesXMes
                                );
                        if (draSalida.getCarga() != 23) {
                            Long diferencia = this.milisegundosDiferencia(jornada.getHSalida(), draSalida.getHora(), 0);
                            extra += diferencia;
                            draSalida.setMilisegundosExtra(diferencia);
                        }

                        detalle.add(draSalida);
                    }

                    if (jornada.getJornadaConRefrigerio()) {
                        LOG.info("ANALIZANDO EL REFRIGERIO");
                        if (!permisoRefrigerio) {
                            DetalleRegistroAsistencia draSalidaRefrigerio
                                    = analizarEvento(
                                            fecha,
                                            jornada.getHSalidaRefrigerio(),
                                            0,
                                            30,
                                            "S",
                                            "R",
                                            registro,
                                            marcacionesXMes
                                    );

                            DetalleRegistroAsistencia draEntradaRefrigerio
                                    = analizarEvento(
                                            fecha,
                                            jornada.getHSalidaRefrigerio(),
                                            30,
                                            210,
                                            "E",
                                            "R",
                                            registro,
                                            marcacionesXMes
                                    );
                            if (draEntradaRefrigerio.getCarga() != 23) {
                                Long diferencia = this.milisegundosDiferencia(jornada.getHEntradaRefrigerio(), draEntradaRefrigerio.getHora(), 0);
                                draEntradaRefrigerio.setMilisegundosTardanza(diferencia);
                                tardanza += diferencia;
                            } else {
                                falta = true;
                            }

                            //ANALIZAMOS SI HA LLEGADO TARDE
                            detalle.add(draSalidaRefrigerio);
                            detalle.add(draEntradaRefrigerio);
                        }
                    }
                    if (falta) {
                        tipo = "A";
                    } else if (tardanza > 0) {
                        tipo = "T";
                    } else {
                        tipo = "R";
                    }
                    registro.setTurnoOriginal(turno);
                    registro.setTipo(tipo);
                    registro.setMilisegundosExtra(extra);
                    registro.setMilisegundosTardanza(tardanza);
                    registro.setDetalleRegistroAsistenciaList(detalle);
//                    registro.setFecha(fecha);
                    return registro;
                }

            } else {
                LOG.warn("NO ES DIA LABORAL " + fecha);
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

        int comparacion = dia.get(Calendar.DAY_OF_WEEK);

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
        LOG.info("¿ES FERIADO?" + fecha);
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

    private List<DetalleRegistroAsistencia> analizarPermiso(EmpleadoPermiso permiso, RegistroAsistencia2 registro, Date fecha, Date horaInicio, Date horaMaxima, int minutosAntes, int minutosDespues, List<Vista> marcaciones) {

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

        if (marcacionInicio == null) {
            cargaInicio = 23;
            horaI = permiso.getPermisoId().getHoraInicio();
        } else {
            cargaInicio = 1;
            horaI = marcacionInicio.getHora();
        }

        if (marcacionFinal == null) {
            cargaFin = 23;
            horaF = horaMaxima;
        } else {
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
        inicioPermiso.setPermiso(permiso);

        finPermiso.setFecha(fecha);
        finPermiso.setCarga(cargaFin);
        finPermiso.setHora(horaF);
        finPermiso.setRegistroAsistencia(registro);
        finPermiso.setTipoEvento("E");
        finPermiso.setTipoRegistro(tipoRegistro);
        finPermiso.setPermiso(permiso);

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
                if (desde.compareTo(v.getHora()) <= 0 && hasta.compareTo(v.getHora()) >= 0) {
                    comparacion = v.getHora().getTime() - desde.getTime();

                    if (bandera) {
                        vista = v;
                        menor = comparacion;
                        bandera = false;
                    } else if (menor > comparacion) {
                        vista = v;
                        menor = comparacion;
                    }

                }
            }
        }

        return vista;
    }

    private Long milisegundosDiferencia(Date horaRegular, Date horaMarcada, int toleranciaRegular) {
        LOG.info("DIFERENCIA ENTRE " + horaRegular + " y " + horaMarcada + " CON TOLERANCIA " + toleranciaRegular);

        Calendar cal = Calendar.getInstance();
        cal.setTime(horaRegular);
        cal.add(Calendar.MINUTE, toleranciaRegular);

        Long diferencia = horaMarcada.getTime() - cal.getTimeInMillis();

        if (diferencia < 0) {
            return Long.parseLong("0");
        } else {
            return diferencia;
        }
    }

    private DetalleRegistroAsistencia analizarEvento(
            Date fecha,
            Date hora,
            int minutosAntes,
            int minutosDespues,
            String tipoEvento,
            String tipoRegistro,
            RegistroAsistencia2 registro,
            List<Vista> marcacionesXMes) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(hora);
        cal.add(Calendar.MINUTE, -minutosAntes);

        Date desde = cal.getTime();

        cal.add(Calendar.MINUTE, minutosAntes + minutosDespues);

        Date hasta = cal.getTime();

        Vista marcacion = obtenerMarcacion(fecha, desde, hasta, marcacionesXMes);

        Date horaRegistro;
        int carga;

        if (marcacion == null) {
            horaRegistro = null;
            carga = 23;
        } else {
            horaRegistro = marcacion.getHora();
            if (tipoEvento.equals("E")) {
                carga = 1;
            } else {
                carga = -1;
            }
        }

        DetalleRegistroAsistencia detalleRegistroAsistencia = new DetalleRegistroAsistencia();
        detalleRegistroAsistencia.setCarga(carga);
        detalleRegistroAsistencia.setFecha(fecha);
        detalleRegistroAsistencia.setHora(horaRegistro);
        detalleRegistroAsistencia.setRegistroAsistencia(registro);
        detalleRegistroAsistencia.setTipoEvento(tipoEvento);
        detalleRegistroAsistencia.setTipoRegistro(tipoRegistro);

        return detalleRegistroAsistencia;

    }

    private RegistroAsistencia2 analizarTurnoAsistencial(Empleado empleado, Date fechaInicio, Date horaInicio, Date fechaFin, Date horaFin, DetalleHorario turnoAnalizar, DetalleHorario turnoCambio, List<EmpleadoPermiso> permisosXMes, List<Vista> marcacionesXMes) {
        RegistroAsistencia2 registro;
        LOG.info("ENTRA A ANALIZAR EL REGISTRO");
        registro = analizarRegistro(empleado, turnoAnalizar, turnoAnalizar.getFecha(), horaInicio, fechaFin, horaFin, marcacionesXMes, permisosXMes);

        if (registro != null && turnoCambio != null) {
            LOG.info("SE ASIGNA EL TURNO POR EL QUE FUE CAMBIADO");
            registro.setTurnoReemplazo(turnoCambio);
        }

        return registro;
    }

}
