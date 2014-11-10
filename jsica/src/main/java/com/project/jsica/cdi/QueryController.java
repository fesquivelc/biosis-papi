/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.UtilitarioAsistenciaLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author fesquivelc
 */
@Named(value = "queryController")
@RequestScoped
public class QueryController {
    @EJB
    private UtilitarioAsistenciaLocal utilitario;
    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    /**
     * Creates a new instance of QueryController
     */
    public QueryController() {
    }
    
    public void ejecutarQuery(){
        utilitario.query(query);
    }
    
}
