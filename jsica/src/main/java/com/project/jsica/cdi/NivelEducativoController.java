package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.NivelEducativoFacadeLocal;
import com.project.jsica.ejb.entidades.Bitacora;
import com.project.jsica.ejb.entidades.NivelEducativo;
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

@Named(value = "nivelEducativoController")
@ViewScoped
public class NivelEducativoController extends AbstractController<NivelEducativo> {

    @EJB
    private NivelEducativoFacadeLocal nivelEducativoFacade;

    @Inject
    private BitacoraController bitacoraC;

    @Inject
    private FichaGeneralEmpleadoController fichaGeneralEmpleadoListController;

    public NivelEducativoController() {
        // Inform the Abstract parent controller of the concrete NivelEducativo?cap_first Entity
        super(NivelEducativo.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of FichaGeneralEmpleado
     * entities that are retrieved from NivelEducativo?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for FichaGeneralEmpleado page
     */
    public String navigateFichaGeneralEmpleadoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("FichaGeneralEmpleado_items", this.getSelected().getFichaGeneralEmpleadoList());
        }
        return "/fichaGeneralEmpleado/index";
    }

    @Override
    protected void edit(NivelEducativo objeto) {
        this.nivelEducativoFacade.edit(objeto);

        if (this.esNuevo) {
            Bitacora bitacora = new Bitacora();
            //----Bitacora----
            //Fecha y hora//          
            Date fechas = new Date();//           
            //Ip Cliente
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();

            String nombre = this.selected.getNombre();

            bitacora.setUsuario("JC");
            bitacora.setIpCliente(ip_cliente);
            bitacora.setFecha(fechas);
            bitacora.setHora(fechas);
            bitacora.setTabla("NIVEL_EDUCATIVO");
            bitacora.setColumna("NOMBRE");
            bitacora.setAccion("CREAR");
            bitacora.setValorAct(nombre);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);
        } else {
            //Datos antes de modificar
            NivelEducativo antes = this.find(this.selected.getId());

            String nombre1 = antes.getNombre();

            //Datos despues de modificar
            String nombre2 = this.selected.getNombre();

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
            bitacora.setTabla("NIVEL_EDUCATIVO");
            bitacora.setColumna("NOMBRE");
            bitacora.setAccion("MODIFICAR");
            bitacora.setValorAct(nombre2);
            bitacora.setValorAnt(nombre1);

            if (!nombre1.equals(nombre2)) {
                bitacoraC.edit(bitacora);
            }
        }
    }

    @Override
    protected void remove(NivelEducativo objeto) {
        this.nivelEducativoFacade.remove(objeto);

        //Datos antes de modificar
        NivelEducativo antes = this.find(this.selected.getId());

        String nombre1 = antes.getNombre();

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
        bitacora.setTabla("NIVEL_EDUCATIVO");
        bitacora.setColumna("NOMBRE");
        bitacora.setAccion("ELIMINAR");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(nombre1);
        bitacoraC.edit(bitacora);        
    }

    @Override
    public NivelEducativo find(Object id) {
        return this.nivelEducativoFacade.find(id);
    }

    @Override
    public List<NivelEducativo> getItems() {
        return this.nivelEducativoFacade.findAll();
    }

    @Override
    public List<NivelEducativo> search(String namedQuery) {
        return this.nivelEducativoFacade.search(namedQuery);
    }

    @Override
    public List<NivelEducativo> search(String namedQuery, Map<String, Object> parametros) {
        return this.nivelEducativoFacade.search(namedQuery, parametros);
    }

    @Override
    public List<NivelEducativo> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.nivelEducativoFacade.search(namedQuery, parametros, inicio, tamanio);
    }

}
