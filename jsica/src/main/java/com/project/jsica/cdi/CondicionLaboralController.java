package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.CondicionLaboralFacadeLocal;
import com.project.jsica.ejb.entidades.Bitacora;
import com.project.jsica.ejb.entidades.CondicionLaboral;
import java.text.SimpleDateFormat;
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

@Named(value = "condicionLaboralController")
@ViewScoped
public class CondicionLaboralController extends AbstractController<CondicionLaboral> {

    @EJB
    private CondicionLaboralFacadeLocal condicionLaboralFacade;

    @Inject
    private BitacoraController bitacoraC;

    @Inject
    private ContratoController contratoListController;

    public CondicionLaboralController() {
        // Inform the Abstract parent controller of the concrete CondicionLaboral?cap_first Entity
        super(CondicionLaboral.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Contrato entities that
     * are retrieved from CondicionLaboral?cap_first and returns the navigation
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
    protected void edit(CondicionLaboral objeto) {
        this.condicionLaboralFacade.edit(objeto);
        if (this.esNuevo) {
            Bitacora bitacora = new Bitacora();
            //----Bitacora----
            //Fecha y hora//          
            Date fechas = new Date();//           
            //Ip Cliente
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
            
            String codigo = String.valueOf(this.selected.getCodigo());
            String nombre = this.selected.getNombre();

            bitacora.setUsuario("JC");
            bitacora.setIpCliente(ip_cliente);
            bitacora.setFecha(fechas);
            bitacora.setHora(fechas);
            bitacora.setTabla("CONDICION_LABORAL");
            bitacora.setColumna("CODIGO");
            bitacora.setAccion("CREAR");
            bitacora.setValorAct(codigo);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("NOMBRE");
            bitacora.setValorAct(nombre);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

        } else {
            //Datos antes de modificar
            CondicionLaboral antes = this.find(this.selected.getId());
            String codigo1 = String.valueOf(antes.getCodigo());
            String nombre1 = antes.getNombre();
            
//            System.out.println("CODIGO: "+codigo1+" NOMBRE: "+ nombre1);
            //Datos despues de modificar
            String codigo2 = String.valueOf(this.selected.getCodigo());
            String nombre2 = this.selected.getNombre();
//            System.out.println("CODIGO2: "+codigo2+" NOMBRE2: "+ nombre2);
            
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
            bitacora.setTabla("CONDICION_LABORAL");
            bitacora.setColumna("CODIGO");
            bitacora.setAccion("MODIFICAR");
            bitacora.setValorAnt(codigo1);
            bitacora.setValorAct(codigo2);

            if (!codigo1.equals(codigo2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("NOMBRE");
            bitacora.setValorAnt(nombre1);
            bitacora.setValorAct(nombre2);

            if (!nombre1.equals(nombre2)) {
                bitacoraC.edit(bitacora);
            }
        }
    }

    @Override
    protected void remove(CondicionLaboral objeto) {
        Bitacora bitacora = new Bitacora();
        //----Bitacora----
        //Fecha y hora//          
        Date fechas = new Date();     
        //Ip Cliente
        String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
        
        String codigo1 = String.valueOf(this.selected.getCodigo());
        String nombre1 = this.selected.getNombre();

        //DATOS
        bitacora.setUsuario("JC");
        bitacora.setIpCliente(ip_cliente);
        bitacora.setFecha(fechas);
        bitacora.setHora(fechas);
        bitacora.setTabla("CONDICION_LABORAL");
        bitacora.setColumna("CODIGO");
        bitacora.setAccion("ELIMINAR");
        bitacora.setValorAnt(codigo1);
        bitacora.setValorAct(" ");
        bitacoraC.edit(bitacora);

        bitacora.setColumna("NOMBRE");
        bitacora.setValorAnt(nombre1);
        bitacora.setValorAct(" ");
        bitacoraC.edit(bitacora);
        
        this.condicionLaboralFacade.remove(objeto);
    }

    @Override
    public CondicionLaboral find(Object id) {
        return this.condicionLaboralFacade.find(id);
    }

    @Override
    public List<CondicionLaboral> getItems() {
        return this.condicionLaboralFacade.findAll();
    }

    @Override
    public List<CondicionLaboral> search(String namedQuery) {
        return this.condicionLaboralFacade.search(namedQuery);
    }

    @Override
    public List<CondicionLaboral> search(String namedQuery, Map<String, Object> parametros) {
        return this.condicionLaboralFacade.search(namedQuery, parametros);
    }

    @Override
    public List<CondicionLaboral> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.condicionLaboralFacade.search(namedQuery, parametros, inicio, tamanio);
    }

}
