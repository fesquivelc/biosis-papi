/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.lazymodel;

import dao.RegistroAsistenciaFacadeLocal;
import com.project.jsica.ejb.entidades.RegistroAsistencia;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author RyuujiMD
 */
public class RegistroAsistenciaLazyModel extends LazyDataModel<RegistroAsistencia>{

    private final RegistroAsistenciaFacadeLocal registroDAO;
    private static final Logger LOG = Logger.getLogger(RegistroAsistenciaLazyModel.class.getName());


    public RegistroAsistenciaLazyModel(RegistroAsistenciaFacadeLocal registroDAO) {
        this.registroDAO = registroDAO;
    }        
    
    @Override
    public Object getRowKey(RegistroAsistencia object) {
        return object.getId();
    }

    @Override
    public List<RegistroAsistencia> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        
        String sql = "SELECT ra FROM RegistroAsistencia ra";
        LOG.log(Level.INFO, "VIENE A REGISTRODAO {0} {1}", new Object[]{first, pageSize});
        List<RegistroAsistencia> lista =  registroDAO.search(sql, null, first, pageSize);        
        LOG.log(Level.INFO, "TAMANIO DE LA LISTA: {0}", lista.size());
        return lista;
    }

    @Override
    public RegistroAsistencia getRowData(String rowKey) {
        LOG.info("VIEWNW A GETROWDATA");
        return registroDAO.find(Long.parseLong(rowKey));
    }

    @Override
    public int getRowCount() {
        return this.registroDAO.count();
    }
    
    
    
}
