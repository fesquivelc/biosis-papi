package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.PermisoFacadeLocal;
import com.project.jsica.ejb.entidades.Permiso;
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
    @EJB
    private PermisoFacadeLocal permisoFacade;
    @Inject
    private MotivoPermisoController motivoPermisoCodigoController;
    @Inject
    private EmpleadoPermisoController empleadoPermisoListController;

    public PermisoController() {
        // Inform the Abstract parent controller of the concrete Permiso?cap_first Entity
        super(Permiso.class);
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
