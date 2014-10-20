package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.GrupoHorarioFacadeLocal;
import com.project.jsica.ejb.entidades.Bitacora;
import com.project.jsica.ejb.entidades.GrupoHorario;
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

@Named(value = "grupoHorarioController")
@ViewScoped
public class GrupoHorarioController extends AbstractController<GrupoHorario> {

    @EJB
    private GrupoHorarioFacadeLocal grupoHorarioFacade;
    @Inject
    private BitacoraController bitacoraC;
    @Inject
    private EmpleadoController empleadoListController;
    @Inject
    private EmpleadoHorarioController empleadoHorarioListController;

    public GrupoHorarioController() {
        // Inform the Abstract parent controller of the concrete GrupoHorario?cap_first Entity
        super(GrupoHorario.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Empleado entities that
     * are retrieved from GrupoHorario?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Empleado page
     */
    public String navigateEmpleadoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Empleado_items", this.getSelected().getEmpleadoList());
        }
        return "/empleado/index";
    }

    /**
     * Sets the "items" attribute with a collection of EmpleadoHorario entities
     * that are retrieved from GrupoHorario?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for EmpleadoHorario page
     */
    public String navigateEmpleadoHorarioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EmpleadoHorario_items", this.getSelected().getEmpleadoHorarioList());
        }
        return "/empleadoHorario/index";
    }

    @Override
    protected void edit(GrupoHorario objeto) {
        this.grupoHorarioFacade.edit(objeto);

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
            bitacora.setTabla("GRUPO_HORARIO");
            bitacora.setColumna("NOMBRE");
            bitacora.setAccion("CREAR");
            bitacora.setValorAct(nombre);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);
        } else {
            //Datos antes de modificar
            GrupoHorario antes = this.find(this.selected.getId());

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
            bitacora.setTabla("GRUPO_HORARIO");
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
    protected void remove(GrupoHorario objeto) {
        this.grupoHorarioFacade.remove(objeto);

        //Datos antes de modificar
        GrupoHorario antes = this.find(this.selected.getId());

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
        bitacora.setTabla("GRUPO_HORARIO");
        bitacora.setColumna("NOMBRE");
        bitacora.setAccion("ELIMINAR");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(nombre1);
        bitacoraC.edit(bitacora); 
    }

    @Override
    public GrupoHorario find(Object id) {
        return this.grupoHorarioFacade.find(id);
    }

    @Override
    public List<GrupoHorario> getItems() {
        return this.grupoHorarioFacade.findAll();
    }

    @Override
    public List<GrupoHorario> search(String namedQuery) {
        return this.grupoHorarioFacade.search(namedQuery);
    }

    @Override
    public List<GrupoHorario> search(String namedQuery, Map<String, Object> parametros) {
        return this.grupoHorarioFacade.search(namedQuery, parametros);
    }

    @Override
    public List<GrupoHorario> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.grupoHorarioFacade.search(namedQuery, parametros, inicio, tamanio);
    }

}
