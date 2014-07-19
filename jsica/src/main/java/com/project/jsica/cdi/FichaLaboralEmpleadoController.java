package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.FichaLaboralEmpleadoFacadeLocal;
import com.project.jsica.ejb.entidades.FichaLaboralEmpleado;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "fichaLaboralEmpleadoController")
@ViewScoped
public class FichaLaboralEmpleadoController extends AbstractController<FichaLaboralEmpleado> {
    @EJB
    private FichaLaboralEmpleadoFacadeLocal fichaLaboralEmpleadoFacade;
    @Inject
    private EmpleadoController empleadoIdController;
    @Inject
    private TipoEmpleadoController tipoEmpleadoIdController;

    public FichaLaboralEmpleadoController() {
        // Inform the Abstract parent controller of the concrete FichaLaboralEmpleado?cap_first Entity
        super(FichaLaboralEmpleado.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        empleadoIdController.setSelected(null);
        tipoEmpleadoIdController.setSelected(null);
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
     * Sets the "selected" attribute of the TipoEmpleado controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTipoEmpleadoId(ActionEvent event) {
        if (this.getSelected() != null && tipoEmpleadoIdController.getSelected() == null) {
            tipoEmpleadoIdController.setSelected(this.getSelected().getTipoEmpleadoId());
        }
    }

    @Override
    protected void edit(FichaLaboralEmpleado objeto) {
        this.fichaLaboralEmpleadoFacade.edit(objeto);
    }

    @Override
    protected void remove(FichaLaboralEmpleado objeto) {
        this.fichaLaboralEmpleadoFacade.remove(objeto);
    }

    @Override
    public FichaLaboralEmpleado find(Object id) {
        return this.fichaLaboralEmpleadoFacade.find(id);
    }

    @Override
    public List<FichaLaboralEmpleado> getItems() {
        return this.fichaLaboralEmpleadoFacade.findAll();
    }

    @Override
    public List<FichaLaboralEmpleado> search(String namedQuery) {
        return this.fichaLaboralEmpleadoFacade.search(namedQuery);
    }

    @Override
    public List<FichaLaboralEmpleado> search(String namedQuery, Map<String, Object> parametros) {
        return this.fichaLaboralEmpleadoFacade.search(namedQuery, parametros);
    }

    @Override
    public List<FichaLaboralEmpleado> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.fichaLaboralEmpleadoFacade.search(namedQuery, parametros, inicio, tamanio);
    }
}
