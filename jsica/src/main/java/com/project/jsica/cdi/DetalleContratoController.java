package com.project.jsica.cdi;

import com.project.jsica.ejb.entidades.DetalleContrato;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "detalleContratoController")
@ViewScoped
public class DetalleContratoController extends AbstractController<DetalleContrato> {

    @Inject
    private AreaController areaIdController;
    @Inject
    private ContratoController contratoIdController;
    @Inject
    private EmpleadoController empleadoIdController;

    public DetalleContratoController() {
        // Inform the Abstract parent controller of the concrete DetalleContrato?cap_first Entity
        super(DetalleContrato.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        areaIdController.setSelected(null);
        contratoIdController.setSelected(null);
        empleadoIdController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Area controller in order to display
     * its data in a dialog. This is reusing existing the existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareAreaId(ActionEvent event) {
        if (this.getSelected() != null && areaIdController.getSelected() == null) {
            areaIdController.setSelected(this.getSelected().getAreaId());
        }
    }

    /**
     * Sets the "selected" attribute of the Contrato controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareContratoId(ActionEvent event) {
        if (this.getSelected() != null && contratoIdController.getSelected() == null) {
            contratoIdController.setSelected(this.getSelected().getContratoId());
        }
    }

    /**
     * Sets the "selected" attribute of the Empleado controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEmpleadoId(ActionEvent event) {
        if (this.getSelected() != null && empleadoIdController.getSelected() == null) {
            empleadoIdController.setSelected(this.getSelected().getEmpleadoId());
        }
    }
}
