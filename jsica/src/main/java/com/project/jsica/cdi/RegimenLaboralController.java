package com.project.jsica.cdi;

import dao.RegimenLaboralFacadeLocal;
import com.project.jsica.ejb.entidades.RegimenLaboral;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "regimenLaboralController")
@ViewScoped
public class RegimenLaboralController extends AbstractController<RegimenLaboral> {
    @EJB
    private RegimenLaboralFacadeLocal regimenLaboralFacade;
    
    @Inject
    private BitacoraController bitacoraC;
    
    @Inject
    private ContratoController contratoListController;

    public RegimenLaboralController() {
        // Inform the Abstract parent controller of the concrete RegimenLaboral?cap_first Entity
        super(RegimenLaboral.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Contrato entities that
     * are retrieved from RegimenLaboral?cap_first and returns the navigation
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
    protected void edit(RegimenLaboral objeto) {
        this.regimenLaboralFacade.edit(objeto);
        
        
    }

    @Override
    protected void remove(RegimenLaboral objeto) {
        this.regimenLaboralFacade.remove(objeto);
    }

    @Override
    public RegimenLaboral find(Object id) {
        return this.regimenLaboralFacade.find(id);
    }

    @Override
    public List<RegimenLaboral> getItems() {
        return this.regimenLaboralFacade.findAll();
    }

    @Override
    public List<RegimenLaboral> search(String namedQuery) {
        return this.regimenLaboralFacade.search(namedQuery);
    }

    @Override
    public List<RegimenLaboral> search(String namedQuery, Map<String, Object> parametros) {
        return this.regimenLaboralFacade.search(namedQuery, parametros);
    }

    @Override
    public List<RegimenLaboral> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.regimenLaboralFacade.search(namedQuery, parametros, inicio, tamanio);
    }

}
