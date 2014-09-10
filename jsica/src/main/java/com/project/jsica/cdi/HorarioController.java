package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.HorarioFacadeLocal;
import com.project.jsica.ejb.entidades.Area;
import com.project.jsica.ejb.entidades.Bitacora;
import com.project.jsica.ejb.entidades.Empleado;
import com.project.jsica.ejb.entidades.Horario;
import com.project.jsica.ejb.entidades.Servicio;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named(value = "horarioController")
@ViewScoped
public class HorarioController extends AbstractController<Horario> {

    private Empleado empleadoSeleccionado;
    private boolean isEmpleadoSeleccionado;

    private Area departamentoSeleccionado;
    private boolean isDepartamentoSeleccionado;

    private Servicio servicioSeleccionado;
    private boolean isServicioSeleccionado;
    private int mesSeleccionado;
    private boolean isMesSeleccionado;
    private int anioSeleccionado;

    @EJB
    private HorarioFacadeLocal horarioFacade;

    @Inject
    private BitacoraController bitacoraC;

    @Inject
    private DetalleHorarioController detalleHorarioListController;
    @Inject
    private EmpleadoHorarioController empleadoHorarioListController;
    @Inject
    private EmpleadoController empleadoListController;

    //Getters and setters
    public Empleado getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    public void setEmpleadoSeleccionado(Empleado empleadoSeleccionado) {
        this.empleadoSeleccionado = empleadoSeleccionado;
    }

    public boolean isIsEmpleadoSeleccionado() {
        return isEmpleadoSeleccionado;
    }

    public void setIsEmpleadoSeleccionado(boolean isEmpleadoSeleccionado) {
        this.isEmpleadoSeleccionado = isEmpleadoSeleccionado;
    }

    public Area getDepartamentoSeleccionado() {
        return departamentoSeleccionado;
    }

    public void setDepartamentoSeleccionado(Area departamentoSeleccionado) {
        this.departamentoSeleccionado = departamentoSeleccionado;
    }

    public boolean isIsDepartamentoSeleccionado() {
        return isDepartamentoSeleccionado;
    }

    public void setIsDepartamentoSeleccionado(boolean isDepartamentoSeleccionado) {
        this.isDepartamentoSeleccionado = isDepartamentoSeleccionado;
    }

    public Servicio getServicioSeleccionado() {
        return servicioSeleccionado;
    }

    public void setServicioSeleccionado(Servicio servicioSeleccionado) {
        this.servicioSeleccionado = servicioSeleccionado;
    }

    public boolean isIsServicioSeleccionado() {
        return isServicioSeleccionado;
    }

    public void setIsServicioSeleccionado(boolean isServicioSeleccionado) {
        this.isServicioSeleccionado = isServicioSeleccionado;
    }

    public int getMesSeleccionado() {
        return mesSeleccionado;
    }

    public void setMesSeleccionado(int mesSeleccionado) {
        this.mesSeleccionado = mesSeleccionado;
    }

    public int getAnioSeleccionado() {
        return anioSeleccionado;
    }

    public void setAnioSeleccionado(int anioSeleccionado) {
        this.anioSeleccionado = anioSeleccionado;
    }

    public boolean isIsMesSeleccionado() {
        return isMesSeleccionado;
    }

    public void setIsMesSeleccionado(boolean isMesSeleccionado) {
        this.isMesSeleccionado = isMesSeleccionado;
    }

    
    public HorarioController() {
        // Inform the Abstract parent controller of the concrete Horario?cap_first Entity
        super(Horario.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of DetalleHorario entities
     * that are retrieved from Horario?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for DetalleHorario page
     */
    public String navigateDetalleHorarioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetalleHorario_items", this.getSelected().getDetalleHorarioList());
        }
        return "/detalleHorario/index";
    }

