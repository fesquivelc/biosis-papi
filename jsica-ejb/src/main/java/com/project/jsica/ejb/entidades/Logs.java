/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RyuujiMD
 */
@Table(name = "logs")
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logs.findAll", query = "SELECT l FROM Logs l"),
    @NamedQuery(name = "Logs.findById", query = "SELECT a FROM Logs a WHERE a.id = :id"),
    @NamedQuery(name = "Logs.findByUserId", query = "SELECT l FROM Logs l WHERE l.userId = :userId"),
    @NamedQuery(name = "Logs.findByDated", query = "SELECT l FROM Logs l WHERE l.dated = :dated"),
    @NamedQuery(name = "Logs.findByHora", query = "SELECT l FROM Logs l WHERE l.hora = :hora"),
    @NamedQuery(name = "Logs.findByLogger", query = "SELECT l FROM Logs l WHERE l.logger = :logger"),
    @NamedQuery(name = "Logs.findByLevel", query = "SELECT l FROM Logs l WHERE l.level = :level"),
    @NamedQuery(name = "Logs.findByMessage", query = "SELECT l FROM Logs l WHERE l.message = :message")})
public class Logs implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Basic(optional = false)   
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "user_id")
    private String userId;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dated;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String logger;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    private String level;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    private String message;

    public Logs() {
    }    

    public Logs(Long id) {
        this.id = id;
    }

    public Logs(Long id, String userId, Date dated, Date hora, String logger, String level, String message) {
        this.id = id;
        this.userId = userId;
        this.dated = dated;
        this.hora = hora;
        this.logger = logger;
        this.level = level;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }   
    

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId.toUpperCase();
    }

    public Date getDated() {
        return dated;
    }

    public void setDated(Date dated) {
        this.dated = dated;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }       

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger.toUpperCase();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level.toUpperCase();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message.toUpperCase();
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
        if (!(object instanceof Logs)) {
            return false;
        }
        Logs other = (Logs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.jsica.ejb.entidades.Logs[ id=" + id + " ]";
    }
    
}
