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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RyuujiMD
 */
@Entity
@Table(name = "vacaciones")
@XmlRootElement
public class Vacacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long idvacaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dias_restantes")
    private int diasRestantes;
    @JoinColumn(name = "anio_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Anio anioId;
    @JoinColumn(name = "empleado_doc_identidad", referencedColumnName = "doc_identidad")
    @ManyToOne(optional = false)
    private Empleado empleadoId;
    @Column(name = "lunes_viernes")
    private int lunesViernes;
    @Column(name = "sabados")
    private int sabados;
    @Column(name = "domingos")
    private int domingos;

    public Vacacion() {
    }

    public int getLunesViernes() {
        return lunesViernes;
    }

    public void setLunesViernes(int lunesViernes) {
        this.lunesViernes = lunesViernes;
    }

    public int getSabados() {
        return sabados;
    }

    public void setSabados(int sabados) {
        this.sabados = sabados;
    }

    public int getDomingos() {
        return domingos;
    }

    public void setDomingos(int domingos) {
        this.domingos = domingos;
    }

    public Vacacion(Long idvacaciones) {
        this.idvacaciones = idvacaciones;
    }

    public Vacacion(Long idvacaciones, int diasRestantes) {
        this.idvacaciones = idvacaciones;
        this.diasRestantes = diasRestantes;
    }

    public Long getIdvacaciones() {
        return idvacaciones;
    }

    public void setIdvacaciones(Long idvacaciones) {
        this.idvacaciones = idvacaciones;
    }

    public int getDiasRestantes() {
        return diasRestantes;
    }

    public void setDiasRestantes(int diasRestantes) {
        this.diasRestantes = diasRestantes;
    }

    public Anio getAnioId() {
        return anioId;
    }

    public void setAnioId(Anio anioId) {
        this.anioId = anioId;
    }

    public Empleado getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Empleado empleadoId) {
        this.empleadoId = empleadoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvacaciones != null ? idvacaciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vacacion)) {
            return false;
        }
        Vacacion other = (Vacacion) object;
        if ((this.idvacaciones == null && other.idvacaciones != null) || (this.idvacaciones != null && !this.idvacaciones.equals(other.idvacaciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.jsica.ejb.entidades.Vacacion[ idvacaciones=" + idvacaciones + " ]";
    }
    
}
