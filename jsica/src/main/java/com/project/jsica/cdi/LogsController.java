package com.project.jsica.cdi;

import com.project.jsica.ejb.entidades.Logs;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;

@Named(value = "logsController")
@ViewScoped
public class LogsController extends AbstractController<Logs> {    
    public LogsController() {
        // Inform the Abstract parent controller of the concrete Logs?cap_first Entity
        super(Logs.class);
    }

    @Override
    protected void edit(Logs objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void remove(Logs objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Logs find(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Logs> getItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Logs> search(String namedQuery) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Logs> search(String namedQuery, Map<String, Object> parametros) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Logs> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
