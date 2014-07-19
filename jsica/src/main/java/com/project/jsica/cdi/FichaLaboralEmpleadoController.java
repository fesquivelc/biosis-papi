package com.project.jsica.cdi;

import com.project.jsica.ejb.entidades.FichaLaboralEmpleado;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "fichaLaboralEmpleadoController")
@ViewScoped
public class FichaLaboralEmpleadoController extends AbstractController<FichaLaboralEmpleado> {

    @Inject
    private EmpleadoController empleadoIdController;
    @Inject
    private TipoEmpleadoController tipoEmpleadoIdController;

    public FichaLaboralEmpleadoController() {
        // Inform the Abstract parent controller of the concrete FichaLaboralEmpleado?cap_first Entity
        super(FichaLaboralEmpleado.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        empleadoIdController.setSelected(null);
        tipoEmpleadoIdController.setSelected(null);
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

    /**
     * Sets the "selected" attribute of the TipoEmpleado controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTipoEmpleadoId(ActionEvent event) {
        if (this.getSelected() != null && tipoEmpleadoIdController.getSelected() == null) {
            tipoEmpleadoIdController.setSelected(this.getSelected().getTipoEmpleadoId());
        }
    }
}
