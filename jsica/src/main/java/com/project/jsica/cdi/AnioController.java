package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.AnioFacadeLocal;
import com.project.jsica.ejb.entidades.Anio;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "anioController")
@ViewScoped
public class AnioController extends AbstractController<Anio> {
    
    @EJB
    private AnioFacadeLocal anioFacade;

    @Inject
    private ContratoController contratoListController;
    @Inject
    private FeriadoController feriadoListController;
    @Inject
    private VacacionController vacacionListController;

    public AnioController() {
        // Inform the Abstract parent controller of the concrete Anio?cap_first Entity
        super(Anio.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Contrato entities that
     * are retrieved from Anio?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Contrato page
     */
    public String navigateContratoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Contrato_items", this.getSelected().getContratoList());
        }
        return "/contrato/index";
    }

    /**
     * Sets the "items" attribute with a collection of Feriado entities that are
     * retrieved from Anio?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Feriado page
     */
    public String navigateFeriadoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Feriado_items", this.getSelected().getFeriadoList());
        }
        return "/feriado/index";
    }

    /**
     * Sets the "items" attribute with a collection of Vacacion entities that
     * are retrieved from Anio?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Vacacion page
     */
    public String navigateVacacionList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Vacacion_items", this.getSelected().getVacacionList());
        }
        return "/vacacion/index";
    }

    @Override
    protected void edit(Anio objeto) {
        this.anioFacade.edit(objeto);
    }

    @Override
    protected void remove(Anio objeto) {
        this.anioFacade.remove(objeto);
    }

    @Override
    public Anio find(Object id) {
        return this.anioFacade.find(id);
    }

    @Override
    public List<Anio> getItems() {
        return this.anioFacade.findAll();
    }

    @Override
    public List<Anio> search(String namedQuery) {
        return this.anioFacade.search(namedQuery);
    }

    @Override
    public List<Anio> search(String namedQuery, Map<String, Object> parametros) {
        return this.anioFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Anio> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.anioFacade.search(namedQuery, parametros, inicio, tamanio);
    }

}
