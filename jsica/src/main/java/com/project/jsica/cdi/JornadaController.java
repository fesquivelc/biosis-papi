package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.JornadaFacadeLocal;
import com.project.jsica.ejb.entidades.Area;
import com.project.jsica.ejb.entidades.Bitacora;
import com.project.jsica.ejb.entidades.Jornada;
import com.project.jsica.ejb.entidades.Servicio;
import com.project.jsica.ejb.entidades.Sucursal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named(value = "jornadaController")
@ViewScoped
public class JornadaController extends AbstractController<Jornada> {

    private Sucursal sucursalSeleccionado;
    private boolean isSucursalSeleccionado;
    private Area areaSeleccionado;
    private boolean isAreaSeleccionado;
    private Servicio servicioSeleccionado;
    private boolean isServicioSeleccionado;
    
    @EJB
    private JornadaFacadeLocal jornadaFacade;

    @Inject
    private BitacoraController bitacoraC;

    @Inject
    private ServicioController servicioIdController;
    @Inject
    private DetalleHorarioController detalleHorarioListController;

    public JornadaController() {
        // Inform the Abstract parent controller of the concrete Jornada?cap_first Entity
        super(Jornada.class);
    }

    /**
     Metodos getter and setters
     **/
    public Sucursal getSucursalSeleccionado() {    
        return sucursalSeleccionado;
    }

    public void setSucursalSeleccionado(Sucursal sucursalSeleccionado) {
        this.sucursalSeleccionado = sucursalSeleccionado;
    }

    public boolean isIsSucursalSeleccionado() {
        return isSucursalSeleccionado;
    }

    public void setIsSucursalSeleccionado(boolean isSucursalSeleccionado) {
        this.isSucursalSeleccionado = isSucursalSeleccionado;
    }

    public Area getAreaSeleccionado() {
        return areaSeleccionado;
    }

    public void setAreaSeleccionado(Area areaSeleccionado) {
        this.areaSeleccionado = areaSeleccionado;
    }

    public boolean isIsAreaSeleccionado() {
        return isAreaSeleccionado;
    }

    public void setIsAreaSeleccionado(boolean isAreaSeleccionado) {
        this.isAreaSeleccionado = isAreaSeleccionado;
    }

    public Servicio getServicioSeleccionado() {
        return servicioSeleccionado;
    }

    public void setServicioSeleccionado(Servicio servicioSeleccionado) {
        this.servicioSeleccionado = servicioSeleccionado;
    }

    public boolean isIsServicioSeleccionado() {
        return isServicioSeleccionado;
    }

