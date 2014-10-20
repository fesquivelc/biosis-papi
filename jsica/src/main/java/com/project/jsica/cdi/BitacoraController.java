package com.project.jsica.cdi;

import com.project.jsica.cdi.util.JsfUtil;
import com.project.jsica.ejb.dao.BitacoraFacadeLocal;
import com.project.jsica.ejb.entidades.Bitacora;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.apache.log4j.Logger;

@Named(value = "bitacoraController")
@ApplicationScoped

public class BitacoraController extends AbstractController<Bitacora> {
    
    @EJB
    private BitacoraFacadeLocal bitacoraFacade;
    
    public BitacoraController() {
        // Inform the Abstract parent controller of the concrete Anio?cap_first Entity
        super(Bitacora.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    @Override
    protected void edit(Bitacora objeto) {
        this.bitacoraFacade.edit(objeto);
    }

    @Override
    protected void remove(Bitacora objeto) {
       this.bitacoraFacade.remove(objeto);
    }

    @Override
    public Bitacora find(Object id) {
        return this.bitacoraFacade.find(id);
    }

    @Override
    public List<Bitacora> getItems() {
        return this.bitacoraFacade.findAll();
    }

    @Override
    public List<Bitacora> search(String namedQuery) {
        return this.bitacoraFacade.search(namedQuery);
    }

    @Override
    public List<Bitacora> search(String namedQuery, Map<String, Object> parametros) {
        return this.bitacoraFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Bitacora> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.bitacoraFacade.search(namedQuery, parametros, inicio, tamanio);
    }
}
