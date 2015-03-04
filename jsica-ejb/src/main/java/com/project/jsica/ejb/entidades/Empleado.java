/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.jsica.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author RyuujiMD
 */
@Table(name = "empleado")
@Entity
@XmlRootElement
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String apellidos;
    @Id
    @Basic(optional = false)
    @NotNull
    @Pattern(regexp = "(\\d{1,8})", message = "Formato de DNI Invalido")
    @Size(min = 1, max = 45)
    @Column(name = "doc_identidad")
    private String docIdentidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    private Character sexo;
    @Size(max = 255)
    private String foto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    private List<RegistroAsistencia> registroAsistenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoId")
    private List<DetalleContrato> detalleContratoList;
    @JoinColumn(name = "grupo_horario_id", referencedColumnName = "id",nullable = true)
    @ManyToOne(optional = true)
    private GrupoHorario grupoHorarioId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoId")
    private List<Empleado> empleadoList;
    @JoinColumn(name = "empleado_doc_identidad", referencedColumnName = "doc_identidad")
    @ManyToOne(optional = true)
    private Empleado empleadoId;
//    @JoinColumn(name = "servicio_id", referencedColumnName = "id")
//    @ManyToOne(optional = true)
//    private Servicio servicioId;
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private Area area;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jefeInmediatoId")
    private List<CambioTurno> cambioTurnoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoIdempleado")
    private List<Suspension> suspensionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoId")
    private List<EmpleadoPermiso> empleadoPermisoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoId")
    private List<FichaGeneralEmpleado> fichaGeneralEmpleadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoIdempleado")
    private List<Papeleta> papeletaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoIdjefeInmediato")
    private List<Papeleta> papeletaList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoIdjefePersonal")
    private List<Papeleta> papeletaList2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoId")
    private List<Falta> faltaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoId")
    private List<Vacacion> vacacionList;
    @OneToMany(mappedBy = "empleadoId")
    private List<EmpleadoHorario> empleadoHorarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoId")
    private List<Usuario> usuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoId")
    private List<FichaLaboralEmpleado> fichaLaboralEmpleadoList;

    @OneToMany(mappedBy = "empleado2Id")
    private List<CambioTurno> cambioTurnoList2;
    @OneToMany(mappedBy = "empleado1Id")
    private List<CambioTurno> cambioTurnoList3;

    private Boolean activo;

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<CambioTurno> getCambioTurnoList2() {
        return cambioTurnoList2;
    }

    public void setCambioTurnoList2(List<CambioTurno> cambioTurnoList2) {
        this.cambioTurnoList2 = cambioTurnoList2;
    }

    @XmlTransient
    public List<CambioTurno> getCambioTurnoList3() {
        return cambioTurnoList3;
    }

    public void setCambioTurnoList3(List<CambioTurno> cambioTurnoList3) {
        this.cambioTurnoList3 = cambioTurnoList3;
    }

    public Empleado() {
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres.toUpperCase();
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos.toUpperCase();
    }

    public String getDocIdentidad() {
        return docIdentidad;
    }

    public void setDocIdentidad(String docIdentidad) {
        this.docIdentidad = docIdentidad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

//    public String getSituacionTrabajador() {
//        return situacionTrabajador;
//    }
//
//    public void setSituacionTrabajador(String situacionTrabajador) {
//        this.situacionTrabajador = situacionTrabajador.toUpperCase();
//    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getEmpleado() {
        return this.apellidos + " " + this.nombres + " - DNI:" + this.docIdentidad;
    }
    /*Para papeleta*/

    public String getEmpleadoForPapeleta() {
        return this.apellidos + " " + this.nombres + " - DNI:" + this.docIdentidad;
    }

    public Sucursal getSucursal() {
        Sucursal sucursal = this.detalleContratoList.get(0).getAreaId().getSucursalId();
        return sucursal;
    }
    
    
    public FichaGeneralEmpleado getFicha() {
        return this.fichaGeneralEmpleadoList.get(0);
    }

    public void setFicha(FichaGeneralEmpleado ficha) {
        this.fichaGeneralEmpleadoList.set(0, ficha);
    }

    public FichaLaboralEmpleado getFichaLaboral() {
        return this.fichaLaboralEmpleadoList.get(0);
    }

    public void setFichaLaboral(FichaLaboralEmpleado fichaLaboral) {
        this.fichaLaboralEmpleadoList.set(0, fichaLaboral);
    }

    @XmlTransient
    public List<RegistroAsistencia> getRegistroAsistenciaList() {
        return registroAsistenciaList;
    }

    public void setRegistroAsistenciaList(List<RegistroAsistencia> registroAsistenciaList) {
        this.registroAsistenciaList = registroAsistenciaList;
    }

    @XmlTransient
    public List<DetalleContrato> getDetalleContratoList() {
        return detalleContratoList;
    }

    public void setDetalleContratoList(List<DetalleContrato> detalleContratoList) {
        this.detalleContratoList = detalleContratoList;
    }

    public GrupoHorario getGrupoHorarioId() {
        return grupoHorarioId;
    }

    public void setGrupoHorarioId(GrupoHorario grupoHorarioId) {
        this.grupoHorarioId = grupoHorarioId;
    }

    @XmlTransient
    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    public Empleado getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Empleado empleadoId) {
        this.empleadoId = empleadoId;
    }

