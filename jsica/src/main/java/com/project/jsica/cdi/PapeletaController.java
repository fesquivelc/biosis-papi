package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.PapeletaFacadeLocal;
import com.project.jsica.ejb.entidades.Papeleta;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "papeletaController")
@ViewScoped
public class PapeletaController extends AbstractController<Papeleta> {
    @EJB
    private PapeletaFacadeLocal papeletaFacade;
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

    @Override
    protected void edit(Papeleta objeto) {
        this.papeletaFacade.edit(objeto);
    }

    @Override
    protected void remove(Papeleta objeto) {
        this.papeletaFacade.remove(objeto);
    }

    @Override
    public Papeleta find(Object id) {
        return this.papeletaFacade.find(id);
    }

    @Override
    public List<Papeleta> getItems() {
        return this.papeletaFacade.findAll();
    }

    @Override
    public List<Papeleta> search(String namedQuery) {
        return this.papeletaFacade.search(namedQuery);
    }

    @Override
    public List<Papeleta> search(String namedQuery, Map<String, Object> parametros) {
        return this.papeletaFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Papeleta> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.papeletaFacade.search(namedQuery, parametros, inicio, tamanio);
    }
}
