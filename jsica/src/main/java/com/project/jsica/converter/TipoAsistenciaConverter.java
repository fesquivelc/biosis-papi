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
        if(valor.equalsIgnoreCase("R")){
            retorno = "REGULAR";
        }else if(valor.equalsIgnoreCase("T")){
            retorno = "TARDANZA";
        }else if(valor.equalsIgnoreCase("A")){
            retorno = "ABSENTISMO";
        }else if(valor.equalsIgnoreCase("V")){
            retorno = "VACACIONEs";
        }else if(valor.equalsIgnoreCase("O")){
            retorno = "ONOMÁSTICO";
        }else if(valor.equalsIgnoreCase("L")){
            retorno = "LICENCIA";
        }else if(valor.equalsIgnoreCase("F")){
            retorno = "FERIADO";
        }
        return retorno;
    }

}
