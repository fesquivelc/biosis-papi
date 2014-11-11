/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.util;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RyuujiMD
 */
public class FechaUtil {

    private static final Logger LOG = Logger.getLogger(FechaUtil.class.getName());

    public static int compararFechaHora(Date fecha1, Date hora1, Date fecha2, Date hora2) {
        if (fecha1.compareTo(fecha2) < 0) {
            return -1;
        } else if (fecha1.compareTo(fecha2) == 0) {
            LOG.log(Level.INFO, "hora1 {0} vs hora2 {1} : {2}", new String[]{hora1.toString(), hora2.toString(), hora1.compareTo(hora2) + ""});
            return hora1.compareTo(hora2);
        } else {
            return 1;
        }
    }
    
    public static int ultimoDiaMes(int mes, int anio) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(anio, mes - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


}
