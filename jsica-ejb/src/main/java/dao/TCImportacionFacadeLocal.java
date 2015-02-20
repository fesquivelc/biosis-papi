/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import com.project.jsica.ejb.entidades.TCImportacion;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface TCImportacionFacadeLocal {

    void create(TCImportacion tCImportacion);

    void edit(TCImportacion tCImportacion);

    void remove(TCImportacion tCImportacion);

    TCImportacion find(Object id);

    List<TCImportacion> findAll();

    List<TCImportacion> findRange(int[] range);
    
    List<TCImportacion> search(String namedQuery);
    
    List<TCImportacion> search(String namedQuery, Map<String, Object> parametros);
    
    List<TCImportacion> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();
    
}
