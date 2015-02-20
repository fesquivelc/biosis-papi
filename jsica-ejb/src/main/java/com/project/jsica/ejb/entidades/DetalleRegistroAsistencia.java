package com.project.jsica.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "detalle_registro_asistencia")
@XmlRootElement
public class DetalleRegistroAsistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio")
    private Date fechaInicio;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_fin")
    private Date fechaFin;
    
    @Column(name = "orden")
    private int orden;

    private char resultado; //OPCIONES: T = TARDANZA; F = FALTA; R = REGULAR
    
    @Temporal(TemporalType.TIME)
    @Column(nullable = true,name = "hora_inicio")
    private Date horaInicio;
    
    @Temporal(TemporalType.TIME)
    @Column(nullable = true, name = "hora_fin")
    private Date horaFin;
    
    @Column(name = "tipo_registro", length = 1) // P = Permiso x hora, R = refrigerio, T = turno
    private char tipoRegistro;
    
    @JoinColumn(name = "empleado_permiso_id", referencedColumnName = "id", nullable = true)
    @ManyToOne
    private EmpleadoPermiso permiso;
    
    
    @Column(name = "milisegundos_tardanza")
    private Long milisegundosTardanza;
    
    @Column(name = "milisegundos_extra")
    private Long milisegundosExtra;

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
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

    public char getResultado() {
        return resultado;
    }

    public void setResultado(char resultado) {
        this.resultado = resultado;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public char getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(char tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public Long getMilisegundosExtra() {
        return milisegundosExtra;
    }

    public void setMilisegundosExtra(Long milisegundosExtra) {
        this.milisegundosExtra = milisegundosExtra;
    }

    public Long getMilisegundosTardanza() {
        return milisegundosTardanza;
    }

    public void setMilisegundosTardanza(Long milisegundosTardanza) {
        this.milisegundosTardanza = milisegundosTardanza;
    }    
    
    public EmpleadoPermiso getPermiso() {
        return permiso;
    }

    public void setPermiso(EmpleadoPermiso permiso) {
        this.permiso = permiso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
    @JoinColumn(name = "registro_asistencia_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(targetEntity = RegistroAsistencia.class, optional = false)
    private RegistroAsistencia registroAsistencia;

    public DetalleRegistroAsistencia() {

    }

    public RegistroAsistencia getRegistroAsistencia() {
        return registroAsistencia;
    }

    public void setRegistroAsistencia(RegistroAsistencia registroAsistencia) {
        this.registroAsistencia = registroAsistencia;
    }



    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final DetalleRegistroAsistencia other = (DetalleRegistroAsistencia) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    

}
