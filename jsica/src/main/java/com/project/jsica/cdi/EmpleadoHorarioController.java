package com.project.jsica.cdi;

import com.project.jsica.ejb.entidades.EmpleadoHorario;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "empleadoHorarioController")
@ViewScoped
public class EmpleadoHorarioController extends AbstractController<EmpleadoHorario> {

    @Inject
    private EmpleadoController empleadoIdController;
    @Inject
    private HorarioController horarioIdController;
    @Inject
    private GrupoHorarioController grupoHorarioIdController;

    public EmpleadoHorarioController() {
        // Inform the Abstract parent controller of the concrete EmpleadoHorario?cap_first Entity
        super(EmpleadoHorario.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        empleadoIdController.setSelected(null);
        horarioIdController.setSelected(null);
        grupoHorarioIdController.setSelected(null);
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
     * Sets the "selected" attribute of the Horario controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareHorarioId(ActionEvent event) {
        if (this.getSelected() != null && horarioIdController.getSelected() == null) {
            horarioIdController.setSelected(this.getSelected().getHorarioId());
        }
    }

    /**
     * Sets the "selected" attribute of the GrupoHorario controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareGrupoHorarioId(ActionEvent event) {
        if (this.getSelected() != null && grupoHorarioIdController.getSelected() == null) {
            grupoHorarioIdController.setSelected(this.getSelected().getGrupoHorarioId());
        }
    }
}
