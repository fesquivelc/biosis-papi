package com.project.jsica.cdi;

import dao.TipoEmpleadoFacadeLocal;
import com.project.jsica.ejb.entidades.TipoEmpleado;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tipoEmpleadoController")
@ViewScoped
public class TipoEmpleadoController extends AbstractController<TipoEmpleado> {
    @EJB
    private TipoEmpleadoFacadeLocal tipoEmpleadoFacade;
    @Inject
    private FichaLaboralEmpleadoController fichaLaboralEmpleadoListController;

    public TipoEmpleadoController() {
        // Inform the Abstract parent controller of the concrete TipoEmpleado?cap_first Entity
        super(TipoEmpleado.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of FichaLaboralEmpleado
     * entities that are retrieved from TipoEmpleado?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for FichaLaboralEmpleado page
     */
    public String navigateFichaLaboralEmpleadoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("FichaLaboralEmpleado_items", this.getSelected().getFichaLaboralEmpleadoList());
        }
        return "/fichaLaboralEmpleado/index";
    }

    @Override
    protected void edit(TipoEmpleado objeto) {
        this.tipoEmpleadoFacade.edit(objeto);
    }

    @Override
    protected void remove(TipoEmpleado objeto) {
        this.tipoEmpleadoFacade.remove(objeto);
    }

    @Override
    public TipoEmpleado find(Object id) {
        return this.tipoEmpleadoFacade.find(id);
    }

    @Override
    public List<TipoEmpleado> getItems() {
        return this.tipoEmpleadoFacade.findAll();
    }

    @Override
    public List<TipoEmpleado> search(String namedQuery) {
        return this.tipoEmpleadoFacade.search(namedQuery);
    }

    @Override
    public List<TipoEmpleado> search(String namedQuery, Map<String, Object> parametros) {
        return this.tipoEmpleadoFacade.search(namedQuery, parametros);
    }

    @Override
    public List<TipoEmpleado> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.tipoEmpleadoFacade.search(namedQuery, parametros, inicio, tamanio);
    }

}
