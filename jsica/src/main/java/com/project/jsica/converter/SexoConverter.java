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
@FacesConverter(value = "sexoConverter")
public class SexoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string.equalsIgnoreCase("MASCULINO")){
            return "M";
        }else{
            return "F";
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        String sexo = o.toString();
        if(sexo.equalsIgnoreCase("M")){
            return "MASCULINO";
        }else{
            return "FEMENINO";
        }
    }
    
}
