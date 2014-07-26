package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.AnioFacadeLocal;
import com.project.jsica.ejb.entidades.Anio;
import com.project.jsica.ejb.entidades.Bitacora;
import java.util.Calendar;
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


@Named(value = "anioController")
@ViewScoped
public class AnioController extends AbstractController<Anio> {
    //private static final Logger log = Logger.getLogger(AnioController.class.getClass());
    BitacoraController bitacoraC = new BitacoraController();
    
    @EJB
    private AnioFacadeLocal anioFacade;
    
    
    @Inject
    private ContratoController contratoListController;
    @Inject
    private FeriadoController feriadoListController;
    @Inject
    private VacacionController vacacionListController;

    public AnioController() {
        // Inform the Abstract parent controller of the concrete Anio?cap_first Entity
        super(Anio.class, AnioController.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Contrato entities that
     * are retrieved from Anio?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Contrato page
     */
    public String navigateContratoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Contrato_items", this.getSelected().getContratoList());
        }
        return "/contrato/index";
    }

    /**
     * Sets the "items" attribute with a collection of Feriado entities that are
     * retrieved from Anio?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Feriado page
     */
    public String navigateFeriadoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Feriado_items", this.getSelected().getFeriadoList());
        }
        return "/feriado/index";
    }

    /**
     * Sets the "items" attribute with a collection of Vacacion entities that
     * are retrieved from Anio?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Vacacion page
     */
    public String navigateVacacionList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Vacacion_items", this.getSelected().getVacacionList());
        }
        return "/vacacion/index";
    }

    @Override
    protected void edit(Anio objeto) {
        if (this.esNuevo) {
            Bitacora bitacora = bitacoraC.prepareCreate(null);
            Logger.getLogger(AnioController.class.getName()).info("es nuevo");
            if(this.bitacoraC == null){
                Logger.getLogger(AnioController.class.getName()).info("bitacora ejb es null");
            }else{
                Logger.getLogger(AnioController.class.getName()).info("bitacora ejb no es null");
            }
            this.anioFacade.edit(objeto);
            //----Bitacora----
            //Fecha y hora
            Calendar fechas = Calendar.getInstance();
            if(fechas == null){
                Logger.getLogger(AnioController.class.getName()).info("fechas es null");
            }
            String fecha = fechas.get(Calendar.YEAR) +"/"+fechas.get(Calendar.MONTH)+"/"+fechas.get(Calendar.DAY_OF_MONTH);
            String hora = fechas.get(Calendar.HOUR)+":"+fechas.get(Calendar.MINUTE)+":"+fechas.get(Calendar.SECOND);
            Logger.getLogger(AnioController.class.getName()).info("etas son la fecha y hora"+fecha+hora);
            //Ip Cliente
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr(); 
            Logger.getLogger(AnioController.class.getName()).info("despues de recuperar ip");
            
            //Tabla
            Logger.getLogger(AnioController.class.getName()).info("antes de tabla");
            String tabla = this.itemClass.toString();
            Logger.getLogger(AnioController.class.getName()).info("despues de tabla");
            //Campos
            //String id = this.selected.getId().toString();
            String anio = this.selected.getAnio();
            String nombre = this.selected.getNombre();
            String vigente = String.valueOf(this.selected.getVigente());
            Logger.getLogger(AnioController.class.getName()).info("Datos llenados");
            
//            Bitacora bitacora = new Bitacora();
            
//            if(bitacora == null ){
//                Logger.getLogger(AnioController.class.getName()).error("el objeto bitacora es nullllll");
//            }
            
            bitacora.setUsuario(" ");
            bitacora.setIpCliente(ip_cliente);
            Logger.getLogger(AnioController.class.getName()).info("la ip es "+ip_cliente);
            bitacora.setFecha(" ");
            bitacora.setHora(" ");
            bitacora.setTabla(tabla);
            bitacora.setColumna("Año");  
            bitacora.setAccion("CREAR");
            bitacora.setValorAct(anio);
            bitacora.setValorAnt(null);
            Logger.getLogger(AnioController.class.getName()).info("Antes de guardar");
            bitacoraC.edit(bitacora);
            Logger.getLogger(AnioController.class.getName()).info("campo año agregado");
            
            bitacora.setColumna("Nombre");
            bitacora.setValorAct(nombre);
            bitacora.setValorAnt("");
            //bitacoraC.edit(bitacora);
            
            bitacora.setColumna("Vigente");
            bitacora.setValorAct(vigente);
            bitacora.setValorAnt("");
            //bitacoraC.edit(bitacora);
            
        } else {
            this.anioFacade.edit(objeto);
            Logger.getLogger(AnioController.class.getName()).info("No creo, lo actualizo");
        }
        
    }

    @Override
    protected void remove(Anio objeto) {
        this.anioFacade.remove(objeto);
    }

    @Override
    public Anio find(Object id) {
        return this.anioFacade.find(id);
    }

    @Override
    public List<Anio> getItems() {
        return this.anioFacade.findAll();
    }

    @Override
    public List<Anio> search(String namedQuery) {
        return this.anioFacade.search(namedQuery);
    }

    @Override
    public List<Anio> search(String namedQuery, Map<String, Object> parametros) {
        return this.anioFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Anio> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.anioFacade.search(namedQuery, parametros, inicio, tamanio);
    }

}
