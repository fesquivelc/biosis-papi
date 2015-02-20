/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import com.project.jsica.ejb.entidades.CondicionLaboral;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface CondicionLaboralFacadeLocal {

    void create(CondicionLaboral condicionLaboral);

    void edit(CondicionLaboral condicionLaboral);

    void remove(CondicionLaboral condicionLaboral);

    CondicionLaboral find(Object id);

    List<CondicionLaboral> findAll();

    List<CondicionLaboral> findRange(int[] range);
    
    List<CondicionLaboral> search(String namedQuery);
    
    List<CondicionLaboral> search(String namedQuery, Map<String, Object> parametros);
    
    List<CondicionLaboral> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();
    
}
