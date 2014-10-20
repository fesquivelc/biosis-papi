package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.TipoContratoFacadeLocal;
import com.project.jsica.ejb.entidades.TipoContrato;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tipoContratoController")
@ViewScoped
public class TipoContratoController extends AbstractController<TipoContrato> {
    @EJB
    private TipoContratoFacadeLocal tipoContratoFacade;
    @Inject
    private ContratoController contratoListController;

    public TipoContratoController() {
        // Inform the Abstract parent controller of the concrete TipoContrato?cap_first Entity
        super(TipoContrato.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Contrato entities that
     * are retrieved from TipoContrato?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Contrato page
     */
    public String navigateContratoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Contrato_items", this.getSelected().getContratoList());
        }
        return "/contrato/index";
    }

    @Override
    protected void edit(TipoContrato objeto) {
        this.tipoContratoFacade.edit(objeto);
    }

    @Override
    protected void remove(TipoContrato objeto) {
        this.tipoContratoFacade.remove(objeto);
    }

    @Override
    public TipoContrato find(Object id) {
        return this.tipoContratoFacade.find(id);
    }

    @Override
    public List<TipoContrato> getItems() {
        return this.tipoContratoFacade.findAll();
    }

    @Override
    public List<TipoContrato> search(String namedQuery) {
        return this.tipoContratoFacade.search(namedQuery);
    }

    @Override
    public List<TipoContrato> search(String namedQuery, Map<String, Object> parametros) {
        return this.tipoContratoFacade.search(namedQuery, parametros);
    }

    @Override
    public List<TipoContrato> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.tipoContratoFacade.search(namedQuery, parametros, inicio, tamanio);
    }

}
