/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RyuujiMD
 */
@Entity
@Table(name = "feriados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feriado.findAll", query = "SELECT f FROM Feriado f"),
    @NamedQuery(name = "Feriado.findByIdferiados", query = "SELECT f FROM Feriado f WHERE f.idferiados = :idferiados"),
    @NamedQuery(name = "Feriado.findByNombre", query = "SELECT f FROM Feriado f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "Feriado.findByFecha", query = "SELECT f FROM Feriado f WHERE f.fecha = :fecha")})
public class Feriado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long idferiados;
    @Size(max = 45)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "anio_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Anio anioId;

    public Feriado() {
    }

    public Feriado(Long idferiados) {
        this.idferiados = idferiados;
    }

    public Feriado(Long idferiados, Date fecha) {
        this.idferiados = idferiados;
        this.fecha = fecha;
    }

    public Long getIdferiados() {
        return idferiados;
    }

    public void setIdferiados(Long idferiados) {
        this.idferiados = idferiados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Anio getAnioId() {
        return anioId;
    }

    public void setAnioId(Anio anioId) {
        this.anioId = anioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idferiados != null ? idferiados.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feriado)) {
            return false;
        }
        Feriado other = (Feriado) object;
        if ((this.idferiados == null && other.idferiados != null) || (this.idferiados != null && !this.idferiados.equals(other.idferiados))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.jsica.ejb.entidades.Feriado[ idferiados=" + idferiados + " ]";
    }
    
}
