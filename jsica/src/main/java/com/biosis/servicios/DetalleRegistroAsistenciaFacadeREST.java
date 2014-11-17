/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biosis.servicios;

import com.project.jsica.ejb.dao.AbstractFacade;
import com.project.jsica.ejb.entidades.DetalleRegistroAsistencia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author fesquivelc
 */
@Stateless
@Path("detalleRegistroAsistencia")
public class DetalleRegistroAsistenciaFacadeREST extends AbstractFacade<DetalleRegistroAsistencia> {
    
    public DetalleRegistroAsistenciaFacadeREST() {
        super(DetalleRegistroAsistencia.class);
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public DetalleRegistroAsistencia find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<DetalleRegistroAsistencia> findAll() {
        return super.findAll();
    }
}
