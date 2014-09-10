/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author RyuujiMD
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permiso.findAll", query = "SELECT p FROM Permiso p"),
    @NamedQuery(name = "Permiso.findById", query = "SELECT p FROM Permiso p WHERE p.id = :id"),
    @NamedQuery(name = "Permiso.findByHoraInicio", query = "SELECT p FROM Permiso p WHERE p.horaInicio = :horaInicio"),
    @NamedQuery(name = "Permiso.findByHoraFin", query = "SELECT p FROM Permiso p WHERE p.horaFin = :horaFin"),
    @NamedQuery(name = "Permiso.findByFechaInicio", query = "SELECT p FROM Permiso p WHERE p.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Permiso.findByFechaFin", query = "SELECT p FROM Permiso p WHERE p.fechaFin = :fechaFin"),
    @NamedQuery(name = "Permiso.findByTipo", query = "SELECT p FROM Permiso p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "Permiso.findByPorFecha", query = "SELECT p FROM Permiso p WHERE p.porFecha = :porFecha")})
public class Permiso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Column(name = "hora_fin")
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "por_fecha")
    private boolean porFecha;
    @JoinColumn(name = "motivo_permiso_codigo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private MotivoPermiso motivoPermisoCodigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "permisoId")
    private List<EmpleadoPermiso> empleadoPermisoList;
    @OneToMany(mappedBy = "permisoId")
    private List<RegistroAsistencia> registroList;

    public List<RegistroAsistencia> getRegistroList() {
        return registroList;
    }

    public void setRegistroList(List<RegistroAsistencia> registroList) {
        this.registroList = registroList;
    }

    public Permiso() {
    }

    public Permiso(Long id) {
        this.id = id;
    }

    public Permiso(Long id, String tipo, boolean porFecha) {
        this.id = id;
        this.tipo = tipo;
        this.porFecha = porFecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo.toUpperCase();
    }

    public boolean getPorFecha() {
        return porFecha;
    }

    public void setPorFecha(boolean porFecha) {
        this.porFecha = porFecha;
    }

    public MotivoPermiso getMotivoPermisoCodigo() {
        return motivoPermisoCodigo;
    }

    public void setMotivoPermisoCodigo(MotivoPermiso motivoPermisoCodigo) {
        this.motivoPermisoCodigo = motivoPermisoCodigo;
    }
    
    /*Metodo par trabajar las listas*/
    public EmpleadoPermiso getEmpleadoPermiso(){
        return this.empleadoPermisoList.get(0);
    }
    
    public void setEmpleadoPermiso(EmpleadoPermiso empleadoPermiso){
        this.empleadoPermisoList.set(0,empleadoPermiso);
    }
    
    @XmlTransient
    public List<EmpleadoPermiso> getEmpleadoPermisoList() {
        return empleadoPermisoList;
    }

    public void setEmpleadoPermisoList(List<EmpleadoPermiso> empleadoPermisoList) {
        this.empleadoPermisoList = empleadoPermisoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permiso)) {
            return false;
        }
        Permiso other = (Permiso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.jsica.ejb.entidades.Permiso[ id=" + id + " ]";
    }
    
}
