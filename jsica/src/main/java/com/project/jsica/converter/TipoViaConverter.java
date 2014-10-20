/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author RyuujiMD
 */
@FacesConverter(value = "tipoViaConverter")
public class TipoViaConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return string;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        String tipoVia = o.toString();
        if(tipoVia.equalsIgnoreCase("AV")){
            return "AVENIDA";
        }else if(tipoVia.equalsIgnoreCase("AL")){
            return "ALAMEDA";
        }else if(tipoVia.equalsIgnoreCase("BL")){
            return "BLOCK";
        }else if(tipoVia.equalsIgnoreCase("CAL")){
            return "CALLE";
        }else if(tipoVia.equalsIgnoreCase("CAR")){
            return "CARRETERA";
        }else if(tipoVia.equalsIgnoreCase("JR")){
            return "JIRÓN";
        }else if(tipoVia.equalsIgnoreCase("MLC")){
            return "MALECÓN";
        }else if(tipoVia.equalsIgnoreCase("OVL")){
            return "ÓVALO";
        }else if(tipoVia.equalsIgnoreCase("PJ")){
            return "PASAJE";
        }else if(tipoVia.equalsIgnoreCase("PQ")){
            return "PARQUE";
        }else{
            return "PLAZA";
        }
    }
    
}
