package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.RegistroAsistenciaFacadeLocal;
import com.project.jsica.ejb.entidades.RegistroAsistencia;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "registroAsistenciaController")
@ViewScoped
public class RegistroAsistenciaController extends AbstractController<RegistroAsistencia> {
    @EJB
    private RegistroAsistenciaFacadeLocal registroAsistenciaFacade;
    @Inject
    private BiometricoController biometricoIdController;
    @Inject
    private EmpleadoController empleadoIdController;

    public RegistroAsistenciaController() {
        // Inform the Abstract parent controller of the concrete RegistroAsistencia?cap_first Entity
        super(RegistroAsistencia.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        biometricoIdController.setSelected(null);
        empleadoIdController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Biometrico controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareBiometricoId(ActionEvent event) {
        if (this.getSelected() != null && biometricoIdController.getSelected() == null) {
            biometricoIdController.setSelected(this.getSelected().getBiometricoId());
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
    protected void edit(RegistroAsistencia objeto) {
        this.registroAsistenciaFacade.edit(objeto);
    }

    @Override
    protected void remove(RegistroAsistencia objeto) {
        this.registroAsistenciaFacade.remove(objeto);
    }

    @Override
    public RegistroAsistencia find(Object id) {
        return this.registroAsistenciaFacade.find(id);
    }

    @Override
    public List<RegistroAsistencia> getItems() {
        return this.registroAsistenciaFacade.findAll();
    }

    @Override
    public List<RegistroAsistencia> search(String namedQuery) {
        return this.registroAsistenciaFacade.search(namedQuery);
    }

    @Override
    public List<RegistroAsistencia> search(String namedQuery, Map<String, Object> parametros) {
        return this.registroAsistenciaFacade.search(namedQuery, parametros);
    }

    @Override
    public List<RegistroAsistencia> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.registroAsistenciaFacade.search(namedQuery, parametros, inicio, tamanio);
    }
}
