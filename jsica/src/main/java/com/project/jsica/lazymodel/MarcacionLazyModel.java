/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.jsica.lazymodel;

import com.project.jsica.ejb.entidades.Marcacion;
import dao.MarcacionFacadeLocal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author fesquivelc
 */
public class MarcacionLazyModel extends LazyDataModel<Marcacion>{
    private final MarcacionFacadeLocal marcacionControlador;

    public MarcacionLazyModel(MarcacionFacadeLocal MarcacionControlador) {
        this.marcacionControlador = MarcacionControlador;
    }    

    @Override
    public List<Marcacion> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {                
        Calendar fechaInicio = Calendar.getInstance();
        Calendar fechaFin = Calendar.getInstance();
        
        fechaInicio.set(2014, 0, 1);
        return marcacionControlador.buscarXFecha(fechaInicio.getTime(), fechaFin.getTime(), first, pageSize);
    }        
    
    @Override
    public Object getRowKey(Marcacion object) {
        return object.getId();
    }

    @Override
    public Marcacion getRowData(String rowKey) {
        return marcacionControlador.find(Long.valueOf(rowKey)); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRowCount() {
        return marcacionControlador.count();
    }
    
    
    
}
