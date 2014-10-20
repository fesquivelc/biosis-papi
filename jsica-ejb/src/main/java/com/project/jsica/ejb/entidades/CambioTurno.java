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
@Table(name = "cambio_turno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CambioTurno.findAll", query = "SELECT c FROM CambioTurno c"),
    @NamedQuery(name = "CambioTurno.findById", query = "SELECT c FROM CambioTurno c WHERE c.id = :id"),
    @NamedQuery(name = "CambioTurno.findByFechaPedido", query = "SELECT c FROM CambioTurno c WHERE c.fechaPedido = :fechaPedido"),
    @NamedQuery(name = "CambioTurno.findByHoraPedido", query = "SELECT c FROM CambioTurno c WHERE c.horaPedido = :horaPedido")})
public class CambioTurno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_pedido")
    @Temporal(TemporalType.DATE)
    private Date fechaPedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_pedido")
    @Temporal(TemporalType.TIME)
    private Date horaPedido;
    @JoinColumn(name = "detalle_horario_original", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DetalleHorario detalleHorarioOriginal;
    @JoinColumn(name = "detalle_horario_reemplazo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DetalleHorario detalleHorarioReemplazo;
    @JoinColumn(name = "jefe_inmediato_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Empleado jefeInmediatoId;
    
    @JoinColumn(name = "empleado2_id", referencedColumnName = "id")
    @ManyToOne
    private Empleado empleado2Id;
    @JoinColumn(name = "empleado1_id", referencedColumnName = "id")
    @ManyToOne
    private Empleado empleado1Id;

    public Empleado getEmpleado2Id() {
        return empleado2Id;
    }

    public void setEmpleado2Id(Empleado empleado2Id) {
        this.empleado2Id = empleado2Id;
    }

    public Empleado getEmpleado1Id() {
        return empleado1Id;
    }

    public void setEmpleado1Id(Empleado empleado1Id) {
        this.empleado1Id = empleado1Id;
    }

    public CambioTurno() {
    }

    public CambioTurno(Long id) {
        this.id = id;
    }

    public CambioTurno(Long id, Date fechaPedido, Date horaPedido) {
        this.id = id;
        this.fechaPedido = fechaPedido;
        this.horaPedido = horaPedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Date getHoraPedido() {
        return horaPedido;
    }

    public void setHoraPedido(Date horaPedido) {
        this.horaPedido = horaPedido;
    }

    public DetalleHorario getDetalleHorarioOriginal() {
        return detalleHorarioOriginal;
    }

    public void setDetalleHorarioOriginal(DetalleHorario detalleHorarioOriginal) {
        this.detalleHorarioOriginal = detalleHorarioOriginal;
    }

    public DetalleHorario getDetalleHorarioReemplazo() {
        return detalleHorarioReemplazo;
    }

    public void setDetalleHorarioReemplazo(DetalleHorario detalleHorarioReemplazo) {
        this.detalleHorarioReemplazo = detalleHorarioReemplazo;
    }

    public Empleado getJefeInmediatoId() {
        return jefeInmediatoId;
    }

    public void setJefeInmediatoId(Empleado jefeInmediatoId) {
        this.jefeInmediatoId = jefeInmediatoId;
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
        if (!(object instanceof CambioTurno)) {
            return false;
        }
        CambioTurno other = (CambioTurno) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.jsica.ejb.entidades.CambioTurno[ id=" + id + " ]";
    }
    
}
