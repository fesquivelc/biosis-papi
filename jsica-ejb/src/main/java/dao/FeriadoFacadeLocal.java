/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import com.project.jsica.ejb.entidades.Feriado;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface FeriadoFacadeLocal {

    void create(Feriado feriado);

    void edit(Feriado feriado);

    void remove(Feriado feriado);

    Feriado find(Object id);

    List<Feriado> findAll();

    List<Feriado> findRange(int[] range);
    
    List<Feriado> search(String namedQuery);
    
    List<Feriado> search(String namedQuery, Map<String, Object> parametros);
    
    List<Feriado> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();

    List<Feriado> buscarXAnio(Integer anio);

    public Feriado buscarXDia(Date fInicio);

    
}
