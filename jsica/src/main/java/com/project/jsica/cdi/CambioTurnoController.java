package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.CambioTurnoFacadeLocal;
import com.project.jsica.ejb.entidades.Bitacora;
import com.project.jsica.ejb.entidades.CambioTurno;
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

@Named(value = "cambioTurnoController")
@ViewScoped
public class CambioTurnoController extends AbstractController<CambioTurno> {

    @EJB
    private CambioTurnoFacadeLocal cambioTurnoFacade;

    @Inject
    private BitacoraController bitacoraC;

    @Inject
    private DetalleHorarioController detalleHorarioOriginalController;
    @Inject
    private DetalleHorarioController detalleHorarioReemplazoController;
    @Inject
    private EmpleadoController jefeInmediatoIdController;

    public CambioTurnoController() {
        // Inform the Abstract parent controller of the concrete CambioTurno?cap_first Entity
        super(CambioTurno.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        detalleHorarioOriginalController.setSelected(null);
        detalleHorarioReemplazoController.setSelected(null);
        jefeInmediatoIdController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the DetalleHorario controller in order
     * to display its data in a dialog. This is reusing existing the existing
     * View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDetalleHorarioOriginal(ActionEvent event) {
        if (this.getSelected() != null && detalleHorarioOriginalController.getSelected() == null) {
            detalleHorarioOriginalController.setSelected(this.getSelected().getDetalleHorarioOriginal());
        }
    }

    /**
     * Sets the "selected" attribute of the DetalleHorario controller in order
     * to display its data in a dialog. This is reusing existing the existing
     * View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDetalleHorarioReemplazo(ActionEvent event) {
        if (this.getSelected() != null && detalleHorarioReemplazoController.getSelected() == null) {
            detalleHorarioReemplazoController.setSelected(this.getSelected().getDetalleHorarioReemplazo());
        }
    }

    /**
     * Sets the "selected" attribute of the Empleado controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareJefeInmediatoId(ActionEvent event) {
        if (this.getSelected() != null && jefeInmediatoIdController.getSelected() == null) {
            jefeInmediatoIdController.setSelected(this.getSelected().getJefeInmediatoId());
        }
    }

    @Override
    protected void edit(CambioTurno objeto) {
        if (this.esNuevo) {
            Bitacora bitacora = new Bitacora();
            Logger.getLogger(CambioTurnoController.class.getName()).info("es nuevo");
            if (this.bitacoraC == null) {
                Logger.getLogger(CambioTurnoController.class.getName()).info("bitacora ejb es null");
            } else {
                Logger.getLogger(CambioTurnoController.class.getName()).info("bitacora ejb no es null");
            }
            this.cambioTurnoFacade.edit(objeto);
            //----Bitacora----
            //Fecha y hora//          
            Date fechas = new Date();
            SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
//            System.out.println("fecha: "+dt.format(fechas));
//           
            //Ip Cliente
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
            Logger.getLogger(CambioTurnoController.class.getName()).info("despues de recuperar ip");

            //Tabla
            Logger.getLogger(CambioTurnoController.class.getName()).info("tabla es: CambioTurno");
            //Campos

            String detalle_horario_original = this.selected.getDetalleHorarioOriginal().toString();
            String detalle_horario_reemplazo = this.selected.getDetalleHorarioReemplazo().toString();
            String fecha_pedido = this.selected.getFechaPedido().toString();
            String hora_pedido = this.selected.getHoraPedido().toString();
            String jefe_inmediato_id = this.selected.getJefeInmediatoId().getApellidos() + ", " + this.selected.getJefeInmediatoId().getNombres();

//            Logger.getLogger(AnioController.class.getName()).info("Datos llenados: " + ipv4 + " " + ipv6 + " " + modelo + " " + marca + " " + sucursal);
            //Datos
            bitacora.setUsuario(" ");
            bitacora.setIpCliente(ip_cliente);
            Logger.getLogger(AnioController.class.getName()).info("la ip es " + ip_cliente);
            bitacora.setFecha(fechas);
            bitacora.setHora(fechas);
            bitacora.setTabla("CAMBIO_TURNO");
            bitacora.setColumna("DETALLE_HORARIO_ORIGINAL");
            bitacora.setAccion("CREAR");
            bitacora.setValorAct(detalle_horario_original);
            bitacora.setValorAnt(null);
            Logger.getLogger(CambioTurnoController.class.getName()).info("campo 1");
            bitacoraC.edit(bitacora);
            Logger.getLogger(CambioTurnoController.class.getName()).info("campo año agregado");

            bitacora.setColumna("DETALLE_HORARIO_REEMPLAZO");
            bitacora.setValorAct(detalle_horario_reemplazo);
            bitacora.setValorAnt(null);
            Logger.getLogger(CambioTurnoController.class.getName()).info("campo 2");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("FECHA_PEDIDO");
            bitacora.setValorAct(fecha_pedido);
            bitacora.setValorAnt(null);
            Logger.getLogger(CambioTurnoController.class.getName()).info("campo 3");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("HORA_PEDIDO");
            bitacora.setValorAct(hora_pedido);
            bitacora.setValorAnt(null);
            Logger.getLogger(CambioTurnoController.class.getName()).info("campo 4");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("JEFE_INMEDIATO");
            bitacora.setValorAct(jefe_inmediato_id);
            bitacora.setValorAnt(null);
            Logger.getLogger(CambioTurnoController.class.getName()).info("campo 5");
            bitacoraC.edit(bitacora);

        } else {
            //Datos antes de modificar
            CambioTurno antes = this.find(this.selected.getId());
            String detalle_horario_original1 = antes.getDetalleHorarioOriginal().toString();
            String detalle_horario_reemplazo1 = antes.getDetalleHorarioReemplazo().toString();
            String fecha_pedido1 = antes.getFechaPedido().toString();
            String hora_pedido1 = antes.getHoraPedido().toString();
            String jefe_inmediato_id1 = antes.getJefeInmediatoId().getApellidos() + ", " + this.selected.getJefeInmediatoId().getNombres();


            this.cambioTurnoFacade.edit(objeto);
            Logger.getLogger(CambioTurnoController.class.getName()).info("No creó, lo actualizo");

            //Datos despues de modificar
            String detalle_horario_original2 = this.selected.getDetalleHorarioOriginal().toString();
            String detalle_horario_reemplazo2 = this.selected.getDetalleHorarioReemplazo().toString();
            String fecha_pedido2 = this.selected.getFechaPedido().toString();
            String hora_pedido2 = this.selected.getHoraPedido().toString();
            String jefe_inmediato_id2 = this.selected.getJefeInmediatoId().getApellidos() + ", " + this.selected.getJefeInmediatoId().getNombres();


            //----Bitacora----
            Bitacora bitacora = new Bitacora();
            //Fecha y hora//          
            Date fechas = new Date();
            SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
//            System.out.println("fecha: "+dt.format(fechas));
//           
            //Ip Cliente
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
            Logger.getLogger(CambioTurnoController.class.getName()).info("despues de recuperar ip");

            //Tabla
            Logger.getLogger(CambioTurnoController.class.getName()).info("tabla es: BITACORA");
            //Campos

//            Logger.getLogger(AnioController.class.getName()).info("Datos llenados: " + anio + nombre + vigente);
            //Datos
            bitacora.setUsuario(" ");
            bitacora.setIpCliente(ip_cliente);
            Logger.getLogger(CambioTurnoController.class.getName()).info("la ip es " + ip_cliente);
            bitacora.setFecha(fechas);
            bitacora.setHora(fechas);
            bitacora.setTabla("BIOMETRICO");
            bitacora.setColumna("DETALLE_HORARIO_ORIGINAL");
            bitacora.setAccion("MODIFICAR");
            bitacora.setValorAnt(detalle_horario_original1);
            bitacora.setValorAct(detalle_horario_original2);
            Logger.getLogger(CambioTurnoController.class.getName()).info("campo 1");

            if (!detalle_horario_original1.equals(detalle_horario_original2)) {
                bitacoraC.edit(bitacora);
                Logger.getLogger(CambioTurnoController.class.getName()).info("campo ipv4 agregado");
            }

            bitacora.setColumna("DETALLE_HORARIO_REEMPLAZO");
            bitacora.setValorAnt(detalle_horario_reemplazo1);
            bitacora.setValorAct(detalle_horario_reemplazo2);
            Logger.getLogger(CambioTurnoController.class.getName()).info("campo 2");

            if (!detalle_horario_reemplazo1.equals(detalle_horario_reemplazo2)) {
                bitacoraC.edit(bitacora);
            }

//            System.out.println(anio1+" "+nombre1+" "+vigente1);
            bitacora.setColumna("FECHA_PEDIDO");
            bitacora.setValorAnt(fecha_pedido1);
            bitacora.setValorAct(fecha_pedido2);
            Logger.getLogger(CambioTurnoController.class.getName()).info("campo 3");

            if (!fecha_pedido1.equals(fecha_pedido2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("HORA_PEDIDO");
            bitacora.setValorAnt(hora_pedido1);
            bitacora.setValorAct(hora_pedido2);
            Logger.getLogger(CambioTurnoController.class.getName()).info("campo 4");

            if (!hora_pedido1.equals(hora_pedido2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("JEFE_INMEDIATO");
            bitacora.setValorAnt(jefe_inmediato_id1);
            bitacora.setValorAct(jefe_inmediato_id2);
            Logger.getLogger(CambioTurnoController.class.getName()).info("campo 5");

            if (!jefe_inmediato_id1.equals(jefe_inmediato_id2)) {
                bitacoraC.edit(bitacora);
            }

        }
    }

    @Override
    protected void remove(CambioTurno objeto) {
        Bitacora bitacora = new Bitacora();
        //----Bitacora----
            //Fecha y hora//          
            Date fechas = new Date();
            SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
//            System.out.println("fecha: "+dt.format(fechas));
//           
            //Ip Cliente
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
            Logger.getLogger(CambioTurnoController.class.getName()).info("despues de recuperar ip");

            //Tabla
            Logger.getLogger(CambioTurnoController.class.getName()).info("tabla es: CambioTurno");
            //Campos

            String detalle_horario_original1 = this.selected.getDetalleHorarioOriginal().toString();
            String detalle_horario_reemplazo1 = this.selected.getDetalleHorarioReemplazo().toString();
            String fecha_pedido1 = this.selected.getFechaPedido().toString();
            String hora_pedido1 = this.selected.getHoraPedido().toString();
            String jefe_inmediato_id1 = this.selected.getJefeInmediatoId().getApellidos() + ", " + this.selected.getJefeInmediatoId().getNombres();

//            Logger.getLogger(AnioController.class.getName()).info("Datos llenados: " + ipv4 + " " + ipv6 + " " + modelo + " " + marca + " " + sucursal);
            //Datos
            bitacora.setUsuario(" ");
            bitacora.setIpCliente(ip_cliente);
            Logger.getLogger(AnioController.class.getName()).info("la ip es " + ip_cliente);
            bitacora.setFecha(fechas);
            bitacora.setHora(fechas);
            bitacora.setTabla("CAMBIO_TURNO");
            bitacora.setColumna("DETALLE_HORARIO_ORIGINAL");
            bitacora.setAccion("CREAR");
            bitacora.setValorAct(detalle_horario_original1);
            bitacora.setValorAnt(null);
            Logger.getLogger(CambioTurnoController.class.getName()).info("campo 1");
            bitacoraC.edit(bitacora);
            Logger.getLogger(CambioTurnoController.class.getName()).info("campo año agregado");

            bitacora.setColumna("DETALLE_HORARIO_REEMPLAZO");
            bitacora.setValorAct(detalle_horario_reemplazo1);
            bitacora.setValorAnt(null);
            Logger.getLogger(CambioTurnoController.class.getName()).info("campo 2");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("FECHA_PEDIDO");
            bitacora.setValorAct(fecha_pedido1);
            bitacora.setValorAnt(null);
            Logger.getLogger(CambioTurnoController.class.getName()).info("campo 3");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("HORA_PEDIDO");
            bitacora.setValorAct(hora_pedido1);
            bitacora.setValorAnt(null);
            Logger.getLogger(CambioTurnoController.class.getName()).info("campo 4");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("JEFE_INMEDIATO");
            bitacora.setValorAct(jefe_inmediato_id1);
            bitacora.setValorAnt(null);
            Logger.getLogger(CambioTurnoController.class.getName()).info("campo 5");
            bitacoraC.edit(bitacora);
        
        this.cambioTurnoFacade.remove(objeto);
    }

    @Override
    public CambioTurno find(Object id) {
        return this.cambioTurnoFacade.find(id);
    }

    @Override
    public List<CambioTurno> getItems() {
        return this.cambioTurnoFacade.findAll();
    }

    @Override
    public List<CambioTurno> search(String namedQuery) {
        return this.cambioTurnoFacade.search(namedQuery);
    }

    @Override
    public List<CambioTurno> search(String namedQuery, Map<String, Object> parametros) {
        return this.cambioTurnoFacade.search(namedQuery, parametros);
    }

    @Override
    public List<CambioTurno> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.cambioTurnoFacade.search(namedQuery, parametros, inicio, tamanio);
    }
}
