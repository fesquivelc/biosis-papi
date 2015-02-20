/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import com.project.jsica.ejb.entidades.EmpleadoHorario;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface EmpleadoHorarioFacadeLocal {

    void create(EmpleadoHorario empleadoHorario);

    void edit(EmpleadoHorario empleadoHorario);

    void remove(EmpleadoHorario empleadoHorario);

    EmpleadoHorario find(Object id);

    List<EmpleadoHorario> findAll();

    List<EmpleadoHorario> findRange(int[] range);
    
    List<EmpleadoHorario> search(String namedQuery);
    
    List<EmpleadoHorario> search(String namedQuery, Map<String, Object> parametros);
    
    List<EmpleadoHorario> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();

    List<EmpleadoHorario> getHorariosAsistenciales();
    
}
