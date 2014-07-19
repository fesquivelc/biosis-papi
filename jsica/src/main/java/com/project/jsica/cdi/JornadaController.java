package com.project.jsica.cdi;

import com.project.jsica.ejb.entidades.Jornada;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "jornadaController")
@ViewScoped
public class JornadaController extends AbstractController<Jornada> {

    @Inject
    private ServicioController servicioIdController;
    @Inject
    private DetalleHorarioController detalleHorarioListController;

    public JornadaController() {
        // Inform the Abstract parent controller of the concrete Jornada?cap_first Entity
        super(Jornada.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        servicioIdController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Servicio controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareServicioId(ActionEvent event) {
        if (this.getSelected() != null && servicioIdController.getSelected() == null) {
            servicioIdController.setSelected(this.getSelected().getServicioId());
        }
    }

    /**
     * Sets the "items" attribute with a collection of DetalleHorario entities
     * that are retrieved from Jornada?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for DetalleHorario page
     */
    public String navigateDetalleHorarioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetalleHorario_items", this.getSelected().getDetalleHorarioList());
        }
        return "/detalleHorario/index";
    }

}
