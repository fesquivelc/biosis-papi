/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.entidades;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RyuujiMD
 */
@Table(name = "papeleta")
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Papeleta.findAll", query = "SELECT p FROM Papeleta p"),
    @NamedQuery(name = "Papeleta.findById", query = "SELECT p FROM Papeleta p WHERE p.id = :id"),
    @NamedQuery(name = "Papeleta.findByCodigo", query = "SELECT p FROM Papeleta p WHERE p.codigo = :codigo")})
public class Papeleta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Size(max = 45)
    private String codigo;
    @JoinColumn(name = "solicitante_doc_identidad", referencedColumnName = "doc_identidad")
    @ManyToOne(optional = false)
    private Empleado empleadoIdempleado;
    @JoinColumn(name = "jefe_doc_identidad", referencedColumnName = "doc_identidad")
    @ManyToOne(optional = false)
    private Empleado empleadoIdjefeInmediato;
    @JoinColumn(name = "personal_doc_identidad", referencedColumnName = "doc_identidad")
    @ManyToOne(optional = false)
    private Empleado empleadoIdjefePersonal;
    @JoinColumn(name = "empleado_permiso_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EmpleadoPermiso empleadoPermisoId;

    public Papeleta() {
    }

    public Papeleta(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo.toUpperCase();
    }

    public Empleado getEmpleadoIdempleado() {
        return empleadoIdempleado;
    }

    public void setEmpleadoIdempleado(Empleado empleadoIdempleado) {
        this.empleadoIdempleado = empleadoIdempleado;
    }

    public Empleado getEmpleadoIdjefeInmediato() {
        return empleadoIdjefeInmediato;
    }

    public void setEmpleadoIdjefeInmediato(Empleado empleadoIdjefeInmediato) {
        this.empleadoIdjefeInmediato = empleadoIdjefeInmediato;
    }

    public Empleado getEmpleadoIdjefePersonal() {
        return empleadoIdjefePersonal;
    }

    public void setEmpleadoIdjefePersonal(Empleado empleadoIdjefePersonal) {
        this.empleadoIdjefePersonal = empleadoIdjefePersonal;
    }

    public EmpleadoPermiso getEmpleadoPermisoId() {
        return empleadoPermisoId;
    }

    public void setEmpleadoPermisoId(EmpleadoPermiso empleadoPermisoId) {
        this.empleadoPermisoId = empleadoPermisoId;
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
        if (!(object instanceof Papeleta)) {
            return false;
        }
        Papeleta other = (Papeleta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.jsica.ejb.entidades.Papeleta[ id=" + id + " ]";
    }
    
}