//    public Servicio getServicioId() {
//        return servicioId;
//    }
//
//    public void setServicioId(Servicio servicioId) {
//        this.servicioId = servicioId;
//    }

    @XmlTransient
    public List<CambioTurno> getCambioTurnoList() {
        return cambioTurnoList;
    }

    public void setCambioTurnoList(List<CambioTurno> cambioTurnoList) {
        this.cambioTurnoList = cambioTurnoList;
    }

    @XmlTransient
    public List<Suspension> getSuspensionList() {
        return suspensionList;
    }

    public void setSuspensionList(List<Suspension> suspensionList) {
        this.suspensionList = suspensionList;
    }

    @XmlTransient
    public List<EmpleadoPermiso> getEmpleadoPermisoList() {
        return empleadoPermisoList;
    }

    public void setEmpleadoPermisoList(List<EmpleadoPermiso> empleadoPermisoList) {
        this.empleadoPermisoList = empleadoPermisoList;
    }

    @XmlTransient
    public List<FichaGeneralEmpleado> getFichaGeneralEmpleadoList() {
        return fichaGeneralEmpleadoList;
    }

    public void setFichaGeneralEmpleadoList(List<FichaGeneralEmpleado> fichaGeneralEmpleadoList) {
        this.fichaGeneralEmpleadoList = fichaGeneralEmpleadoList;
    }

    @XmlTransient
    public List<Papeleta> getPapeletaList() {
        return papeletaList;
    }

    public void setPapeletaList(List<Papeleta> papeletaList) {
        this.papeletaList = papeletaList;
    }

    @XmlTransient
    public List<Papeleta> getPapeletaList1() {
        return papeletaList1;
    }

    public void setPapeletaList1(List<Papeleta> papeletaList1) {
        this.papeletaList1 = papeletaList1;
    }

    @XmlTransient
    public List<Papeleta> getPapeletaList2() {
        return papeletaList2;
    }

    public void setPapeletaList2(List<Papeleta> papeletaList2) {
        this.papeletaList2 = papeletaList2;
    }

    @XmlTransient
    public List<Falta> getFaltaList() {
        return faltaList;
    }

    public void setFaltaList(List<Falta> faltaList) {
        this.faltaList = faltaList;
    }

    @XmlTransient
    public List<Vacacion> getVacacionList() {
        return vacacionList;
    }

    public void setVacacionList(List<Vacacion> vacacionList) {
        this.vacacionList = vacacionList;
    }

    @XmlTransient
    public List<EmpleadoHorario> getEmpleadoHorarioList() {
        return empleadoHorarioList;
    }

    public void setEmpleadoHorarioList(List<EmpleadoHorario> empleadoHorarioList) {
        this.empleadoHorarioList = empleadoHorarioList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @XmlTransient
    public List<FichaLaboralEmpleado> getFichaLaboralEmpleadoList() {
        return fichaLaboralEmpleadoList;
    }

    public void setFichaLaboralEmpleadoList(List<FichaLaboralEmpleado> fichaLaboralEmpleadoList) {
        this.fichaLaboralEmpleadoList = fichaLaboralEmpleadoList;
    }
    
    public String getCodigo(){
        return this.getFichaLaboral().getCodigoTrabajador();
    }
    
    public String getNombreCompleto(){
        return this.apellidos + " " + this.nombres;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.docIdentidad);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empleado other = (Empleado) obj;
        if (!Objects.equals(this.docIdentidad, other.docIdentidad)) {
            return false;
        }
        return true;
    }
    
    
}
