package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.OcupacionFacadeLocal;
import com.project.jsica.ejb.entidades.Bitacora;
import com.project.jsica.ejb.entidades.Ocupacion;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

@Named(value = "ocupacionController")
@ViewScoped
public class OcupacionController extends AbstractController<Ocupacion> {

    private static final Logger log = Logger.getLogger(OcupacionController.class.getClass());

    @EJB
    private OcupacionFacadeLocal ocupacionFacade;

    @Inject
    private BitacoraController bitacoraC;

    @Inject
    private ContratoController contratoListController;

    public OcupacionController() {
        // Inform the Abstract parent controller of the concrete Ocupacion?cap_first Entity
        super(Ocupacion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Contrato entities that
     * are retrieved from Ocupacion?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Contrato page
     */
    public String navigateContratoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Contrato_items", this.getSelected().getContratoList());
        }
        return "/contrato/index";
    }

    @Override
    protected void edit(Ocupacion objeto) {
        log.info("CREAR OCUPACION");
        this.ocupacionFacade.edit(objeto);

        if (this.esNuevo) {
            Bitacora bitacora = new Bitacora();
            //----Bitacora----
            //Fecha y hora//          
            Date fechas = new Date();//           
            //Ip Cliente
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();

            String nombre = this.selected.getNombre();
            String descripcion = this.selected.getDescripcion();

            bitacora.setUsuario("JC");
            bitacora.setIpCliente(ip_cliente);
            bitacora.setFecha(fechas);
            bitacora.setHora(fechas);
            bitacora.setTabla("OCUPACION");
            bitacora.setColumna("NOMBRE");
            bitacora.setAccion("CREAR");
            bitacora.setValorAct(nombre);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("DESCRIPCION");
            bitacora.setValorAct(descripcion);
            bitacoraC.edit(bitacora);
        } else {
            //Datos antes de modificar
            Ocupacion antes = this.find(this.selected.getId());

            String nombre1 = antes.getNombre();
            String descripcion1 = antes.getDescripcion();

            //Datos despues de modificar
            String nombre2 = this.selected.getNombre();
            String descripcion2 = this.selected.getDescripcion();

            //----Bitacora----
            Bitacora bitacora = new Bitacora();
            //Fecha y hora//          
            Date fechas = new Date();
//           
            //Ip Cliente
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();

            //Datos
            bitacora.setUsuario("JC");
            bitacora.setIpCliente(ip_cliente);
            bitacora.setFecha(fechas);
            bitacora.setHora(fechas);
            bitacora.setTabla("OCUPACION");
            bitacora.setColumna("NOMBRE");
            bitacora.setAccion("MODIFICAR");
            bitacora.setValorAct(nombre2);
            bitacora.setValorAnt(nombre1);

            if (!nombre1.equals(nombre2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("DESCRIPCION");
            bitacora.setValorAct(descripcion2);
            bitacora.setValorAnt(descripcion1);

            if (!descripcion1.equals(descripcion2)) {
                bitacoraC.edit(bitacora);
            }
        }
    }

    @Override
    protected void remove(Ocupacion objeto) {
        this.ocupacionFacade.remove(objeto);

        Ocupacion antes = this.find(this.selected.getId());

        String nombre1 = antes.getNombre();
        String descripcion1 = antes.getDescripcion();

        //----Bitacora----
        Bitacora bitacora = new Bitacora();
        //Fecha y hora//          
        Date fechas = new Date();
//           
        //Ip Cliente
        String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();

        //Datos
        bitacora.setUsuario("JC");
        bitacora.setIpCliente(ip_cliente);
        bitacora.setFecha(fechas);
        bitacora.setHora(fechas);
        bitacora.setTabla("OCUPACION");
        bitacora.setColumna("NOMBRE");
        bitacora.setAccion("ELIMINAR");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(nombre1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("DESCRIPCION");
        bitacora.setValorAnt(descripcion1);
        bitacoraC.edit(bitacora);
    }

    @Override
    public Ocupacion find(Object id) {
        return this.ocupacionFacade.find(id);
    }

    @Override
    public List<Ocupacion> getItems() {
        return this.ocupacionFacade.findAll();
    }

    @Override
    public List<Ocupacion> search(String namedQuery) {
        return this.ocupacionFacade.search(namedQuery);
    }

    @Override
    public List<Ocupacion> search(String namedQuery, Map<String, Object> parametros) {
        return this.ocupacionFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Ocupacion> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.ocupacionFacade.search(namedQuery, parametros, inicio, tamanio);
    }

}
