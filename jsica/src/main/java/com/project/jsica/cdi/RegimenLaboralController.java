package com.project.jsica.cdi;

import com.project.jsica.ejb.entidades.RegimenLaboral;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "regimenLaboralController")
@ViewScoped
public class RegimenLaboralController extends AbstractController<RegimenLaboral> {

    @Inject
    private ContratoController contratoListController;

    public RegimenLaboralController() {
        // Inform the Abstract parent controller of the concrete RegimenLaboral?cap_first Entity
        super(RegimenLaboral.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Contrato entities that
     * are retrieved from RegimenLaboral?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Contrato page
     */
    public String navigateContratoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Contrato_items", this.getSelected().getContratoList());
        }
        return "/contrato/index";
    }

}
