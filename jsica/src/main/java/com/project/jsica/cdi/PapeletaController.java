package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.PapeletaFacadeLocal;
import com.project.jsica.ejb.entidades.Bitacora;
import com.project.jsica.ejb.entidades.Papeleta;
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

@Named(value = "papeletaController")
@ViewScoped
public class PapeletaController extends AbstractController<Papeleta> {

    @EJB
    private PapeletaFacadeLocal papeletaFacade;

    @Inject
    private BitacoraController bitacoraC;

    @Inject
    private EmpleadoController empleadoIdempleadoController;
    @Inject
    private EmpleadoController empleadoIdjefeInmediatoController;
    @Inject
    private EmpleadoController empleadoIdjefePersonalController;
    @Inject
    private EmpleadoPermisoController empleadoPermisoIdController;

    public PapeletaController() {
        // Inform the Abstract parent controller of the concrete Papeleta?cap_first Entity
        super(Papeleta.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        empleadoIdempleadoController.setSelected(null);
        empleadoIdjefeInmediatoController.setSelected(null);
        empleadoIdjefePersonalController.setSelected(null);
        empleadoPermisoIdController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Empleado controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEmpleadoIdempleado(ActionEvent event) {
        if (this.getSelected() != null && empleadoIdempleadoController.getSelected() == null) {
            empleadoIdempleadoController.setSelected(this.getSelected().getEmpleadoIdempleado());
        }
    }

    /**
     * Sets the "selected" attribute of the Empleado controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEmpleadoIdjefeInmediato(ActionEvent event) {
        if (this.getSelected() != null && empleadoIdjefeInmediatoController.getSelected() == null) {
            empleadoIdjefeInmediatoController.setSelected(this.getSelected().getEmpleadoIdjefeInmediato());
        }
    }

    /**
     * Sets the "selected" attribute of the Empleado controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEmpleadoIdjefePersonal(ActionEvent event) {
        if (this.getSelected() != null && empleadoIdjefePersonalController.getSelected() == null) {
            empleadoIdjefePersonalController.setSelected(this.getSelected().getEmpleadoIdjefePersonal());
        }
    }

    /**
     * Sets the "selected" attribute of the EmpleadoPermiso controller in order
     * to display its data in a dialog. This is reusing existing the existing
     * View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEmpleadoPermisoId(ActionEvent event) {
        if (this.getSelected() != null && empleadoPermisoIdController.getSelected() == null) {
            empleadoPermisoIdController.setSelected(this.getSelected().getEmpleadoPermisoId());
        }
    }

    @Override
    protected void edit(Papeleta objeto) {
        this.papeletaFacade.edit(objeto);

        if (this.esNuevo) {
            Bitacora bitacora = new Bitacora();
            //----Bitacora----
            //Fecha y hora//          
            Date fechas = new Date();//           
            //Ip Cliente
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();

            String codigo = this.selected.getCodigo();
            String empleadoIdEmpleado = this.selected.getEmpleadoIdempleado().toString();
            String empleadoIdJefeInmediato = this.selected.getEmpleadoIdjefeInmediato().toString();
            String empleadoIdJefePersonal = this.selected.getEmpleadoIdjefePersonal().toString();
            String empleadoPermisoId = this.selected.getEmpleadoPermisoId().toString();

            bitacora.setUsuario("JC");
            bitacora.setIpCliente(ip_cliente);
            bitacora.setFecha(fechas);
            bitacora.setHora(fechas);
            bitacora.setTabla("PAPELETA");
            bitacora.setColumna("CODIGO");
            bitacora.setAccion("CREAR");
            bitacora.setValorAct(codigo);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("EMPLEADO_IDEMPLEADO");
            bitacora.setValorAct(empleadoIdEmpleado);
            bitacoraC.edit(bitacora);

            bitacora.setColumna("EMPLEADO_IDJEFE_INMEDIATO");
            bitacora.setValorAct(empleadoIdJefeInmediato);
            bitacoraC.edit(bitacora);

            bitacora.setColumna("EMPLEADO_IDJEFE_PERSONAL");
            bitacora.setValorAct(empleadoIdJefePersonal);
            bitacoraC.edit(bitacora);

            bitacora.setColumna("EMPLEADO_PERMISO_ID");
            bitacora.setValorAct(empleadoPermisoId);
            bitacoraC.edit(bitacora);
        } else {
            //Datos antes de modificar
            Papeleta antes = this.find(this.selected.getId());

            String codigo1 = antes.getCodigo();
            String empleadoIdEmpleado1 = antes.getEmpleadoIdempleado().toString();
            String empleadoIdJefeInmediato1 = antes.getEmpleadoIdjefeInmediato().toString();
            String empleadoIdJefePersonal1 = antes.getEmpleadoIdjefePersonal().toString();
            String empleadoPermisoId1 = antes.getEmpleadoPermisoId().toString();

            //Datos despues de modificar
            String codigo2 = this.selected.getCodigo();
            String empleadoIdEmpleado2 = this.selected.getEmpleadoIdempleado().toString();
            String empleadoIdJefeInmediato2 = this.selected.getEmpleadoIdjefeInmediato().toString();
            String empleadoIdJefePersonal2 = this.selected.getEmpleadoIdjefePersonal().toString();
            String empleadoPermisoId2 = this.selected.getEmpleadoPermisoId().toString();

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
            bitacora.setTabla("PAPELETA");
            bitacora.setColumna("CODIGO");
            bitacora.setAccion("MODIFICAR");
            bitacora.setValorAct(codigo2);
            bitacora.setValorAnt(codigo1);

            if (!codigo1.equals(codigo2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("EMPLEADO_IDEMPLEADO");
            bitacora.setValorAct(empleadoIdEmpleado2);
            bitacora.setValorAnt(empleadoIdEmpleado1);

            if (!empleadoIdEmpleado1.equals(empleadoIdEmpleado2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("EMPLEADO_IDJEFE_INMEDIATO");
            bitacora.setValorAct(empleadoIdJefeInmediato2);
            bitacora.setValorAnt(empleadoIdJefeInmediato1);

            if (!empleadoIdJefeInmediato1.equals(empleadoIdJefeInmediato2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("EMPLEADO_IDJEFE_PERSONAL");
            bitacora.setValorAct(empleadoIdJefePersonal2);
            bitacora.setValorAnt(empleadoIdJefePersonal1);

            if (!empleadoIdJefePersonal1.equals(empleadoIdJefePersonal2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("EMPLEADO_PERMISO_ID");
            bitacora.setValorAct(empleadoPermisoId2);
            bitacora.setValorAnt(empleadoPermisoId1);

            if (!empleadoPermisoId1.equals(empleadoPermisoId2)) {
                bitacoraC.edit(bitacora);
            }
        }
    }

    @Override
    protected void remove(Papeleta objeto) {
        this.papeletaFacade.remove(objeto);

        Papeleta antes = this.find(this.selected.getId());

        String codigo1 = antes.getCodigo();
        String empleadoIdEmpleado1 = antes.getEmpleadoIdempleado().toString();
        String empleadoIdJefeInmediato1 = antes.getEmpleadoIdjefeInmediato().toString();
        String empleadoIdJefePersonal1 = antes.getEmpleadoIdjefePersonal().toString();
        String empleadoPermisoId1 = antes.getEmpleadoPermisoId().toString();

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
        bitacora.setTabla("PAPELETA");
        bitacora.setColumna("CODIGO");
        bitacora.setAccion("ELIMINAR");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(codigo1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("EMPLEADO_IDEMPLEADO");
        bitacora.setValorAnt(empleadoIdEmpleado1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("EMPLEADO_IDJEFE_INMEDIATO");
        bitacora.setValorAnt(empleadoIdJefeInmediato1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("EMPLEADO_IDJEFE_PERSONAL");
        bitacora.setValorAnt(empleadoIdJefePersonal1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("EMPLEADO_PERMISO_ID");
        bitacora.setValorAnt(empleadoPermisoId1);
        bitacoraC.edit(bitacora);
    }

    @Override
    public Papeleta find(Object id) {
        return this.papeletaFacade.find(id);
    }

    @Override
    public List<Papeleta> getItems() {
        return this.papeletaFacade.findAll();
    }

    @Override
    public List<Papeleta> search(String namedQuery) {
        return this.papeletaFacade.search(namedQuery);
    }

    @Override
    public List<Papeleta> search(String namedQuery, Map<String, Object> parametros) {
        return this.papeletaFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Papeleta> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.papeletaFacade.search(namedQuery, parametros, inicio, tamanio);
    }
}
