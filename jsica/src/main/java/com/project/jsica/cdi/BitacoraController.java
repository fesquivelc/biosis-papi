package com.project.jsica.cdi;

import com.project.jsica.cdi.util.JsfUtil;
import com.project.jsica.ejb.dao.BitacoraFacadeLocal;
import com.project.jsica.ejb.entidades.Bitacora;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.apache.log4j.Logger;

//@Named(value = "bitacoraController")
//@ApplicationScoped
public class BitacoraController(){
    protected Bitacora selected;
    protected Collection<Bitacora> items;
    private boolean esNuevo;

    private enum PersistAction {

        CREATE,
        DELETE,
        UPDATE
    }

    public BitacoraController() {
    }

    public Bitacora getSelected() {
        return selected;
    }
    
    public void setSelected(Bitacora selected) {
        this.selected = selected;
    }
    
    public void setItems(Collection<Bitacora> items) {
        this.items = items;
    }

    /**
     * Retrieve the currently selected item.
     *
     * @return the currently selected Entity
     */
    /**
     * Pass in the currently selected item.
     *
     * @param selected the Entity that should be set as selected
     */
    /**
     * Sets any embeddable key fields if an Entity uses composite keys. If the
     * entity does not have composite keys, this method performs no actions and
     * exists purely to be overridden inside a concrete controller class.
     */
    protected void setEmbeddableKeys() {
        // Nothing to do if entity does not have any embeddable key.
    }

    ;

    /**
     * Sets the concrete embedded key of an Entity that uses composite keys.
     * This method will be overriden inside concrete controller classes and does
     * nothing if the specific entity has no composite keys.
     */
    protected void initializeEmbeddableKey() {
        // Nothing to do if entity does not have any embeddable key.
    }

    /**
     * Returns all items as a Collection object.
     *
     * @return a collection of Entity items returned by the data layer
     */
//    public Collection<T> getItems() {
//        if (items == null) {
//            items = this.ejbFacade.findAll();
//        }
//        return items;
//    }
    /**
     * Pass in collection of items
     *
     * @param items a collection of Entity items
     */
    /**
     * Apply changes to an existing item to the data layer.
     *
     * @param event an event from the widget that wants to save an Entity to the
     * data layer
     */
    public void save(ActionEvent event) {
        persist(PersistAction.UPDATE, "listo");
    }

    /**
     * Store a new item in the data layer.
     *
     * @param event an event from the widget that wants to save a new Entity to
     * the data layer
     */
    public void saveNew(ActionEvent event) {
        persist(PersistAction.CREATE, "listo");
        if (!isValidationFailed()) {
            items = null; // Invalidate list of items to trigger re-query.
        }
    }

    /**
     * Remove an existing item from the data layer.
     *
     * @param event an event from the widget that wants to delete an Entity from
     * the data layer
     */
    public void delete(ActionEvent event) {
        persist(PersistAction.DELETE, "Listo");
        if (!isValidationFailed()) {
            selected = null; // Remove selection
            items = null; // Invalidate list of items to trigger re-query.
        }
    }

    /**
     * Performs any data modification actions for an entity. The actions that
     * can be performed by this method are controlled by the
     * {@link PersistAction} enumeration and are either CREATE, EDIT or DELETE.
     *
     * @param persistAction a specific action that should be performed on the
     * current item
     * @param successMessage a message that should be displayed when persisting
     * the item succeeds
     */
    private void edit(Bitacora objeto){
         getEntityManager().merge(objeto);
    };
//
//    protected abstract void remove(Bitacora objeto);

//    public abstract Bitacora find(Object id);

//    public abstract List<Bitacora> getItems();

//    public abstract List<Bitacora> search(String namedQuery);

//    public abstract List<Bitacora> search(String namedQuery, Map<String, Object> parametros);
//
//    public abstract List<Bitacora> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            this.setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    if (this.esNuevo) {
                        Logger.getLogger(this.getClass()).info("LO NUEVO NUEVO");
                    } else {
                        Logger.getLogger(this.getClass()).info("ACTUALIZATE");
                    }

                    this.edit(selected);
                    this.esNuevo = false;

                } else {
                    this.remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                Throwable cause = JsfUtil.getRootCause(ex.getCause());
                if (cause != null) {
                    if (cause instanceof ConstraintViolationException) {
                        ConstraintViolationException excp = (ConstraintViolationException) cause;
                        for (ConstraintViolation s : excp.getConstraintViolations()) {
                            JsfUtil.addErrorMessage(s.getMessage());
                        }
                    } else {
                        String msg = cause.getLocalizedMessage();
                        if (msg.length() > 0) {
                            JsfUtil.addErrorMessage(msg);
                        } else {
                            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass()).error(ex.getStackTrace());
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/MyBundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    /**
     * Creates a new instance of an underlying entity and assigns it to Selected
     * property.
     *
     * @param event an event from the widget that wants to create a new,
     * unmanaged Entity for the data layer
     * @return a new, unmanaged Entity
     */
    public Bitacora prepareCreate(ActionEvent event) {
        Bitacora newItem = new Bitacora();
        try {
//            newItem = Bitacora.newInstance();
            this.selected = newItem;
            initializeEmbeddableKey();
            return newItem;
        }catch (Exception ex) {
            Logger.getLogger(this.getClass()).error(ex);
        }
        return null;
    }

    /**
     * Inform the user interface whether any validation error exist on a page.
     *
     * @return a logical value whether form validation has passed or failed
     */
    public boolean isValidationFailed() {
        return JsfUtil.isValidationFailed();
    }

    /**
     * Retrieve all messages as a String to be displayed on the page.
     *
     * @param clientComponent the component for which the message applies
     * @param defaultMessage a default message to be shown
     * @return a concatenation of all messages
     */
    public String getComponentMessages(String clientComponent, String defaultMessage) {
        return JsfUtil.getComponentMessages(clientComponent, defaultMessage);
    }

    /**
     * Retrieve a collection of Entity items for a specific Controller from
     * another JSF page via Request parameters.
     */
//    @PostConstruct
//    public void initParams() {
//        Object paramItems = FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(Bitacora.getSimpleName() + "_items");
//        if (paramItems != null) {
//            this.items = (Collection<Bitacora>) paramItems;
//        }
//    }
}
