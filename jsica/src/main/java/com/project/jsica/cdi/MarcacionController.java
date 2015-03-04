/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.jsica.cdi;

import com.personal.utiles.FechaUtil;
import com.project.jsica.ejb.entidades.Marcacion;
import com.project.jsica.lazymodel.MarcacionLazyModel;
import dao.MarcacionFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author fesquivelc
 */
@Named(value = "marcacionController")
@ConversationScoped
public class MarcacionController extends AbstractController<Marcacion>implements Serializable {

    /**
     * Creates a new instance of MarcacionController
     */
    @EJB
    private MarcacionFacadeLocal marcacionControlador;
    private MarcacionLazyModel lazyModel;
    public MarcacionController() {
        super(Marcacion.class); 
        
    }
    
    @PostConstruct
    public void initLazyModel(){
        lazyModel = new MarcacionLazyModel(marcacionControlador);
    }

    public LazyDataModel<Marcacion> getLazyModel() {
        return lazyModel;
    }
    
    

    @Override
    protected void edit(Marcacion objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void remove(Marcacion objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Marcacion find(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Marcacion> getItems() {
        Calendar inicio = Calendar.getInstance();
        Calendar fin = Calendar.getInstance();
        
        inicio.set(2014, 11, 1);
        fin.set(2015, 0, 1);
        return marcacionControlador.buscarXFecha(inicio.getTime(), fin.getTime());
        
    }

    @Override
    public List<Marcacion> search(String namedQuery) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Marcacion> search(String namedQuery, Map<String, Object> parametros) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Marcacion> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
