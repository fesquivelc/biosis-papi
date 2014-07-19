package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.GrupoHorarioFacadeLocal;
import com.project.jsica.ejb.entidades.GrupoHorario;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "grupoHorarioController")
@ViewScoped
public class GrupoHorarioController extends AbstractController<GrupoHorario> {
    @EJB
    private GrupoHorarioFacadeLocal grupoHorarioFacade;
    @Inject
    private EmpleadoController empleadoListController;
    @Inject
    private EmpleadoHorarioController empleadoHorarioListController;

    public GrupoHorarioController() {
        // Inform the Abstract parent controller of the concrete GrupoHorario?cap_first Entity
        super(GrupoHorario.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Empleado entities that
     * are retrieved from GrupoHorario?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Empleado page
     */
    public String navigateEmpleadoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Empleado_items", this.getSelected().getEmpleadoList());
        }
        return "/empleado/index";
    }

    /**
     * Sets the "items" attribute with a collection of EmpleadoHorario entities
     * that are retrieved from GrupoHorario?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for EmpleadoHorario page
     */
    public String navigateEmpleadoHorarioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EmpleadoHorario_items", this.getSelected().getEmpleadoHorarioList());
        }
        return "/empleadoHorario/index";
    }

    @Override
    protected void edit(GrupoHorario objeto) {
        this.grupoHorarioFacade.edit(objeto);
    }

    @Override
    protected void remove(GrupoHorario objeto) {
        this.grupoHorarioFacade.remove(objeto);
    }

    @Override
    public GrupoHorario find(Object id) {
        return this.grupoHorarioFacade.find(id);
    }

    @Override
    public List<GrupoHorario> getItems() {
        return this.grupoHorarioFacade.findAll();
    }

    @Override
    public List<GrupoHorario> search(String namedQuery) {
        return this.grupoHorarioFacade.search(namedQuery);
    }

    @Override
    public List<GrupoHorario> search(String namedQuery, Map<String, Object> parametros) {
        return this.grupoHorarioFacade.search(namedQuery, parametros);
    }

    @Override
    public List<GrupoHorario> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.grupoHorarioFacade.search(namedQuery, parametros, inicio, tamanio);
    }

}
