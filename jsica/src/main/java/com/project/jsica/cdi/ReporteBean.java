/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.jsica.cdi;

import com.biosis.reportes.entidades.ReportePermisoBean;
import com.opencsv.CSVWriter;
import com.personal.utiles.FechaUtil;
import com.project.algoritmo.AnalisisFinalLocal;
import com.project.jsica.cdi.util.JsfUtil;
import com.project.jsica.ejb.entidades.Area;
import com.project.jsica.ejb.entidades.DetalleRegistroAsistencia;
import com.project.jsica.ejb.entidades.Empleado;
import com.project.jsica.ejb.entidades.EmpleadoPermiso;
import com.project.jsica.ejb.entidades.RegistroAsistencia;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author RyuujiMD
 */
@Named(value = "reporteBean")
@ConversationScoped
public class ReporteBean implements Serializable {

    @Inject
    private EmpleadoPermisoController empleadoPermisoController;
    @Inject
    private AreaController areaController;
    @Inject
    private RegistroAsistenciaController registroAsistenciaController;
    @Inject
    private EmpleadoController empleadoController;
    @EJB
    private AnalisisFinalLocal analisisService;
    private boolean nuevo = true;
    private Date desde;
    private Date hasta;
    private Empleado empleado;
    private Area areaSeleccionada;
    private Boolean conGoce;
    private int opcion = 1;
    private List<ReportePermisoBean> reportePermisos;
    private List<RegistroAsistencia> registroAsistencia;

    private RegistroAsistencia registroSeleccionado;

    public RegistroAsistencia getRegistroSeleccionado() {
        return registroSeleccionado;
    }

    public void setRegistroSeleccionado(RegistroAsistencia registroSeleccionado) {
        this.registroSeleccionado = registroSeleccionado;
    }

    public Boolean getConGoce() {
        return conGoce;
    }

    public void setConGoce(Boolean conGoce) {
        this.conGoce = conGoce;
    }

    public void setReportePermisos(List<ReportePermisoBean> reportePermisos) {
        this.reportePermisos = reportePermisos;
    }

    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(ReporteBean.class.getName());

    public List<Area> getAreas() {
        String sql = "SELECT a FROM Area a";
        return areaController.search(sql);
    }

    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        LOG.info("OPCION " + opcion);
        this.opcion = opcion;
        this.nuevo = true;
        realizarAnalisis();

    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        LOG.info("SE SELECCIONA UN EMPLEADO");
        this.empleado = empleado;
    }

    public Area getAreaSeleccionada() {
        return areaSeleccionada;
    }

    public void setAreaSeleccionada(Area areaSeleccionada) {
        LOG.info("SE SELECCIONA UN AREA");
        this.areaSeleccionada = areaSeleccionada;
    }

    public List<RegistroAsistencia> getReporteAsistencias() {
        if (nuevo) {
            if (opcion == 1) {
                LOG.info("REPORTE DE ASISTENCIA POR EMPLEADO");
                registroAsistencia = registroAsistenciaController.buscarXEmpleado(empleado, desde, hasta);
            } else {
                LOG.info("REPORTE DE ASISTENCIA POR AREA");
                registroAsistencia = registroAsistenciaController.buscarXArea(areaSeleccionada, desde, hasta);
            }
            nuevo = false;
        }

        return registroAsistencia;

    }

    public void reporteAsistenciaCSV() {
        DateFormat dfFecha = new SimpleDateFormat("dd.MM.yyyy");
        List<RegistroAsistencia> registros = getReporteAsistencias();

        OutputStream out = null;
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

            response.reset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=reporte-asistencia.csv"); // The Save As popup magic is done here. You can give it any file name you want, this only won't work in MSIE, it will use current request URL as file name instead.

            out = response.getOutputStream();

            CSVWriter writer = new CSVWriter(new OutputStreamWriter(out), ',');
            String[] linea = new String[7];

            for (RegistroAsistencia reg : registros) {
                linea[0] = reg.getEmpleado().getFichaLaboral().getCodigoTrabajador();
                linea[1] = String.format("%s %s", reg.getEmpleado().getApellidos(), reg.getEmpleado().getNombres());
                linea[2] = dfFecha.format(reg.getFecha());
                linea[3] = reg.getTurnoOriginal().getJornadaCodigo().getNombre();

                writer.writeNext(linea, true);
            }
            
            writer.flush();
            out.flush();
            writer.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException ex) {
            Logger.getLogger(ReporteBean.class.getName()).log(Level.WARN, null, ex);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(ReporteBean.class.getName()).log(Level.WARN, null, ex);
                }
            }
        }
    }

    public ReporteBean() {
    }

    private void agregarData(WritableSheet hoja, String[] titulos, List<String[]> contenido, Integer[] anchuras) {
        this.agregarData(hoja, titulos, contenido, anchuras, 0);
    }

    private void agregarData(WritableSheet hoja, String[] titulos, List<String[]> contenido, Integer[] anchuras, int fila) {
        int columnas = titulos.length;
        int filas = contenido.size();

        for (int filaActual = fila; filaActual <= filas; filaActual++) {
            for (int columna = 0; columna < columnas; columna++) {
                if (filaActual == fila) {
                    agregarCelda(hoja, filaActual, columna, titulos[columna]);
                    hoja.setColumnView(columna, anchuras[columna]);
                } else {
                    agregarCelda(hoja, filaActual, columna, contenido.get(filaActual - 1)[columna]);
                }
            }
        }
    }

    private List<Empleado> getEmpleados(int opcion) {
        List<Empleado> empleados = new ArrayList<>();
        if (opcion == 1) {
            LOG.info("SE AGREGA EMPLEADO");
            empleados.add(empleado);
        } else if (opcion == 2) {
            empleados.addAll(this.empleadoController.buscarTodos());
        }
        return empleados;
    }

    public void reporteHorasTrabajadas(int opcion) {
        this.opcion = opcion;
        SimpleDateFormat dtFecha = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat dtHora = new SimpleDateFormat("HH:mm");

        List<Empleado> empleados = this.getEmpleados(opcion);

        OutputStream out = null;

    }

