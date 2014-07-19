package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.CondicionLaboralFacadeLocal;
import com.project.jsica.ejb.entidades.CondicionLaboral;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "condicionLaboralController")
@ViewScoped
public class CondicionLaboralController extends AbstractController<CondicionLaboral> {
    @EJB
    private CondicionLaboralFacadeLocal condicionLaboralFacade;

    @Inject
    private ContratoController contratoListController;

    public CondicionLaboralController() {
        // Inform the Abstract parent controller of the concrete CondicionLaboral?cap_first Entity
        super(CondicionLaboral.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Contrato entities that
     * are retrieved from CondicionLaboral?cap_first and returns the navigation
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
    protected void edit(CondicionLaboral objeto) {
        this.condicionLaboralFacade.edit(objeto);
    }

    @Override
    protected void remove(CondicionLaboral objeto) {
        this.condicionLaboralFacade.remove(objeto);
    }

    @Override
    public CondicionLaboral find(Object id) {
        return this.condicionLaboralFacade.find(id);
    }

    @Override
    public List<CondicionLaboral> getItems() {
        return this.condicionLaboralFacade.findAll();
    }

    @Override
    public List<CondicionLaboral> search(String namedQuery) {
        return this.condicionLaboralFacade.search(namedQuery);
    }

    @Override
    public List<CondicionLaboral> search(String namedQuery, Map<String, Object> parametros) {
        return this.condicionLaboralFacade.search(namedQuery, parametros);
    }

    @Override
    public List<CondicionLaboral> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.condicionLaboralFacade.search(namedQuery, parametros, inicio, tamanio);
    }

}
