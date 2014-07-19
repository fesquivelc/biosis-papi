package com.project.jsica.cdi;

import com.project.jsica.ejb.entidades.MotivoPermiso;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "motivoPermisoController")
@ViewScoped
public class MotivoPermisoController extends AbstractController<MotivoPermiso> {

    @Inject
    private PermisoController permisoListController;

    public MotivoPermisoController() {
        // Inform the Abstract parent controller of the concrete MotivoPermiso?cap_first Entity
        super(MotivoPermiso.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Permiso entities that are
     * retrieved from MotivoPermiso?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Permiso page
     */
    public String navigatePermisoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Permiso_items", this.getSelected().getPermisoList());
        }
        return "/permiso/index";
    }

}
