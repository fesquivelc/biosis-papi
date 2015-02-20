package com.project.jsica.cdi;

import dao.BiometricoFacadeLocal;
import com.project.jsica.ejb.entidades.Biometrico;
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

@Named(value = "biometricoController")
@ViewScoped
public class BiometricoController extends AbstractController<Biometrico> {

    @EJB
    private BiometricoFacadeLocal biometricoFacade;

    @Inject
    private BitacoraController bitacoraC;

    @Inject
    private RegistroAsistenciaController registroAsistenciaListController;
    @Inject
    private SucursalController sucursalIdController;

    public BiometricoController() {
        // Inform the Abstract parent controller of the concrete Biometrico?cap_first Entity
        super(Biometrico.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        sucursalIdController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of RegistroAsistencia
     * entities that are retrieved from Biometrico?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for RegistroAsistencia page
     */
    public String navigateRegistroAsistenciaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RegistroAsistencia_items", this.getSelected().getRegistroAsistenciaList());
        }
        return "/registroAsistencia/index";
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

    @Override
    protected void edit(Biometrico objeto) {
        this.biometricoFacade.edit(objeto);

        if (this.esNuevo) {
            Bitacora bitacora = new Bitacora();
            Date fechas = new Date();
//           
            //Ip Cliente
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();

            String ipv4 = this.selected.getIpv4();
            String ipv6 = this.selected.getIpv6();
            String modelo = this.selected.getModelo();
            String marca = this.selected.getMarca();
            String sucursal = this.selected.getSucursalId().getNombre();

            bitacora.setUsuario("JC");
            bitacora.setIpCliente(ip_cliente);
            bitacora.setFecha(fechas);
            bitacora.setHora(fechas);
            bitacora.setTabla("BIOMETRICO");
            bitacora.setColumna("IPV4");
            bitacora.setAccion("CREAR");
            bitacora.setValorAct(ipv4);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("IPV6");
            bitacora.setValorAct(ipv6);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("MODELO");
            bitacora.setValorAct(modelo);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("MARCA");
            bitacora.setValorAct(marca);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("SUCURSAL");
            bitacora.setValorAct(sucursal);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

        } else {
            //Datos antes de modificar
            Biometrico antes = this.find(this.selected.getId());
            String ipv41 = antes.getIpv4();
            String ipv61 = antes.getIpv6();
            String modelo1 = antes.getModelo();
            String marca1 = antes.getMarca();
            String sucursal1 = antes.getSucursalId().getNombre();

            //Datos despues de modificar
            String ipv42 = this.selected.getIpv4();
            String ipv62 = this.selected.getIpv6();
            String modelo2 = this.selected.getModelo();
            String marca2 = this.selected.getMarca();
            String sucursal2 = this.selected.getSucursalId().getNombre();

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
            bitacora.setTabla("BIOMETRICO");
            bitacora.setColumna("IPV4");
            bitacora.setAccion("MODIFICAR");
            bitacora.setValorAnt(ipv41);
            bitacora.setValorAct(ipv42);

            if (!ipv41.equals(ipv42)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("IPV6");
            bitacora.setValorAnt(ipv61);
            bitacora.setValorAct(ipv62);

            if (!ipv61.equals(ipv62)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("MODELO");
            bitacora.setValorAnt(modelo1);
            bitacora.setValorAct(modelo2);

            if (!modelo1.equals(modelo2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("MARCA");
            bitacora.setValorAnt(marca1);
            bitacora.setValorAct(marca2);

            if (!marca1.equals(marca2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("SUCURSAL");
            bitacora.setValorAnt(sucursal1);
            bitacora.setValorAct(sucursal2);

            if (!sucursal1.equals(sucursal2)) {
                bitacoraC.edit(bitacora);
            }

        }
    }

    @Override
    protected void remove(Biometrico objeto) {
        Biometrico antes = this.find(this.selected.getId());
        String ipv41 = antes.getIpv4();
        String ipv61 = antes.getIpv6();
        String modelo1 = antes.getModelo();
        String marca1 = antes.getMarca();
        String sucursal1 = antes.getSucursalId().getNombre();

        Bitacora bitacora = new Bitacora();
        //----Bitacora----
        //Fecha y hora//          
        Date fechas = new Date();
        //Ip Cliente
        String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();

        //Datos
        bitacora.setUsuario("JC");
        bitacora.setIpCliente(ip_cliente);
        bitacora.setFecha(fechas);
        bitacora.setHora(fechas);
        bitacora.setTabla("BIOMETRICO");
        bitacora.setColumna("IPV4");
        bitacora.setAccion("ELIMINAR");
        bitacora.setValorAnt(ipv41);
        bitacora.setValorAct(" ");
        bitacoraC.edit(bitacora);

        bitacora.setColumna("IPV6");
        bitacora.setValorAnt(ipv61);
        bitacora.setValorAct(" ");
        bitacoraC.edit(bitacora);

        bitacora.setColumna("MODELO");
        bitacora.setValorAnt(modelo1);
        bitacora.setValorAct(" ");
        bitacoraC.edit(bitacora);

        bitacora.setColumna("MARCA");
        bitacora.setValorAnt(marca1);
        bitacora.setValorAct(" ");
        bitacoraC.edit(bitacora);

        bitacora.setColumna("SUCURSAL");
        bitacora.setValorAnt(sucursal1);
        bitacora.setValorAct(" ");
        bitacoraC.edit(bitacora);

        this.biometricoFacade.remove(objeto);
    }

    @Override
    public Biometrico find(Object id) {
        return this.biometricoFacade.find(id);
    }

    @Override
    public List<Biometrico> getItems() {
        return this.biometricoFacade.findAll();
    }

    @Override
    public List<Biometrico> search(String namedQuery) {
        return this.biometricoFacade.search(namedQuery);
    }

    @Override
    public List<Biometrico> search(String namedQuery, Map<String, Object> parametros) {
        return this.biometricoFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Biometrico> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.biometricoFacade.search(namedQuery, parametros, inicio, tamanio);
    }
}
