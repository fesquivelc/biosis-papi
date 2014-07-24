package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.OcupacionFacadeLocal;
import com.project.jsica.ejb.entidades.Ocupacion;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;

@Named(value = "ocupacionController")
@ViewScoped
public class OcupacionController extends AbstractController<Ocupacion> {
    private static final Logger log = Logger.getLogger(OcupacionController.class.getClass());
    
    @EJB
    private OcupacionFacadeLocal ocupacionFacade;
    @Inject
    private ContratoController contratoListController;

    public OcupacionController() {
        // Inform the Abstract parent controller of the concrete Ocupacion?cap_first Entity
        super(Ocupacion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Contrato entities that
     * are retrieved from Ocupacion?cap_first and returns the navigation
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
    protected void edit(Ocupacion objeto) {
        log.info("CREAR OCUPACION");
        this.ocupacionFacade.edit(objeto);
    }

    @Override
    protected void remove(Ocupacion objeto) {
        this.ocupacionFacade.remove(objeto);
    }

    @Override
    public Ocupacion find(Object id) {
        return this.ocupacionFacade.find(id);
    }

    @Override
    public List<Ocupacion> getItems() {
        return this.ocupacionFacade.findAll();
    }

    @Override
    public List<Ocupacion> search(String namedQuery) {
        return this.ocupacionFacade.search(namedQuery);
    }

    @Override
    public List<Ocupacion> search(String namedQuery, Map<String, Object> parametros) {
        return this.ocupacionFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Ocupacion> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.ocupacionFacade.search(namedQuery, parametros, inicio, tamanio);
    }

}
