package com.project.jsica.cdi;

import com.project.jsica.ejb.entidades.NivelEducativo;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "nivelEducativoController")
@ViewScoped
public class NivelEducativoController extends AbstractController<NivelEducativo> {

    @Inject
    private FichaGeneralEmpleadoController fichaGeneralEmpleadoListController;

    public NivelEducativoController() {
        // Inform the Abstract parent controller of the concrete NivelEducativo?cap_first Entity
        super(NivelEducativo.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of FichaGeneralEmpleado
     * entities that are retrieved from NivelEducativo?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for FichaGeneralEmpleado page
     */
    public String navigateFichaGeneralEmpleadoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("FichaGeneralEmpleado_items", this.getSelected().getFichaGeneralEmpleadoList());
        }
        return "/fichaGeneralEmpleado/index";
    }

}
