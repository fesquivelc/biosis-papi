/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.entidades;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RyuujiMD
 */
@Entity
@Table(name = "ficha_laboral_empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FichaLaboralEmpleado.findAll", query = "SELECT f FROM FichaLaboralEmpleado f"),
    @NamedQuery(name = "FichaLaboralEmpleado.findById", query = "SELECT f FROM FichaLaboralEmpleado f WHERE f.id = :id"),
    @NamedQuery(name = "FichaLaboralEmpleado.findByCodigoTrabajador", query = "SELECT f FROM FichaLaboralEmpleado f WHERE f.codigoTrabajador = :codigoTrabajador")})
public class FichaLaboralEmpleado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Size(max = 45)
    @Column(name = "codigo_trabajador",nullable = false)    
    private String codigoTrabajador;
    @JoinColumn(name = "empleado_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Empleado empleadoId;
    @JoinColumn(name = "tipo_empleado_id", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private TipoEmpleado tipoEmpleadoId;

    public FichaLaboralEmpleado() {
    }

    public FichaLaboralEmpleado(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoTrabajador() {
        return codigoTrabajador;
    }

    public void setCodigoTrabajador(String codigoTrabajador) {
        this.codigoTrabajador = codigoTrabajador.toUpperCase();
    }

    public Empleado getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Empleado empleadoId) {
        this.empleadoId = empleadoId;
    }

    public TipoEmpleado getTipoEmpleadoId() {
        return tipoEmpleadoId;
    }

    public void setTipoEmpleadoId(TipoEmpleado tipoEmpleadoId) {
        this.tipoEmpleadoId = tipoEmpleadoId;
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
        if (!(object instanceof FichaLaboralEmpleado)) {
            return false;
        }
        FichaLaboralEmpleado other = (FichaLaboralEmpleado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.jsica.ejb.entidades.FichaLaboralEmpleado[ id=" + id + " ]";
    }
    
}
