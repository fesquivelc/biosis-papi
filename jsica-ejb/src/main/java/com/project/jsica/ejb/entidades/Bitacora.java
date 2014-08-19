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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Documentos
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bitacora.findAll", query = "SELECT b FROM Bitacora b"),
    @NamedQuery(name = "Bitacora.findById", query = "SELECT b FROM Bitacora b WHERE b.id = :id"),
    @NamedQuery(name = "Bitacora.findByIpCliente", query = "SELECT b FROM Bitacora b WHERE b.ipCliente = :ipCliente"),
    @NamedQuery(name = "Bitacora.findByUsuario", query = "SELECT b FROM Bitacora b WHERE b.usuario = :usuario"),
    @NamedQuery(name = "Bitacora.findByFecha", query = "SELECT b FROM Bitacora b WHERE b.fecha = :fecha"),
    @NamedQuery(name = "Bitacora.findByHora", query = "SELECT b FROM Bitacora b WHERE b.hora = :hora"),
    @NamedQuery(name = "Bitacora.findByTabla", query = "SELECT b FROM Bitacora b WHERE b.tabla = :tabla"),
    @NamedQuery(name = "Bitacora.findByColumna", query = "SELECT b FROM Bitacora b WHERE b.columna = :columna"),
    @NamedQuery(name = "Bitacora.findByAccion", query = "SELECT b FROM Bitacora b WHERE b.accion = :accion"),
    @NamedQuery(name = "Bitacora.findByValorAnt", query = "SELECT b FROM Bitacora b WHERE b.valorAnt = :valorAnt"),
    @NamedQuery(name = "Bitacora.findByValorAct", query = "SELECT b FROM Bitacora b WHERE b.valorAct = :valorAct")})
public class Bitacora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @NotNull
    @Size(min = 1, max = 255)
    private String ipCliente;
    @NotNull
    @Size(min = 1, max = 255)
    private String usuario;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @NotNull
    @Temporal(TemporalType.TIME)
    private Date hora;
    @NotNull
    @Size(min = 1, max = 255)
    private String tabla;
    @NotNull
    @Size(min = 1, max = 255)
    private String columna;
    @NotNull
    @Size(min = 1, max = 255)
    private String accion;
    @Size(min = 1, max = 255)
    private String valorAnt;
    @Size(min = 1, max = 255)
    private String valorAct;

    public Bitacora() {
    }

    public Bitacora(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public void setIpCliente(String ipCliente) {
        this.ipCliente = ipCliente;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario.toUpperCase();
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
        
    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla.toUpperCase();
    }

    public String getColumna() {
        return columna;
    }

    public void setColumna(String columna) {
        this.columna = columna.toUpperCase();
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion.toUpperCase();
    }

    public String getValorAnt() {
        return valorAnt;
    }

    public void setValorAnt(String valorAnt) {
        this.valorAnt = valorAnt.toUpperCase();
    }

    public String getValorAct() {
        return valorAct;
    }

    public void setValorAct(String valorAct) {
        this.valorAct = valorAct.toUpperCase();
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
        if (!(object instanceof Bitacora)) {
            return false;
        }
        Bitacora other = (Bitacora) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.project.jsica.ejb.entidades.Bitacora[ id=" + id + " ]";
    }
    
}
