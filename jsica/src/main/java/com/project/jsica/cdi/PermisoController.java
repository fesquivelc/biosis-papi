package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.PermisoFacadeLocal;
import com.project.jsica.ejb.entidades.Empleado;
import com.project.jsica.ejb.entidades.EmpleadoPermiso;
import com.project.jsica.ejb.entidades.Papeleta;
import com.project.jsica.ejb.entidades.Permiso;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "permisoController")
@ViewScoped
public class PermisoController extends AbstractController<Permiso> {
    
    private Permiso permisoSeleccionado;
    private EmpleadoPermiso empleadoPermisoSeleccionado;
    private Papeleta papeletaSeleccionada;
    
    @EJB
    private PermisoFacadeLocal permisoFacade;
    @Inject
    private MotivoPermisoController motivoPermisoCodigoController;
    @Inject
    private EmpleadoPermisoController empleadoPermisoListController;
    private Empleado empleadoSeleccionado;
    
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

    @Override
    public Permiso prepareCreate(ActionEvent event) {

        Permiso permiso = new Permiso();
        this.empleadoPermisoSeleccionado = new EmpleadoPermiso();
        this.papeletaSeleccionada = new Papeleta();
        
        permiso.setEmpleadoPermisoList(new ArrayList<EmpleadoPermiso>());
        this.empleadoPermisoSeleccionado.setPapeletaList(new ArrayList<Papeleta>());
        
        this.empleadoPermisoSeleccionado.setEmpleadoId(empleadoSeleccionado);
        this.empleadoPermisoSeleccionado.setPermisoId(permiso);
        this.papeletaSeleccionada.setEmpleadoPermisoId(empleadoPermisoSeleccionado);
    
        permiso.getEmpleadoPermisoList().add(empleadoPermisoSeleccionado);
        this.empleadoPermisoSeleccionado.getPapeletaList().add(papeletaSeleccionada);
           
        this.setSelected(permiso);
        return permiso;
    }

    
    @Override
    protected void edit(Permiso objeto) {
        this.permisoFacade.edit(objeto);
    }

    @Override
    protected void remove(Permiso objeto) {
        this.permisoFacade.remove(objeto);
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
