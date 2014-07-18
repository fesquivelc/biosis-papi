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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
    @NamedQuery(name = "Ubigeo.findAll", query = "SELECT u FROM Ubigeo u"),
    @NamedQuery(name = "Ubigeo.findByCodigo", query = "SELECT u FROM Ubigeo u WHERE u.codigo = :codigo"),
    @NamedQuery(name = "Ubigeo.findByProvincia", query = "SELECT u FROM Ubigeo u WHERE u.provincia = :provincia"),
    @NamedQuery(name = "Ubigeo.findByDepartamento", query = "SELECT u FROM Ubigeo u WHERE u.departamento = :departamento"),
    @NamedQuery(name = "Ubigeo.findByDistrito", query = "SELECT u FROM Ubigeo u WHERE u.distrito = :distrito"),
    @NamedQuery(name = "Ubigeo.findByPais", query = "SELECT u FROM Ubigeo u WHERE u.pais = :pais")})
public class Ubigeo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String provincia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String departamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String distrito;
    @Size(max = 255)
    private String pais;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ubigeoCodigo")
    private List<FichaGeneralEmpleado> fichaGeneralEmpleadoList;

    public Ubigeo() {
    }

    public Ubigeo(String codigo) {
        this.codigo = codigo;
    }

    public Ubigeo(String codigo, String provincia, String departamento, String distrito) {
        this.codigo = codigo;
        this.provincia = provincia;
        this.departamento = departamento;
        this.distrito = distrito;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @XmlTransient
    public List<FichaGeneralEmpleado> getFichaGeneralEmpleadoList() {
        return fichaGeneralEmpleadoList;
    }

    public void setFichaGeneralEmpleadoList(List<FichaGeneralEmpleado> fichaGeneralEmpleadoList) {
        this.fichaGeneralEmpleadoList = fichaGeneralEmpleadoList;
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
        if (!(object instanceof Ubigeo)) {
            return false;
        }
        Ubigeo other = (Ubigeo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.jsica.ejb.entidades.Ubigeo[ codigo=" + codigo + " ]";
    }
    
}
