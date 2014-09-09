/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.jsica.ejb.dao;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.ejb.Stateless;

/**
 *
 * @author RyuujiMD
 */
@Stateless
public class UtilitarioAsistencia implements UtilitarioAsistenciaLocal {

    Date fechaPartida;
    Date horaPartida;

    Date fechaLlegada;
    Date horaLlegada;
    Connection connSQLServer;

    String usuario;
    String contrasena;
    String url;
    String driverManager;
    String query;
    String queryPlus;

    Properties properties;
    
    

    @Override
    public void crearEspejo() {

    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