//    public List getReporteHorasExtraTotal() {
//        List<Empleado> empleados = this.getEmpleados(opcion);
//        List<EmpleadoPermiso> permisos;
//        List<ReportePermisoBean> reporte = new ArrayList<>();
//        LOG.info("VIENE AL METODO REPORTE PERMISOS");
//        for (Empleado emp : empleados) {
//            permisos = this.empleadoPermisoController.buscarXEmpleado(emp, desde, hasta, conGoce);
//
//            List<DetalleRegistroAsistencia> detalles;
//            for (EmpleadoPermiso permiso : permisos) {
//                
//                detalles = permiso.getRegistroList();
//
//                if (!detalles.isEmpty()) {
//                    DetalleRegistroAsistencia detalleEntrada = detalles.get(0);
//                    DetalleRegistroAsistencia detalleSalida = detalles.get(1);
//                    DetalleRegistroAsistencia aux;
//
//                    if (detalleEntrada.getCarga() != 23 && detalleEntrada.getCarga() != 23) {
//                        if (detalleEntrada.getHora().compareTo(detalleSalida.getHora()) < 0) {
//                            aux = detalleEntrada;
//                            detalleEntrada = detalleSalida;
//                            detalleSalida = aux;
//                        }
//
//                        long milisegundosDiferencia = detalleEntrada.getHora().getTime() - detalleSalida.getHora().getTime();
//
//                        int hora[] = FechaUtil.milisToTime(milisegundosDiferencia);
//
//                        ReportePermisoBean reporteBean = new ReportePermisoBean();
//                        reporteBean.setCodigo(emp.getFichaLaboral().getCodigoTrabajador());
//                        reporteBean.setNombre(emp.getApellidos() + " " +emp.getNombres());
//                        reporteBean.setHoraInicio(detalleSalida.getHora());
//                        reporteBean.setHoraFin(detalleEntrada.getHora());
//                        reporteBean.setHoras(hora[0]);
//                        reporteBean.setMinutos(hora[1]);
//                        reporteBean.setSegundos(hora[2]);
//                        reporteBean.setFechaReal(detalleEntrada.getFecha());
//                        reporteBean.setMotivo(permiso.getPermisoId().getMotivoPermisoCodigo().getNombre());
//
//                        reporte.add(reporteBean);
//
//                    }
//                }
//
//            }
//        }
//
//        return reporte;
//    }
//    public void reporteHorasExtra(int opcion) {
//        this.opcion = opcion;
//        SimpleDateFormat dtFecha = new SimpleDateFormat("dd.MM.yyyy");
//        SimpleDateFormat dtHora = new SimpleDateFormat("HH:mm");
//
//        List<Empleado> empleados = this.getEmpleados(opcion);
//
//        OutputStream out = null;
//
//        try {
//            String header = "REPORTE_DE_HORAS_EXTRA_DEL_" + dtFecha.format(desde) + "_AL_" + dtFecha.format(hasta) + ".xls";
//            out = JsfUtil.getOutputStream(header, JsfUtil.ContentType.XLS);
//
//            WritableWorkbook w = Workbook.createWorkbook(out);
//            WritableSheet hojaResumen = w.createSheet("RESUMEN", 0);
//            WritableSheet hojaDetalle = w.createSheet("DETALLE", 1);
//
//            String[] tituloResumen = {"CODIGO", "APELLIDOS Y NOMBRES", "TOTAL HORAS", "TOTAL MINUTOS", "TOTAL SEGUNDOS"};
//            String[] subtituloDetalle = {"FECHA", "HORAS", "MINUTOS", "SEGUNDOS"};
//            String[] tituloDetalle = {"CODIGO", "APELLIDOS Y NOMBRES"};
//            Integer[] anchurasResumen = {10, 40, 10, 10, 10};
//            Integer[] anchurasDetalle = {10, 10, 10, 10};
//
//            List<String[]> resumen = new ArrayList<>();
//
//            // CODIGO - APELLIDOS Y NOMBRES
//            int filaResumen = 0;
//            int filaDetalle = 0;
//
//            for (Empleado emp : empleados) {
//                List<RegistroAsistencia> registrosSalida = registroAsistenciaController.getRegistros(false, false, false, emp, desde, hasta);
//                List<String[]> detalles = new ArrayList<>();
//
//                //LLENANDO EL DETALLE
//                hojaDetalle.mergeCells(1, filaDetalle, 3, filaDetalle);
//                filaDetalle++;
//                agregarCelda(hojaDetalle, filaDetalle, 0, emp.getFichaLaboral().getCodigoTrabajador());
//                agregarCelda(hojaDetalle, filaDetalle, 1, emp.getApellidos() + emp.getNombres());
//                filaDetalle++;
//
//                int filaInicio = filaDetalle;
//                long milisegundosTotales = 0;
//
//                for (RegistroAsistencia ra : registrosSalida) {
//                    long diferencia = ra.getHora().getTime() - ra.getTurnoOriginal().getJornadaCodigo().getHSalida().getTime();
//                    if (diferencia > 0) {
//                        int horas = (int) diferencia / (60 * 60 * 1000);
//                        int minutos = (int) (Math.abs(diferencia - (horas * 60 * 60 * 1000)) / (60 * 1000));
//                        String detalle[] = {dtFecha.format(ra.getFecha()), horas + "", minutos + "", ""};
//                        detalles.add(detalle);
//                        milisegundosTotales += diferencia;
//
//                        filaDetalle++;
//                    }
//                }
//
//                //SE AGREGA LA DATA DEL DETALLE A LA HOJA
//                this.agregarData(hojaDetalle, subtituloDetalle, detalles, anchurasDetalle, filaInicio);
//
//                filaDetalle++;
//
//                //LLENADO DEL RESUMEN
//                int horas = (int) milisegundosTotales / (60 * 60 * 1000);
//                int minutos = (int) (Math.abs(milisegundosTotales - (horas * 60 * 60 * 1000)) / (60 * 1000));
//
//                String[] resmn = {emp.getFichaLaboral().getCodigoTrabajador(), emp.getApellidos() + " " + emp.getNombres(), horas + "", minutos + "", ""};
//                resumen.add(resmn);
//            }
//
//            this.agregarData(hojaResumen, tituloResumen, resumen, anchurasResumen);
//
//            w.write();
//            w.close();
//
//            FacesContext.getCurrentInstance().responseComplete();
//        } catch (Exception e) {
//        }
//    }
//
//    public List getReportePermisos() {
//        List<Empleado> empleados = this.getEmpleados(opcion);
//        List<EmpleadoPermiso> permisos;
//        List<ReportePermisoBean> reporte = new ArrayList<>();
//        LOG.info("VIENE AL METODO REPORTE PERMISOS");
//        for (Empleado emp : empleados) {
//            permisos = this.empleadoPermisoController.buscarXEmpleado(emp, desde, hasta, conGoce);
//
//            List<DetalleRegistroAsistencia> detalles;
//            for (EmpleadoPermiso permiso : permisos) {
//                
//                detalles = permiso.getRegistroList();
//
//                if (!detalles.isEmpty()) {
//                    DetalleRegistroAsistencia detalleEntrada = detalles.get(0);
//                    DetalleRegistroAsistencia detalleSalida = detalles.get(1);
//                    DetalleRegistroAsistencia aux;
//
//                    if (detalleEntrada.getCarga() != 23 && detalleEntrada.getCarga() != 23) {
//                        if (detalleEntrada.getHora().compareTo(detalleSalida.getHora()) < 0) {
//                            aux = detalleEntrada;
//                            detalleEntrada = detalleSalida;
//                            detalleSalida = aux;
//                        }
//
//                        long milisegundosDiferencia = detalleEntrada.getHora().getTime() - detalleSalida.getHora().getTime();
//
//                        int hora[] = FechaUtil.milisToTime(milisegundosDiferencia);
//
//                        ReportePermisoBean reporteBean = new ReportePermisoBean();
//                        reporteBean.setCodigo(emp.getFichaLaboral().getCodigoTrabajador());
//                        reporteBean.setNombre(emp.getApellidos() + " " +emp.getNombres());
//                        reporteBean.setHoraInicio(detalleSalida.getHora());
//                        reporteBean.setHoraFin(detalleEntrada.getHora());
//                        reporteBean.setHoras(hora[0]);
//                        reporteBean.setMinutos(hora[1]);
//                        reporteBean.setSegundos(hora[2]);
//                        reporteBean.setFechaReal(detalleEntrada.getFecha());
//                        reporteBean.setMotivo(permiso.getPermisoId().getMotivoPermisoCodigo().getNombre());
//
//                        reporte.add(reporteBean);
//
//                    }
//                }
//
//            }
//        }
//
//        return reporte;
//    }
//    public void reportePermisos(int opcion) {
//        this.opcion = opcion;
//        SimpleDateFormat dtFecha = new SimpleDateFormat("dd.MM.yyyy");
//        SimpleDateFormat dtHora = new SimpleDateFormat("HH:mm");
//
//        List<Empleado> empleados = this.getEmpleados(opcion);
//
//        OutputStream out = null;
//        try {
//            String header = "REPORTE_DE_PERMISOS_DEL_" + dtFecha.format(desde) + "_AL_" + dtFecha.format(hasta) + ".xls";
//            out = JsfUtil.getOutputStream(header, JsfUtil.ContentType.XLS);
//
//            WritableWorkbook w = Workbook.createWorkbook(out);
//            WritableSheet hoja1 = w.createSheet("SIN SUSTENTO", 0);
//            WritableSheet hoja2 = w.createSheet("CON SUSTENTO", 1);
//
//            String[] titulos = {"CODIGO", "APELLIDOS Y NOMBRES", "FECHA REAL", "INICIO", "FIN", "MINUTOS", "HORAS", "MOTIVO"};
//            Integer[] anchuras = {10, 30, 10, 10, 10, 10, 10, 10};
//            List<String[]> contenidos1 = new ArrayList<>();
//            List<String[]> contenidos2 = new ArrayList<>();
//
//            if (empleados.isEmpty()) {
//                LOG.info("NO HAY EMPLEADOS");
//                JsfUtil.addErrorMessage("NO HAY DATOS");
//            } else {
//                LOG.info("SI HAY EMPLEADOS");
//                for (Empleado emp : empleados) {
//                    List<Permiso> permisos = empleadoPermisoController.permisosXEmpleado(desde, hasta, emp);
//
//                    for (Permiso permiso : permisos) {
//                        List<RegistroAsistencia> registros = permiso.getRegistroList();
//
//                        RegistroAsistencia registro1 = registros.get(0);
//                        RegistroAsistencia registro2 = registros.get(1);
//
//                        Date horaInicio;
//                        Date horaFin;
//
//                        if (registro1.getHora().compareTo(registro2.getHora()) < 0) {
//                            horaInicio = registro1.getHora();
//                            horaFin = registro2.getHora();
//                        } else {
//                            horaInicio = registro2.getHora();
//                            horaFin = registro1.getHora();
//                        }
//
//                        long diferencia = Math.abs(registro1.getHora().getTime() - registro2.getHora().getTime());
//                        int horas = (int) diferencia / (60 * 60 * 1000);
//                        int minutos = (int) (Math.abs(diferencia - (horas * 60 * 60 * 1000)) / (60 * 1000));
//
//                        String[] contenido = new String[titulos.length];
//                        contenido[0] = emp.getFichaLaboral().getCodigoTrabajador();
//                        contenido[1] = emp.getApellidos() + " " + emp.getNombres();
//                        contenido[2] = dtFecha.format(permiso.getFechaInicio());
//                        contenido[3] = dtHora.format(horaInicio);
//                        contenido[4] = dtHora.format(horaFin);
//                        contenido[5] = minutos + "";
//                        contenido[6] = horas + "";
//                        contenido[7] = permiso.getMotivoPermisoCodigo().getNombre();
//
//                        if (permiso.getMotivoPermisoCodigo().getConGoce()) {
//                            LOG.info("CON GOCE");
//                            contenidos2.add(contenido);
////                        agregarCelda(hoja1, fila2, 7, "SUSTENTO");
//                        } else {
//                            LOG.info("SIN GOCE");
//                            contenidos1.add(contenido);
//                        }
//
//                    }
//                }
//
//                agregarData(hoja2, titulos, contenidos2, anchuras);
//                agregarData(hoja1, titulos, contenidos1, anchuras);
//
//                w.write();
//                w.close();
//
//                FacesContext.getCurrentInstance().responseComplete();
//            }
//
//        } catch (IOException | WriteException ex) {
//            Logger.getLogger(ReporteBean.class.getName()).log(Level.WARN, null, ex);
//        } finally {
//            if (out != null) {
//                try {
//                    out.close();
//                } catch (IOException ex) {
//                    Logger.getLogger(ReporteBean.class.getName()).log(Level.WARN, null, ex);
//                }
//            }
//        }
//    }
    public void agregarHoja(Workbook libro, String nombre) {

    }

    public void agregarCelda(WritableSheet hoja, int fila, int columna, String contenido) {
        try {
            WritableCellFormat cellFormat = new WritableCellFormat();
            cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
            if (fila == 0) {
                cellFormat.setBackground(Colour.ORANGE);
            }

            hoja.addCell(new Label(columna, fila, contenido, cellFormat));

        } catch (WriteException ex) {
            java.util.logging.Logger.getLogger(ReporteBean.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void reporte() {
        OutputStream out = null;
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

            response.reset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=reporte"); // The Save As popup magic is done here. You can give it any file name you want, this only won't work in MSIE, it will use current request URL as file name instead.

            out = response.getOutputStream();

            WritableWorkbook w = Workbook.createWorkbook(out);
            WritableSheet hoja1 = w.createSheet("Reporte", 0);

            //FILA DE TITULO 
            agregarCelda(hoja1, 0, 0, "CODIGO");
            agregarCelda(hoja1, 0, 1, "APELLIDOS Y NOMBRES");
            agregarCelda(hoja1, 0, 2, "FECHA REAL");
            agregarCelda(hoja1, 0, 3, "INICIO");
            agregarCelda(hoja1, 0, 4, "FIN");
            agregarCelda(hoja1, 0, 5, "MINUTOS");
            agregarCelda(hoja1, 0, 6, "MOTIVO");
            agregarCelda(hoja1, 0, 7, "SUSTENTO");

            w.write();
            w.close();

            fc.responseComplete();
        } catch (IOException | WriteException ex) {
            Logger.getLogger(ReporteBean.class.getName()).log(Level.WARN, null, ex);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(ReporteBean.class.getName()).log(Level.WARN, null, ex);
                }
            }
        }
    }

    private void realizarAnalisis() {
        if (opcion == 2) {
            LOG.info("SE REALIZA EL ANALISIS POR AREA");
            analisisService.analizarEmpleados(areaSeleccionada);
        }
    }

}
