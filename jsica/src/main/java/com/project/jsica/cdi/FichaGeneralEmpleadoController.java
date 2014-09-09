package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.FichaGeneralEmpleadoFacadeLocal;
import com.project.jsica.ejb.entidades.Bitacora;
import com.project.jsica.ejb.entidades.FichaGeneralEmpleado;
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

@Named(value = "fichaGeneralEmpleadoController")
@ViewScoped
public class FichaGeneralEmpleadoController extends AbstractController<FichaGeneralEmpleado> {

    @EJB
    private FichaGeneralEmpleadoFacadeLocal fichaGeneralEmpleadoFacade;

    @Inject
    private BitacoraController bitacoraC;

    @Inject
    private EmpleadoController empleadoIdController;
    @Inject
    private UbigeoController ubigeoCodigoController;
    @Inject
    private NivelEducativoController nivelEducativoIdController;

    public FichaGeneralEmpleadoController() {
        // Inform the Abstract parent controller of the concrete FichaGeneralEmpleado?cap_first Entity
        super(FichaGeneralEmpleado.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        empleadoIdController.setSelected(null);
        ubigeoCodigoController.setSelected(null);
        nivelEducativoIdController.setSelected(null);
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
     * Sets the "selected" attribute of the Ubigeo controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareUbigeoCodigo(ActionEvent event) {
        if (this.getSelected() != null && ubigeoCodigoController.getSelected() == null) {
            ubigeoCodigoController.setSelected(this.getSelected().getUbigeoCodigo());
        }
    }

    /**
     * Sets the "selected" attribute of the NivelEducativo controller in order
     * to display its data in a dialog. This is reusing existing the existing
     * View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareNivelEducativoId(ActionEvent event) {
        if (this.getSelected() != null && nivelEducativoIdController.getSelected() == null) {
            nivelEducativoIdController.setSelected(this.getSelected().getNivelEducativoId());
        }
    }

    @Override
    protected void edit(FichaGeneralEmpleado objeto) {
        this.fichaGeneralEmpleadoFacade.edit(objeto);

        if (this.esNuevo) {
            Bitacora bitacora = new Bitacora();
            //----Bitacora----
            //Fecha y hora//          
            Date fechas = new Date();//           
            //Ip Cliente
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();

            String telFijo = this.selected.getTelefonoFijo();
            String telCelular = this.selected.getTelefonoCelular();
            String direccion = this.selected.getDireccion();
            String direccionTipoZona = this.selected.getDireccionTipoZona();
            String direccionTipoVia = this.selected.getDireccionTipoVia();
            String estadoCivil = this.selected.getEstadoCivil();
            String email = this.selected.getEmail();
            String empleadoId = this.selected.getEmpleadoId().toString();
            String ubigeoCodigo = this.selected.getUbigeoCodigo().toString();
            String nivelEducativo = this.selected.getNivelEducativoId().toString();

            bitacora.setUsuario("JC");
            bitacora.setIpCliente(ip_cliente);
            bitacora.setFecha(fechas);
            bitacora.setHora(fechas);
            bitacora.setTabla("FICHA_GENERAL_EMPLEADO");
            bitacora.setColumna("TELEFONO_FIJO");
            bitacora.setAccion("CREAR");
            bitacora.setValorAct(telFijo);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("TELEFONO_CELULAR");
            bitacora.setValorAct(telCelular);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("DIRECCION");
            bitacora.setValorAct(direccion);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("DIRECCION_TIPO_ZONA");
            bitacora.setValorAct(direccionTipoZona);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("DIRECCION_TIPO_VIA");
            bitacora.setValorAct(direccionTipoVia);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("ESTADO_CIVIL");
            bitacora.setValorAct(estadoCivil);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("EMAIL");
            bitacora.setValorAct(email);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("EMPLEADO_ID");
            bitacora.setValorAct(empleadoId);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("UBIGEO_CODIGO");
            bitacora.setValorAct(ubigeoCodigo);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("NIVEL_EDUCATIVO");
            bitacora.setValorAct(nivelEducativo);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);
        } else {
            //Datos antes de modificar
            FichaGeneralEmpleado antes = this.find(this.selected.getId());

            String telFijo1 = antes.getTelefonoFijo();
            String telCelular1 = antes.getTelefonoCelular();
            String direccion1 = antes.getDireccion();
            String direccionTipoZona1 = antes.getDireccionTipoZona();
            String direccionTipoVia1 = antes.getDireccionTipoVia();
            String estadoCivil1 = antes.getEstadoCivil();
            String email1 = antes.getEmail();
            String empleadoId1 = antes.getEmpleadoId().toString();
            String ubigeoCodigo1 = antes.getUbigeoCodigo().toString();
            String nivelEducativo1 = antes.getNivelEducativoId().toString();

            //Datos despues de modificar
            String telFijo2 = this.selected.getTelefonoFijo();
            String telCelular2 = this.selected.getTelefonoCelular();
            String direccion2 = this.selected.getDireccion();
            String direccionTipoZona2 = this.selected.getDireccionTipoZona();
            String direccionTipoVia2 = this.selected.getDireccionTipoVia();
            String estadoCivil2 = this.selected.getEstadoCivil();
            String email2 = this.selected.getEmail();
            String empleadoId2 = this.selected.getEmpleadoId().toString();
            String ubigeoCodigo2 = this.selected.getUbigeoCodigo().toString();
            String nivelEducativo2 = this.selected.getNivelEducativoId().toString();

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
            bitacora.setTabla("FICHA_GENERAL_EMPLEADO");
            bitacora.setColumna("TELEFONO_FIJO");
            bitacora.setAccion("MODIFICAR");
            bitacora.setValorAct(telFijo2);
            bitacora.setValorAnt(telFijo1);

            if (!telFijo1.equals(telFijo2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("TELEFONO_CELULAR");
            bitacora.setValorAct(telCelular2);
            bitacora.setValorAnt(telCelular1);

            if (!telCelular1.equals(telCelular2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("DIRECCION");
            bitacora.setValorAct(direccion2);
            bitacora.setValorAnt(direccion1);

            if (!direccion1.equals(direccion2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("DIRECCION_TIPO_ZONA");
            bitacora.setValorAct(direccionTipoZona2);
            bitacora.setValorAnt(direccionTipoZona1);

            if (!direccionTipoZona1.equals(direccionTipoZona2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("DIRECCION_TIPO_VIA");
            bitacora.setValorAct(direccionTipoVia2);
            bitacora.setValorAnt(direccionTipoVia1);

            if (!direccionTipoVia1.equals(direccionTipoVia2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("ESTADO_CIVIL");
            bitacora.setValorAct(estadoCivil2);
            bitacora.setValorAnt(estadoCivil1);

            if (!estadoCivil1.equals(estadoCivil2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("EMAIL");
            bitacora.setValorAct(email2);
            bitacora.setValorAnt(email1);

            if (!email1.equals(email2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("EMPLEADO_ID");
            bitacora.setValorAct(empleadoId2);
            bitacora.setValorAnt(empleadoId1);

            if (!empleadoId1.equals(empleadoId2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("UBIGEO_CODIGO");
            bitacora.setValorAct(ubigeoCodigo2);
            bitacora.setValorAnt(ubigeoCodigo1);

            if (!ubigeoCodigo1.equals(ubigeoCodigo2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("NIVEL_EDUCATIVO");
            bitacora.setValorAct(nivelEducativo2);
            bitacora.setValorAnt(nivelEducativo1);

            if (!nivelEducativo1.equals(nivelEducativo2)) {
                bitacoraC.edit(bitacora);
            }
        }
    }

    @Override
    protected void remove(FichaGeneralEmpleado objeto) {
        this.fichaGeneralEmpleadoFacade.remove(objeto);

        //Datos antes de modificar
        FichaGeneralEmpleado antes = this.find(this.selected.getId());

        String telFijo1 = antes.getTelefonoFijo();
        String telCelular1 = antes.getTelefonoCelular();
        String direccion1 = antes.getDireccion();
        String direccionTipoZona1 = antes.getDireccionTipoZona();
        String direccionTipoVia1 = antes.getDireccionTipoVia();
        String estadoCivil1 = antes.getEstadoCivil();
        String email1 = antes.getEmail();
        String empleadoId1 = antes.getEmpleadoId().toString();
        String ubigeoCodigo1 = antes.getUbigeoCodigo().toString();
        String nivelEducativo1 = antes.getNivelEducativoId().toString();

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
        bitacora.setTabla("FICHA_GENERAL_EMPLEADO");
        bitacora.setColumna("TELEFONO_FIJO");
        bitacora.setAccion("ELIMINAR");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(telFijo1);
        bitacoraC.edit(bitacora);

        bitacora.setColumna("TELEFONO_CELULAR");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(telCelular1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("DIRECCION");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(direccion1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("DIRECCION_TIPO_ZONA");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(direccionTipoZona1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("DIRECCION_TIPO_VIA");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(direccionTipoVia1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("ESTADO_CIVIL");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(estadoCivil1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("EMAIL");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(email1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("EMPLEADO_ID");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(empleadoId1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("UBIGEO_CODIGO");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(ubigeoCodigo1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("NIVEL_EDUCATIVO");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(nivelEducativo1);
        bitacoraC.edit(bitacora);        
    }

    @Override
    public FichaGeneralEmpleado find(Object id) {
        return this.fichaGeneralEmpleadoFacade.find(id);
    }

    @Override
    public List<FichaGeneralEmpleado> getItems() {
        return this.fichaGeneralEmpleadoFacade.findAll();
    }

    @Override
    public List<FichaGeneralEmpleado> search(String namedQuery) {
        return this.fichaGeneralEmpleadoFacade.search(namedQuery);
    }

    @Override
    public List<FichaGeneralEmpleado> search(String namedQuery, Map<String, Object> parametros) {
        return this.fichaGeneralEmpleadoFacade.search(namedQuery, parametros);
    }

    @Override
    public List<FichaGeneralEmpleado> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.fichaGeneralEmpleadoFacade.search(namedQuery, parametros, inicio, tamanio);
    }
}
