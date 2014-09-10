/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RyuujiMD
 */
@Entity
@Table(name = "registro_asistencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroAsistencia.findAll", query = "SELECT r FROM RegistroAsistencia r"),
    @NamedQuery(name = "RegistroAsistencia.findById", query = "SELECT r FROM RegistroAsistencia r WHERE r.id = :id"),
    @NamedQuery(name = "RegistroAsistencia.findByFecha", query = "SELECT r FROM RegistroAsistencia r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "RegistroAsistencia.findByHora", query = "SELECT r FROM RegistroAsistencia r WHERE r.hora = :hora")})
public class RegistroAsistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIME)
    private Date hora;
    @JoinColumn(name = "biometrico_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Biometrico biometricoId;
    @JoinColumn(name = "empleado_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Empleado empleadoId;
    @Column(name = "e_o_s")
    private Boolean eOS;
    private String tipo;
    @JoinColumn(name = "turno_original", referencedColumnName = "id")
    @ManyToOne
    private DetalleHorario turnoOriginal;
    @JoinColumn(name = "turno_reemplazo", referencedColumnName = "id")
    @ManyToOne
    private DetalleHorario turnoReemplazo;
    @JoinColumn(name = "permiso_id", referencedColumnName = "id")
    @ManyToOne
    private Permiso permisoId;
    

    public Boolean iseOS() {
        return eOS;
    }

    public void seteOS(Boolean eOS) {
        this.eOS = eOS;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public DetalleHorario getTurnoOriginal() {
        return turnoOriginal;
    }

    public void setTurnoOriginal(DetalleHorario turnoOriginal) {
        this.turnoOriginal = turnoOriginal;
    }

    public DetalleHorario getTurnoReemplazo() {
        return turnoReemplazo;
    }

    public void setTurnoReemplazo(DetalleHorario turnoReemplazo) {
        this.turnoReemplazo = turnoReemplazo;
    }

    public Permiso getPermisoId() {
        return permisoId;
    }

    public void setPermisoId(Permiso permisoId) {
        this.permisoId = permisoId;
    }
    
    

    public RegistroAsistencia() {
    }

    public RegistroAsistencia(Long id) {
        this.id = id;
    }

    public RegistroAsistencia(Long id, Date fecha, Date hora) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Biometrico getBiometricoId() {
        return biometricoId;
    }

    public void setBiometricoId(Biometrico biometricoId) {
        this.biometricoId = biometricoId;
    }

    public Empleado getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Empleado empleadoId) {
        this.empleadoId = empleadoId;
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
        if (!(object instanceof RegistroAsistencia)) {
            return false;
        }
        RegistroAsistencia other = (RegistroAsistencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.jsica.ejb.entidades.RegistroAsistencia[ id=" + id + " ]";
    }
    
}
