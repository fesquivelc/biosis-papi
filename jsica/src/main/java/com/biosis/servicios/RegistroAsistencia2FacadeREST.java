/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biosis.servicios;

import com.project.jsica.ejb.dao.RegistroAsistenciaFinalFacadeLocal;
import com.project.jsica.ejb.entidades.RegistroAsistencia2;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author fesquivelc
 */
@ConversationScoped
@Path("asistencia")
public class RegistroAsistencia2FacadeREST implements Serializable{
//    @Inject
//    private AnalisisAsistenciaBean analisis;
    @EJB
    private RegistroAsistenciaFinalFacadeLocal registro;
    
    private final DateFormat fechaParser = new SimpleDateFormat("yyyy-MM-dd");
    private static final Logger LOG = Logger.getLogger(RegistroAsistencia2FacadeREST.class.getName());
    
    @GET
    @Path("prueba")
    @Produces({"application/xml", "application/json"}) 
    public RegistroAsistencia2 prueba(){
        RegistroAsistencia2 registroa = new RegistroAsistencia2();
        registroa.setTipo("H");
        return registroa;
    }

    
    @GET
    @Path("fechas/{fechaInicio}/{fechaFin}")
    @Produces({"application/xml", "application/json"})
    public List<RegistroAsistencia2> buscar(
            @PathParam("fechaInicio") String fechaInicio,
            @PathParam("fechaFin") String fechaFin){
        
        List<RegistroAsistencia2> registros = null;
        
        try {
            Date fInicio = fechaParser.parse(fechaInicio);
            Date fFin = fechaParser.parse(fechaFin);
            LOG.info("BUSCANDO REGISTROS");
            registros = registro.busquedaXFecha(fInicio, fFin);
            LOG.info(registros);
            return registros;
        } catch (ParseException ex) {
            Logger.getLogger(RegistroAsistencia2FacadeREST.class.getName()).log(Level.FATAL, null, ex);
            return null;
        }
        
    }
    //O POR FECHAS Y POR EMPLEADO
    
    //O POR FECHAS Y POR AREA
    
    

    //POR RAZONES DE SEGURIDAD TAMPOCO DEBE EXISTIR
//    @GET
//    @Override
//    @Produces({"application/xml", "application/json"})
//    public List<RegistroAsistencia2> findAll() {
//        return super.findAll();
//    }

    
}
