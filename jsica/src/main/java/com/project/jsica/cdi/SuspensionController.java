package com.project.jsica.cdi;

import com.project.jsica.ejb.entidades.Suspension;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "suspensionController")
@ViewScoped
public class SuspensionController extends AbstractController<Suspension> {

    @Inject
    private EmpleadoController empleadoIdempleadoController;

    public SuspensionController() {
        // Inform the Abstract parent controller of the concrete Suspension?cap_first Entity
        super(Suspension.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        empleadoIdempleadoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Empleado controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEmpleadoIdempleado(ActionEvent event) {
        if (this.getSelected() != null && empleadoIdempleadoController.getSelected() == null) {
            empleadoIdempleadoController.setSelected(this.getSelected().getEmpleadoIdempleado());
        }
    }
}
