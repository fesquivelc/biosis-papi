package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.ContratoFacadeLocal;
import com.project.jsica.ejb.entidades.Area;
import com.project.jsica.ejb.entidades.Bitacora;
import com.project.jsica.ejb.entidades.Contrato;
import com.project.jsica.ejb.entidades.DetalleContrato;
import com.project.jsica.ejb.entidades.Sucursal;
import java.util.ArrayList;
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

@Named(value = "contratoController")
@ViewScoped
public class ContratoController extends AbstractController<Contrato> {
    private DetalleContrato detallecontratoseleccionado;
    
    private Sucursal sucursalSeleccionado;
    private boolean isSucursalSeleccionado;    
    private Area areaSeleccionado;
    private boolean isAreaSeleccionado;

    @EJB
    private ContratoFacadeLocal contratoFacade;
    
    @Inject
    private BitacoraController bitacoraC;

    @Inject
    private DetalleContratoController detalleContratoListController;
    @Inject
    private ContratoController condicionLaboralIdController;
    @Inject
    private RegimenLaboralController regimenLaboralIdController;
    @Inject
    private AnioController anioIdController;
    @Inject
    private TipoContratoController tipoContratoIdController;
    @Inject
    private OcupacionController ocupacionIdController;

    public ContratoController() {
        // Inform the Abstract parent controller of the concrete Contrato?cap_first Entity
        super(Contrato.class);
    }
    
    public DetalleContrato getDetallecontratoseleccionado() {
        return detallecontratoseleccionado;
    }

    public void setDetallecontratoseleccionado(DetalleContrato detallecontratoseleccionado) {
        this.detallecontratoseleccionado = detallecontratoseleccionado;
    }

    public Sucursal getSucursalSeleccionado() {
        return sucursalSeleccionado;
    }

    public void setSucursalSeleccionado(Sucursal sucursalSeleccionado) {
        this.sucursalSeleccionado = sucursalSeleccionado;
    }

    public Area getAreaSeleccionado() {
        return areaSeleccionado;
    }

    public void setAreaSeleccionado(Area areaSeleccionado) {
        this.areaSeleccionado = areaSeleccionado;
    }

    public boolean isIsSucursalSeleccionado() {
        return isSucursalSeleccionado;
    }

    public void setIsSucursalSeleccionado(boolean isSucursalSeleccionado) {
        this.isSucursalSeleccionado = isSucursalSeleccionado;
    }

    public boolean isIsAreaSeleccionado() {
        return isAreaSeleccionado;
    }

    public void setIsAreaSeleccionado(boolean isAreaSeleccionado) {
        this.isAreaSeleccionado = isAreaSeleccionado;
    }
    
    
    
    


    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        condicionLaboralIdController.setSelected(null);
        regimenLaboralIdController.setSelected(null);
        anioIdController.setSelected(null);
        tipoContratoIdController.setSelected(null);
        ocupacionIdController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of DetalleContrato entities
     * that are retrieved from Contrato?cap_first and returns the navigation
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
     * Sets the "selected" attribute of the Contrato controller in order
     * to display its data in a dialog. This is reusing existing the existing
     * View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareContratoId(ActionEvent event) {
        if (this.getSelected() != null && condicionLaboralIdController.getSelected() == null) {
            condicionLaboralIdController.setSelected(this.getSelected());
        }
    }

