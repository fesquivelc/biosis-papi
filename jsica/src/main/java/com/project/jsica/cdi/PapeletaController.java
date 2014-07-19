package com.project.jsica.cdi;

import com.project.jsica.ejb.entidades.Papeleta;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "papeletaController")
@ViewScoped
public class PapeletaController extends AbstractController<Papeleta> {

    @Inject
    private EmpleadoController empleadoIdempleadoController;
    @Inject
    private EmpleadoController empleadoIdjefeInmediatoController;
    @Inject
    private EmpleadoController empleadoIdjefePersonalController;
    @Inject
    private EmpleadoPermisoController empleadoPermisoIdController;

    public PapeletaController() {
        // Inform the Abstract parent controller of the concrete Papeleta?cap_first Entity
        super(Papeleta.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        empleadoIdempleadoController.setSelected(null);
        empleadoIdjefeInmediatoController.setSelected(null);
        empleadoIdjefePersonalController.setSelected(null);
        empleadoPermisoIdController.setSelected(null);
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

    /**
     * Sets the "selected" attribute of the Empleado controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEmpleadoIdjefeInmediato(ActionEvent event) {
        if (this.getSelected() != null && empleadoIdjefeInmediatoController.getSelected() == null) {
            empleadoIdjefeInmediatoController.setSelected(this.getSelected().getEmpleadoIdjefeInmediato());
        }
    }

    /**
     * Sets the "selected" attribute of the Empleado controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEmpleadoIdjefePersonal(ActionEvent event) {
        if (this.getSelected() != null && empleadoIdjefePersonalController.getSelected() == null) {
            empleadoIdjefePersonalController.setSelected(this.getSelected().getEmpleadoIdjefePersonal());
        }
    }

    /**
     * Sets the "selected" attribute of the EmpleadoPermiso controller in order
     * to display its data in a dialog. This is reusing existing the existing
     * View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEmpleadoPermisoId(ActionEvent event) {
        if (this.getSelected() != null && empleadoPermisoIdController.getSelected() == null) {
            empleadoPermisoIdController.setSelected(this.getSelected().getEmpleadoPermisoId());
        }
    }
}
