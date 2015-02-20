/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import com.project.jsica.ejb.entidades.Area;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface AreaFacadeLocal {

    void create(Area area);

    void edit(Area area);

    void remove(Area area);

    Area find(Object id);

    List<Area> findAll();

    List<Area> findRange(int[] range);
    
    List<Area> search(String namedQuery);
    
    List<Area> search(String namedQuery, Map<String, Object> parametros);
    
    List<Area> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();
    
}
