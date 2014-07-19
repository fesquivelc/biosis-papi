package com.project.jsica.cdi;

import com.project.jsica.ejb.entidades.GrupoHorario;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "grupoHorarioController")
@ViewScoped
public class GrupoHorarioController extends AbstractController<GrupoHorario> {

    @Inject
    private EmpleadoController empleadoListController;
    @Inject
    private EmpleadoHorarioController empleadoHorarioListController;

    public GrupoHorarioController() {
        // Inform the Abstract parent controller of the concrete GrupoHorario?cap_first Entity
        super(GrupoHorario.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Empleado entities that
     * are retrieved from GrupoHorario?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Empleado page
     */
    public String navigateEmpleadoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Empleado_items", this.getSelected().getEmpleadoList());
        }
        return "/empleado/index";
    }

    /**
     * Sets the "items" attribute with a collection of EmpleadoHorario entities
     * that are retrieved from GrupoHorario?cap_first and returns the navigation
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

}
