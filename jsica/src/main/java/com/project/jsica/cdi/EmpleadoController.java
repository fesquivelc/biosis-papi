package com.project.jsica.cdi;

import com.project.jsica.ejb.dao.EmpleadoFacadeLocal;
import com.project.jsica.ejb.entidades.Area;
import com.project.jsica.ejb.entidades.Empleado;
import com.project.jsica.ejb.entidades.FichaGeneralEmpleado;
import com.project.jsica.ejb.entidades.FichaLaboralEmpleado;
import com.project.jsica.ejb.entidades.Servicio;
import com.project.jsica.ejb.entidades.Sucursal;
import java.util.ArrayList;
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

@Named(value = "empleadoController")
@ViewScoped
public class EmpleadoController extends AbstractController<Empleado> {

    private Empleado seleccionado;
    private FichaGeneralEmpleado fichaGeneralSeleccionada;
    private FichaLaboralEmpleado fichaLaboralSeleccionada;

    private Sucursal sucursalSeleccionado;
    private boolean isSucursalSeleccionado;
    private Area areaSeleccionado;
    private boolean isAreaSeleccionado;
    private Servicio servicioSeleccionado;
    private boolean isServicioSeleccionado;
    

    @EJB
    private EmpleadoFacadeLocal empleadoFacade;

    @Inject
    private BitacoraController bitacoraC;

    @Inject
    private AreaController areaController;
    @Inject
    private RegistroAsistenciaController registroAsistenciaListController;
    @Inject
    private DetalleContratoController detalleContratoListController;
    @Inject
    private GrupoHorarioController grupoHorarioIdController;
    @Inject
    private EmpleadoController empleadoListController;
    @Inject
    private EmpleadoController empleadoIdController;
    @Inject
    private ServicioController servicioIdController;
    @Inject
    private CambioTurnoController cambioTurnoListController;
    @Inject
    private SuspensionController suspensionListController;
    @Inject
    private EmpleadoPermisoController empleadoPermisoListController;
    @Inject
    private FichaGeneralEmpleadoController fichaGeneralEmpleadoListController;
    @Inject
    private PapeletaController papeletaListController;
    @Inject
    private PapeletaController papeletaList1Controller;
    @Inject
    private PapeletaController papeletaList2Controller;
    @Inject
    private FaltaController faltaListController;
    @Inject
    private VacacionController vacacionListController;
    @Inject
    private EmpleadoHorarioController empleadoHorarioListController;
    @Inject
    private UsuarioController usuarioListController;
    @Inject
    private FichaLaboralEmpleadoController fichaLaboralEmpleadoListController;

    public List<Empleado> buscarTodos(){
        return this.empleadoFacade.buscarTodos();
    }
    
    public EmpleadoController() {
        // Inform the Abstract parent controller of the concrete Empleado?cap_first Entity
        super(Empleado.class);
    }

