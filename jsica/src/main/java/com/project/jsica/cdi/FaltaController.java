package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.FaltaFacadeLocal;
import com.project.jsica.ejb.entidades.Falta;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "faltaController")
@ViewScoped
public class FaltaController extends AbstractController<Falta> {
    @EJB
    private FaltaFacadeLocal faltaFacade;
    @Inject
    private EmpleadoController empleadoIdController;

    public FaltaController() {
        // Inform the Abstract parent controller of the concrete Falta?cap_first Entity
        super(Falta.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        empleadoIdController.setSelected(null);
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

    @Override
    protected void edit(Falta objeto) {
        this.faltaFacade.edit(objeto);
    }

    @Override
    protected void remove(Falta objeto) {
        this.faltaFacade.remove(objeto);
    }

    @Override
    public Falta find(Object id) {
        return this.faltaFacade.find(id);
    }

    @Override
    public List<Falta> getItems() {
        return this.faltaFacade.findAll();
    }

    @Override
    public List<Falta> search(String namedQuery) {
        return this.faltaFacade.search(namedQuery);
    }

    @Override
    public List<Falta> search(String namedQuery, Map<String, Object> parametros) {
        return this.faltaFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Falta> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.faltaFacade.search(namedQuery, parametros, inicio, tamanio);
    }
}
