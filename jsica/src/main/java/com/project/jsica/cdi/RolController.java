package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.RolFacadeLocal;
import com.project.jsica.ejb.entidades.Rol;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "rolController")
@ViewScoped
public class RolController extends AbstractController<Rol> {
    @EJB
    private RolFacadeLocal rolFacade;
    @Inject
    private UsuarioController usuarioListController;

    public RolController() {
        // Inform the Abstract parent controller of the concrete Rol?cap_first Entity
        super(Rol.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Usuario entities that are
     * retrieved from Rol?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Usuario page
     */
    public String navigateUsuarioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Usuario_items", this.getSelected().getUsuarioList());
        }
        return "/usuario/index";
    }

    @Override
    protected void edit(Rol objeto) {
        this.rolFacade.edit(objeto);
    }

    @Override
    protected void remove(Rol objeto) {
        this.rolFacade.remove(objeto);
    }

    @Override
    public Rol find(Object id) {
        return this.rolFacade.find(id);
    }

    @Override
    public List<Rol> getItems() {
        return this.rolFacade.findAll();
    }

    @Override
    public List<Rol> search(String namedQuery) {
        return this.rolFacade.search(namedQuery);
    }

    @Override
    public List<Rol> search(String namedQuery, Map<String, Object> parametros) {
        return this.rolFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Rol> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.rolFacade.search(namedQuery, parametros, inicio, tamanio);
    }

}
