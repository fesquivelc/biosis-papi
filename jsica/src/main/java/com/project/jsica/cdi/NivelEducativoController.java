package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.NivelEducativoFacadeLocal;
import com.project.jsica.ejb.entidades.NivelEducativo;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "nivelEducativoController")
@ViewScoped
public class NivelEducativoController extends AbstractController<NivelEducativo> {
    @EJB
    private NivelEducativoFacadeLocal nivelEducativoFacade;
    @Inject
    private FichaGeneralEmpleadoController fichaGeneralEmpleadoListController;

    public NivelEducativoController() {
        // Inform the Abstract parent controller of the concrete NivelEducativo?cap_first Entity
        super(NivelEducativo.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of FichaGeneralEmpleado
     * entities that are retrieved from NivelEducativo?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for FichaGeneralEmpleado page
     */
    public String navigateFichaGeneralEmpleadoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("FichaGeneralEmpleado_items", this.getSelected().getFichaGeneralEmpleadoList());
        }
        return "/fichaGeneralEmpleado/index";
    }

    @Override
    protected void edit(NivelEducativo objeto) {
        this.nivelEducativoFacade.edit(objeto);
    }

    @Override
    protected void remove(NivelEducativo objeto) {
        this.nivelEducativoFacade.remove(objeto);
    }

    @Override
    public NivelEducativo find(Object id) {
        return this.nivelEducativoFacade.find(id);
    }

    @Override
    public List<NivelEducativo> getItems() {
        return this.nivelEducativoFacade.findAll();
    }

    @Override
    public List<NivelEducativo> search(String namedQuery) {
        return this.nivelEducativoFacade.search(namedQuery);
    }

    @Override
    public List<NivelEducativo> search(String namedQuery, Map<String, Object> parametros) {
        return this.nivelEducativoFacade.search(namedQuery, parametros);
    }

    @Override
    public List<NivelEducativo> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.nivelEducativoFacade.search(namedQuery, parametros, inicio, tamanio);
    }

}
