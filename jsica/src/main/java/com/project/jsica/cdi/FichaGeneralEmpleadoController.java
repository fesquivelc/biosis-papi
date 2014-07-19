package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.FichaGeneralEmpleadoFacadeLocal;
import com.project.jsica.ejb.entidades.FichaGeneralEmpleado;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "fichaGeneralEmpleadoController")
@ViewScoped
public class FichaGeneralEmpleadoController extends AbstractController<FichaGeneralEmpleado> {
    @EJB
    private FichaGeneralEmpleadoFacadeLocal fichaGeneralEmpleadoFacade;
    @Inject
    private EmpleadoController empleadoIdController;
    @Inject
    private UbigeoController ubigeoCodigoController;
    @Inject
    private NivelEducativoController nivelEducativoIdController;

    public FichaGeneralEmpleadoController() {
        // Inform the Abstract parent controller of the concrete FichaGeneralEmpleado?cap_first Entity
        super(FichaGeneralEmpleado.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        empleadoIdController.setSelected(null);
        ubigeoCodigoController.setSelected(null);
        nivelEducativoIdController.setSelected(null);
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
     * Sets the "selected" attribute of the Ubigeo controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareUbigeoCodigo(ActionEvent event) {
        if (this.getSelected() != null && ubigeoCodigoController.getSelected() == null) {
            ubigeoCodigoController.setSelected(this.getSelected().getUbigeoCodigo());
        }
    }

    /**
     * Sets the "selected" attribute of the NivelEducativo controller in order
     * to display its data in a dialog. This is reusing existing the existing
     * View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareNivelEducativoId(ActionEvent event) {
        if (this.getSelected() != null && nivelEducativoIdController.getSelected() == null) {
            nivelEducativoIdController.setSelected(this.getSelected().getNivelEducativoId());
        }
    }

    @Override
    protected void edit(FichaGeneralEmpleado objeto) {
        this.fichaGeneralEmpleadoFacade.edit(objeto);
    }

    @Override
    protected void remove(FichaGeneralEmpleado objeto) {
        this.fichaGeneralEmpleadoFacade.remove(objeto);
    }

    @Override
    public FichaGeneralEmpleado find(Object id) {
        return this.fichaGeneralEmpleadoFacade.find(id);
    }

    @Override
    public List<FichaGeneralEmpleado> getItems() {
        return this.fichaGeneralEmpleadoFacade.findAll();
    }

    @Override
    public List<FichaGeneralEmpleado> search(String namedQuery) {
        return this.fichaGeneralEmpleadoFacade.search(namedQuery);
    }

    @Override
    public List<FichaGeneralEmpleado> search(String namedQuery, Map<String, Object> parametros) {
        return this.fichaGeneralEmpleadoFacade.search(namedQuery, parametros);
    }

    @Override
    public List<FichaGeneralEmpleado> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.fichaGeneralEmpleadoFacade.search(namedQuery, parametros, inicio, tamanio);
    }
}
