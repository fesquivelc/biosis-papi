package com.project.jsica.cdi;

import dao.ContratoFacadeLocal;
import com.project.jsica.ejb.entidades.Area;
import com.project.jsica.ejb.entidades.Bitacora;
import com.project.jsica.ejb.entidades.Contrato;
import com.project.jsica.ejb.entidades.DetalleContrato;
import com.project.jsica.ejb.entidades.Sucursal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named(value = "contratoController")
@ViewScoped
public class ContratoController extends AbstractController<Contrato> {

    private DetalleContrato detallecontratoseleccionado;

    private Sucursal sucursalSeleccionado;
    private boolean isSucursalSeleccionado;
    private Area areaSeleccionado;
    private boolean isAreaSeleccionado;

    @EJB
    private ContratoFacadeLocal contratoFacade;

    @Inject
    private BitacoraController bitacoraC;

    @Inject
    private DetalleContratoController detalleContratoListController;
    @Inject
    private ContratoController condicionLaboralIdController;
    @Inject
    private RegimenLaboralController regimenLaboralIdController;
    @Inject
    private AnioController anioIdController;
    @Inject
    private TipoContratoController tipoContratoIdController;
    @Inject
    private OcupacionController ocupacionIdController;

    public ContratoController() {
        // Inform the Abstract parent controller of the concrete Contrato?cap_first Entity
        super(Contrato.class);
    }

    public DetalleContrato getDetallecontratoseleccionado() {
        return detallecontratoseleccionado;
    }

    public void setDetallecontratoseleccionado(DetalleContrato detallecontratoseleccionado) {
        this.detallecontratoseleccionado = detallecontratoseleccionado;
    }

    public Sucursal getSucursalSeleccionado() {
        return sucursalSeleccionado;
    }

    public void setSucursalSeleccionado(Sucursal sucursalSeleccionado) {
        this.sucursalSeleccionado = sucursalSeleccionado;
    }

    public Area getAreaSeleccionado() {
        return areaSeleccionado;
    }

    public void setAreaSeleccionado(Area areaSeleccionado) {
        this.areaSeleccionado = areaSeleccionado;
    }

    public boolean isIsSucursalSeleccionado() {
        return isSucursalSeleccionado;
    }

    public void setIsSucursalSeleccionado(boolean isSucursalSeleccionado) {
        this.isSucursalSeleccionado = isSucursalSeleccionado;
    }

    public boolean isIsAreaSeleccionado() {
        return isAreaSeleccionado;
    }

    public void setIsAreaSeleccionado(boolean isAreaSeleccionado) {
        this.isAreaSeleccionado = isAreaSeleccionado;
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        condicionLaboralIdController.setSelected(null);
        regimenLaboralIdController.setSelected(null);
        anioIdController.setSelected(null);
        tipoContratoIdController.setSelected(null);
        ocupacionIdController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of DetalleContrato entities
     * that are retrieved from Contrato?cap_first and returns the navigation
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
     * Sets the "selected" attribute of the Contrato controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareContratoId(ActionEvent event) {
        if (this.getSelected() != null && condicionLaboralIdController.getSelected() == null) {
            condicionLaboralIdController.setSelected(this.getSelected());
        }
    }

    /**
     * Sets the "selected" attribute of the RegimenLaboral controller in order
     * to display its data in a dialog. This is reusing existing the existing
     * View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRegimenLaboralId(ActionEvent event) {
        if (this.getSelected() != null && regimenLaboralIdController.getSelected() == null) {
            regimenLaboralIdController.setSelected(this.getSelected().getRegimenLaboralId());
        }
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
     * Sets the "selected" attribute of the Ocupacion controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareOcupacionId(ActionEvent event) {
        if (this.getSelected() != null && ocupacionIdController.getSelected() == null) {
            ocupacionIdController.setSelected(this.getSelected().getOcupacionId());
        }
    }

    @Override
    protected void edit(Contrato objeto) {
        this.contratoFacade.edit(objeto);        
    }

    @Override
    protected void remove(Contrato objeto) { 
        this.contratoFacade.remove(objeto);
    }

    @Override
    public Contrato find(Object id) {
        return this.contratoFacade.find(id);
    }

    @Override
    public List<Contrato> getItems() {
        return this.contratoFacade.findAll();
    }

    @Override
    public List<Contrato> search(String namedQuery) {
        return this.contratoFacade.search(namedQuery);
    }

    @Override
    public List<Contrato> search(String namedQuery, Map<String, Object> parametros) {
        return this.contratoFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Contrato> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.contratoFacade.search(namedQuery, parametros, inicio, tamanio);
    }

    @Override
    public Contrato prepareCreate(ActionEvent event) {
        Contrato contrato = new Contrato();

        contrato.setDetalleContratoList(new ArrayList<DetalleContrato>());
        detallecontratoseleccionado = new DetalleContrato();
        contrato.getDetalleContratoList().add(detallecontratoseleccionado);
        detallecontratoseleccionado.setContratoId(contrato);
        this.setSelected(contrato);
        return contrato;
    }

    public void onSucursalSeleccionado() {
        if (this.sucursalSeleccionado != null) {
            if (this.sucursalSeleccionado.getId() != 0) {
                this.isSucursalSeleccionado = true;
                return;
            }
        }
        this.isSucursalSeleccionado = false;
    }

    public void onSucursalSeleccionado2() {
        if (this.detallecontratoseleccionado.getAreaId().getSucursalId() != null) {
            if (this.detallecontratoseleccionado.getAreaId().getSucursalId().getId() != 0) {
                this.isSucursalSeleccionado = true;
                return;
            }
        }
        this.isSucursalSeleccionado = false;
    }

    public List<Area> getAreas() {
        if (this.isSucursalSeleccionado) {
            return this.sucursalSeleccionado.getAreaList();
        } else {
            return null;
        }
    }

//     public List<Area> getAreas2(){        
//        if(this.isSucursalSeleccionado){
//            return this.detallecontratoseleccionado.ge this.sucursalSeleccionado.getAreaList();
//        }else{
//            return null;
//        }
//    }
}