    public Empleado getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Empleado seleccionado) {
        this.seleccionado = seleccionado;
    }

    public FichaGeneralEmpleado getFichaSeleccionada() {
        return fichaGeneralSeleccionada;
    }

    public void setFichaSeleccionada(FichaGeneralEmpleado fichaSeleccionada) {
        this.fichaGeneralSeleccionada = fichaSeleccionada;
    }

    public FichaLaboralEmpleado getFichaLaboralSeleccionada() {
        return fichaLaboralSeleccionada;
    }

    public void setFichaLaboralSeleccionada(FichaLaboralEmpleado fichaLaboralSeleccionada) {
        this.fichaLaboralSeleccionada = fichaLaboralSeleccionada;
    }

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

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        grupoHorarioIdController.setSelected(null);
        empleadoIdController.setSelected(null);
        servicioIdController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of RegistroAsistencia
     * entities that are retrieved from Empleado?cap_first and returns the
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
     * Sets the "items" attribute with a collection of DetalleContrato entities
     * that are retrieved from Empleado?cap_first and returns the navigation
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
     * Sets the "selected" attribute of the GrupoHorario controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareGrupoHorarioId(ActionEvent event) {
        if (this.getSelected() != null && grupoHorarioIdController.getSelected() == null) {
            grupoHorarioIdController.setSelected(this.getSelected().getGrupoHorarioId());
        }
    }

    /**
     * Sets the "items" attribute with a collection of Empleado entities that
     * are retrieved from Empleado?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Empleado page
     */
    public String navigateEmpleadoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Empleado_items", this.getSelected().getEmpleadoList());
        }
        return "/empleado/index";
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

    /**
     * Sets the "selected" attribute of the Servicio controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareArea(ActionEvent event) {
        if (this.getSelected() != null && servicioIdController.getSelected() == null) {
            areaController.setSelected(this.getSelected().getArea());
        }
    }

    /**
     * Sets the "items" attribute with a collection of CambioTurno entities that
     * are retrieved from Empleado?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for CambioTurno page
     */
    public String navigateCambioTurnoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CambioTurno_items", this.getSelected().getCambioTurnoList());
        }
        return "/cambioTurno/index";
    }

    /**
     * Sets the "items" attribute with a collection of Suspension entities that
     * are retrieved from Empleado?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Suspension page
     */
    public String navigateSuspensionList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Suspension_items", this.getSelected().getSuspensionList());
        }
        return "/suspension/index";
    }

    /**
     * Sets the "items" attribute with a collection of EmpleadoPermiso entities
     * that are retrieved from Empleado?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for EmpleadoPermiso page
     */
    public String navigateEmpleadoPermisoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EmpleadoPermiso_items", this.getSelected().getEmpleadoPermisoList());
        }
        return "/empleadoPermiso/index";
    }

    /**
     * Sets the "items" attribute with a collection of FichaGeneralEmpleado
     * entities that are retrieved from Empleado?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for FichaGeneralEmpleado page
     */
    public String navigateFichaGeneralEmpleadoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("FichaGeneralEmpleado_items", this.getSelected().getFichaGeneralEmpleadoList());
        }
        return "/fichaGeneralEmpleado/index";
    }

    /**
     * Sets the "items" attribute with a collection of Papeleta entities that
     * are retrieved from Empleado?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Papeleta page
     */
    public String navigatePapeletaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Papeleta_items", this.getSelected().getPapeletaList());
        }
        return "/papeleta/index";
    }

    /**
     * Sets the "items" attribute with a collection of Papeleta entities that
     * are retrieved from Empleado?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Papeleta page
     */
    public String navigatePapeletaList1() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Papeleta_items", this.getSelected().getPapeletaList1());
        }
        return "/papeleta/index";
    }

    /**
     * Sets the "items" attribute with a collection of Papeleta entities that
     * are retrieved from Empleado?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Papeleta page
     */
    public String navigatePapeletaList2() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Papeleta_items", this.getSelected().getPapeletaList2());
        }
        return "/papeleta/index";
    }

    /**
     * Sets the "items" attribute with a collection of Falta entities that are
     * retrieved from Empleado?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Falta page
     */
    public String navigateFaltaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Falta_items", this.getSelected().getFaltaList());
        }
        return "/falta/index";
    }

    /**
     * Sets the "items" attribute with a collection of Vacacion entities that
     * are retrieved from Empleado?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Vacacion page
     */
    public String navigateVacacionList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Vacacion_items", this.getSelected().getVacacionList());
        }
        return "/vacacion/index";
    }

    /**
     * Sets the "items" attribute with a collection of EmpleadoHorario entities
     * that are retrieved from Empleado?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for EmpleadoHorario page
     */
    public String navigateEmpleadoHorarioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EmpleadoHorario_items", this.getSelected().getEmpleadoHorarioList());
        }
        return "/empleadoHorario/index";
    }

    /**
     * Sets the "items" attribute with a collection of Usuario entities that are
     * retrieved from Empleado?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Usuario page
     */
    public String navigateUsuarioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Usuario_items", this.getSelected().getUsuarioList());
        }
        return "/usuario/index";
    }

    /**
     * Sets the "items" attribute with a collection of FichaLaboralEmpleado
     * entities that are retrieved from Empleado?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for FichaLaboralEmpleado page
     */
    public String navigateFichaLaboralEmpleadoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("FichaLaboralEmpleado_items", this.getSelected().getFichaLaboralEmpleadoList());
        }
        return "/fichaLaboralEmpleado/index";
    }

    @Override
    protected void edit(Empleado objeto) {
        this.empleadoFacade.edit(objeto);
        LOG.info("EMPLEADO GUARDADO");
//        if (this.esNuevo) {
//            Bitacora bitacora = new Bitacora();
//            //----Bitacora----
//            //Fecha y hora//          
//            Date fechas = new Date();//           
//            //Ip Cliente
//            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
//
//            String nombres = this.selected.getNombres();
//            String apellidos = this.selected.getApellidos();
//            String doc = this.selected.getDocIdentidad();
//            String fechaNacimiento = this.selected.getFechaNacimiento().toString();
//            String situacionTrabajador = this.selected.getSituacionTrabajador();
//            String sexo = this.selected.getSexo().toString();
//            String grupoHorario = this.selected.getGrupoHorarioId().getNombre();
//            String foto = this.selected.getFoto();
//            String jefe = this.selected.getEmpleadoId().getNombres() + this.selected.getEmpleadoId().getApellidos();
//            String servicio = this.selected.getServicioId().getNombre();
//
//            bitacora.setUsuario("JC");
//            bitacora.setIpCliente(ip_cliente);
//            bitacora.setFecha(fechas);
//            bitacora.setHora(fechas);
//            bitacora.setTabla("EMPLEADO");
//            bitacora.setColumna("NOMBRES");
//            bitacora.setAccion("CREAR");
//            bitacora.setValorAct(nombres);
//            bitacora.setValorAnt(" ");
//            bitacoraC.edit(bitacora);
//
//            bitacora.setColumna("APELLIDOS");
//            bitacora.setValorAct(apellidos);
//            bitacora.setValorAnt(" ");
//            bitacoraC.edit(bitacora);
//
//            bitacora.setColumna("DOC_IDENTIDAD");
//            bitacora.setValorAct(doc);
//            bitacora.setValorAnt(" ");
//            bitacoraC.edit(bitacora);
//
//            bitacora.setColumna("FECHA_NACIMIENTO");
//            bitacora.setValorAct(fechaNacimiento);
//            bitacora.setValorAnt(" ");
//            bitacoraC.edit(bitacora);
//
//            bitacora.setColumna("SITUACION_TRABAJADOR");
//            bitacora.setValorAct(situacionTrabajador);
//            bitacora.setValorAnt(" ");
//            bitacoraC.edit(bitacora);
//
//            bitacora.setColumna("SEXO");
//            bitacora.setValorAct(sexo);
//            bitacora.setValorAnt(" ");
//            bitacoraC.edit(bitacora);
//
//            bitacora.setColumna("GRUPO_HORARIO_ID");
//            bitacora.setValorAct(grupoHorario);
//            bitacora.setValorAnt(" ");
//            bitacoraC.edit(bitacora);
//
//            bitacora.setColumna("FOTO");
//            bitacora.setValorAct(foto);
//            bitacora.setValorAnt(" ");
//            bitacoraC.edit(bitacora);
//
//            bitacora.setColumna("EMPLEADO_ID");
//            bitacora.setValorAct(jefe);
//            bitacora.setValorAnt(" ");
//            bitacoraC.edit(bitacora);
//
//            bitacora.setColumna("SERVICIO_ID");
//            bitacora.setValorAct(servicio);
//            bitacora.setValorAnt(" ");
//            bitacoraC.edit(bitacora);
//
//        } else {
//            //Datos antes de modificar
//            Empleado antes = this.find(this.selected.getId());
//
//            String nombres1 = antes.getNombres();
//            String apellidos1 = antes.getApellidos();
//            String doc1 = antes.getDocIdentidad();
//            String fechaNacimiento1 = antes.getFechaNacimiento().toString();
//            String situacionTrabajador1 = antes.getSituacionTrabajador();
//            String sexo1 = antes.getSexo().toString();
//            String grupoHorario1 = antes.getGrupoHorarioId().getNombre();
//            String foto1 = antes.getFoto();
//            String jefe1 = antes.getEmpleadoId().getNombres() + antes.getEmpleadoId().getApellidos();
//            String servicio1 = antes.getServicioId().getNombre();
//
//            //Datos despues de modificar
//            String nombres2 = this.selected.getNombres();
//            String apellidos2 = this.selected.getApellidos();
//            String doc2 = this.selected.getDocIdentidad();
//            String fechaNacimiento2 = this.selected.getFechaNacimiento().toString();
//            String situacionTrabajador2 = this.selected.getSituacionTrabajador();
//            String sexo2 = this.selected.getSexo().toString();
//            String grupoHorario2 = this.selected.getGrupoHorarioId().getNombre();
//            String foto2 = this.selected.getFoto();
//            String jefe2 = this.selected.getEmpleadoId().getNombres() + this.selected.getEmpleadoId().getApellidos();
//            String servicio2 = this.selected.getServicioId().getNombre();
//
//            //----Bitacora----
//            Bitacora bitacora = new Bitacora();
//            //Fecha y hora//          
//            Date fechas = new Date();
////           
//            //Ip Cliente
//            String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
//
//            //Datos
//            bitacora.setUsuario("JC");
//            bitacora.setIpCliente(ip_cliente);
//            bitacora.setFecha(fechas);
//            bitacora.setHora(fechas);
//            bitacora.setTabla("EMPLEADO");
//            bitacora.setColumna("NOMBRES");
//            bitacora.setAccion("MODIFICAR");
//            bitacora.setValorAct(nombres2);
//            bitacora.setValorAnt(nombres1);
//
//            if (!nombres1.equals(nombres2)) {
//                bitacoraC.edit(bitacora);
//            }
//
//            bitacora.setColumna("APELLIDOS");
//            bitacora.setValorAct(apellidos2);
//            bitacora.setValorAnt(apellidos1);
//
//            if (!apellidos1.equals(apellidos2)) {
//                bitacoraC.edit(bitacora);
//            }
//
//            bitacora.setColumna("DOC_IDENTIDAD");
//            bitacora.setValorAct(doc2);
//            bitacora.setValorAnt(doc1);
//
//            if (!doc1.equals(doc2)) {
//                bitacoraC.edit(bitacora);
//            }
//
//            bitacora.setColumna("FECHA_NACIMIENTO");
//            bitacora.setValorAct(fechaNacimiento2);
//            bitacora.setValorAnt(fechaNacimiento1);
//
//            if (!fechaNacimiento1.equals(fechaNacimiento2)) {
//                bitacoraC.edit(bitacora);
//            }
//
//            bitacora.setColumna("SITUACION_TRABAJADOR");
//            bitacora.setValorAct(situacionTrabajador2);
//            bitacora.setValorAnt(situacionTrabajador1);
//
//            if (!situacionTrabajador1.equals(situacionTrabajador2)) {
//                bitacoraC.edit(bitacora);
//            }
//
//            bitacora.setColumna("SEXO");
//            bitacora.setValorAct(sexo2);
//            bitacora.setValorAnt(sexo1);
//
//            if (!sexo1.equals(sexo2)) {
//                bitacoraC.edit(bitacora);
//            }
//
//            bitacora.setColumna("GRUPO_HORARIO_ID");
//            bitacora.setValorAct(grupoHorario2);
//            bitacora.setValorAnt(grupoHorario1);
//
//            if (!grupoHorario1.equals(grupoHorario2)) {
//                bitacoraC.edit(bitacora);
//            }
//
//            bitacora.setColumna("FOTO");
//            bitacora.setValorAct(foto2);
//            bitacora.setValorAnt(foto1);
//
//            if (!foto1.equals(foto2)) {
//                bitacoraC.edit(bitacora);
//            }
//
//            bitacora.setColumna("EMPLEADO_ID");
//            bitacora.setValorAct(jefe2);
//            bitacora.setValorAnt(jefe1);
//
//            if (!jefe1.equals(jefe2)) {
//                bitacoraC.edit(bitacora);
//            }
//
//            bitacora.setColumna("SERVICIO_ID");
//            bitacora.setValorAct(servicio2);
//            bitacora.setValorAnt(servicio1);
//
//            if (!servicio1.equals(servicio2)) {
//                bitacoraC.edit(bitacora);
//            }
//        }
    }

    @Override
    protected void remove(Empleado objeto) {
        this.empleadoFacade.remove(objeto);
        LOG.info("EMPLEADO ELIMINADO");
//
//        Empleado antes = this.find(this.selected.getId());
//
//        String nombres1 = antes.getNombres();
//        String apellidos1 = antes.getApellidos();
//        String doc1 = antes.getDocIdentidad();
//        String fechaNacimiento1 = antes.getFechaNacimiento().toString();
//        String situacionTrabajador1 = antes.getSituacionTrabajador();
//        String sexo1 = antes.getSexo().toString();
//        String grupoHorario1 = antes.getGrupoHorarioId().getNombre();
//        String foto1 = antes.getFoto();
//        String jefe1 = antes.getEmpleadoId().getNombres() + antes.getEmpleadoId().getApellidos();
//        String servicio1 = antes.getServicioId().getNombre();
//
//        Bitacora bitacora = new Bitacora();
//        //Fecha y hora//          
//        Date fechas = new Date();
////           
//        //Ip Cliente
//        String ip_cliente = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
//
//        bitacora.setUsuario("JC");
//        bitacora.setIpCliente(ip_cliente);
//        bitacora.setFecha(fechas);
//        bitacora.setHora(fechas);
//        bitacora.setTabla("EMPLEADO");
//        bitacora.setColumna("NOMBRES");
//        bitacora.setAccion("ELIMINAR");
//        bitacora.setValorAct(" ");
//        bitacora.setValorAnt(nombres1);
//        bitacoraC.edit(bitacora);
//
//        bitacora.setColumna("APELLIDOS");
//        bitacora.setValorAct(" ");
//        bitacora.setValorAnt(apellidos1);
//        bitacoraC.edit(bitacora);
//        
//        bitacora.setColumna("DOC_IDENTIDAD");
//        bitacora.setValorAct(" ");
//        bitacora.setValorAnt(doc1);
//        bitacoraC.edit(bitacora);
//        
//        bitacora.setColumna("FECHA_NACIMIENTO");
//        bitacora.setValorAct(" ");
//        bitacora.setValorAnt(fechaNacimiento1);
//        bitacoraC.edit(bitacora);
//        
//        bitacora.setColumna("SITUACION_TRABAJADOR");
//        bitacora.setValorAct(" ");
//        bitacora.setValorAnt(situacionTrabajador1);
//        bitacoraC.edit(bitacora);
//        
//        bitacora.setColumna("SEXO");
//        bitacora.setValorAct(" ");
//        bitacora.setValorAnt(sexo1);
//        bitacoraC.edit(bitacora);
//        
//        bitacora.setColumna("GRUPO_HORARIO_ID");
//        bitacora.setValorAct(" ");
//        bitacora.setValorAnt(grupoHorario1);
//        bitacoraC.edit(bitacora);
//        
//        bitacora.setColumna("FOTO");
//        bitacora.setValorAct(" ");
//        bitacora.setValorAnt(foto1);
//        bitacoraC.edit(bitacora);
//        
//        bitacora.setColumna("EMPLEADO_ID");
//        bitacora.setValorAct(" ");
//        bitacora.setValorAnt(jefe1);
//        bitacoraC.edit(bitacora);
//        
//        bitacora.setColumna("SERVICIO_ID");
//        bitacora.setValorAct(" ");
//        bitacora.setValorAnt(servicio1);
//        bitacoraC.edit(bitacora);
        
    }

    @Override
    public Empleado find(Object id) {
        return this.empleadoFacade.find(id);
    }

    @Override
    public List<Empleado> getItems() {
        return this.empleadoFacade.findAll();
    }

    @Override
    public List<Empleado> search(String namedQuery) {
        return this.empleadoFacade.search(namedQuery);
    }

    @Override
    public List<Empleado> search(String namedQuery, Map<String, Object> parametros) {
        return this.empleadoFacade.search(namedQuery, parametros);
    }

    @Override
    public List<Empleado> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio) {
        return this.empleadoFacade.search(namedQuery, parametros, inicio, tamanio);
    }

    public List<Empleado> metodo(String parametro) {
        String query = "SELECT e FROM Empleado e WHERE CONCAT(e.nombres,e.apellidos,e.docIdentidad) LIKE CONCAT('%',:parametro,'%')";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("parametro", parametro.toUpperCase());
        return this.empleadoFacade.search(query, parametros);
    }

    public List<Empleado> metodoPorServicio(String parametro){
        String query = "SELECT e FROM Empleado e WHERE CONCAT(e.nombres,e.apellidos,e.docIdentidad) LIKE CONCAT('%',:parametro,'%') AND e.servicioId=:servicio";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("parametro", parametro.toUpperCase());
        parametros.put("servicio", servicioSeleccionado);
        return this.empleadoFacade.search(query, parametros);
    }
    @Override
    public Empleado prepareCreate(ActionEvent event) {
        Empleado empleado = new Empleado();

        empleado.setFichaGeneralEmpleadoList(new ArrayList<FichaGeneralEmpleado>());
        empleado.setFichaLaboralEmpleadoList(new ArrayList<FichaLaboralEmpleado>());

        fichaGeneralSeleccionada = new FichaGeneralEmpleado();
        fichaLaboralSeleccionada = new FichaLaboralEmpleado();

        empleado.getFichaGeneralEmpleadoList().add(fichaGeneralSeleccionada);
        empleado.getFichaLaboralEmpleadoList().add(fichaLaboralSeleccionada);

        fichaGeneralSeleccionada.setEmpleadoId(empleado);
        fichaLaboralSeleccionada.setEmpleadoId(empleado);

        this.setSelected(empleado);
        return empleado;
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
