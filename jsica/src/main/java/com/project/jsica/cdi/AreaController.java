package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.AreaFacadeLocal;
import com.project.jsica.ejb.entidades.Area;
import com.project.jsica.ejb.entidades.Bitacora;
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

@Named(value = "areaController")
@ViewScoped
public class AreaController extends AbstractController<Area> {

    @EJB
    private AreaFacadeLocal areaFacade;

    @Inject
    private BitacoraController bitacoraC;

    @Inject
    private DetalleContratoController detalleContratoListController;
    @Inject
    private SucursalController sucursalIdController;
    @Inject
    private ServicioController servicioListController;

    public AreaController() {
        // Inform the Abstract parent controller of the concrete Area?cap_first Entity
        super(Area.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        sucursalIdController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of DetalleContrato entities
     * that are retrieved from Area?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for DetalleContrato page
     */
    public String navigateDetalleContratoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetalleContrato_items", this.getSelected().getDetalleContratoList());
        }
        return "/detalleContrato/index";
    }

    /**
     * Sets the "selected" attribute of the Sucursal controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareSucursalId(ActionEvent event) {
        if (this.getSelected() != null && sucursalIdController.getSelected() == null) {
            sucursalIdController.setSelected(this.getSelected().getSucursalId());
        }
    }

    /**
     * Sets the "items" attribute with a collection of Servicio entities that
     * are retrieved from Area?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Servicio page
     */
    public String navigateServicioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Servicio_items", this.getSelected().getServicioList());
        }
        return "/servicio/index";
    }

    @Override
    protected void edit(Area objeto) {
        if (this.esNuevo) {
            Bitacora bitacora = new Bitacora();
            Logger.getLogger(AreaController.class.getName()).info("es nuevo");
            if (this.bitacoraC == null) {
                Logger.getLogger(AreaController.class.getName()).info("bitacora ejb es null");
            } else {
                Logger.getLogger(AreaController.class.getName()).info("bitacora ejb no es null");
            }
            this.areaFacade.edit(objeto);
            //----Bitacora----
            //Fecha y hora//          
            Date fechas = new Date();
            SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
//            System.out.println("fecha: "+dt.format(fechas));
//           
            //Ip Cliente
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
            Logger.getLogger(AreaController.class.getName()).info("despues de recuperar ip");

            //Tabla
            Logger.getLogger(AreaController.class.getName()).info("tabla es: Area");
            //Campos

            String nombre = this.selected.getNombre();
            String descripcion = this.selected.getDescripcion();
            String sucursal = this.selected.getSucursalId().getNombre();

            Logger.getLogger(AnioController.class.getName()).info("Datos llenados: " + nombre + descripcion + sucursal);

            //Datos
            bitacora.setUsuario(" ");
            bitacora.setIpCliente(ip_cliente);
            Logger.getLogger(AnioController.class.getName()).info("la ip es " + ip_cliente);
            bitacora.setFecha(fechas);
            bitacora.setHora(fechas);
            bitacora.setTabla("AREA");
            bitacora.setColumna("NOMBRE");
            bitacora.setAccion("CREAR");
            bitacora.setValorAct(nombre);
            bitacora.setValorAnt(null);
            Logger.getLogger(AreaController.class.getName()).info("campo 1");
            bitacoraC.edit(bitacora);
            Logger.getLogger(AreaController.class.getName()).info("campo año agregado");

            bitacora.setColumna("DESCRIPCION");
            bitacora.setValorAct(descripcion);
            bitacora.setValorAnt(null);
            Logger.getLogger(AreaController.class.getName()).info("campo 2");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("SUCURSAL");
            bitacora.setValorAct(sucursal);
            bitacora.setValorAnt(null);
            Logger.getLogger(AreaController.class.getName()).info("campo 3");
            bitacoraC.edit(bitacora);

        } else {
            //Datos antes de modificar
            Area antes = this.find(this.selected.getId());
            String nombre1 = antes.getNombre();
            String descripcion1 = antes.getDescripcion();
            String sucursal1 = antes.getSucursalId().getNombre();

            this.areaFacade.edit(objeto);
            Logger.getLogger(AreaController.class.getName()).info("No creó, lo actualizo");

            //Datos despues de modificar
            String nombre2 = this.selected.getNombre();
            String descripcion2 = this.selected.getDescripcion();
            String sucursal2 = this.selected.getSucursalId().getNombre();

            //----Bitacora----
            Bitacora bitacora = new Bitacora();
            //Fecha y hora//          
            Date fechas = new Date();
            SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
//            System.out.println("fecha: "+dt.format(fechas));
//           
            //Ip Cliente
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
            Logger.getLogger(AreaController.class.getName()).info("despues de recuperar ip");

            //Tabla
            Logger.getLogger(AreaController.class.getName()).info("tabla es: AREA");
            //Campos

//            Logger.getLogger(AnioController.class.getName()).info("Datos llenados: " + anio + nombre + vigente);
            //Datos
            bitacora.setUsuario(" ");
            bitacora.setIpCliente(ip_cliente);
            Logger.getLogger(AreaController.class.getName()).info("la ip es " + ip_cliente);
            bitacora.setFecha(fechas);
            bitacora.setHora(fechas);
            bitacora.setTabla("AREA");
            bitacora.setColumna("NOMBRE");
            bitacora.setAccion("MODIFICAR");
            bitacora.setValorAnt(nombre1);
            bitacora.setValorAct(nombre2);
            Logger.getLogger(AreaController.class.getName()).info("campo 1");

            if (!nombre1.equals(nombre2)) {
                bitacoraC.edit(bitacora);
                Logger.getLogger(AreaController.class.getName()).info("campo año agregado");
            }

            bitacora.setColumna("DESCRIPCION");
            bitacora.setValorAnt(nombre1);
            bitacora.setValorAct(nombre2);
            Logger.getLogger(AreaController.class.getName()).info("campo 2");

            if (!descripcion1.equals(descripcion2)) {
                bitacoraC.edit(bitacora);
            }

//            System.out.println(anio1+" "+nombre1+" "+vigente1);
            bitacora.setColumna("SUCURSAL");
            bitacora.setValorAnt(sucursal1);
            bitacora.setValorAct(sucursal2);
            Logger.getLogger(AreaController.class.getName()).info("campo 3");

            if (!sucursal1.equals(sucursal2)) {
                bitacoraC.edit(bitacora);
            }

        }
    }

    @Override
    protected void remove(Area objeto) {
        //Datos antes de borrar
        Area antes = this.find(this.selected.getId());
        String nombre1 = antes.getNombre();
        String descripcion1 = antes.getDescripcion();
        String sucursal1 = antes.getSucursalId().getNombre();

        this.areaFacade.remove(objeto);
        
        Bitacora bitacora = new Bitacora();
        //----Bitacora----
        //Fecha y hora//          
        Date fechas = new Date();
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
//            System.out.println("fecha: "+dt.format(fechas));
//           
        //Ip Cliente
        String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
        Logger.getLogger(AreaController.class.getName()).info("despues de recuperar ip");

        //Tabla
        Logger.getLogger(AreaController.class.getName()).info("tabla es: Area");
            //Campos


        Logger.getLogger(AnioController.class.getName()).info("Datos llenados: " + nombre1 + descripcion1 + sucursal1);

        //Datos
        bitacora.setUsuario(" ");
        bitacora.setIpCliente(ip_cliente);
        Logger.getLogger(AnioController.class.getName()).info("la ip es " + ip_cliente);
        bitacora.setFecha(fechas);
        bitacora.setHora(fechas);
        bitacora.setTabla("AREA");
        bitacora.setColumna("NOMBRE");
        bitacora.setAccion("ELIMINAR");
        bitacora.setValorAct(nombre1);
        bitacora.setValorAnt(null);
        Logger.getLogger(AreaController.class.getName()).info("campo 1");
        bitacoraC.edit(bitacora);
        Logger.getLogger(AreaController.class.getName()).info("campo año agregado");

        bitacora.setColumna("DESCRIPCION");
        bitacora.setValorAct(descripcion1);
        bitacora.setValorAnt(null);
        Logger.getLogger(AreaController.class.getName()).info("campo 2");
        bitacoraC.edit(bitacora);

        bitacora.setColumna("SUCURSAL");
        bitacora.setValorAct(sucursal1);
        bitacora.setValorAnt(null);
        Logger.getLogger(AreaController.class.getName()).info("campo 3");
        bitacoraC.edit(bitacora);
    }

    @Override
    public Area find(Object id) {
        return this.areaFacade.find(id);
    }

    @Override
    public List<Area> getItems() {
        return this.areaFacade.findAll();
    }

    @Override
    public List<Area> search(String namedQuery) {
        return this.areaFacade.search(namedQuery);
    }

    @Override
    public List<Area> search(String namedQuery, Map<String, Object> parametros) {
        return this.areaFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Area> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.areaFacade.search(namedQuery, parametros, inicio, tamanio);
    }

}
