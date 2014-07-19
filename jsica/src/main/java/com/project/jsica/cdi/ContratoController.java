package com.project.jsica.cdi;

import com.project.jsica.ejb.entidades.Contrato;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "contratoController")
@ViewScoped
public class ContratoController extends AbstractController<Contrato> {

    @Inject
    private DetalleContratoController detalleContratoListController;
    @Inject
    private CondicionLaboralController condicionLaboralIdController;
    @Inject
    private RegimenLaboralController regimenLaboralIdController;
    @Inject
    private AnioController anioIdController;
    @Inject
    private TipoContratoController tipoContratoIdController;
    @Inject
    private OcupacionController ocupacionIdController;

    public ContratoController() {
        // Inform the Abstract parent controller of the concrete Contrato?cap_first Entity
        super(Contrato.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        condicionLaboralIdController.setSelected(null);
        regimenLaboralIdController.setSelected(null);
        anioIdController.setSelected(null);
        tipoContratoIdController.setSelected(null);
        ocupacionIdController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of DetalleContrato entities
     * that are retrieved from Contrato?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for DetalleContrato page
     */
    public String navigateDetalleContratoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetalleContrato_items", this.getSelected().getDetalleContratoList());
        }
        return "/detalleContrato/index";
    }

    /**
     * Sets the "selected" attribute of the CondicionLaboral controller in order
     * to display its data in a dialog. This is reusing existing the existing
     * View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCondicionLaboralId(ActionEvent event) {
        if (this.getSelected() != null && condicionLaboralIdController.getSelected() == null) {
            condicionLaboralIdController.setSelected(this.getSelected().getCondicionLaboralId());
        }
    }

    /**
     * Sets the "selected" attribute of the RegimenLaboral controller in order
     * to display its data in a dialog. This is reusing existing the existing
     * View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRegimenLaboralId(ActionEvent event) {
        if (this.getSelected() != null && regimenLaboralIdController.getSelected() == null) {
            regimenLaboralIdController.setSelected(this.getSelected().getRegimenLaboralId());
        }
    }

    /**
     * Sets the "selected" attribute of the Anio controller in order to display
     * its data in a dialog. This is reusing existing the existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareAnioId(ActionEvent event) {
        if (this.getSelected() != null && anioIdController.getSelected() == null) {
            anioIdController.setSelected(this.getSelected().getAnioId());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoContrato controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTipoContratoId(ActionEvent event) {
        if (this.getSelected() != null && tipoContratoIdController.getSelected() == null) {
            tipoContratoIdController.setSelected(this.getSelected().getTipoContratoId());
        }
    }

    /**
     * Sets the "selected" attribute of the Ocupacion controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareOcupacionId(ActionEvent event) {
        if (this.getSelected() != null && ocupacionIdController.getSelected() == null) {
            ocupacionIdController.setSelected(this.getSelected().getOcupacionId());
        }
    }
}
