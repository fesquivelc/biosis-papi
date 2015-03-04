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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RyuujiMD
 */
@Entity
@Table(name = "ficha_general_empleado")
@XmlRootElement
public class FichaGeneralEmpleado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Size(max = 10)
    @Column(name = "telefono_fijo")
    private String telefonoFijo;
    @Size(max = 10)
    @Column(name = "telefono_celular")
    private String telefonoCelular;
    @Size(max = 255)
    private String direccion;
    @Size(max = 4)
    @Column(name = "direccion_tipo_zona")
    private String direccionTipoZona;
    @Size(max = 4)
    @Column(name = "direccion_tipo_via")
    private String direccionTipoVia;
    @Size(max = 2)
    @Column(name = "estado_civil")
    private String estadoCivil;
    //@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="Formato de email Invalido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    private String email;
    @JoinColumn(name = "empleado_doc_identidad", referencedColumnName = "doc_identidad")
    @ManyToOne(optional = false)
    private Empleado empleadoId;
    @JoinColumn(name = "ubigeo_codigo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Ubigeo ubigeoCodigo;
    @JoinColumn(name = "nivel_educativo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private NivelEducativo nivelEducativoId;

    public FichaGeneralEmpleado() {
    }

    public FichaGeneralEmpleado(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo.toUpperCase();
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular.toUpperCase();
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion.toUpperCase();
    }

    public String getDireccionTipoZona() {
        return direccionTipoZona;
    }

    public void setDireccionTipoZona(String direccionTipoZona) {
        this.direccionTipoZona = direccionTipoZona.toUpperCase();
    }

    public String getDireccionTipoVia() {
        return direccionTipoVia;
    }

    public void setDireccionTipoVia(String direccionTipoVia) {
        this.direccionTipoVia = direccionTipoVia.toUpperCase();
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil.toUpperCase();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Empleado getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Empleado empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Ubigeo getUbigeoCodigo() {
        return ubigeoCodigo;
    }

    public void setUbigeoCodigo(Ubigeo ubigeoCodigo) {
        this.ubigeoCodigo = ubigeoCodigo;
    }

    public NivelEducativo getNivelEducativoId() {
        return nivelEducativoId;
    }

    public void setNivelEducativoId(NivelEducativo nivelEducativoId) {
        this.nivelEducativoId = nivelEducativoId;
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
        if (!(object instanceof FichaGeneralEmpleado)) {
            return false;
        }
        FichaGeneralEmpleado other = (FichaGeneralEmpleado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.jsica.ejb.entidades.FichaGeneralEmpleado[ id=" + id + " ]";
    }
    
}
