package com.project.jsica.converter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "eventoAsistenciaConverter")
public class EventoAsistenciaConverter implements Converter {
    private static final Logger LOG = Logger.getLogger(EventoAsistenciaConverter.class.getName());
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        LOG.log(Level.INFO, "OBJETO A TRANSFORMAR {0}", o);
        if(o == null){
            return "";
        }
        boolean valor = (Boolean)o;
        
        if(valor){
            return "ENTRADA";
        }else{
            return "SALIDA";
        }
    }

}
