/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RyuujiMD
 */
@Entity
@Table(name = "ficha_laboral_empleado")
@XmlRootElement
public class FichaLaboralEmpleado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Size(max = 45)
    @Column(name = "codigo_trabajador",nullable = false)    
    private String codigoTrabajador;
    @JoinColumn(name = "empleado_doc_identidad", referencedColumnName = "doc_identidad")
    @ManyToOne(optional = false)
    private Empleado empleadoId;
    @JoinColumn(name = "tipo_empleado_id", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private TipoEmpleado tipoEmpleadoId;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_contrato")
    private Date fechaContrato;

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }        

    public FichaLaboralEmpleado() {
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
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.codigoTrabajador);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FichaLaboralEmpleado other = (FichaLaboralEmpleado) obj;
        if (!Objects.equals(this.codigoTrabajador, other.codigoTrabajador)) {
            return false;
        }
        return true;
    }
    
    
}