    public void setIsServicioSeleccionado(boolean isServicioSeleccionado) {
        this.isServicioSeleccionado = isServicioSeleccionado;
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        servicioIdController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Servicio controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareServicioId(ActionEvent event) {
        if (this.getSelected() != null && servicioIdController.getSelected() == null) {
            servicioIdController.setSelected(this.getSelected().getServicioId());
        }
    }

    /**
     * Sets the "items" attribute with a collection of DetalleHorario entities
     * that are retrieved from Jornada?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for DetalleHorario page
     */
    public String navigateDetalleHorarioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetalleHorario_items", this.getSelected().getDetalleHorarioList());
        }
        return "/detalleHorario/index";
    }

    @Override
    protected void edit(Jornada objeto) {
        this.jornadaFacade.edit(objeto);

        if (this.esNuevo) {
            Bitacora bitacora = new Bitacora();
            //----Bitacora----
            //Fecha y hora//          
            Date fechas = new Date();//           
            //Ip Cliente
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();

            String codigo = this.selected.getCodigo();
            String nombre = this.selected.getNombre();
            String horaEntrada = this.selected.getHEntrada().toString();
            String horaSalida = this.selected.getHSalida().toString();
            String flexible = String.valueOf(this.selected.getFlexible());
            String servicioId = this.selected.getServicioId().toString();

            bitacora.setUsuario("JC");
            bitacora.setIpCliente(ip_cliente);
            bitacora.setFecha(fechas);
            bitacora.setHora(fechas);
            bitacora.setTabla("JORNADA");
            bitacora.setColumna("CODIGO");
            bitacora.setAccion("CREAR");
            bitacora.setValorAct(codigo);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("NOMBRE");
            bitacora.setValorAct(nombre);
            bitacoraC.edit(bitacora);

            bitacora.setColumna("H_ENTRADA");
            bitacora.setValorAct(horaEntrada);
            bitacoraC.edit(bitacora);

            bitacora.setColumna("H_SALIDA");
            bitacora.setValorAct(horaSalida);
            bitacoraC.edit(bitacora);

            bitacora.setColumna("FLEXIBLE");
            bitacora.setValorAct(flexible);
            bitacoraC.edit(bitacora);

            bitacora.setColumna("SERVICIO_ID");
            bitacora.setValorAct(servicioId);
            bitacoraC.edit(bitacora);
        } else {
            //Datos antes de modificar
            Jornada antes = this.find(this.selected.getCodigo());

            String codigo1 = antes.getCodigo();
            String nombre1 = antes.getNombre();
            String horaEntrada1 = antes.getHEntrada().toString();
            String horaSalida1 = antes.getHSalida().toString();
            String flexible1 = String.valueOf(antes.getFlexible());
            String servicioId1 = antes.getServicioId().toString();

            //Datos despues de modificar
            String codigo2 = this.selected.getCodigo();
            String nombre2 = this.selected.getNombre();
            String horaEntrada2 = this.selected.getHEntrada().toString();
            String horaSalida2 = this.selected.getHSalida().toString();
            String flexible2 = String.valueOf(this.selected.getFlexible());
            String servicioId2 = this.selected.getServicioId().toString();

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
            bitacora.setTabla("JORNADA");
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

            bitacora.setColumna("H_ENTRADA");
            bitacora.setValorAct(horaEntrada2);
            bitacora.setValorAnt(horaEntrada1);

            if (!horaEntrada1.equals(horaEntrada2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("H_SALIDA");
            bitacora.setValorAct(horaSalida2);
            bitacora.setValorAnt(horaSalida1);

            if (!horaSalida1.equals(horaSalida2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("FLEXIBLE");
            bitacora.setValorAct(flexible2);
            bitacora.setValorAnt(flexible1);

            if (!flexible1.equals(flexible2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("SERVICIO_ID");
            bitacora.setValorAct(servicioId2);
            bitacora.setValorAnt(servicioId1);

            if (!servicioId1.equals(servicioId2)) {
                bitacoraC.edit(bitacora);
            }
        }
    }

    @Override
    protected void remove(Jornada objeto) {
        this.jornadaFacade.remove(objeto);

        //Datos antes de modificar
        Jornada antes = this.find(this.selected.getCodigo());

        String codigo1 = antes.getCodigo();
        String nombre1 = antes.getNombre();
        String horaEntrada1 = antes.getHEntrada().toString();
        String horaSalida1 = antes.getHSalida().toString();
        String flexible1 = String.valueOf(antes.getFlexible());
        String servicioId1 = antes.getServicioId().toString();

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
        bitacora.setTabla("JORNADA");
        bitacora.setColumna("CODIGO");
        bitacora.setAccion("ELIMINAR");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(codigo1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("NOMBRE");
        bitacora.setValorAnt(nombre1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("H_ENTRADA");
        bitacora.setValorAnt(horaEntrada1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("H_SALIDA");
        bitacora.setValorAnt(horaSalida1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("FLEXIBLE");
        bitacora.setValorAnt(flexible1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("SERVICIO_ID");
        bitacora.setValorAnt(servicioId1);
        bitacoraC.edit(bitacora);
    }

    @Override
    public Jornada find(Object id) {
        return this.jornadaFacade.find(id);
    }

    @Override
    public List<Jornada> getItems() {
        return this.jornadaFacade.findAll();
    }

    @Override
    public List<Jornada> search(String namedQuery) {
        return this.jornadaFacade.search(namedQuery);
    }

    @Override
    public List<Jornada> search(String namedQuery, Map<String, Object> parametros) {
        return this.jornadaFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Jornada> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.jornadaFacade.search(namedQuery, parametros, inicio, tamanio);
    }

    private static final Logger LOG = Logger.getLogger(DetalleHorarioController.class.getName());
     public void onSucursalSeleccionado() {
        if (this.sucursalSeleccionado != null) {
            LOG.log(Level.INFO, "ID DEL DEPARTAMENTO: {0}", this.sucursalSeleccionado.getId());
            if (this.sucursalSeleccionado.getId() != 0) {
                this.isSucursalSeleccionado = true;
                return;
            }
        }
        this.isSucursalSeleccionado = false;
    }
    
      public void onAreaSeleccionado() {
        if (this.areaSeleccionado != null) {
            if (this.areaSeleccionado.getId() != 0) {
                this.isAreaSeleccionado = true;
                return;
            }
        }
        this.isAreaSeleccionado = false;
    }
      
     public void onServicioSeleccionado(){
        this.isServicioSeleccionado = this.servicioSeleccionado!=null;
    }
    
    public List<Area> getAreas() {
        if (this.isSucursalSeleccionado) {
            return this.sucursalSeleccionado.getAreaList();
        } else {
            return null;
        }
    }

    public List<Servicio> getServicios() {
        if (this.isAreaSeleccionado) {
            return this.areaSeleccionado.getServicioList();
        } else {
            return null;
        }
    }
}
