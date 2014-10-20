package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.FaltaFacadeLocal;
import com.project.jsica.ejb.entidades.Bitacora;
import com.project.jsica.ejb.entidades.Falta;
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

@Named(value = "faltaController")
@ViewScoped
public class FaltaController extends AbstractController<Falta> {

    @EJB
    private FaltaFacadeLocal faltaFacade;

    @Inject
    private BitacoraController bitacoraC;

    @Inject
    private EmpleadoController empleadoIdController;

    public FaltaController() {
        // Inform the Abstract parent controller of the concrete Falta?cap_first Entity
        super(Falta.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        empleadoIdController.setSelected(null);
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

    @Override
    protected void edit(Falta objeto) {
        this.faltaFacade.edit(objeto);

        if (this.esNuevo) {
            Bitacora bitacora = new Bitacora();
            //----Bitacora----
            //Fecha y hora//          
            Date fechas = new Date();//           
            //Ip Cliente
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();

            String idEmpleado = this.selected.getEmpleadoId().toString();
            String fecha = this.selected.getFecha().toString();

            bitacora.setUsuario("JC");
            bitacora.setIpCliente(ip_cliente);
            bitacora.setFecha(fechas);
            bitacora.setHora(fechas);
            bitacora.setTabla("FALTA");
            bitacora.setColumna("ID_EMPLEADO");
            bitacora.setAccion("CREAR");
            bitacora.setValorAct(idEmpleado);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("FECHA");
            bitacora.setValorAct(fecha);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);
        } else {
            //Datos antes de modificar
            Falta antes = this.find(this.selected.getId());

            String idEmpleado1 = antes.getEmpleadoId().toString();
            String fecha1 = antes.getFecha().toString();

            //Datos despues de modificar
            String idEmpleado2 = this.selected.getEmpleadoId().toString();
            String fecha2 = this.selected.getFecha().toString();

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
            bitacora.setTabla("FALTA");
            bitacora.setColumna("ID_EMPLEADO");
            bitacora.setAccion("MODIFICAR");
            bitacora.setValorAct(idEmpleado2);
            bitacora.setValorAnt(idEmpleado1);

            if (!idEmpleado1.equals(idEmpleado2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("FECHA");
            bitacora.setValorAct(fecha2);
            bitacora.setValorAnt(fecha1);

            if (!fecha1.equals(fecha2)) {
                bitacoraC.edit(bitacora);
            }
        }
    }

    @Override
    protected void remove(Falta objeto) {
        this.faltaFacade.remove(objeto);

        Falta antes = this.find(this.selected.getId());

        String idEmpleado1 = antes.getEmpleadoId().toString();
        String fecha1 = antes.getFecha().toString();

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
        bitacora.setTabla("FALTA");
        bitacora.setColumna("ID_EMPLEADO");
        bitacora.setAccion("ELIMINAR");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(idEmpleado1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("FECHA");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(fecha1);
        bitacoraC.edit(bitacora);        
    }

    @Override
    public Falta find(Object id) {
        return this.faltaFacade.find(id);
    }

    @Override
    public List<Falta> getItems() {
        return this.faltaFacade.findAll();
    }

    @Override
    public List<Falta> search(String namedQuery) {
        return this.faltaFacade.search(namedQuery);
    }

    @Override
    public List<Falta> search(String namedQuery, Map<String, Object> parametros) {
        return this.faltaFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Falta> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.faltaFacade.search(namedQuery, parametros, inicio, tamanio);
    }
}
