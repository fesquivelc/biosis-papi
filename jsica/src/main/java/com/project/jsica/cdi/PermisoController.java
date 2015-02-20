package com.project.jsica.cdi;

import dao.PermisoFacadeLocal;
import com.project.jsica.ejb.entidades.Empleado;
import com.project.jsica.ejb.entidades.EmpleadoPermiso;
import com.project.jsica.ejb.entidades.Papeleta;
import com.project.jsica.ejb.entidades.Permiso;
import java.util.ArrayList;
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

@Named(value = "permisoController")
@ViewScoped
public class PermisoController extends AbstractController<Permiso> {

    private Permiso permisoSeleccionado;
    private EmpleadoPermiso empleadoPermisoSeleccionado;
    private Papeleta papeletaSeleccionada;

    @EJB
    private PermisoFacadeLocal permisoFacade;

    @Inject
    private BitacoraController bitacoraC;

    @Inject
    private MotivoPermisoController motivoPermisoCodigoController;
    @Inject
    private EmpleadoPermisoController empleadoPermisoListController;
    private Empleado empleadoSeleccionado;
    private boolean porPapeleta;

    public PermisoController() {
        // Inform the Abstract parent controller of the concrete Permiso?cap_first Entity
        super(Permiso.class);
    }

    /*Metodo */
    public Permiso getPermisoSeleccionado() {
        return permisoSeleccionado;
    }

    public void setPermisoSeleccionado(Permiso permisoSeleccionado) {
        this.permisoSeleccionado = permisoSeleccionado;
    }

    public Papeleta getPapeletaSeleccionada() {
        return papeletaSeleccionada;
    }

    public void setPapeletaSeleccionada(Papeleta papeletaSeleccionada) {
        this.papeletaSeleccionada = papeletaSeleccionada;
    }

    public Empleado getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    public void setEmpleadoSeleccionado(Empleado empleadoSeleccionado) {
        this.empleadoSeleccionado = empleadoSeleccionado;
    }

    public boolean isPorPapeleta() {
        return porPapeleta;
    }

