package com.project.jsica.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "tipoAsistenciaConverter")
public class TipoAsistenciaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        String valor = o.toString();
        String retorno = "";
        if(valor.equalsIgnoreCase("AT")){
            retorno = "ASISTENCIA";
        }else if(valor.equalsIgnoreCase("TT")){
            retorno = "TARDANZA";
        }else if(valor.equalsIgnoreCase("FT")){
            retorno = "FALTA";
        }else if(valor.equalsIgnoreCase("AC")){
            retorno = "ASISTENCIA A CT";
        }else if(valor.equalsIgnoreCase("TC")){
            retorno = "TARDANZA A CT";
        }else if(valor.equalsIgnoreCase("FC")){
            retorno = "FALTA a CT";
        }
        return retorno;
    }

}
