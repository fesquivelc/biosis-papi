package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.UbigeoFacadeLocal;
import com.project.jsica.ejb.entidades.Ubigeo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "ubigeoController")
@ViewScoped
public class UbigeoController extends AbstractController<Ubigeo> {
    private Ubigeo seleccionado;
    @EJB
    private UbigeoFacadeLocal ubigeoFacade;
    @Inject
    private FichaGeneralEmpleadoController fichaGeneralEmpleadoListController;    
    
    public Ubigeo getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Ubigeo seleccionado) {
        this.seleccionado = seleccionado;
    }

    public UbigeoController() {
        // Inform the Abstract parent controller of the concrete Ubigeo?cap_first Entity
        super(Ubigeo.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of FichaGeneralEmpleado
     * entities that are retrieved from Ubigeo?cap_first and returns the
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
    protected void edit(Ubigeo objeto) {
        this.ubigeoFacade.edit(objeto);
    }

    @Override
    protected void remove(Ubigeo objeto) {
        this.ubigeoFacade.remove(objeto);
    }

    @Override
    public Ubigeo find(Object id) {
        return this.ubigeoFacade.find(id);
    }

    @Override
    public List<Ubigeo> getItems() {
        return this.ubigeoFacade.findAll();
    }

    @Override
    public List<Ubigeo> search(String namedQuery) {
        return this.ubigeoFacade.search(namedQuery);
    }

    @Override
    public List<Ubigeo> search(String namedQuery, Map<String, Object> parametros) {
        return this.ubigeoFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Ubigeo> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.ubigeoFacade.search(namedQuery, parametros, inicio, tamanio);
    }
    
    public List<Ubigeo> metodo(String parametro)
    {
        String query = "SELECT u FROM Ubigeo u WHERE CONCAT(u.departamento,u.provincia,u.distrito) LIKE CONCAT('%',:parametro,'%')";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("parametro", parametro);
        
        return this.getEjbFacade().search(query, parametros);
    }

}
