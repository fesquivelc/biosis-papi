package com.project.jsica.cdi;

import dao.PapeletaFacadeLocal;
import com.project.jsica.ejb.entidades.Area;
import com.project.jsica.ejb.entidades.Bitacora;
import com.project.jsica.ejb.entidades.Empleado;
import com.project.jsica.ejb.entidades.EmpleadoPermiso;
import com.project.jsica.ejb.entidades.Papeleta;
import com.project.jsica.ejb.entidades.Permiso;
import com.project.jsica.ejb.entidades.Servicio;
import java.util.ArrayList;
import java.util.Date;
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

@Named(value = "papeletaController")
@ViewScoped
public class PapeletaController extends AbstractController<Papeleta> {

    private Papeleta papeleta;
    private EmpleadoPermiso empleadoPermiso;
    private Permiso permiso;

    @EJB
    private PapeletaFacadeLocal papeletaFacade;

    @Inject
    private BitacoraController bitacoraC;

    @Inject
    private EmpleadoController empleadoIdempleadoController;
    @Inject
    private EmpleadoController empleadoIdjefeInmediatoController;
    @Inject
    private EmpleadoController empleadoIdjefePersonalController;
    @Inject
    private EmpleadoPermisoController empleadoPermisoIdController;

    private Area departamentoSeleccionado;
    private boolean isDepartamentoSeleccionado;
    
    private Servicio servicioSeleccionado;
    private boolean isServicioSeleccionado;
    
    private Empleado empleadoSeleccionado;
    private boolean isEmpleadoSeleccionado;

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
   
    /*Metodos para nuevas entidades*/
    public Papeleta getPapeleta() {
        return papeleta;
    }

    public void setPapeleta(Papeleta papeleta) {
        this.papeleta = papeleta;
    }

    public EmpleadoPermiso getEmpleadoPermiso() {
        return empleadoPermiso;
    }

