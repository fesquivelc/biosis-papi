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
 * @author Gabriel
 */
@FacesConverter(value = "estadoCivilConverter")
public class EstadoCivilConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return string; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
       String estado = o.toString();
        if(estado.equalsIgnoreCase("SO")){
            return "SOLTERO";
        }else if(estado.equalsIgnoreCase("CA")){
            return "CASADO";
        }else{
            return "VIUDO";
        } 
    }
    
    
}
