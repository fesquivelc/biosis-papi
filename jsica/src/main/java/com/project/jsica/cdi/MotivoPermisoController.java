package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.MotivoPermisoFacadeLocal;
import com.project.jsica.ejb.entidades.Bitacora;
import com.project.jsica.ejb.entidades.MotivoPermiso;
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

@Named(value = "motivoPermisoController")
@ViewScoped
public class MotivoPermisoController extends AbstractController<MotivoPermiso> {

    @EJB
    private MotivoPermisoFacadeLocal motivoPermisoFacade;

    @Inject
    private BitacoraController bitacoraC;

    @Inject
    private PermisoController permisoListController;

    public MotivoPermisoController() {
        // Inform the Abstract parent controller of the concrete MotivoPermiso?cap_first Entity
        super(MotivoPermiso.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Permiso entities that are
     * retrieved from MotivoPermiso?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Permiso page
     */
    public String navigatePermisoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Permiso_items", this.getSelected().getPermisoList());
        }
        return "/permiso/index";
    }

    @Override
    protected void edit(MotivoPermiso objeto) {
        this.motivoPermisoFacade.edit(objeto);

        if (this.esNuevo) {
            Bitacora bitacora = new Bitacora();
            //----Bitacora----
            //Fecha y hora//          
            Date fechas = new Date();//           
            //Ip Cliente
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();

            String codigo = this.selected.getCodigo();
            String nombre = this.selected.getNombre();
            String descripcion = this.selected.getDescripcion();
            String conGoce = String.valueOf(this.selected.getConGoce());

            bitacora.setUsuario("JC");
            bitacora.setIpCliente(ip_cliente);
            bitacora.setFecha(fechas);
            bitacora.setHora(fechas);
            bitacora.setTabla("MOTIVO_PERMISO");
            bitacora.setColumna("CODIGO");
            bitacora.setAccion("CREAR");
            bitacora.setValorAct(codigo);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("NOMBRE");
            bitacora.setValorAct(nombre);
            bitacoraC.edit(bitacora);

            bitacora.setColumna("DESCRIPCION");
            bitacora.setValorAct(descripcion);
            bitacoraC.edit(bitacora);

            bitacora.setColumna("CON_GOCE");
            bitacora.setValorAct(conGoce);
            bitacoraC.edit(bitacora);
        } else {
            //Datos antes de modificar
            MotivoPermiso antes = this.find(this.selected.getCodigo());

            String codigo1 = antes.getCodigo();
            String nombre1 = antes.getNombre();
            String descripcion1 = antes.getDescripcion();
            String conGoce1 = String.valueOf(antes.getConGoce());

            //Datos despues de modificar
            String codigo2 = this.selected.getCodigo();
            String nombre2 = this.selected.getNombre();
            String descripcion2 = this.selected.getDescripcion();
            String conGoce2 = String.valueOf(this.selected.getConGoce());

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
            bitacora.setTabla("MOTIVO_PERMISO");
            bitacora.setColumna("CODIGO");
            bitacora.setAccion("MODIFICAR");
            bitacora.setValorAct(codigo2);
            bitacora.setValorAnt(codigo1);

            if (!codigo1.equals(codigo2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("NOMBRE");
            bitacora.setValorAct(nombre2);
            bitacora.setValorAnt(nombre1);

            if (!nombre1.equals(nombre2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("DESCRIPCION");
            bitacora.setValorAct(descripcion2);
            bitacora.setValorAnt(descripcion1);

            if (!descripcion1.equals(descripcion2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("CON_GOCE");
            bitacora.setValorAct(conGoce2);
            bitacora.setValorAnt(conGoce1);

            if (!conGoce1.equals(conGoce2)) {
                bitacoraC.edit(bitacora);
            }
        }
    }

    @Override
    protected void remove(MotivoPermiso objeto) {
        this.motivoPermisoFacade.remove(objeto);

        MotivoPermiso antes = this.find(this.selected.getCodigo());

        String codigo1 = antes.getCodigo();
        String nombre1 = antes.getNombre();
        String descripcion1 = antes.getDescripcion();
        String conGoce1 = String.valueOf(antes.getConGoce());
        
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
        bitacora.setTabla("MOTIVO_PERMISO");
        bitacora.setColumna("CODIGO");
        bitacora.setAccion("ELIMINAR");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(codigo1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("NOMBRE");
        bitacora.setValorAnt(nombre1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("DESCRIPCION");
        bitacora.setValorAnt(descripcion1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("CON_GOCE");
        bitacora.setValorAnt(conGoce1);
        bitacoraC.edit(bitacora);
    }

    @Override
    public MotivoPermiso find(Object id) {
        return this.motivoPermisoFacade.find(id);
    }

    @Override
    public List<MotivoPermiso> getItems() {
        return this.motivoPermisoFacade.findAll();
    }

    @Override
    public List<MotivoPermiso> search(String namedQuery) {
        return this.motivoPermisoFacade.search(namedQuery);
    }

    @Override
    public List<MotivoPermiso> search(String namedQuery, Map<String, Object> parametros) {
        return this.motivoPermisoFacade.search(namedQuery, parametros);
    }

    @Override
    public List<MotivoPermiso> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.motivoPermisoFacade.search(namedQuery, parametros, inicio, tamanio);
    }

}
