package com.project.jsica.cdi;

import com.project.jsica.ejb.entidades.Ubigeo;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "ubigeoController")
@ViewScoped
public class UbigeoController extends AbstractController<Ubigeo> {

    @Inject
    private FichaGeneralEmpleadoController fichaGeneralEmpleadoListController;

    public UbigeoController() {
        // Inform the Abstract parent controller of the concrete Ubigeo?cap_first Entity
        super(Ubigeo.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of FichaGeneralEmpleado
     * entities that are retrieved from Ubigeo?cap_first and returns the
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
