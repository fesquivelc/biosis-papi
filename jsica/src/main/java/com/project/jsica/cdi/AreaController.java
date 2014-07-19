package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.AreaFacadeLocal;
import com.project.jsica.ejb.entidades.Area;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "areaController")
@ViewScoped
public class AreaController extends AbstractController<Area> {
    @EJB
    private AreaFacadeLocal areaFacade;
    @Inject
    private DetalleContratoController detalleContratoListController;
    @Inject
    private SucursalController sucursalIdController;
    @Inject
    private ServicioController servicioListController;

    public AreaController() {
        // Inform the Abstract parent controller of the concrete Area?cap_first Entity
        super(Area.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        sucursalIdController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of DetalleContrato entities
     * that are retrieved from Area?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for DetalleContrato page
     */
    public String navigateDetalleContratoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetalleContrato_items", this.getSelected().getDetalleContratoList());
        }
        return "/detalleContrato/index";
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

    /**
     * Sets the "items" attribute with a collection of Servicio entities that
     * are retrieved from Area?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Servicio page
     */
    public String navigateServicioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Servicio_items", this.getSelected().getServicioList());
        }
        return "/servicio/index";
    }

    @Override
    protected void edit(Area objeto) {
        this.areaFacade.edit(objeto);
    }

    @Override
    protected void remove(Area objeto) {
        this.areaFacade.remove(objeto);
    }

    @Override
    public Area find(Object id) {
        return this.areaFacade.find(id);
    }

    @Override
    public List<Area> getItems() {
        return this.areaFacade.findAll();
    }

    @Override
    public List<Area> search(String namedQuery) {
        return this.areaFacade.search(namedQuery);
    }

    @Override
    public List<Area> search(String namedQuery, Map<String, Object> parametros) {
        return this.areaFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Area> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.areaFacade.search(namedQuery, parametros, inicio, tamanio);
    }

}
