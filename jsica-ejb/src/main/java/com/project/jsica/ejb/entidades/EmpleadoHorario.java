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
@Table(name = "empleado_horario")
@XmlRootElement
public class EmpleadoHorario implements Serializable {
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
    @Column(name = "por_grupo")
    private boolean porGrupo;
    @JoinColumn(name = "empleado_doc_identidad", referencedColumnName = "doc_identidad")
    @ManyToOne
    private Empleado empleadoId;
    @JoinColumn(name = "horario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Horario horarioId;
    @JoinColumn(name = "grupo_horario_id", referencedColumnName = "id")
    @ManyToOne
    private GrupoHorario grupoHorarioId;

    public EmpleadoHorario() {
    }

    public EmpleadoHorario(Long id) {
        this.id = id;
    }

    public EmpleadoHorario(Long id, Date fecha, boolean porGrupo) {
        this.id = id;
        this.fecha = fecha;
        this.porGrupo = porGrupo;
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

    public boolean getPorGrupo() {
        return porGrupo;
    }

    public void setPorGrupo(boolean porGrupo) {
        this.porGrupo = porGrupo;
    }

    public Empleado getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Empleado empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Horario getHorarioId() {
        return horarioId;
    }

    public void setHorarioId(Horario horarioId) {
        this.horarioId = horarioId;
    }

    public GrupoHorario getGrupoHorarioId() {
        return grupoHorarioId;
    }

    public void setGrupoHorarioId(GrupoHorario grupoHorarioId) {
        this.grupoHorarioId = grupoHorarioId;
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
        if (!(object instanceof EmpleadoHorario)) {
            return false;
        }
        EmpleadoHorario other = (EmpleadoHorario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EmpleadoHorario{" + "id=" + id + ", fecha=" + fecha + ", porGrupo=" + porGrupo + ", empleadoId=" + empleadoId + ", horarioId=" + horarioId + ", grupoHorarioId=" + grupoHorarioId + '}';
    }
    
    
}
