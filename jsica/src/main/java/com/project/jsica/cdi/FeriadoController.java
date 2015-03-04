package com.project.jsica.cdi;

import dao.FeriadoFacadeLocal;
import com.project.jsica.ejb.entidades.Feriado;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "feriadoController")
@ViewScoped
public class FeriadoController extends AbstractController<Feriado> {

    @EJB
    private FeriadoFacadeLocal feriadoFacade;

    @Inject
    private BitacoraController bitacoraC;

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

    @Override
    protected void edit(Feriado objeto) {
        this.feriadoFacade.edit(objeto);
    }

    @Override
    protected void remove(Feriado objeto) {
        this.feriadoFacade.remove(objeto);
    }

    @Override
    public Feriado find(Object id) {
        return this.feriadoFacade.find(id);
    }

    @Override
    public List<Feriado> getItems() {
        return this.feriadoFacade.findAll();
    }

    @Override
    public List<Feriado> search(String namedQuery) {
        return this.feriadoFacade.search(namedQuery);
    }

    @Override
    public List<Feriado> search(String namedQuery, Map<String, Object> parametros) {
        return this.feriadoFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Feriado> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.feriadoFacade.search(namedQuery, parametros, inicio, tamanio);
    }
}
