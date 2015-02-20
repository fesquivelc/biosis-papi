package com.project.jsica.cdi;

import dao.SuspensionFacadeLocal;
import com.project.jsica.ejb.entidades.Suspension;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "suspensionController")
@ViewScoped
public class SuspensionController extends AbstractController<Suspension> {
    @EJB
    private SuspensionFacadeLocal suspensionFacade;
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

    @Override
    protected void edit(Suspension objeto) {
        this.suspensionFacade.edit(objeto);
    }

    @Override
    protected void remove(Suspension objeto) {
        this.suspensionFacade.remove(objeto);
    }

    @Override
    public Suspension find(Object id) {
        return this.suspensionFacade.find(id);
    }

    @Override
    public List<Suspension> getItems() {
        return this.suspensionFacade.findAll();
    }

    @Override
    public List<Suspension> search(String namedQuery) {
        return this.suspensionFacade.search(namedQuery);
    }

    @Override
    public List<Suspension> search(String namedQuery, Map<String, Object> parametros) {
        return this.suspensionFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Suspension> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.suspensionFacade.search(namedQuery, parametros, inicio, tamanio);
    }
}
