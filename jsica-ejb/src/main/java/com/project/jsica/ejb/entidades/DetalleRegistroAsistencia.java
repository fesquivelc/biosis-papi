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
    
    private Date fecha;

    @Temporal(TemporalType.TIME)
    @Column(nullable = true)
    private Date hora;

    @Column(name = "tipo_evento", length = 1) //E = Entrada o S = Salida
    private String tipoEvento;
    
    @Column(name = "tipo_registro", length = 1) // P = Permiso x hora, R = refrigerio, T = turno
    private String tipoRegistro;
    
    private Integer carga; //numero a sumar, aun no tengo idea para que xD
    
    @JoinColumn(name = "empleado_permiso_id", referencedColumnName = "id", nullable = true)
    @ManyToOne
    private EmpleadoPermiso permiso;
    
    @Column(name = "tardanza")
    private Boolean tardanza;
    
    @Column(name = "milisegundos_tardanza")
    private Long milisegundosTardanza;
    
    @Column(name = "milisegundos_extra")
    private Long milisegundosExtra;

    public Long getMilisegundosExtra() {
        return milisegundosExtra;
    }

    public void setMilisegundosExtra(Long milisegundosExtra) {
        this.milisegundosExtra = milisegundosExtra;
    }

    
    public Boolean getTardanza() {
        return tardanza;
    }

    public void setTardanza(Boolean tardanza) {
        this.tardanza = tardanza;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public Integer getCarga() {
        return carga;
    }

    public void setCarga(Integer carga) {
        this.carga = carga;
    }

    public RegistroAsistencia2 getRegistroAsistencia() {
        return registroAsistencia;
    }

    public void setRegistroAsistencia(RegistroAsistencia2 registroAsistencia) {
        this.registroAsistencia = registroAsistencia;
    }

    
    
    @JoinColumn(name = "registro_asistencia_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(targetEntity = RegistroAsistencia2.class, optional = false)
    private RegistroAsistencia2 registroAsistencia;

    public DetalleRegistroAsistencia() {

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
