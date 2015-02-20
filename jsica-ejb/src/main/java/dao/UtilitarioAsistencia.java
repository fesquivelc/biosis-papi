/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.AbstractFacade.biosis_PU;
import com.project.jsica.ejb.entidades.TCImportacion;
import com.project.jsica.ejb.entidades.Vista;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author RyuujiMD
 */
@Stateless
public class UtilitarioAsistencia implements UtilitarioAsistenciaLocal,Serializable {

    @EJB
    private VistaFacadeLocal vistaDAO;
    @EJB
    private TCImportacionFacadeLocal tcDAO;

    @PersistenceContext(unitName = biosis_PU)
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    Date fechaPartida;
    Date horaPartida;

    Date fechaLlegada;
    Date horaLlegada;
    Connection connSQLServer;

    String usuario;
    String contrasena;
    String url;
    String driverManager;
    String tipoSistema;
    String tipoGestorBD;
    String query;
    String queryPlus;

    Properties properties;

    int contador = 0;
    private static final Logger LOG = Logger.getLogger(UtilitarioAsistencia.class.getName());

    private void instanciar() {
        try {
            File fileProperties = new File("biosis/conexion.properties");
            LOG.info("PATH DEL FICHERO DE CONEXION: " + fileProperties.getAbsolutePath());
//            fileProperties.createNewFile();            
            FileInputStream fileInputStreamProperties = new FileInputStream(fileProperties);

            properties = new Properties();
            properties.load(fileInputStreamProperties);

            this.usuario = properties.getProperty("usuario");
            this.contrasena = properties.getProperty("contrasena");
            this.url = properties.getProperty("url");
            

//            this.tipoSistema = properties.getProperty("tipoSistema");
            this.tipoGestorBD = properties.getProperty("tipoBase");
            
            LOG.info("TIPO DE BASE DE DATOS "+tipoGestorBD);
            
            this.driverManager = this.getDriverManager(tipoGestorBD);

            LOG.info("DRIVER MANAGER: "+driverManager);
            
            this.query = getQuery();

            LOG.log(Level.INFO, "CONSULTA: " + query);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UtilitarioAsistencia.class.getName()).log(Level.FATAL, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UtilitarioAsistencia.class.getName()).log(Level.FATAL, null, ex);
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
            LOG.info(driverManager);
            Class.forName(driverManager);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UtilitarioAsistencia.class.getName()).log(Level.FATAL, null, ex);
        }
        try {
            connSQLServer = DriverManager.getConnection(url, usuario, contrasena);
        } catch (SQLException ex) {
            Logger.getLogger(UtilitarioAsistencia.class.getName()).log(Level.FATAL, null, ex);
        }
    }

    private void cargaMasiva() {
        this.cargaMasiva(null, null);
    }

    private void cargaMasiva(Date fecha, Date hora) {
        this.conectarBiostar();

//        List<Vista> vistas = new ArrayList<>();
        PreparedStatement ps;
        try {
            if (fecha == null || hora == null) {
                ps = connSQLServer.prepareStatement(this.query);
                LOG.log(Level.INFO, "QUERY {0}" + this.query);
            } else {
                java.sql.Date pFecha = new java.sql.Date(fecha.getTime());
                Time pHora = new Time(hora.getTime());

//                String carga = this.query + " " + this.queryPlus;
                DateFormat dtFecha = new SimpleDateFormat("yyyy/MM/dd");
                DateFormat dtHora = new SimpleDateFormat("HH:mm:ss");

                String carga = this.query + "WHERE fecha > '" + dtFecha.format(pFecha) + "' OR (fecha =  '" + dtFecha.format(pFecha) + "' AND  hora >= '" + dtHora.format(pHora) + "')";
                LOG.log(Level.INFO, "CONSULTA DE CARGA MASIVA " + carga);
                ps = connSQLServer.prepareStatement(carga);

//
//                ps.setDate(1, pFecha);
//                ps.setDate(2, pFecha);
//                ps.setTime(3, pHora);
            }
            ResultSet rs = ps.executeQuery();

            this.generarInserts(rs);

            LOG.log(Level.INFO, "CONTADOR: " + contador);

//            contador = 0;
//            while (rs.next()) {
//                Vista vista = new Vista();
//                vista.setDni(rs.getInt("dni"));
//                vista.setEquipoIp(rs.getString("equipo_ip"));
//                vista.setFecha(rs.getDate("fecha"));
//                vista.setHora(rs.getTime("hora"));
//                vistas.add(vista);
////
//                contador++;
//                if (contador == 500) {
//                    for (Vista v : vistas) {
//                        vistaDAO.create(v);
//                    }
//                    vistas.clear();
//                    contador = 0;
//                }
//            }
//
//            for (Vista vista : vistas) {
//                vistaDAO.create(vista);
//            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilitarioAsistencia.class.getName()).log(Level.FATAL, null, ex);
        } finally {
            try {
                if (this.connSQLServer != null) {
                    this.connSQLServer.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(UtilitarioAsistencia.class.getName()).log(Level.FATAL, null, ex);
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
        String jpql = "SELECT v FROM Vista v ORDER BY v.fecha, v.hora ASC";

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

    private String getQuery() {
        /*
         1. BIOSTAR
         2. BIOADMIN 
         3. PRUEBA
         */

//        int tipoSistemaMarcacion = Integer.parseInt(tipo);
        /*
         1. SQL SERVER
         2. MySQL
         */
//        int tipoBase = Integer.parseInt(base);

        String consulta = "SELECT dni,fecha,hora,equipo_ip FROM vista ";
        LOG.info(consulta);
        return consulta;
    }

    @Override
    public int getContador() {
        return contador;
    }

    private void generarInserts(ResultSet rs) {
        try {
//            String consulta = "BEGIN ";            
            String consulta = "";
            DateFormat dtFecha = new SimpleDateFormat("yyyy/MM/dd");
            DateFormat dtHora = new SimpleDateFormat("HH:mm:ss");

            String dni;
            String equipo_ip;
            String fecha;
            String hora;

            contador = 0;

            while (rs.next()) {
                consulta += "INSERT INTO vista(dni, equipo_ip, fecha, hora) VALUES";
                dni = rs.getInt("dni") + "";
                equipo_ip = rs.getString("equipo_ip");
                fecha = dtFecha.format(rs.getDate("fecha"));
                hora = dtHora.format(rs.getTime("hora"));

                if (dni.length() == 7) {
                    dni = "0" + dni;
                }

                consulta += "(";
                consulta += "'" + dni + "',";
                consulta += "'" + equipo_ip + "',";
                consulta += "'" + fecha + "',";
                consulta += "'" + hora + "'";
                consulta += ");";
                contador++;

                if (contador >= 5000) {
                    LOG.log(Level.INFO, "CONTADOR: " + contador);
                    getEntityManager().createNativeQuery("BEGIN;" + consulta + "END;").executeUpdate();
                    contador = 0;
                    consulta = "";
                }
            }
            if (contador > 0) {
                getEntityManager().createNativeQuery("BEGIN;" + consulta + "END;").executeUpdate();
                contador = 0;
            }
            //            consulta += " END;";
            LOG.info(consulta);
        } catch (SQLException ex) {

            Logger.getLogger(UtilitarioAsistencia.class.getName()).log(Level.FATAL, null, ex);
        }
    }

    @Override
    public void query(String query) {
        try {
            this.conectarBiostar();
            PreparedStatement ps = connSQLServer.prepareStatement(query);
            ps.executeUpdate();
            LOG.info("SE LOGRO");
        } catch (SQLException ex) {
            Logger.getLogger(UtilitarioAsistencia.class.getName()).log(Level.FATAL, null, ex);
        }
    }

    private String getDriverManager(String base) {
        /*
         1. SQL SERVER
         2. MySQL
         3. PostgreSQL
         */
        int tipoBase = Integer.parseInt(base);
        
        switch(tipoBase){
            case 1:
                return "com.microsoft.jdbc.sqlserver.SQLServerDriver";
            case 2:
                return "com.mysql.jdbc.Driver";
            case 3:
                return "org.postgresql.Driver";
        }
        
        return "";
    }

}
