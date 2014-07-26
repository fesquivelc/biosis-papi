package com.project.jsica.cdi;


import com.project.jsica.ejb.dao.BitacoraFacadeLocal;
import com.project.jsica.ejb.entidades.Bitacora;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "bitacoraController")
@ApplicationScoped
public class BitacoraController extends AbstractController<Bitacora> {
    @EJB
    private BitacoraFacadeLocal bitacoraFacade;
    
    public BitacoraController() {
        // Inform the Abstract parent controller of the concrete Area?cap_first Entity
        super(Bitacora.class);
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