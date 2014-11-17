/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.jsica.converter;

import com.project.jsica.ejb.entidades.Empleado;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author fesquivelc
 */
@FacesConverter(value = "codigoConverter")
public class CodigoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o != null){
            return getCodigo((Empleado)o);
        }else{
            return "";
        }
    }

    private String getCodigo(Empleado empleado) {
        return empleado.getFichaLaboral().getCodigoTrabajador();
    }
    
}
