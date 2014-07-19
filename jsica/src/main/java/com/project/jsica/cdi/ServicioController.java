package com.project.jsica.cdi;

import com.project.jsica.ejb.entidades.Servicio;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "servicioController")
@ViewScoped
public class ServicioController extends AbstractController<Servicio> {

    @Inject
    private JornadaController jornadaListController;
    @Inject
    private EmpleadoController empleadoListController;
    @Inject
    private AreaController areaIdController;

    public ServicioController() {
        // Inform the Abstract parent controller of the concrete Servicio?cap_first Entity
        super(Servicio.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        areaIdController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of Jornada entities that are
     * retrieved from Servicio?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Jornada page
     */
    public String navigateJornadaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Jornada_items", this.getSelected().getJornadaList());
        }
        return "/jornada/index";
    }

    /**
     * Sets the "items" attribute with a collection of Empleado entities that
     * are retrieved from Servicio?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Empleado page
     */
    public String navigateEmpleadoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Empleado_items", this.getSelected().getEmpleadoList());
        }
        return "/empleado/index";
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
}
