/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import com.project.jsica.ejb.entidades.TipoContrato;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface TipoContratoFacadeLocal {

    void create(TipoContrato tipoContrato);

    void edit(TipoContrato tipoContrato);

    void remove(TipoContrato tipoContrato);

    TipoContrato find(Object id);

    List<TipoContrato> findAll();

    List<TipoContrato> findRange(int[] range);
    
    List<TipoContrato> search(String namedQuery);
    
    List<TipoContrato> search(String namedQuery, Map<String, Object> parametros);
    
    List<TipoContrato> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();
    
}
