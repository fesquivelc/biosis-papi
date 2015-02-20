/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.jsica.converter;

import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author fesquivelc
 */
@FacesConverter(value = "milisAMinutos")
public class MilisAMinutos implements Converter{
    private static final Logger LOG = Logger.getLogger(MilisAMinutos.class.getName());

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        try {
            long milis = Long.parseLong(o.toString());
            double minutos = milis / (60*1000);
            return minutos + "";
        } catch (Exception e) {
            LOG.warning(e.toString());
            return "";
        }
        
    }
    
}
