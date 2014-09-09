package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.RegistroAsistenciaFacadeLocal;
import com.project.jsica.ejb.entidades.Empleado;
import com.project.jsica.ejb.entidades.RegistroAsistencia;
import java.util.Date;
import java.util.HashMap;
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

@Named(value = "registroAsistenciaController")
@ViewScoped
public class RegistroAsistenciaController extends AbstractController<RegistroAsistencia> {
    private Empleado empleadoSeleccionado;
    private Date fechaInicio;
    private Date fechaFin;
    @EJB
    private RegistroAsistenciaFacadeLocal registroAsistenciaFacade;
    @Inject
    private BiometricoController biometricoIdController;
    @Inject
    private EmpleadoController empleadoIdController;

    private static final Logger LOG = Logger.getLogger(DetalleHorarioController.class.getName());
    /*Metodo para llamar empleados*/
    
    public Empleado getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    public void setEmpleadoSeleccionado(Empleado empleadoSeleccionado) {
        this.empleadoSeleccionado = empleadoSeleccionado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public RegistroAsistenciaController() {
        // Inform the Abstract parent controller of the concrete RegistroAsistencia?cap_first Entity
        super(RegistroAsistencia.class);
    }

    /*Lista de Registros devueltos para empleado*/
    public List<RegistroAsistencia> getRegistrosAsistencia(){
        if(this.empleadoSeleccionado!=null){
            if(this.fechaInicio != null && this.fechaFin !=null){
                String namedString = "SELECT ra FROM RegistroAsistencia ra WHERE ra.empleadoId = :empleado AND ra.fecha BETWEEN :fechaInicio AND :fechaFin ORDER BY ra.fecha,ra.hora";
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("empleado",empleadoSeleccionado);
                parametros.put("fechaInicio",fechaInicio);
                parametros.put("fechaFin", fechaFin);                             
                return this.registroAsistenciaFacade.search(namedString,parametros);
            }else if(this.fechaInicio!=null && this.fechaFin==null){
                String namedString = "SELECT ra FROM RegistroAsistencia ra WHERE ra.empleadoId = :empleado AND ra.fecha >= :fechaInicio ORDER BY ra.fecha,ra.hora";
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("empleado",empleadoSeleccionado);
                parametros.put("fechaInicio",fechaInicio);
                                    
                return this.registroAsistenciaFacade.search(namedString,parametros);
            }
            String namedString = "SELECT ra FROM RegistroAsistencia ra WHERE ra.empleadoId = :empleado ORDER BY ra.fecha,ra.hora";
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("empleado",empleadoSeleccionado);
            return this.registroAsistenciaFacade.search(namedString,parametros);
            
        }else{
            
            return null;
        }
    }
    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        biometricoIdController.setSelected(null);
        empleadoIdController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Biometrico controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareBiometricoId(ActionEvent event) {
        if (this.getSelected() != null && biometricoIdController.getSelected() == null) {
            biometricoIdController.setSelected(this.getSelected().getBiometricoId());
        }
    }

    /**
     * Sets the "selected" attribute of the Empleado controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEmpleadoId(ActionEvent event) {
        if (this.getSelected() != null && empleadoIdController.getSelected() == null) {
            empleadoIdController.setSelected(this.getSelected().getEmpleadoId());
        }
    }

    @Override
    protected void edit(RegistroAsistencia objeto) {
        this.registroAsistenciaFacade.edit(objeto);
    }

    @Override
    protected void remove(RegistroAsistencia objeto) {
        this.registroAsistenciaFacade.remove(objeto);
    }

    @Override
    public RegistroAsistencia find(Object id) {
        return this.registroAsistenciaFacade.find(id);
    }

    @Override
    public List<RegistroAsistencia> getItems() {
        return this.registroAsistenciaFacade.findAll();
    }

    @Override
    public List<RegistroAsistencia> search(String namedQuery) {
        return this.registroAsistenciaFacade.search(namedQuery);
    }

    @Override
    public List<RegistroAsistencia> search(String namedQuery, Map<String, Object> parametros) {
        return this.registroAsistenciaFacade.search(namedQuery, parametros);
    }

    @Override
    public List<RegistroAsistencia> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.registroAsistenciaFacade.search(namedQuery, parametros, inicio, tamanio);
    }
}