    public void setEmpleadoPermiso(EmpleadoPermiso empleadoPermiso) {
        this.empleadoPermiso = empleadoPermiso;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    public PapeletaController() {
        // Inform the Abstract parent controller of the concrete Papeleta?cap_first Entity
        super(Papeleta.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        empleadoIdempleadoController.setSelected(null);
        empleadoIdjefeInmediatoController.setSelected(null);
        empleadoIdjefePersonalController.setSelected(null);
        empleadoPermisoIdController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Empleado controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEmpleadoIdempleado(ActionEvent event) {
        if (this.getSelected() != null && empleadoIdempleadoController.getSelected() == null) {
            empleadoIdempleadoController.setSelected(this.getSelected().getEmpleadoIdempleado());
        }
    }

    /**
     * Sets the "selected" attribute of the Empleado controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEmpleadoIdjefeInmediato(ActionEvent event) {
        if (this.getSelected() != null && empleadoIdjefeInmediatoController.getSelected() == null) {
            empleadoIdjefeInmediatoController.setSelected(this.getSelected().getEmpleadoIdjefeInmediato());
        }
    }

    /**
     * Sets the "selected" attribute of the Empleado controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEmpleadoIdjefePersonal(ActionEvent event) {
        if (this.getSelected() != null && empleadoIdjefePersonalController.getSelected() == null) {
            empleadoIdjefePersonalController.setSelected(this.getSelected().getEmpleadoIdjefePersonal());
        }
    }

    /**
     * Sets the "selected" attribute of the EmpleadoPermiso controller in order
     * to display its data in a dialog. This is reusing existing the existing
     * View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEmpleadoPermisoId(ActionEvent event) {
        if (this.getSelected() != null && empleadoPermisoIdController.getSelected() == null) {
            empleadoPermisoIdController.setSelected(this.getSelected().getEmpleadoPermisoId());
        }
    }

    @Override
    protected void edit(Papeleta objeto) {
        this.papeletaFacade.edit(objeto);

        if (this.esNuevo) {
            Bitacora bitacora = new Bitacora();
            //----Bitacora----
            //Fecha y hora//          
            Date fechas = new Date();//           
            //Ip Cliente
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();

            String codigo = this.selected.getCodigo();
            String empleadoIdEmpleado = this.selected.getEmpleadoIdempleado().toString();
            String empleadoIdJefeInmediato = this.selected.getEmpleadoIdjefeInmediato().toString();
            String empleadoIdJefePersonal = this.selected.getEmpleadoIdjefePersonal().toString();
            String empleadoPermisoId = this.selected.getEmpleadoPermisoId().toString();

            bitacora.setUsuario("JC");
            bitacora.setIpCliente(ip_cliente);
            bitacora.setFecha(fechas);
            bitacora.setHora(fechas);
            bitacora.setTabla("PAPELETA");
            bitacora.setColumna("CODIGO");
            bitacora.setAccion("CREAR");
            bitacora.setValorAct(codigo);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("EMPLEADO_IDEMPLEADO");
            bitacora.setValorAct(empleadoIdEmpleado);
            bitacoraC.edit(bitacora);

            bitacora.setColumna("EMPLEADO_IDJEFE_INMEDIATO");
            bitacora.setValorAct(empleadoIdJefeInmediato);
            bitacoraC.edit(bitacora);

            bitacora.setColumna("EMPLEADO_IDJEFE_PERSONAL");
            bitacora.setValorAct(empleadoIdJefePersonal);
            bitacoraC.edit(bitacora);

            bitacora.setColumna("EMPLEADO_PERMISO_ID");
            bitacora.setValorAct(empleadoPermisoId);
            bitacoraC.edit(bitacora);
        } else {
            //Datos antes de modificar
            Papeleta antes = this.find(this.selected.getId());

            String codigo1 = antes.getCodigo();
            String empleadoIdEmpleado1 = antes.getEmpleadoIdempleado().toString();
            String empleadoIdJefeInmediato1 = antes.getEmpleadoIdjefeInmediato().toString();
            String empleadoIdJefePersonal1 = antes.getEmpleadoIdjefePersonal().toString();
            String empleadoPermisoId1 = antes.getEmpleadoPermisoId().toString();

            //Datos despues de modificar
            String codigo2 = this.selected.getCodigo();
            String empleadoIdEmpleado2 = this.selected.getEmpleadoIdempleado().toString();
            String empleadoIdJefeInmediato2 = this.selected.getEmpleadoIdjefeInmediato().toString();
            String empleadoIdJefePersonal2 = this.selected.getEmpleadoIdjefePersonal().toString();
            String empleadoPermisoId2 = this.selected.getEmpleadoPermisoId().toString();

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
            bitacora.setTabla("PAPELETA");
            bitacora.setColumna("CODIGO");
            bitacora.setAccion("MODIFICAR");
            bitacora.setValorAct(codigo2);
            bitacora.setValorAnt(codigo1);

            if (!codigo1.equals(codigo2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("EMPLEADO_IDEMPLEADO");
            bitacora.setValorAct(empleadoIdEmpleado2);
            bitacora.setValorAnt(empleadoIdEmpleado1);

            if (!empleadoIdEmpleado1.equals(empleadoIdEmpleado2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("EMPLEADO_IDJEFE_INMEDIATO");
            bitacora.setValorAct(empleadoIdJefeInmediato2);
            bitacora.setValorAnt(empleadoIdJefeInmediato1);

            if (!empleadoIdJefeInmediato1.equals(empleadoIdJefeInmediato2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("EMPLEADO_IDJEFE_PERSONAL");
            bitacora.setValorAct(empleadoIdJefePersonal2);
            bitacora.setValorAnt(empleadoIdJefePersonal1);

            if (!empleadoIdJefePersonal1.equals(empleadoIdJefePersonal2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("EMPLEADO_PERMISO_ID");
            bitacora.setValorAct(empleadoPermisoId2);
            bitacora.setValorAnt(empleadoPermisoId1);

            if (!empleadoPermisoId1.equals(empleadoPermisoId2)) {
                bitacoraC.edit(bitacora);
            }
        }
    }

    @Override
    protected void remove(Papeleta objeto) {
        this.papeletaFacade.remove(objeto);

        Papeleta antes = this.find(this.selected.getId());

        String codigo1 = antes.getCodigo();
        String empleadoIdEmpleado1 = antes.getEmpleadoIdempleado().toString();
        String empleadoIdJefeInmediato1 = antes.getEmpleadoIdjefeInmediato().toString();
        String empleadoIdJefePersonal1 = antes.getEmpleadoIdjefePersonal().toString();
        String empleadoPermisoId1 = antes.getEmpleadoPermisoId().toString();

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
        bitacora.setTabla("PAPELETA");
        bitacora.setColumna("CODIGO");
        bitacora.setAccion("ELIMINAR");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(codigo1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("EMPLEADO_IDEMPLEADO");
        bitacora.setValorAnt(empleadoIdEmpleado1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("EMPLEADO_IDJEFE_INMEDIATO");
        bitacora.setValorAnt(empleadoIdJefeInmediato1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("EMPLEADO_IDJEFE_PERSONAL");
        bitacora.setValorAnt(empleadoIdJefePersonal1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("EMPLEADO_PERMISO_ID");
        bitacora.setValorAnt(empleadoPermisoId1);
        bitacoraC.edit(bitacora);
    }

    @Override
    public Papeleta find(Object id) {
        return this.papeletaFacade.find(id);
    }

    @Override
    public List<Papeleta> getItems() {
        return this.papeletaFacade.findAll();
    }

    @Override
    public List<Papeleta> search(String namedQuery) {
        return this.papeletaFacade.search(namedQuery);
    }

    @Override
    public List<Papeleta> search(String namedQuery, Map<String, Object> parametros) {
        return this.papeletaFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Papeleta> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.papeletaFacade.search(namedQuery, parametros, inicio, tamanio);
    }
    /*metodo create*/
    private static final Logger LOG = Logger.getLogger(DetalleHorarioController.class.getName());
    
    public void onDepartamentoSeleccionado(){
        if(this.departamentoSeleccionado!=null){
            LOG.log(Level.INFO,"ID DEL DEPARTAMENTO:{0}", this.departamentoSeleccionado.getId());
            if(this.departamentoSeleccionado.getId()!=0){
                this.isDepartamentoSeleccionado = true;
                return;
            }
            this.isDepartamentoSeleccionado = false;
        }
    }
    
    public void onServicioSeleccionado(){
        this.isServicioSeleccionado = this.servicioSeleccionado!=null;
    }
    
    public List<Servicio> getServicios(){
        if(this.isDepartamentoSeleccionado){
            return this.departamentoSeleccionado.getServicioList();
        }else{
            return null;
        }
    }
    
//    public List<Empleado> getEmpleados(){
//        if(this.isServicioSeleccionado){
//            LOG.log(Level.INFO, "ID DEL SERVICIO:{0}", this.servicioSeleccionado.getId());
//            return this.servicioSeleccionado.getEmpleadoList();
//        }else{
//            return null;
//        }
//    }
    
    public void onEmpleadoSeleccionado(){
        if(this.empleadoSeleccionado!=null){
            LOG.log(Level.INFO, "ID DEL EMPLEADO SELECCIONADO: {0}", this.empleadoSeleccionado.getId());
            if(this.empleadoSeleccionado.getId()!=0){
                this.isEmpleadoSeleccionado = true;
                return;
            }
        }
        this.isEmpleadoSeleccionado = false;
    }
}
