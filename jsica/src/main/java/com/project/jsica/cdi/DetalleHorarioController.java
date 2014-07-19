package com.project.jsica.cdi;

import com.project.jsica.ejb.entidades.DetalleHorario;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "detalleHorarioController")
@ViewScoped
public class DetalleHorarioController extends AbstractController<DetalleHorario> {

    @Inject
    private HorarioController horarioIdController;
    @Inject
    private JornadaController jornadaCodigoController;
    @Inject
    private CambioTurnoController cambioTurnoListController;
    @Inject
    private CambioTurnoController cambioTurnoList1Controller;

    public DetalleHorarioController() {
        // Inform the Abstract parent controller of the concrete DetalleHorario?cap_first Entity
        super(DetalleHorario.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        horarioIdController.setSelected(null);
        jornadaCodigoController.setSelected(null);
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
     * Sets the "selected" attribute of the Jornada controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareJornadaCodigo(ActionEvent event) {
        if (this.getSelected() != null && jornadaCodigoController.getSelected() == null) {
            jornadaCodigoController.setSelected(this.getSelected().getJornadaCodigo());
        }
    }

    /**
     * Sets the "items" attribute with a collection of CambioTurno entities that
     * are retrieved from DetalleHorario?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for CambioTurno page
     */
    public String navigateCambioTurnoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CambioTurno_items", this.getSelected().getCambioTurnoList());
        }
        return "/cambioTurno/index";
    }

    /**
     * Sets the "items" attribute with a collection of CambioTurno entities that
     * are retrieved from DetalleHorario?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for CambioTurno page
     */
    public String navigateCambioTurnoList1() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CambioTurno_items", this.getSelected().getCambioTurnoList1());
        }
        return "/cambioTurno/index";
    }

}
