/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import com.project.jsica.ejb.entidades.Biometrico;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface BiometricoFacadeLocal {

    void create(Biometrico biometrico);

    void edit(Biometrico biometrico);

    void remove(Biometrico biometrico);

    Biometrico find(Object id);

    List<Biometrico> findAll();

    List<Biometrico> findRange(int[] range);
    
    List<Biometrico> search(String namedQuery);
    
    List<Biometrico> search(String namedQuery, Map<String, Object> parametros);
    
    List<Biometrico> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();

    Biometrico searchByIp(String ip);
    
}
