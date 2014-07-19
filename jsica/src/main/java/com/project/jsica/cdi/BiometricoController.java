package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.BiometricoFacadeLocal;
import com.project.jsica.ejb.entidades.Biometrico;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "biometricoController")
@ViewScoped
public class BiometricoController extends AbstractController<Biometrico> {
    @EJB
    private BiometricoFacadeLocal biometricoFacade;
    @Inject
    private RegistroAsistenciaController registroAsistenciaListController;
    @Inject
    private SucursalController sucursalIdController;

    public BiometricoController() {
        // Inform the Abstract parent controller of the concrete Biometrico?cap_first Entity
        super(Biometrico.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        sucursalIdController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of RegistroAsistencia
     * entities that are retrieved from Biometrico?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for RegistroAsistencia page
     */
    public String navigateRegistroAsistenciaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RegistroAsistencia_items", this.getSelected().getRegistroAsistenciaList());
        }
        return "/registroAsistencia/index";
    }

    /**
     * Sets the "selected" attribute of the Sucursal controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareSucursalId(ActionEvent event) {
        if (this.getSelected() != null && sucursalIdController.getSelected() == null) {
            sucursalIdController.setSelected(this.getSelected().getSucursalId());
        }
    }

    @Override
    protected void edit(Biometrico objeto) {
        this.biometricoFacade.edit(objeto);
    }

    @Override
    protected void remove(Biometrico objeto) {
        this.biometricoFacade.remove(objeto);
    }

    @Override
    public Biometrico find(Object id) {
        return this.biometricoFacade.find(id);
    }

    @Override
    public List<Biometrico> getItems() {
        return this.biometricoFacade.findAll();
    }

    @Override
    public List<Biometrico> search(String namedQuery) {
        return this.biometricoFacade.search(namedQuery);
    }

    @Override
    public List<Biometrico> search(String namedQuery, Map<String, Object> parametros) {
        return this.biometricoFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Biometrico> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.biometricoFacade.search(namedQuery, parametros, inicio, tamanio);
    }
}
