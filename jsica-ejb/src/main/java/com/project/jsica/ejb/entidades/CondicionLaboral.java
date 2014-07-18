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
@Table(name = "condicion_laboral")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CondicionLaboral.findAll", query = "SELECT c FROM CondicionLaboral c"),
    @NamedQuery(name = "CondicionLaboral.findById", query = "SELECT c FROM CondicionLaboral c WHERE c.id = :id"),
    @NamedQuery(name = "CondicionLaboral.findByCodigo", query = "SELECT c FROM CondicionLaboral c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "CondicionLaboral.findByNombre", query = "SELECT c FROM CondicionLaboral c WHERE c.nombre = :nombre")})
public class CondicionLaboral implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    private int codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "condicionLaboralId")
    private List<Contrato> contratoList;

    public CondicionLaboral() {
    }

    public CondicionLaboral(Long id) {
        this.id = id;
    }

    public CondicionLaboral(Long id, int codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Contrato> getContratoList() {
        return contratoList;
    }

    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
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
        if (!(object instanceof CondicionLaboral)) {
            return false;
        }
        CondicionLaboral other = (CondicionLaboral) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.jsica.ejb.entidades.CondicionLaboral[ id=" + id + " ]";
    }
    
}
