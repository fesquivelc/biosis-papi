package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.MotivoPermisoFacadeLocal;
import com.project.jsica.ejb.entidades.MotivoPermiso;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "motivoPermisoController")
@ViewScoped
public class MotivoPermisoController extends AbstractController<MotivoPermiso> {
    @EJB
    private MotivoPermisoFacadeLocal motivoPermisoFacade;
    @Inject
    private PermisoController permisoListController;

    public MotivoPermisoController() {
        // Inform the Abstract parent controller of the concrete MotivoPermiso?cap_first Entity
        super(MotivoPermiso.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Permiso entities that are
     * retrieved from MotivoPermiso?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Permiso page
     */
    public String navigatePermisoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Permiso_items", this.getSelected().getPermisoList());
        }
        return "/permiso/index";
    }

    @Override
    protected void edit(MotivoPermiso objeto) {
        this.motivoPermisoFacade.edit(objeto);
    }

    @Override
    protected void remove(MotivoPermiso objeto) {
        this.motivoPermisoFacade.remove(objeto);
    }

    @Override
    public MotivoPermiso find(Object id) {
        return this.motivoPermisoFacade.find(id);
    }

    @Override
    public List<MotivoPermiso> getItems() {
        return this.motivoPermisoFacade.findAll();
    }

    @Override
    public List<MotivoPermiso> search(String namedQuery) {
        return this.motivoPermisoFacade.search(namedQuery);
    }

    @Override
    public List<MotivoPermiso> search(String namedQuery, Map<String, Object> parametros) {
        return this.motivoPermisoFacade.search(namedQuery, parametros);
    }

    @Override
    public List<MotivoPermiso> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.motivoPermisoFacade.search(namedQuery, parametros, inicio, tamanio);
    }

}
