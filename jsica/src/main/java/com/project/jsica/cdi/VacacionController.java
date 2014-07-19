package com.project.jsica.cdi;

import com.project.jsica.ejb.entidades.Vacacion;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "vacacionController")
@ViewScoped
public class VacacionController extends AbstractController<Vacacion> {

    @Inject
    private AnioController anioIdController;
    @Inject
    private EmpleadoController empleadoIdController;

    public VacacionController() {
        // Inform the Abstract parent controller of the concrete Vacacion?cap_first Entity
        super(Vacacion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        anioIdController.setSelected(null);
        empleadoIdController.setSelected(null);
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