    /**
     * Sets the "selected" attribute of the RegimenLaboral controller in order
     * to display its data in a dialog. This is reusing existing the existing
     * View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRegimenLaboralId(ActionEvent event) {
        if (this.getSelected() != null && regimenLaboralIdController.getSelected() == null) {
            regimenLaboralIdController.setSelected(this.getSelected().getRegimenLaboralId());
        }
    }

    /**
     * Sets the "selected" attribute of the Anio controller in order to display
     * its data in a dialog. This is reusing existing the existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareAnioId(ActionEvent event) {
        if (this.getSelected() != null && anioIdController.getSelected() == null) {
            anioIdController.setSelected(this.getSelected().getAnioId());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoContrato controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTipoContratoId(ActionEvent event) {
        if (this.getSelected() != null && tipoContratoIdController.getSelected() == null) {
            tipoContratoIdController.setSelected(this.getSelected().getTipoContratoId());
        }
    }

    /**
     * Sets the "selected" attribute of the Ocupacion controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareOcupacionId(ActionEvent event) {
        if (this.getSelected() != null && ocupacionIdController.getSelected() == null) {
            ocupacionIdController.setSelected(this.getSelected().getOcupacionId());
        }
    }

    @Override
    protected void edit(Contrato objeto) {
        this.contratoFacade.edit(objeto);
        if (this.esNuevo) {
            Bitacora bitacora = new Bitacora();
            //----Bitacora----
            //Fecha y hora//          
            Date fechas = new Date();//           
            //Ip Cliente
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
            
            String fechaInicio = this.selected.getFechaInicio().toString();
            String fechaFin = this.selected.getFechaFin().toString();
            String codigo = this.selected.getCodigo();
            String sueldoBasico = this.selected.getSueldoBasico().toString();
            String condicionLaboral = this.selected.getCondicionLaboralId().getNombre();
            String regimenLaboral = this.selected.getRegimenLaboralId().getNombre();
            String anio = this.selected.getAnioId().getNombre();
            String tipoContrato = this.selected.getTipoContratoId().getNombre();

            bitacora.setUsuario("JC");
            bitacora.setIpCliente(ip_cliente);
            bitacora.setFecha(fechas);
            bitacora.setHora(fechas);
            bitacora.setTabla("CONTRATO");
            bitacora.setColumna("FECHA_INICIO");
            bitacora.setAccion("CREAR");
            bitacora.setValorAct(fechaInicio);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("FECHA_FIN");
            bitacora.setValorAct(fechaFin);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);
            
            bitacora.setColumna("CODIGO");
            bitacora.setValorAct(codigo);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);
            
            bitacora.setColumna("SUELDO_BASICO");
            bitacora.setValorAct(sueldoBasico);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);
            
            bitacora.setColumna("CONDICION_LABORAL");
            bitacora.setValorAct(condicionLaboral);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);
            
            bitacora.setColumna("REGIMEN_LABORAL");
            bitacora.setValorAct(regimenLaboral);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);
            
            bitacora.setColumna("AÑO");
            bitacora.setValorAct(anio);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);
            
            bitacora.setColumna("TIPO_CONTRATO");
            bitacora.setValorAct(tipoContrato);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

        } else {
            //Datos antes de modificar
            Contrato antes = this.find(this.selected.getId());
            String fechaInicio1 = antes.getFechaInicio().toString();
            String fechaFin1 = antes.getFechaFin().toString();
            String codigo1 = antes.getCodigo();
            String sueldoBasico1 = antes.getSueldoBasico().toString();
            String condicionLaboral1 = antes.getCondicionLaboralId().getNombre();
            String regimenLaboral1 = antes.getRegimenLaboralId().getNombre();
            String anio1 = antes.getAnioId().getNombre();
            String tipoContrato1 = antes.getTipoContratoId().getNombre();
            
            //Datos despues de modificar
            String fechaInicio2 = this.selected.getFechaInicio().toString();
            String fechaFin2 = this.selected.getFechaFin().toString();
            String codigo2 = this.selected.getCodigo();
            String sueldoBasico2 = this.selected.getSueldoBasico().toString();
            String condicionLaboral2 = this.selected.getCondicionLaboralId().getNombre();
            String regimenLaboral2 = this.selected.getRegimenLaboralId().getNombre();
            String anio2 = this.selected.getAnioId().getNombre();
            String tipoContrato2 = this.selected.getTipoContratoId().getNombre();
            
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
            bitacora.setTabla("CONTRATO");
            bitacora.setColumna("FECHA_INICIO");
            bitacora.setAccion("MODIFICAR");
            bitacora.setValorAct(fechaInicio2);
            bitacora.setValorAnt(fechaInicio1);

            if (!fechaInicio1.equals(fechaInicio2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("FECHA_FIN");
            bitacora.setValorAct(fechaFin2);
            bitacora.setValorAnt(fechaFin1);

            if (!fechaFin1.equals(fechaFin2)) {
                bitacoraC.edit(bitacora);
            }
            
            bitacora.setColumna("CODIGO");
            bitacora.setValorAct(codigo2);
            bitacora.setValorAnt(codigo1);
            
            if (!codigo1.equals(codigo2)) {
                bitacoraC.edit(bitacora);
            }
            
            bitacora.setColumna("SUELDO_BASICO");
            bitacora.setValorAct(sueldoBasico2);
            bitacora.setValorAnt(sueldoBasico1);
            
            if (!sueldoBasico1.equals(sueldoBasico2)) {
                bitacoraC.edit(bitacora);
            }
            
            bitacora.setColumna("CONDICION_LABORAL");
            bitacora.setValorAct(condicionLaboral2);
            bitacora.setValorAnt(condicionLaboral1);
            
            if(!condicionLaboral1.equals(condicionLaboral2)) {
                bitacoraC.edit(bitacora);
            }
            
            bitacora.setColumna("REGIMEN_LABORAL");
            bitacora.setValorAct(regimenLaboral2);
            bitacora.setValorAnt(regimenLaboral1);
            
            if(!regimenLaboral1.equals(regimenLaboral2)) {
                bitacoraC.edit(bitacora);
            }
            
            bitacora.setColumna("AÑO");
            bitacora.setValorAct(anio2);
            bitacora.setValorAnt(anio1);
            
            if(!anio1.equals(anio2)) {
                bitacoraC.edit(bitacora);
            }
            
            bitacora.setColumna("TIPO_CONTRATO");
            bitacora.setValorAct(tipoContrato2);
            bitacora.setValorAnt(tipoContrato1);
            
            if(!tipoContrato1.equals(tipoContrato2)) {
                bitacoraC.edit(bitacora);
            }
        }
    }

    @Override
    protected void remove(Contrato objeto) {
        this.contratoFacade.remove(objeto);
    }

    @Override
    public Contrato find(Object id) {
        return this.contratoFacade.find(id);
    }

    @Override
    public List<Contrato> getItems() {
        return this.contratoFacade.findAll();
    }

    @Override
    public List<Contrato> search(String namedQuery) {
        return this.contratoFacade.search(namedQuery);
    }

    @Override
    public List<Contrato> search(String namedQuery, Map<String, Object> parametros) {
        return this.contratoFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Contrato> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.contratoFacade.search(namedQuery, parametros, inicio, tamanio);
    }
    
    @Override
    public Contrato prepareCreate(ActionEvent event) {
        Contrato contrato = new Contrato();

        contrato.setDetalleContratoList(new ArrayList<DetalleContrato>());
        detallecontratoseleccionado = new DetalleContrato();
        contrato.getDetalleContratoList().add(detallecontratoseleccionado);
        detallecontratoseleccionado.setContratoId(contrato);
        this.setSelected(contrato);
        return contrato;
    }
    
     
    public void onSucursalSeleccionado(){
        if(this.sucursalSeleccionado!= null){           
            if(this.sucursalSeleccionado.getId() !=0){
                this.isSucursalSeleccionado = true;
                return;
            }
        }
        this.isSucursalSeleccionado= false;       
    }  
    
    public void onSucursalSeleccionado2(){
        if(this.detallecontratoseleccionado.getAreaId().getSucursalId()!= null){           
            if(this.detallecontratoseleccionado.getAreaId().getSucursalId().getId() !=0){
                this.isSucursalSeleccionado = true;
                return;
            }
        }
        this.isSucursalSeleccionado= false;       
    }  
        
    public List<Area> getAreas(){        
        if(this.isSucursalSeleccionado){
            return this.sucursalSeleccionado.getAreaList();
        }else{
            return null;
        }
    }
    
     public List<Area> getAreas2(){        
        if(this.isSucursalSeleccionado){
            return this.detallecontratoseleccionado.ge this.sucursalSeleccionado.getAreaList();
        }else{
            return null;
        }
    }

}
