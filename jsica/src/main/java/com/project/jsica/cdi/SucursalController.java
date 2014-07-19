package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.SucursalFacadeLocal;
import com.project.jsica.ejb.entidades.Sucursal;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "sucursalController")
@ViewScoped
public class SucursalController extends AbstractController<Sucursal> {
    @EJB
    private SucursalFacadeLocal sucursalFacade;
    @Inject
    private AreaController areaListController;
    @Inject
    private SucursalController sucursalListController;
    @Inject
    private SucursalController principalIdController;
    @Inject
    private BiometricoController biometricoListController;

    public SucursalController() {
        // Inform the Abstract parent controller of the concrete Sucursal?cap_first Entity
        super(Sucursal.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        principalIdController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of Area entities that are
     * retrieved from Sucursal?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Area page
     */
    public String navigateAreaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Area_items", this.getSelected().getAreaList());
        }
        return "/area/index";
    }

    /**
     * Sets the "items" attribute with a collection of Sucursal entities that
     * are retrieved from Sucursal?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Sucursal page
     */
    public String navigateSucursalList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Sucursal_items", this.getSelected().getSucursalList());
        }
        return "/sucursal/index";
    }

    /**
     * Sets the "selected" attribute of the Sucursal controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePrincipalId(ActionEvent event) {
        if (this.getSelected() != null && principalIdController.getSelected() == null) {
            principalIdController.setSelected(this.getSelected().getPrincipalId());
        }
    }

    /**
     * Sets the "items" attribute with a collection of Biometrico entities that
     * are retrieved from Sucursal?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Biometrico page
     */
    public String navigateBiometricoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Biometrico_items", this.getSelected().getBiometricoList());
        }
        return "/biometrico/index";
    }

    @Override
    protected void edit(Sucursal objeto) {
        this.sucursalFacade.edit(objeto);
    }

    @Override
    protected void remove(Sucursal objeto) {
        this.sucursalFacade.remove(objeto);
    }

    @Override
    public Sucursal find(Object id) {
        return this.sucursalFacade.find(id);
    }

    @Override
    public List<Sucursal> getItems() {
        return this.sucursalFacade.findAll();
    }

    @Override
    public List<Sucursal> search(String namedQuery) {
        return this.sucursalFacade.search(namedQuery);
    }

    @Override
    public List<Sucursal> search(String namedQuery, Map<String, Object> parametros) {
        return this.sucursalFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Sucursal> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.sucursalFacade.search(namedQuery, parametros, inicio, tamanio);
    }

}
