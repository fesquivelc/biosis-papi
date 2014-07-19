package com.project.jsica.cdi;

import com.project.jsica.ejb.entidades.Feriado;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "feriadoController")
@ViewScoped
public class FeriadoController extends AbstractController<Feriado> {

    @Inject
    private AnioController anioIdController;

    public FeriadoController() {
        // Inform the Abstract parent controller of the concrete Feriado?cap_first Entity
        super(Feriado.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        anioIdController.setSelected(null);
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
}
