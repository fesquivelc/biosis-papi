package com.project.jsica.cdi;

import com.project.jsica.ejb.entidades.TipoEmpleado;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tipoEmpleadoController")
@ViewScoped
public class TipoEmpleadoController extends AbstractController<TipoEmpleado> {

    @Inject
    private FichaLaboralEmpleadoController fichaLaboralEmpleadoListController;

    public TipoEmpleadoController() {
        // Inform the Abstract parent controller of the concrete TipoEmpleado?cap_first Entity
        super(TipoEmpleado.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of FichaLaboralEmpleado
     * entities that are retrieved from TipoEmpleado?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for FichaLaboralEmpleado page
     */
    public String navigateFichaLaboralEmpleadoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("FichaLaboralEmpleado_items", this.getSelected().getFichaLaboralEmpleadoList());
        }
        return "/fichaLaboralEmpleado/index";
    }

}
