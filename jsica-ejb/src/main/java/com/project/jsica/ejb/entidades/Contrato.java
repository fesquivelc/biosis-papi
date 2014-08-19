/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c"),
    @NamedQuery(name = "Contrato.findById", query = "SELECT c FROM Contrato c WHERE c.id = :id"),
    @NamedQuery(name = "Contrato.findByFechaInicio", query = "SELECT c FROM Contrato c WHERE c.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Contrato.findByFechaFin", query = "SELECT c FROM Contrato c WHERE c.fechaFin = :fechaFin"),
    @NamedQuery(name = "Contrato.findByCodigo", query = "SELECT c FROM Contrato c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Contrato.findBySueldoBasico", query = "SELECT c FROM Contrato c WHERE c.sueldoBasico = :sueldoBasico")})
public class Contrato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Size(max = 45)
    private String codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sueldo_basico")
    private Double sueldoBasico;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contratoId")
    private List<DetalleContrato> detalleContratoList;
    @JoinColumn(name = "condicion_laboral_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CondicionLaboral condicionLaboralId;
    @JoinColumn(name = "regimen_laboral_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RegimenLaboral regimenLaboralId;
    @JoinColumn(name = "anio_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Anio anioId;
    @JoinColumn(name = "tipo_contrato_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoContrato tipoContratoId;
    @JoinColumn(name = "ocupacion_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Ocupacion ocupacionId;

    public Contrato() {
    }

    public Contrato(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo.toUpperCase();
    }

    public Double getSueldoBasico() {
        return sueldoBasico;
    }

    public void setSueldoBasico(Double sueldoBasico) {
        this.sueldoBasico = sueldoBasico;
    }

    @XmlTransient
    public List<DetalleContrato> getDetalleContratoList() {
        return detalleContratoList;
    }

    public void setDetalleContratoList(List<DetalleContrato> detalleContratoList) {
        this.detalleContratoList = detalleContratoList;
    }

    public CondicionLaboral getCondicionLaboralId() {
        return condicionLaboralId;
    }

    public void setCondicionLaboralId(CondicionLaboral condicionLaboralId) {
        this.condicionLaboralId = condicionLaboralId;
    }

    public RegimenLaboral getRegimenLaboralId() {
        return regimenLaboralId;
    }

    public void setRegimenLaboralId(RegimenLaboral regimenLaboralId) {
        this.regimenLaboralId = regimenLaboralId;
    }

    public Anio getAnioId() {
        return anioId;
    }

    public void setAnioId(Anio anioId) {
        this.anioId = anioId;
    }

    public TipoContrato getTipoContratoId() {
        return tipoContratoId;
    }

    public void setTipoContratoId(TipoContrato tipoContratoId) {
        this.tipoContratoId = tipoContratoId;
    }

    public Ocupacion getOcupacionId() {
        return ocupacionId;
    }

    public void setOcupacionId(Ocupacion ocupacionId) {
        this.ocupacionId = ocupacionId;
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
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.jsica.ejb.entidades.Contrato[ id=" + id + " ]";
    }
    
}
