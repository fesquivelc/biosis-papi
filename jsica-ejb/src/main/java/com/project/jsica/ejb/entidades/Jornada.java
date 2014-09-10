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
    @NamedQuery(name = "Jornada.findAll", query = "SELECT j FROM Jornada j"),
    @NamedQuery(name = "Jornada.findByCodigo", query = "SELECT j FROM Jornada j WHERE j.codigo = :codigo"),
    @NamedQuery(name = "Jornada.findByNombre", query = "SELECT j FROM Jornada j WHERE j.nombre = :nombre"),
    @NamedQuery(name = "Jornada.findByHEntrada", query = "SELECT j FROM Jornada j WHERE j.hEntrada = :hEntrada"),
    @NamedQuery(name = "Jornada.findByHSalida", query = "SELECT j FROM Jornada j WHERE j.hSalida = :hSalida"),
    @NamedQuery(name = "Jornada.findByFlexible", query = "SELECT j FROM Jornada j WHERE j.flexible = :flexible")})
public class Jornada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String nombre;
    @Column(name = "h_entrada")
    @Temporal(TemporalType.TIME)
    private Date hEntrada;
    @Column(name = "h_salida")
    @Temporal(TemporalType.TIME)
    private Date hSalida;
    @Column(name = "termina_dia_siguiente")
    private boolean terminaDiaSiguiente;
    @Basic(optional = false)
    @NotNull
    private boolean flexible;
    @Basic(optional = false)
    @NotNull
    private boolean asistencial;
    @JoinColumn(name = "servicio_id", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private Servicio servicioId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jornadaCodigo")
    private List<DetalleHorario> detalleHorarioList;

    public Jornada() {
    }

    public Jornada(String codigo) {
        this.codigo = codigo;
    }

    public Jornada(String codigo, String nombre, boolean flexible) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.flexible = flexible;
    }

    public boolean isTerminaDiaSiguiente() {
        return terminaDiaSiguiente;
    }

    public void setTerminaDiaSiguiente(boolean terminaDiaSiguiente) {
        this.terminaDiaSiguiente = terminaDiaSiguiente;
    }
    
    
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo.toUpperCase();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    public Date getHEntrada() {
        return hEntrada;
    }

    public void setHEntrada(Date hEntrada) {
        this.hEntrada = hEntrada;
    }

    public Date getHSalida() {
        return hSalida;
    }

    public void setHSalida(Date hSalida) {
        this.hSalida = hSalida;
    }

    public boolean getFlexible() {
        return flexible;
    }

    public void setFlexible(boolean flexible) {
        this.flexible = flexible;
    }
    
    public boolean isAsistencial() {
        return asistencial;
    }

    public void setAsistencial(boolean asistencial) {
        this.asistencial = asistencial;
    }

     public Servicio getServicioId() {
        return servicioId;
    }

    public void setServicioId(Servicio servicioId) {
        this.servicioId = servicioId;
    }

    @XmlTransient
    public List<DetalleHorario> getDetalleHorarioList() {
        return detalleHorarioList;
    }

    public void setDetalleHorarioList(List<DetalleHorario> detalleHorarioList) {
        this.detalleHorarioList = detalleHorarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jornada)) {
            return false;
        }
        Jornada other = (Jornada) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.jsica.ejb.entidades.Jornada[ codigo=" + codigo + " ]";
    }
    
}
