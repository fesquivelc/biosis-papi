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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author RyuujiMD
 */
@Entity
@Table(name = "biometrico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Biometrico.findAll", query = "SELECT b FROM Biometrico b"),
    @NamedQuery(name = "Biometrico.findById", query = "SELECT b FROM Biometrico b WHERE b.id = :id"),
    @NamedQuery(name = "Biometrico.findByIpv4", query = "SELECT b FROM Biometrico b WHERE b.ipv4 = :ipv4"),
    @NamedQuery(name = "Biometrico.findByIpv6", query = "SELECT b FROM Biometrico b WHERE b.ipv6 = :ipv6"),
    @NamedQuery(name = "Biometrico.findByModelo", query = "SELECT b FROM Biometrico b WHERE b.modelo = :modelo"),
    @NamedQuery(name = "Biometrico.findByMarca", query = "SELECT b FROM Biometrico b WHERE b.marca = :marca")})
public class Biometrico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Size(max = 45)
    private String ipv4;
    @Size(max = 45)
    @Basic(optional = true)
    private String ipv6;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String modelo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String marca;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "biometricoId")
    private List<RegistroAsistencia> registroAsistenciaList;
    @JoinColumn(name = "sucursal_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Sucursal sucursalId;

    public Biometrico() {
    }

    public Biometrico(Long id) {
        this.id = id;
    }

    public Biometrico(Long id, String modelo, String marca) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getIpv6() {
        return ipv6;
    }

    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo.toUpperCase();
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca.toUpperCase();
    }

    @XmlTransient
    public List<RegistroAsistencia> getRegistroAsistenciaList() {
        return registroAsistenciaList;
    }

    public void setRegistroAsistenciaList(List<RegistroAsistencia> registroAsistenciaList) {
        this.registroAsistenciaList = registroAsistenciaList;
    }

    public Sucursal getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(Sucursal sucursalId) {
        this.sucursalId = sucursalId;
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
        if (!(object instanceof Biometrico)) {
            return false;
        }
        Biometrico other = (Biometrico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.jsica.ejb.entidades.Biometrico[ id=" + id + " ]";
    }
    
}
