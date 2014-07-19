package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.CambioTurnoFacadeLocal;
import com.project.jsica.ejb.entidades.CambioTurno;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "cambioTurnoController")
@ViewScoped
public class CambioTurnoController extends AbstractController<CambioTurno> {
    @EJB
    private CambioTurnoFacadeLocal cambioTurnoFacade;
    @Inject
    private DetalleHorarioController detalleHorarioOriginalController;
    @Inject
    private DetalleHorarioController detalleHorarioReemplazoController;
    @Inject
    private EmpleadoController jefeInmediatoIdController;

    public CambioTurnoController() {
        // Inform the Abstract parent controller of the concrete CambioTurno?cap_first Entity
        super(CambioTurno.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        detalleHorarioOriginalController.setSelected(null);
        detalleHorarioReemplazoController.setSelected(null);
        jefeInmediatoIdController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the DetalleHorario controller in order
     * to display its data in a dialog. This is reusing existing the existing
     * View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDetalleHorarioOriginal(ActionEvent event) {
        if (this.getSelected() != null && detalleHorarioOriginalController.getSelected() == null) {
            detalleHorarioOriginalController.setSelected(this.getSelected().getDetalleHorarioOriginal());
        }
    }

    /**
     * Sets the "selected" attribute of the DetalleHorario controller in order
     * to display its data in a dialog. This is reusing existing the existing
     * View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDetalleHorarioReemplazo(ActionEvent event) {
        if (this.getSelected() != null && detalleHorarioReemplazoController.getSelected() == null) {
            detalleHorarioReemplazoController.setSelected(this.getSelected().getDetalleHorarioReemplazo());
        }
    }

    /**
     * Sets the "selected" attribute of the Empleado controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareJefeInmediatoId(ActionEvent event) {
        if (this.getSelected() != null && jefeInmediatoIdController.getSelected() == null) {
            jefeInmediatoIdController.setSelected(this.getSelected().getJefeInmediatoId());
        }
    }

    @Override
    protected void edit(CambioTurno objeto) {
        this.cambioTurnoFacade.edit(objeto);
    }

    @Override
    protected void remove(CambioTurno objeto) {
        this.cambioTurnoFacade.remove(objeto);
    }

    @Override
    public CambioTurno find(Object id) {
        return this.cambioTurnoFacade.find(id);
    }

    @Override
    public List<CambioTurno> getItems() {
        return this.cambioTurnoFacade.findAll();
    }

    @Override
    public List<CambioTurno> search(String namedQuery) {
        return this.cambioTurnoFacade.search(namedQuery);
    }

    @Override
    public List<CambioTurno> search(String namedQuery, Map<String, Object> parametros) {
        return this.cambioTurnoFacade.search(namedQuery, parametros);
    }

    @Override
    public List<CambioTurno> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.cambioTurnoFacade.search(namedQuery, parametros, inicio, tamanio);
    }
}
