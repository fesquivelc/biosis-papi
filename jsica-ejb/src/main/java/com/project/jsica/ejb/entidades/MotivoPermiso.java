/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author RyuujiMD
 */
@Entity
@Table(name = "motivo_permiso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MotivoPermiso.findAll", query = "SELECT m FROM MotivoPermiso m"),
    @NamedQuery(name = "MotivoPermiso.findByCodigo", query = "SELECT m FROM MotivoPermiso m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "MotivoPermiso.findByNombre", query = "SELECT m FROM MotivoPermiso m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MotivoPermiso.findByDescripcion", query = "SELECT m FROM MotivoPermiso m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "MotivoPermiso.findByConGoce", query = "SELECT m FROM MotivoPermiso m WHERE m.conGoce = :conGoce")})
public class MotivoPermiso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    private String codigo; // VAC : VACACIONES ; LIC = LICENCIAS
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String nombre;
    @Size(max = 255)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "con_goce")
    private boolean conGoce;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "motivoPermisoCodigo")
    private List<Permiso> permisoList;

    public MotivoPermiso() {
    }

    public MotivoPermiso(String codigo) {
        this.codigo = codigo;
    }

    public MotivoPermiso(String codigo, String nombre, boolean conGoce) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.conGoce = conGoce;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion.toUpperCase();
    }

    public boolean getConGoce() {
        return conGoce;
    }

    public void setConGoce(boolean conGoce) {
        this.conGoce = conGoce;
    }

    @XmlTransient
    public List<Permiso> getPermisoList() {
        return permisoList;
    }

    public void setPermisoList(List<Permiso> permisoList) {
        this.permisoList = permisoList;
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
        if (!(object instanceof MotivoPermiso)) {
            return false;
        }
        MotivoPermiso other = (MotivoPermiso) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.jsica.ejb.entidades.MotivoPermiso[ codigo=" + codigo + " ]";
    }
    
}
