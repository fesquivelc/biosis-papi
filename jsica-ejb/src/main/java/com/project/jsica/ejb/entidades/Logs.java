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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RyuujiMD
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logs.findAll", query = "SELECT l FROM Logs l"),
    @NamedQuery(name = "Logs.findByUserId", query = "SELECT l FROM Logs l WHERE l.userId = :userId"),
    @NamedQuery(name = "Logs.findByDated", query = "SELECT l FROM Logs l WHERE l.dated = :dated"),
    @NamedQuery(name = "Logs.findByLogger", query = "SELECT l FROM Logs l WHERE l.logger = :logger"),
    @NamedQuery(name = "Logs.findByLevel", query = "SELECT l FROM Logs l WHERE l.level = :level"),
    @NamedQuery(name = "Logs.findByMessage", query = "SELECT l FROM Logs l WHERE l.message = :message")})
public class Logs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
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

    public Logs(String userId) {
        this.userId = userId;
    }

    public Logs(String userId, Date dated, String logger, String level, String message) {
        this.userId = userId;
        this.dated = dated;
        this.logger = logger;
        this.level = level;
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDated() {
        return dated;
    }

    public void setDated(Date dated) {
        this.dated = dated;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logs)) {
            return false;
        }
        Logs other = (Logs) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.jsica.ejb.entidades.Logs[ userId=" + userId + " ]";
    }
    
}
