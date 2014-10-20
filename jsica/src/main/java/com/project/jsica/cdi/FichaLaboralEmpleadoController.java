package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.FichaLaboralEmpleadoFacadeLocal;
import com.project.jsica.ejb.entidades.Bitacora;
import com.project.jsica.ejb.entidades.FichaLaboralEmpleado;
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

@Named(value = "fichaLaboralEmpleadoController")
@ViewScoped
public class FichaLaboralEmpleadoController extends AbstractController<FichaLaboralEmpleado> {

    @EJB
    private FichaLaboralEmpleadoFacadeLocal fichaLaboralEmpleadoFacade;

    @Inject
    private BitacoraController bitacoraC;

    @Inject
    private EmpleadoController empleadoIdController;
    @Inject
    private TipoEmpleadoController tipoEmpleadoIdController;

    public FichaLaboralEmpleadoController() {
        // Inform the Abstract parent controller of the concrete FichaLaboralEmpleado?cap_first Entity
        super(FichaLaboralEmpleado.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        empleadoIdController.setSelected(null);
        tipoEmpleadoIdController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Empleado controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEmpleadoId(ActionEvent event) {
        if (this.getSelected() != null && empleadoIdController.getSelected() == null) {
            empleadoIdController.setSelected(this.getSelected().getEmpleadoId());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoEmpleado controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTipoEmpleadoId(ActionEvent event) {
        if (this.getSelected() != null && tipoEmpleadoIdController.getSelected() == null) {
            tipoEmpleadoIdController.setSelected(this.getSelected().getTipoEmpleadoId());
        }
    }

    @Override
    protected void edit(FichaLaboralEmpleado objeto) {
        this.fichaLaboralEmpleadoFacade.edit(objeto);

        if (this.esNuevo) {
            Bitacora bitacora = new Bitacora();
            //----Bitacora----
            //Fecha y hora//          
            Date fechas = new Date();//           
            //Ip Cliente
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();

            String codigoTrabajador = this.selected.getCodigoTrabajador();
            String empleadoId = this.selected.getEmpleadoId().toString();
            String tipoEmpleadoId = this.selected.getTipoEmpleadoId().toString();

            bitacora.setUsuario("JC");
            bitacora.setIpCliente(ip_cliente);
            bitacora.setFecha(fechas);
            bitacora.setHora(fechas);
            bitacora.setTabla("FICHA_LABORAL_EMPLEADO");
            bitacora.setColumna("CODIGO_TRABAJADOR");
            bitacora.setAccion("CREAR");
            bitacora.setValorAct(codigoTrabajador);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("EMPLEADO_ID");
            bitacora.setValorAct(empleadoId);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("TIPO_EMPLEADO_ID");
            bitacora.setValorAct(tipoEmpleadoId);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

        } else {
            //Datos antes de modificar
            FichaLaboralEmpleado antes = this.find(this.selected.getId());

            String codigoTrabajador1 = antes.getCodigoTrabajador();
            String empleadoId1 = antes.getEmpleadoId().toString();
            String tipoEmpleadoId1 = antes.getTipoEmpleadoId().toString();

            //Datos despues de modificar
            String codigoTrabajador2 = this.selected.getCodigoTrabajador();
            String empleadoId2 = this.selected.getEmpleadoId().toString();
            String tipoEmpleadoId2 = this.selected.getTipoEmpleadoId().toString();

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
            bitacora.setTabla("FICHA_LABORAL_EMPLEADO");
            bitacora.setColumna("CODIGO_TRABAJADOR");
            bitacora.setAccion("MODIFICAR");
            bitacora.setValorAct(codigoTrabajador2);
            bitacora.setValorAnt(codigoTrabajador1);

            if (!codigoTrabajador1.equals(codigoTrabajador2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("EMPLEADO_ID");
            bitacora.setValorAct(empleadoId2);
            bitacora.setValorAnt(empleadoId1);

            if (!empleadoId1.equals(empleadoId2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("TIPO_EMPLEADO_ID");
            bitacora.setValorAct(tipoEmpleadoId2);
            bitacora.setValorAnt(tipoEmpleadoId1);

            if (!tipoEmpleadoId1.equals(tipoEmpleadoId2)) {
                bitacoraC.edit(bitacora);
            }
        }
    }

    @Override
    protected void remove(FichaLaboralEmpleado objeto) {
        this.fichaLaboralEmpleadoFacade.remove(objeto);

        FichaLaboralEmpleado antes = this.find(this.selected.getId());

        String codigoTrabajador1 = antes.getCodigoTrabajador();
        String empleadoId1 = antes.getEmpleadoId().toString();
        String tipoEmpleadoId1 = antes.getTipoEmpleadoId().toString();

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
        bitacora.setTabla("FICHA_LABORAL_EMPLEADO");
        bitacora.setColumna("CODIGO_TRABAJADOR");
        bitacora.setAccion("ELIMINAR");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(codigoTrabajador1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("EMPLEADO_ID");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(empleadoId1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("TIPO_EMPLEADO_ID");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(tipoEmpleadoId1);
        bitacoraC.edit(bitacora);        
    }

    @Override
    public FichaLaboralEmpleado find(Object id) {
        return this.fichaLaboralEmpleadoFacade.find(id);
    }

    @Override
    public List<FichaLaboralEmpleado> getItems() {
        return this.fichaLaboralEmpleadoFacade.findAll();
    }

    @Override
    public List<FichaLaboralEmpleado> search(String namedQuery) {
        return this.fichaLaboralEmpleadoFacade.search(namedQuery);
    }

    @Override
    public List<FichaLaboralEmpleado> search(String namedQuery, Map<String, Object> parametros) {
        return this.fichaLaboralEmpleadoFacade.search(namedQuery, parametros);
    }

    @Override
    public List<FichaLaboralEmpleado> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.fichaLaboralEmpleadoFacade.search(namedQuery, parametros, inicio, tamanio);
    }
}
