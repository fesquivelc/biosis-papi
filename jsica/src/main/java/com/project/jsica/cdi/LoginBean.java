/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.jsica.cdi;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author RyuujiMD
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    private static final Logger LOG = Logger.getLogger(LoginBean.class.getName());
    
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            LOG.info("ENTRA AL LOGOUT");
            request.logout();            
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed."));
        }
        return "/faces/index.xhtml";
        
    }

}
