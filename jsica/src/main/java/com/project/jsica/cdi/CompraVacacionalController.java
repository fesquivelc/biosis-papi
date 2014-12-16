package com.project.jsica.cdi;

import com.project.jsica.ejb.entidades.CompraVacacional;
import com.project.jsica.cdi3.util.JsfUtil;
import com.project.jsica.cdi3.util.JsfUtil.PersistAction;
import com.project.jsica.ejb.dao.CompraVacacionalFacadeLocal;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("compraVacacionalController")
@SessionScoped
public class CompraVacacionalController implements Serializable {

    @EJB
    private CompraVacacionalFacadeLocal ejbFacade;
    private List<CompraVacacional> items = null;
    private CompraVacacional selected;
    private static final Logger LOG = Logger.getLogger(CompraVacacionalController.class.getName());
    

    public CompraVacacionalController() {
    }

    public CompraVacacional getSelected() {
        return selected;
    }

    public void setSelected(CompraVacacional selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CompraVacacionalFacadeLocal getFacade() {
        return ejbFacade;
    }

    public CompraVacacional prepareCreate() {
        LOG.info("PREPARE CREATE");
        selected = new CompraVacacional();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, "Ha sido registrado correctamente");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "Ha sido actualizado correctamente");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Ha sido eliminado correctamente");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CompraVacacional> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public CompraVacacional getCompraVacacional(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<CompraVacacional> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CompraVacacional> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = CompraVacacional.class)
    public static class CompraVacacionalControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CompraVacacionalController controller = (CompraVacacionalController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "compraVacacionalController");
            return controller.getCompraVacacional(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof CompraVacacional) {
                CompraVacacional o = (CompraVacacional) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CompraVacacional.class.getName()});
                return null;
            }
        }

    }

}
