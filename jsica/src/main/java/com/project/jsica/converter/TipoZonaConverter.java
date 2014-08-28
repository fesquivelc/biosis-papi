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
@FacesConverter(value = "tipoZonaConverter")
public class TipoZonaConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return string;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        String tipoVia = o.toString();
        if(tipoVia.equalsIgnoreCase("U.R.")){
            return "URBANIZACIÓN";
        }else if(tipoVia.equalsIgnoreCase("A.H.")){
            return "ASENTAMIENTO HUMANO";
        }else if(tipoVia.equalsIgnoreCase("C.H.")){
            return "CONJUNTO HABITACIONAL";
        }else if(tipoVia.equalsIgnoreCase("CAS.")){
            return "CASERIO";
        }else if(tipoVia.equalsIgnoreCase("COO.")){
            return "COOPERATIVA";
        }else if(tipoVia.equalsIgnoreCase("FND.")){
            return "FUNDO";
        }else if(tipoVia.equalsIgnoreCase("GRU.")){
            return "GRUPO";
        }else if(tipoVia.equalsIgnoreCase("P.J.")){
            return "PRUEBLO JOVEN";
        }else if(tipoVia.equalsIgnoreCase("RES.")){
            return "RESIDENCIAL";
        }else if(tipoVia.equalsIgnoreCase("U.V.")){
            return "UNIDAD VECINAL";
        }else{
            return "ZONA INDUSTRIAL";
        }
    }
    
}
