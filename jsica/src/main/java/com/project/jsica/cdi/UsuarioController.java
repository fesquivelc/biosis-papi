package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.UsuarioFacadeLocal;
import com.project.jsica.ejb.entidades.Usuario;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "usuarioController")
@ViewScoped
public class UsuarioController extends AbstractController<Usuario> {
    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    @Inject
    private EmpleadoController empleadoIdController;
    @Inject
    private RolController rolIdController;

    public UsuarioController() {
        // Inform the Abstract parent controller of the concrete Usuario?cap_first Entity
        super(Usuario.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        empleadoIdController.setSelected(null);
        rolIdController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Empleado controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEmpleadoId(ActionEvent event) {
        if (this.getSelected() != null && empleadoIdController.getSelected() == null) {
            empleadoIdController.setSelected(this.getSelected().getEmpleadoId());
        }
    }

    /**
     * Sets the "selected" attribute of the Rol controller in order to display
     * its data in a dialog. This is reusing existing the existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRolId(ActionEvent event) {
        if (this.getSelected() != null && rolIdController.getSelected() == null) {
            rolIdController.setSelected(this.getSelected().getRolId());
        }
    }

    @Override
    protected void edit(Usuario objeto) {
        this.usuarioFacade.edit(objeto);
    }

    @Override
    protected void remove(Usuario objeto) {
        this.usuarioFacade.remove(objeto);
    }

    @Override
    public Usuario find(Object id) {
        return this.usuarioFacade.find(id);
    }

    @Override
    public List<Usuario> getItems() {
        return this.usuarioFacade.findAll();
    }

    @Override
    public List<Usuario> search(String namedQuery) {
        return this.usuarioFacade.search(namedQuery);
    }

    @Override
    public List<Usuario> search(String namedQuery, Map<String, Object> parametros) {
        return this.usuarioFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Usuario> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.usuarioFacade.search(namedQuery, parametros, inicio, tamanio);
    }
}