    public void setPorPapeleta(boolean porPapeleta) {
        this.porPapeleta = porPapeleta;
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        motivoPermisoCodigoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the MotivoPermiso controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareMotivoPermisoCodigo(ActionEvent event) {
        if (this.getSelected() != null && motivoPermisoCodigoController.getSelected() == null) {
            motivoPermisoCodigoController.setSelected(this.getSelected().getMotivoPermisoCodigo());
        }
    }

    /**
     * Sets the "items" attribute with a collection of EmpleadoPermiso entities
     * that are retrieved from Permiso?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for EmpleadoPermiso page
     */
    public String navigateEmpleadoPermisoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EmpleadoPermiso_items", this.getSelected().getEmpleadoPermisoList());
        }
        return "/empleadoPermiso/index";
    }
    private static final Logger LOG = Logger.getLogger(PermisoController.class.getName());

    @Override
    public Permiso prepareCreate(ActionEvent event) {
        LOG.log(Level.INFO, "llego aqui permiso");
        Permiso permiso = new Permiso();
        this.empleadoPermisoSeleccionado = new EmpleadoPermiso();
        this.papeletaSeleccionada = new Papeleta();

        permiso.setEmpleadoPermisoList(new ArrayList<EmpleadoPermiso>());
        this.empleadoPermisoSeleccionado.setPapeletaList(new ArrayList<Papeleta>());

        this.empleadoPermisoSeleccionado.setEmpleadoId(this.empleadoSeleccionado);
        this.empleadoPermisoSeleccionado.setPermisoId(permiso);
        this.papeletaSeleccionada.setEmpleadoPermisoId(this.empleadoPermisoSeleccionado);

        permiso.getEmpleadoPermisoList().add(this.empleadoPermisoSeleccionado);
        this.empleadoPermisoSeleccionado.getPapeletaList().add(this.papeletaSeleccionada);

        this.setSelected(permiso);
        LOG.log(Level.INFO, "llego aqui permiso2");
        return permiso;
    }

    @Override
    public void saveNew(ActionEvent event) {
        if (this.porPapeleta) {
            LOG.log(Level.INFO, "pasa check");
            this.getSelected().getEmpleadoPermiso().setPapeleta(null);
        }
        super.saveNew(event); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void edit(Permiso objeto) {
        this.permisoFacade.edit(objeto);
//
//        if (this.esNuevo) {
//            Bitacora bitacora = new Bitacora();
//            //----Bitacora----
//            //Fecha y hora//          
//            Date fechas = new Date();//           
//            //Ip Cliente
//            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
//
//            String horaInicio = this.selected.getHoraInicio().toString();
//            String horaFin = this.selected.getHoraFin().toString();
//            String fechaInicio = this.selected.getFechaInicio().toString();
//            String fechaFin = this.selected.getFechaFin().toString();
//            String tipo = this.selected.getTipo();
//            String porFecha = String.valueOf(this.selected.getPorFecha());
//            String motivoPermisoCodigo = this.selected.getMotivoPermisoCodigo().toString();
//
//            bitacora.setUsuario("JC");
//            bitacora.setIpCliente(ip_cliente);
//            bitacora.setFecha(fechas);
//            bitacora.setHora(fechas);
//            bitacora.setTabla("PERMISO");
//            bitacora.setColumna("HORA_INICIO");
//            bitacora.setAccion("CREAR");
//            bitacora.setValorAct(horaInicio);
//            bitacora.setValorAnt(" ");
//            bitacoraC.edit(bitacora);
//
//            bitacora.setColumna("HORA_FIN");
//            bitacora.setValorAct(horaFin);
//            bitacoraC.edit(bitacora);
//
//            bitacora.setColumna("FECHA_INICIO");
//            bitacora.setValorAct(fechaInicio);
//            bitacoraC.edit(bitacora);
//
//            bitacora.setColumna("FECHA_FIN");
//            bitacora.setValorAct(fechaFin);
//            bitacoraC.edit(bitacora);
//
//            bitacora.setColumna("TIPO");
//            bitacora.setValorAct(tipo);
//            bitacoraC.edit(bitacora);
//
//            bitacora.setColumna("POR_FECHA");
//            bitacora.setValorAct(porFecha);
//            bitacoraC.edit(bitacora);
//
//            bitacora.setColumna("MOTIVO_PERMISO_CODIGO");
//            bitacora.setValorAct(motivoPermisoCodigo);
//            bitacoraC.edit(bitacora);
//        } else {
//            //Datos antes de modificar
//            Permiso antes = this.find(this.selected.getId());
//
//            String horaInicio1 = antes.getHoraInicio().toString();
//            String horaFin1 = antes.getHoraFin().toString();
//            String fechaInicio1 = antes.getFechaInicio().toString();
//            String fechaFin1 = antes.getFechaFin().toString();
//            String tipo1 = antes.getTipo();
//            String porFecha1 = String.valueOf(antes.getPorFecha());
//            String motivoPermisoCodigo1 = antes.getMotivoPermisoCodigo().toString();
//
//            //Datos despues de modificar
//            String horaInicio2 = this.selected.getHoraInicio().toString();
//            String horaFin2 = this.selected.getHoraFin().toString();
//            String fechaInicio2 = this.selected.getFechaInicio().toString();
//            String fechaFin2 = this.selected.getFechaFin().toString();
//            String tipo2 = this.selected.getTipo();
//            String porFecha2 = String.valueOf(this.selected.getPorFecha());
//            String motivoPermisoCodigo2 = this.selected.getMotivoPermisoCodigo().toString();
//
//            //----Bitacora----
//            Bitacora bitacora = new Bitacora();
//            //Fecha y hora//          
//            Date fechas = new Date();
////           
//            //Ip Cliente
//            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
//
//            //Datos
//            bitacora.setUsuario("JC");
//            bitacora.setIpCliente(ip_cliente);
//            bitacora.setFecha(fechas);
//            bitacora.setHora(fechas);
//            bitacora.setTabla("PERMISO");
//            bitacora.setColumna("HORA_INICIO");
//            bitacora.setAccion("MODIFICAR");
//            bitacora.setValorAct(horaInicio2);
//            bitacora.setValorAnt(horaInicio1);
//
//            if (!horaInicio1.equals(horaInicio2)) {
//                bitacoraC.edit(bitacora);
//            }
//
//            bitacora.setColumna("HORA_FIN");
//            bitacora.setValorAct(horaFin2);
//            bitacora.setValorAnt(horaFin1);
//
//            if (!horaFin1.equals(horaFin2)) {
//                bitacoraC.edit(bitacora);
//            }
//
//            bitacora.setColumna("FECHA_INICIO");
//            bitacora.setValorAct(fechaInicio2);
//            bitacora.setValorAnt(fechaInicio1);
//
//            if (!fechaInicio1.equals(fechaInicio2)) {
//                bitacoraC.edit(bitacora);
//            }
//
//            bitacora.setColumna("FECHA_FIN");
//            bitacora.setValorAct(fechaFin2);
//            bitacora.setValorAnt(fechaFin1);
//
//            if (!fechaFin1.equals(fechaFin1)) {
//                bitacoraC.edit(bitacora);
//            }
//
//            bitacora.setColumna("TIPO");
//            bitacora.setValorAct(tipo2);
//            bitacora.setValorAnt(tipo1);
//
//            if (!tipo1.equals(tipo2)) {
//                bitacoraC.edit(bitacora);
//            }
//
//            bitacora.setColumna("POR_FECHA");
//            bitacora.setValorAct(porFecha2);
//            bitacora.setValorAnt(porFecha1);
//
//            if (!porFecha1.equals(porFecha2)) {
//                bitacoraC.edit(bitacora);
//            }
//
//            bitacora.setColumna("MOTIVO_PERMISO_CODIGO");
//            bitacora.setValorAct(motivoPermisoCodigo2);
//            bitacora.setValorAnt(motivoPermisoCodigo1);
//
//            if (!motivoPermisoCodigo1.equals(motivoPermisoCodigo2)) {
//                bitacoraC.edit(bitacora);
//            }
//        }
    }

    @Override
    protected void remove(Permiso objeto) {
        this.permisoFacade.remove(objeto);
//
//        Permiso antes = this.find(this.selected.getId());
//
//        String horaInicio1 = antes.getHoraInicio().toString();
//        String horaFin1 = antes.getHoraFin().toString();
//        String fechaInicio1 = antes.getFechaInicio().toString();
//        String fechaFin1 = antes.getFechaFin().toString();
//        String tipo1 = antes.getTipo();
//        String porFecha1 = String.valueOf(antes.getPorFecha());
//        String motivoPermisoCodigo1 = antes.getMotivoPermisoCodigo().toString();
//
//        //----Bitacora----
//        Bitacora bitacora = new Bitacora();
//        //Fecha y hora//          
//        Date fechas = new Date();
//        //Ip Cliente
//        String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
//
//        //Datos
//        bitacora.setUsuario("JC");
//        bitacora.setIpCliente(ip_cliente);
//        bitacora.setFecha(fechas);
//        bitacora.setHora(fechas);
//        bitacora.setTabla("PERMISO");
//        bitacora.setColumna("HORA_INICIO");
//        bitacora.setAccion("ELIMINAR");
//        bitacora.setValorAct(" ");
//        bitacora.setValorAnt(horaInicio1);
//        bitacoraC.edit(bitacora);
//        
//        bitacora.setColumna("HORA_FIN");
//        bitacora.setValorAnt(horaFin1);
//        bitacoraC.edit(bitacora);
//        
//        bitacora.setColumna("FECHA_INICIO");
//        bitacora.setValorAnt(fechaInicio1);
//        bitacoraC.edit(bitacora);
//        
//        bitacora.setColumna("FECHA_FIN");
//        bitacora.setValorAnt(fechaFin1);
//        bitacoraC.edit(bitacora);
//        
//        bitacora.setColumna("TIPO");
//        bitacora.setValorAnt(tipo1);
//        bitacoraC.edit(bitacora);
//        
//        bitacora.setColumna("POR_FECHA");
//        bitacora.setValorAnt(porFecha1);
//        bitacoraC.edit(bitacora);
//        
//        bitacora.setColumna("MOTIVO_PERMISO_CODIGO");
//        bitacora.setValorAnt(motivoPermisoCodigo1);
//        bitacoraC.edit(bitacora);
    }

    @Override
    public Permiso find(Object id) {
        return this.permisoFacade.find(id);
    }

    @Override
    public List<Permiso> getItems() {
        return this.permisoFacade.findAll();
    }

    @Override
    public List<Permiso> search(String namedQuery) {
        return this.permisoFacade.search(namedQuery);
    }

    @Override
    public List<Permiso> search(String namedQuery, Map<String, Object> parametros) {
        return this.permisoFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Permiso> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.permisoFacade.search(namedQuery, parametros, inicio, tamanio);
    }

}
