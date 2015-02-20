package com.project.jsica.cdi;

import dao.VacacionFacadeLocal;
import com.project.jsica.ejb.entidades.Vacacion;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "vacacionController")
@ViewScoped
public class VacacionController extends AbstractController<Vacacion> {
    @EJB
    private VacacionFacadeLocal vacacionFacade;
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

    @Override
    protected void edit(Vacacion objeto) {
        this.vacacionFacade.edit(objeto);
    }

    @Override
    protected void remove(Vacacion objeto) {
        this.vacacionFacade.remove(objeto);
    }

    @Override
    public Vacacion find(Object id) {
        return this.vacacionFacade.find(id);
    }

    @Override
    public List<Vacacion> getItems() {
        return this.vacacionFacade.findAll();
    }

    @Override
    public List<Vacacion> search(String namedQuery) {
        return this.vacacionFacade.search(namedQuery);
    }

    @Override
    public List<Vacacion> search(String namedQuery, Map<String, Object> parametros) {
        return this.vacacionFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Vacacion> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.vacacionFacade.search(namedQuery, parametros, inicio, tamanio);
    }
}
