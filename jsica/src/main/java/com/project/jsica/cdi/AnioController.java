package com.project.jsica.cdi;

import dao.AnioFacadeLocal;
import com.project.jsica.ejb.entidades.Anio;
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
import org.eclipse.persistence.config.ResultType;

@Named(value = "anioController")
@ViewScoped
public class AnioController extends AbstractController<Anio> {
    //private static final Logger log = Logger.getLogger(AnioController.class.getClass());

    @EJB
    private AnioFacadeLocal anioFacade;

    @Inject
    private BitacoraController bitacoraC;

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
        //BITACORA
        if (this.esNuevo) {
            Bitacora bitacora = new Bitacora();

            Date fechas = new Date();
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();

            String anio = this.selected.getAnio();
            String nombre = this.selected.getNombre();
            String vigencia = String.valueOf(this.selected.getVigente());

            bitacora.setUsuario("JC");
            bitacora.setFecha(fechas);
            bitacora.setHora(fechas);
            bitacora.setIpCliente(ip_cliente);
            bitacora.setAccion("CREAR");
            bitacora.setTabla("AÑO");
            bitacora.setColumna("AÑO");
            bitacora.setValorAct(anio);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("NOMBRE");
            bitacora.setValorAct(nombre);
            bitacoraC.edit(bitacora);

            bitacora.setColumna("VIGENCIA");
            bitacora.setValorAct(vigencia);
            bitacoraC.edit(bitacora);
        } else {
            Bitacora bitacora = new Bitacora();
            Anio antes = this.find(this.selected.getId());

            String anio1 = antes.getAnio();
            String nombre1 = antes.getNombre();
            String vigencia1 = String.valueOf(antes.getVigente());

            String anio2 = this.selected.getAnio();
            String nombre2 = this.selected.getNombre();
            String vigencia2 = String.valueOf(this.selected.getVigente());

            Date fechas = new Date();
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();

            bitacora.setUsuario("JC");
            bitacora.setFecha(fechas);
            bitacora.setHora(fechas);
            bitacora.setIpCliente(ip_cliente);
            bitacora.setAccion("MODIFICAR");
            bitacora.setTabla("AÑO");
            bitacora.setColumna("AÑO");
            bitacora.setValorAnt(anio1);
            bitacora.setValorAct(anio2);
            bitacoraC.edit(bitacora);

            bitacora.setColumna("NOMBRE");
            bitacora.setValorAnt(nombre1);
            bitacora.setValorAct(nombre2);
            bitacoraC.edit(bitacora);

            bitacora.setColumna("VIGENCIA");
            bitacora.setValorAnt(vigencia1);
            bitacora.setValorAct(vigencia2);
            bitacoraC.edit(bitacora);
        }

        this.anioFacade.edit(objeto);

    }

    @Override
    protected void remove(Anio objeto) {
        Bitacora bitacora = new Bitacora();
        Anio antes = this.find(this.selected.getId());

        String anio1 = antes.getAnio();
        String nombre1 = antes.getNombre();
        String vigencia1 = String.valueOf(antes.getVigente());
        
        String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
        Date fechas = new Date();

        bitacora.setUsuario("JC");
        bitacora.setFecha(fechas);
        bitacora.setHora(fechas);
        bitacora.setIpCliente(ip_cliente);
        bitacora.setAccion("ELIMINAR");
        bitacora.setTabla("AÑO");
        bitacora.setColumna("AÑO");
        bitacora.setValorAnt(anio1);
        bitacora.setValorAct(" ");
        bitacoraC.edit(bitacora);

        bitacora.setColumna("NOMBRE");
        bitacora.setValorAnt(nombre1);
        bitacoraC.edit(bitacora);

        bitacora.setColumna("VIGENCIA");
        bitacora.setValorAnt(vigencia1);
        bitacoraC.edit(bitacora);

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

    @Override
    public void saveNew(ActionEvent event) {
        if(this.selected.getVigente()){
            this.anioFacade.vigenciaFalsa();
        }
        super.saveNew(event); //To change body of generated methods, choose Tools | Templates.
        
    }

}
