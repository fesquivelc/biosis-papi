package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.PapeletaFacadeLocal;
import com.project.jsica.ejb.entidades.Area;
import com.project.jsica.ejb.entidades.Empleado;
import com.project.jsica.ejb.entidades.EmpleadoPermiso;
import com.project.jsica.ejb.entidades.Papeleta;
import com.project.jsica.ejb.entidades.Permiso;
import com.project.jsica.ejb.entidades.Servicio;
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

@Named(value = "papeletaController")
@ViewScoped
public class PapeletaController extends AbstractController<Papeleta> {
    private Papeleta papeleta;
    private EmpleadoPermiso empleadoPermiso;
    private Permiso permiso;
    @EJB
    private PapeletaFacadeLocal papeletaFacade;
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
    }

    @Override
    protected void remove(Papeleta objeto) {
        this.papeletaFacade.remove(objeto);
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
    @Override
    public Papeleta prepareCreate(ActionEvent event){
        this.papeleta = new Papeleta();
        this.empleadoPermiso = new EmpleadoPermiso();
        this.permiso = new Permiso();
        
        this.empleadoPermiso.setPermisoId(permiso);
        this.empleadoPermiso.setPapeletaList(new ArrayList<Papeleta>());
        this.empleadoPermiso.getPapeletaList().add(papeleta);
        this.empleadoPermiso.setEmpleadoId(empleadoSeleccionado);
        this.papeleta.setEmpleadoPermisoId(empleadoPermiso);
        
        this.setSelected(papeleta);
        return this.papeleta;
    }
    
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
    
    public List<Empleado> getEmpleados(){
        if(this.isServicioSeleccionado){
            LOG.log(Level.INFO, "ID DEL SERVICIO:{0}", this.servicioSeleccionado.getId());
            return this.servicioSeleccionado.getEmpleadoList();
        }else{
            return null;
        }
    }
    
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
