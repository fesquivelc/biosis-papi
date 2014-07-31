package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.AnioFacadeLocal;
import com.project.jsica.ejb.dao.BitacoraFacade;
import com.project.jsica.ejb.entidades.Anio;
import com.project.jsica.ejb.entidades.Bitacora;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        if (this.esNuevo) {
            Bitacora bitacora = new Bitacora();
            Logger.getLogger(AnioController.class.getName()).info("es nuevo");
            if (this.bitacoraC == null) {
                Logger.getLogger(AnioController.class.getName()).info("bitacora ejb es null");
            } else {
                Logger.getLogger(AnioController.class.getName()).info("bitacora ejb no es null");
            }
            this.anioFacade.edit(objeto);
            //----Bitacora----
            //Fecha y hora//          
            Date fechas = new Date();
            SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
//            System.out.println("fecha: "+dt.format(fechas));
//           
            //Ip Cliente
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
            Logger.getLogger(AnioController.class.getName()).info("despues de recuperar ip");

            //Tabla
            Logger.getLogger(AnioController.class.getName()).info("tabla es: Año");
            //Campos
            String anio = this.selected.getAnio();
            String nombre = this.selected.getNombre();
            String vigente = String.valueOf(this.selected.getVigente());
            Logger.getLogger(AnioController.class.getName()).info("Datos llenados: " + anio + nombre + vigente);

            //Datos
            bitacora.setUsuario(" ");
            bitacora.setIpCliente(ip_cliente);
            Logger.getLogger(AnioController.class.getName()).info("la ip es " + ip_cliente);
            bitacora.setFecha(fechas);
            bitacora.setHora(fechas);
            bitacora.setTabla("AÑO");
            bitacora.setColumna("AÑO");
            bitacora.setAccion("CREAR");
            bitacora.setValorAct(anio);
            bitacora.setValorAnt(null);
            Logger.getLogger(AnioController.class.getName()).info("campo 1");
            bitacoraC.edit(bitacora);
            Logger.getLogger(AnioController.class.getName()).info("campo año agregado");

            bitacora.setColumna("Nombre");
            bitacora.setValorAct(nombre);
            Logger.getLogger(AnioController.class.getName()).info("campo 2");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("Vigente");
            bitacora.setValorAct(vigente);
            bitacora.setValorAnt(null);
            Logger.getLogger(AnioController.class.getName()).info("campo 3");
            bitacoraC.edit(bitacora);

        } else {
            //Datos antes de modificar
            Anio antes = this.find(this.selected.getId());
            String anio1 = antes.getAnio();
            String nombre1 = antes.getNombre();
            String vigente1 = String.valueOf(antes.getVigente());

            this.anioFacade.edit(objeto);
            Logger.getLogger(AnioController.class.getName()).info("No creo, lo actualizo");

            //Datos despues de modificar
            String anio2 = this.selected.getAnio();
            String nombre2 = this.selected.getNombre();
            String vigente2 = String.valueOf(this.selected.getVigente());

            //----Bitacora----
            Bitacora bitacora = new Bitacora();
            //Fecha y hora//          
            Date fechas = new Date();
            SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
//            System.out.println("fecha: "+dt.format(fechas));
//           
            //Ip Cliente
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
            Logger.getLogger(AnioController.class.getName()).info("despues de recuperar ip");

            //Tabla
            Logger.getLogger(AnioController.class.getName()).info("tabla es: Año");
            //Campos

//            Logger.getLogger(AnioController.class.getName()).info("Datos llenados: " + anio + nombre + vigente);
            //Datos
            bitacora.setUsuario(" ");
            bitacora.setIpCliente(ip_cliente);
            Logger.getLogger(AnioController.class.getName()).info("la ip es " + ip_cliente);
            bitacora.setFecha(fechas);
            bitacora.setHora(fechas);
            bitacora.setTabla("AÑO");
            bitacora.setColumna("AÑO");
            bitacora.setAccion("MODIFICAR");
            bitacora.setValorAnt(anio1);
            bitacora.setValorAct(anio2);
            Logger.getLogger(AnioController.class.getName()).info("campo 1");

            if (!anio1.equals(anio2)) {
                bitacoraC.edit(bitacora);
                Logger.getLogger(AnioController.class.getName()).info("campo año agregado");
            }

            bitacora.setColumna("Nombre");
            bitacora.setValorAnt(nombre1);
            bitacora.setValorAct(nombre2);
            Logger.getLogger(AnioController.class.getName()).info("campo 2");
            
            if(!nombre1.equals(nombre2)){
                bitacoraC.edit(bitacora);
            }
            

//            System.out.println(anio1+" "+nombre1+" "+vigente1);
            bitacora.setColumna("Vigente");
            bitacora.setValorAnt(vigente1);
            bitacora.setValorAct(vigente2);
            Logger.getLogger(AnioController.class.getName()).info("campo 3");
            
            if(!vigente1.equals(vigente2)){
                bitacoraC.edit(bitacora);
            }
            
        }

    }

    @Override
    protected void remove(Anio objeto) {
        //Datos antes de borrar
        Anio antes = this.find(this.selected.getId());
        String anio1 = antes.getAnio();
        String nombre1 = antes.getNombre();
        String vigente1 = String.valueOf(antes.getVigente());

        this.anioFacade.remove(objeto);


        //----Bitacora----
        Bitacora bitacora = new Bitacora();
        //Fecha y hora//          
        Date fechas = new Date();
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
//            System.out.println("fecha: "+dt.format(fechas));
//           
        //Ip Cliente
        String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
        Logger.getLogger(AnioController.class.getName()).info("despues de recuperar ip");

        //Tabla
        Logger.getLogger(AnioController.class.getName()).info("tabla es: Año");
            //Campos

//            Logger.getLogger(AnioController.class.getName()).info("Datos llenados: " + anio + nombre + vigente);
        //Datos
        bitacora.setUsuario(" ");
        bitacora.setIpCliente(ip_cliente);
        Logger.getLogger(AnioController.class.getName()).info("la ip es " + ip_cliente);
        bitacora.setFecha(fechas);
        bitacora.setHora(fechas);
        bitacora.setTabla("AÑO");
        bitacora.setColumna("AÑO");
        bitacora.setAccion("ELIMINAR");
        bitacora.setValorAnt(anio1);
        bitacora.setValorAct(null);
        Logger.getLogger(AnioController.class.getName()).info("campo 1");

        bitacoraC.edit(bitacora);
        Logger.getLogger(AnioController.class.getName()).info("campo año agregado");

        bitacora.setColumna("Nombre");
        bitacora.setValorAnt(nombre1);
        Logger.getLogger(AnioController.class.getName()).info("campo 2");
        bitacoraC.edit(bitacora);

//            System.out.println(anio1+" "+nombre1+" "+vigente1);
        bitacora.setColumna("Vigente");
        bitacora.setValorAnt(vigente1);
        Logger.getLogger(AnioController.class.getName()).info("campo 3");
        bitacoraC.edit(bitacora);

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
