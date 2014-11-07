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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author fesquivelc
 */
@Entity
@Table(name = "registro_asistencia_2")
public class RegistroAsistencia2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "turno_original", referencedColumnName = "id", nullable = true)
    @ManyToOne
    private DetalleHorario turnoOriginal;
    @JoinColumn(name = "turno_reemplazo", referencedColumnName = "id", nullable = true)
    @ManyToOne
    private DetalleHorario turnoReemplazo;
    @Column(length = 1)
    private String tipo; // R = Regular, F = Falta, T = Tardanza, P = permiso, V = vacacion, L = licencia
    private Integer suma;
    @Column(name = "turno_terminado")
    private Boolean turnoTerminado; //determina si ya ha sido analizado en su totalidad
    @Column(name = "milisegundos_tardanza")
    private Long milisegundosTardanza;
    @Column(name = "milisegundos_extra")
    private Long milisegundosExtra;
    @JoinColumn(name = "empleado_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Empleado empleado;
    @JoinColumn(name = "empleado_permiso_id", referencedColumnName = "id", nullable = true)
    @ManyToOne
    private EmpleadoPermiso permiso;
    @JoinColumn(name = "feriado_id", referencedColumnName = "id", nullable = true)
    @ManyToOne
    private Feriado feriado;
    
    @OneToMany(mappedBy = "registroAsistencia",cascade = CascadeType.ALL)
    private List<DetalleRegistroAsistencia> detalleRegistroAsistenciaList;

    public List<DetalleRegistroAsistencia> getDetalleRegistroAsistenciaList() {
        return detalleRegistroAsistenciaList;
    }

    public void setDetalleRegistroAsistenciaList(List<DetalleRegistroAsistencia> detalleRegistroAsistenciaList) {
        this.detalleRegistroAsistenciaList = detalleRegistroAsistenciaList;
    }

    public EmpleadoPermiso getPermiso() {
        return permiso;
    }

    public void setPermiso(EmpleadoPermiso permiso) {
        this.permiso = permiso;
    }

    public Feriado getFeriado() {
        return feriado;
    }

    public void setFeriado(Feriado feriado) {
        this.feriado = feriado;
    }

    public Long getId() {
        return id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getSuma() {
        return suma;
    }

    public void setSuma(Integer suma) {
        this.suma = suma;
    }

    public Boolean getTurnoTerminado() {
        return turnoTerminado;
    }

    public void setTurnoTerminado(Boolean turnoTerminado) {
        this.turnoTerminado = turnoTerminado;
    }

    public Long getMilisegundosTardanza() {
        return milisegundosTardanza;
    }

    public void setMilisegundosTardanza(Long milisegundosTardanza) {
        this.milisegundosTardanza = milisegundosTardanza;
    }

    public Long getMilisegundosExtra() {
        return milisegundosExtra;
    }

    public void setMilisegundosExtra(Long milisegundosExtra) {
        this.milisegundosExtra = milisegundosExtra;
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
        if (!(object instanceof RegistroAsistencia2)) {
            return false;
        }
        RegistroAsistencia2 other = (RegistroAsistencia2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.jsica.ejb.entidades.RegistroAsistencia2[ id=" + id + " ]";
    }

}
