package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.HorarioFacadeLocal;
import com.project.jsica.ejb.entidades.Horario;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "horarioController")
@ViewScoped
public class HorarioController extends AbstractController<Horario> {
    @EJB
    private HorarioFacadeLocal horarioFacade;
    @Inject
    private DetalleHorarioController detalleHorarioListController;
    @Inject
    private EmpleadoHorarioController empleadoHorarioListController;

    public HorarioController() {
        // Inform the Abstract parent controller of the concrete Horario?cap_first Entity
        super(Horario.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of DetalleHorario entities
     * that are retrieved from Horario?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for DetalleHorario page
     */
    public String navigateDetalleHorarioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetalleHorario_items", this.getSelected().getDetalleHorarioList());
        }
        return "/detalleHorario/index";
    }

    /**
     * Sets the "items" attribute with a collection of EmpleadoHorario entities
     * that are retrieved from Horario?cap_first and returns the navigation
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
    protected void edit(Horario objeto) {
        this.horarioFacade.edit(objeto);
    }

    @Override
    protected void remove(Horario objeto) {
        this.horarioFacade.remove(objeto);
    }

    @Override
    public Horario find(Object id) {
        return this.horarioFacade.find(id);
    }

    @Override
    public List<Horario> getItems() {
        return this.horarioFacade.findAll();
    }

    @Override
    public List<Horario> search(String namedQuery) {
        return this.horarioFacade.search(namedQuery);
    }

    @Override
    public List<Horario> search(String namedQuery, Map<String, Object> parametros) {
        return this.horarioFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Horario> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.horarioFacade.search(namedQuery, parametros, inicio, tamanio);
    }

}
