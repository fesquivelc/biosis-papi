package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.FeriadoFacadeLocal;
import com.project.jsica.ejb.entidades.Bitacora;
import com.project.jsica.ejb.entidades.Feriado;
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

@Named(value = "feriadoController")
@ViewScoped
public class FeriadoController extends AbstractController<Feriado> {

    @EJB
    private FeriadoFacadeLocal feriadoFacade;

    @Inject
    private BitacoraController bitacoraC;

    @Inject
    private AnioController anioIdController;

    public FeriadoController() {
        // Inform the Abstract parent controller of the concrete Feriado?cap_first Entity
        super(Feriado.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        anioIdController.setSelected(null);
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

    @Override
    protected void edit(Feriado objeto) {
        this.feriadoFacade.edit(objeto);

        if (this.esNuevo) {
            Bitacora bitacora = new Bitacora();
            //----Bitacora----
            //Fecha y hora//          
            Date fechas = new Date();//           
            //Ip Cliente
            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();

            String nombre = this.selected.getNombre();
            String fechaInicio = this.selected.getFechaInicio().toString();
            String fechaFin = this.selected.getFechaFin().toString();
            String idAnio = this.selected.getAnioId().toString();

            bitacora.setUsuario("JC");
            bitacora.setIpCliente(ip_cliente);
            bitacora.setFecha(fechas);
            bitacora.setHora(fechas);
            bitacora.setTabla("FERIADO");
            bitacora.setColumna("NOMBRE");
            bitacora.setAccion("CREAR");
            bitacora.setValorAct(nombre);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("FECHA_INICIO");
            bitacora.setValorAct(fechaInicio);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("FECHA_FIN");
            bitacora.setValorAct(fechaFin);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);

            bitacora.setColumna("ANIO_ID");
            bitacora.setValorAct(idAnio);
            bitacora.setValorAnt(" ");
            bitacoraC.edit(bitacora);
        } else {
            //Datos antes de modificar
            Feriado antes = this.find(this.selected.getIdferiados());

            String nombre1 = antes.getNombre();
            String fechaInicio1 = antes.getFechaInicio().toString();
            String fechaFin1 = antes.getFechaFin().toString();
            String idAnio1 = antes.getAnioId().toString();

            //Datos despues de modificar
            String nombre2 = this.selected.getNombre();
            String fechaInicio2 = this.selected.getFechaInicio().toString();
            String fechaFin2 = this.selected.getFechaFin().toString();
            String idAnio2 = this.selected.getAnioId().toString();

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
            bitacora.setTabla("FERIADO");
            bitacora.setColumna("NOMBRE");
            bitacora.setAccion("MODIFICAR");
            bitacora.setValorAct(nombre2);
            bitacora.setValorAnt(nombre1);

            if (!nombre1.equals(nombre2)) {
                bitacoraC.edit(bitacora);
            }

            bitacora.setColumna("FECHA_INICIO");
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

            bitacora.setColumna("ID_ANIO");
            bitacora.setValorAct(idAnio2);
            bitacora.setValorAnt(idAnio1);

            if (!idAnio1.equals(idAnio2)) {
                bitacoraC.edit(bitacora);
            }
        }
    }

    @Override
    protected void remove(Feriado objeto) {
        this.feriadoFacade.remove(objeto);

        Feriado antes = this.find(this.selected.getIdferiados());

        String nombre1 = antes.getNombre();
        String fechaInicio1 = antes.getFechaInicio().toString();
        String fechaFin1 = antes.getFechaFin().toString();
        String idAnio1 = antes.getAnioId().toString();

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
        bitacora.setTabla("FERIADO");
        bitacora.setColumna("NOMBRE");
        bitacora.setAccion("MODIFICAR");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(nombre1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("FECHA_INICIO");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(fechaInicio1);
        bitacoraC.edit(bitacora);
        
        bitacora.setColumna("FECHA_FIN");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(fechaFin1);
        bitacoraC.edit(bitacora);

        bitacora.setColumna("ID_ANIO");
        bitacora.setValorAct(" ");
        bitacora.setValorAnt(idAnio1);
        bitacoraC.edit(bitacora);        
    }

    @Override
    public Feriado find(Object id) {
        return this.feriadoFacade.find(id);
    }

    @Override
    public List<Feriado> getItems() {
        return this.feriadoFacade.findAll();
    }

    @Override
    public List<Feriado> search(String namedQuery) {
        return this.feriadoFacade.search(namedQuery);
    }

    @Override
    public List<Feriado> search(String namedQuery, Map<String, Object> parametros) {
        return this.feriadoFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Feriado> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.feriadoFacade.search(namedQuery, parametros, inicio, tamanio);
    }
}