    /**
     * Sets the "items" attribute with a collection of EmpleadoHorario entities
     * that are retrieved from Horario?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for EmpleadoHorario page
     */
    public String navigateEmpleadoHorarioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EmpleadoHorario_items", this.getSelected().getEmpleadoHorarioList());
        }
        return "/empleadoHorario/index";
    }

    @Override
    protected void edit(Horario objeto) {
        this.horarioFacade.edit(objeto);

        if (this.esNuevo) {
            Bitacora bitacora = new Bitacora();
            //----Bitacora----
            //Fecha y hora//          
            Date fechas = new Date();//           
            //Ip Cliente
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();

            String nombre = this.selected.getNombre();
            String descripcion = this.selected.getDescripcion();
            String porFecha = String.valueOf(this.selected.getPorFecha());
            String fecha = this.selected.getFecha().toString();
            String lunes = String.valueOf(this.selected.getLunes());
            String martes = String.valueOf(this.selected.getMartes());
            String miercoles = String.valueOf(this.selected.getMiercoles());
            String jueves = String.valueOf(this.selected.getJueves());
            String viernes = String.valueOf(this.selected.getViernes());
            String sabado = String.valueOf(this.selected.getSabado());
            String domingo = String.valueOf(this.selected.getDomingo());

            bitacora.setUsuario("JC");
            bitacora.setIpCliente(ip_cliente);
            bitacora.setFecha(fechas);
            bitacora.setHora(fechas);
            bitacora.setTabla("HORARIO");
            bitacora.setColumna("NOMBRE");
            bitacora.setAccion("CREAR");
            bitacora.setValorAct(nombre);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("DESCRIPCION");
            bitacora.setValorAct(descripcion);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("POR_FECHA");
            bitacora.setValorAct(porFecha);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("FECHA");
            bitacora.setValorAct(fecha);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("LUNES");
            bitacora.setValorAct(lunes);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("MARTES");
            bitacora.setValorAct(martes);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("MIERCOLES");
            bitacora.setValorAct(miercoles);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("JUEVES");
            bitacora.setValorAct(jueves);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("VIERNES");
            bitacora.setValorAct(viernes);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("SABADO");
            bitacora.setValorAct(sabado);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("DOMINGO");
            bitacora.setValorAct(domingo);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);
        } else {
            //Datos antes de modificar
            Horario antes = this.find(this.selected.getId());

            String nombre1 = antes.getNombre();
            String descripcion1 = antes.getDescripcion();
            String porFecha1 = String.valueOf(antes.getPorFecha());
            String fecha1 = antes.getFecha().toString();
            String lunes1 = String.valueOf(antes.getLunes());
            String martes1 = String.valueOf(antes.getMartes());
            String miercoles1 = String.valueOf(antes.getMiercoles());
            String jueves1 = String.valueOf(antes.getJueves());
            String viernes1 = String.valueOf(antes.getViernes());
            String sabado1 = String.valueOf(antes.getSabado());
            String domingo1 = String.valueOf(antes.getDomingo());

            //Datos despues de modificar
            String nombre2 = this.selected.getNombre();

            //----Bitacora----
            Bitacora bitacora = new Bitacora();
            //Fecha y hora//          
            Date fechas = new Date();
//           
            //Ip Cliente
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();

            //Datos
            bitacora.setUsuario("JC");
            bitacora.setIpCliente(ip_cliente);
            bitacora.setFecha(fechas);
            bitacora.setHora(fechas);
            bitacora.setTabla("HORARIO");
            bitacora.setColumna("NOMBRE");
            bitacora.setAccion("MODIFICAR");
            bitacora.setValorAct(nombre2);
            bitacora.setValorAnt(nombre1);

            if (!nombre1.equals(nombre2)) {
                bitacoraC.edit(bitacora);
            }
        }
    }

    @Override
    protected void remove(Horario objeto) {
        this.horarioFacade.remove(objeto);
    }

    @Override
    public Horario find(Object id) {
        return this.horarioFacade.find(id);
    }

    @Override
    public List<Horario> getItems() {
        return this.horarioFacade.findAll();
    }

    @Override
    public List<Horario> search(String namedQuery) {
        return this.horarioFacade.search(namedQuery);
    }

    @Override
    public List<Horario> search(String namedQuery, Map<String, Object> parametros) {
        return this.horarioFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Horario> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.horarioFacade.search(namedQuery, parametros, inicio, tamanio);
    }

    private static final Logger LOG = Logger.getLogger(DetalleHorarioController.class.getName());

    public void onDepartamentoSeleccionado() {
        if (this.departamentoSeleccionado != null) {
            LOG.log(Level.INFO, "ID DEL DEPARTAMENTO:{0}", this.departamentoSeleccionado.getId());
            if (this.departamentoSeleccionado.getId() != 0) {
                this.isDepartamentoSeleccionado = true;
                return;
            }
            this.isDepartamentoSeleccionado = false;
        }
    }

    public void onServicioSeleccionado() {
        this.isServicioSeleccionado = this.servicioSeleccionado != null;
    }

    public List<Servicio> getServicios() {
        if (this.isDepartamentoSeleccionado) {
            return this.departamentoSeleccionado.getServicioList();
        } else {
            return null;
        }
    }

    public List<Empleado> getEmpleados() {
        if (this.isServicioSeleccionado) {
            LOG.log(Level.INFO, "ID DEL SERVICIO:{0}", this.servicioSeleccionado.getId());
            return this.servicioSeleccionado.getEmpleadoList();
        } else {
            return null;
        }
    }

    public void onEmpleadoSeleccionado() {
        if (this.empleadoSeleccionado != null) {
            LOG.log(Level.INFO, "ID DEL EMPLEADO SELECCIONADO: {0}", this.empleadoSeleccionado.getId());
            if (this.empleadoSeleccionado.getId() != 0) {
                this.isEmpleadoSeleccionado = true;
                return;
            }
        }
        this.isEmpleadoSeleccionado = false;
    }

    public List<Empleado> getEmpleadosFiltrados() {

        String namedString = "SELECT e FROM Empleado e WHERE CONCAT(e.nombres,e.apellidos,e.docIdentidad) LIKE CONCAT('%',:empleado,'%') AND e.servicioId=:servicio";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("empleado", empleadoSeleccionado);
        parametros.put("servicio", servicioSeleccionado);
        return this.empleadoListController.search(namedString, parametros);
    }


    public List<Date> getDias() {
        if(this.anioSeleccionado>0){
            List<Date> fechas = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        int mes = this.mesSeleccionado;
        int anio = this.anioSeleccionado;
        cal.set(anio, mes, 1);
        int ultimo = cal.getMaximum(Calendar.DAY_OF_MONTH);
        LOG.log(Level.INFO, "Ultimo dia",Integer.toString(ultimo));
        for (int i = 1; i <= ultimo; i++) {
            cal.set(Calendar.DAY_OF_MONTH, i);
            Date fecha = cal.getTime();
            fechas.add(fecha);  
        }
        return fechas;
        }
        return null;

    }
}
