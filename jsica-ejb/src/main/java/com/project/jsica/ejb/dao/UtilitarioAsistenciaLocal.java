/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface UtilitarioAsistenciaLocal {

    void crearEspejo();

    Date getFechaPartida();

    Date getFechaLlegada();

    Date getHoraPartida();

    Date getHoraLlegada();

    int getContador();

    void query(String query);
    
}
