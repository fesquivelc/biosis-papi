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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author RyuujiMD
 */
@Entity
@Table(name = "empleado_permiso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpleadoPermiso.findAll", query = "SELECT e FROM EmpleadoPermiso e"),
    @NamedQuery(name = "EmpleadoPermiso.findById", query = "SELECT e FROM EmpleadoPermiso e WHERE e.id = :id")})
public class EmpleadoPermiso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @JoinColumn(name = "empleado_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Empleado empleadoId;
    @JoinColumn(name = "permiso_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Permiso permisoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoPermisoId")
    private List<Papeleta> papeletaList;

    public EmpleadoPermiso() {
    }

    public EmpleadoPermiso(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Empleado getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Empleado empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Permiso getPermisoId() {
        return permisoId;
    }

    public void setPermisoId(Permiso permisoId) {
        this.permisoId = permisoId;
    }

    /*Metodos para papeleta*/
    public Papeleta getPapeleta(){
        return this.papeletaList.get(0);
    }
    
    public void setPapeleta(Papeleta papeleta){
        this.papeletaList.set(0, papeleta);
    }
    @XmlTransient
    public List<Papeleta> getPapeletaList() {
        return papeletaList;
    }

    public void setPapeletaList(List<Papeleta> papeletaList) {
        this.papeletaList = papeletaList;
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
        if (!(object instanceof EmpleadoPermiso)) {
            return false;
        }
        EmpleadoPermiso other = (EmpleadoPermiso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.jsica.ejb.entidades.EmpleadoPermiso[ id=" + id + " ]";
    }
    
}
