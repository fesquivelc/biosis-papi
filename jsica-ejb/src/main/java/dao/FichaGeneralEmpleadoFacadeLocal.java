/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import com.project.jsica.ejb.entidades.FichaGeneralEmpleado;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface FichaGeneralEmpleadoFacadeLocal {

    void create(FichaGeneralEmpleado fichaGeneralEmpleado);

    void edit(FichaGeneralEmpleado fichaGeneralEmpleado);

    void remove(FichaGeneralEmpleado fichaGeneralEmpleado);

    FichaGeneralEmpleado find(Object id);

    List<FichaGeneralEmpleado> findAll();

    List<FichaGeneralEmpleado> findRange(int[] range);
    
    List<FichaGeneralEmpleado> search(String namedQuery);
    
    List<FichaGeneralEmpleado> search(String namedQuery, Map<String, Object> parametros);
    
    List<FichaGeneralEmpleado> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();
    
}
