/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.TCImportacion;
import com.project.jsica.ejb.entidades.Vista;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author RyuujiMD
 */
@Stateless
public class UtilitarioAsistencia implements UtilitarioAsistenciaLocal {
    @EJB
    private VistaFacadeLocal vistaDAO;
    @EJB
    private TCImportacionFacadeLocal tcDAO;

    
    
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
    
    private void instanciar(){
        try {
            File fileProperties = new File("biosis/biostar-conexion.properties");
//            fileProperties.createNewFile();            
            FileInputStream fileInputStreamProperties = new FileInputStream(fileProperties);

            properties = new Properties();
            properties.load(fileInputStreamProperties);

            this.usuario = properties.getProperty("usuario");
            this.contrasena = properties.getProperty("contrasena");
            this.url = properties.getProperty("url");
            this.driverManager = properties.getProperty("driverManager");

            this.query = properties.getProperty("query");
            this.queryPlus = properties.getProperty("queryPlus");
                        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UtilitarioAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UtilitarioAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void crearEspejo() {
        //INSTANCIAMOS LAS PROPIEDADES
        this.instanciar();        
        
        List<TCImportacion> importaciones = tcDAO.search("SELECT t FROM TCImportacion t ORDER BY t.id DESC", null, -1, 1);
        if (importaciones.isEmpty()) {
            this.cargaMasiva();
            this.crearPuntosDePartida();
        } else {
            fechaPartida = importaciones.get(0).getFecha();
            horaPartida = importaciones.get(0).getHora();
            this.cargaMasiva(fechaPartida, horaPartida);
        }

        //se guardan los nuevos valores en la tabla TCImportacion
        TCImportacion tc = new TCImportacion();
        /*
         SE CREAN LOS PUNTOS EN LOS CUALES TERMINARA EL ANALISIS MES A MES
         */
        tc.setFecha(new Date());
        tc.setHora(new Date());
        tcDAO.create(tc);

        this.crearPuntosDeLlegada();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    private void conectarBiostar() {
        try {
            Class.forName(driverManager);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UtilitarioAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connSQLServer = DriverManager.getConnection(url, usuario, contrasena);
        } catch (SQLException ex) {
            Logger.getLogger(UtilitarioAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargaMasiva() {
        this.cargaMasiva(null, null);
    }

    private void cargaMasiva(Date fecha, Date hora) {
        this.conectarBiostar();
        
        List<Vista> vistas = new ArrayList<>();
        PreparedStatement ps;
        try {
            if (fecha == null || hora == null) {
                ps = connSQLServer.prepareStatement(this.query);
            } else {
                ps = connSQLServer.prepareStatement(this.query + " " + this.queryPlus);

                java.sql.Date pFecha = new java.sql.Date(fecha.getTime());
                Time pHora = new Time(hora.getTime());

                ps.setDate(1, pFecha);
                ps.setDate(2, pFecha);
                ps.setTime(3, pHora);
            }
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Vista vista = new Vista();
                vista.setDni(rs.getInt("dni"));
                vista.setEquipoIp(rs.getString("equipo_ip"));
                vista.setFecha(rs.getDate("fecha"));
                vista.setHora(rs.getTime("hora"));
                vistas.add(vista);
            }
            
            for(Vista vista : vistas){
                vistaDAO.create(vista);
            }            

        } catch (SQLException ex) {
            Logger.getLogger(UtilitarioAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (this.connSQLServer != null) {
                    this.connSQLServer.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(UtilitarioAsistencia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    private void crearPuntosDeLlegada() {
        String jpql = "SELECT tc FROM TCImportacion tc ORDER BY tc.id DESC";

        TCImportacion tc = tcDAO.search(jpql, null, -1, 1).get(0);

        fechaLlegada = tc.getFecha();
        horaLlegada = tc.getHora();
    }

    private void crearPuntosDePartida() {
        String jpql = "SELECT v FROM Vista v ORDER BY v.fecha ASC";

        /*
         NO HAY QUE PEDIR MAS DE UN ELEMENTO A LA CONSULTA 
         EN CASO CONTRARIO SE HACE DEMASIADO LENTO
         */
        Vista v = vistaDAO.search(jpql, null, -1, 1).get(0);

        fechaPartida = v.getFecha();        
        horaPartida = v.getHora();
    }

    @Override
    public Date getFechaPartida() {
        return this.fechaPartida;
    }

    @Override
    public Date getFechaLlegada() {
        return this.fechaLlegada;
    }

    @Override
    public Date getHoraPartida() {
        return this.horaPartida;
    }

    @Override
    public Date getHoraLlegada() {
        return this.horaLlegada;
    }
    
    
}
