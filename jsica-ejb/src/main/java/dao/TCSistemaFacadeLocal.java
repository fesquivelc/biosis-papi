/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.project.jsica.ejb.entidades.TCSistema;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fesquivelc
 */
@Local
public interface TCSistemaFacadeLocal {

    void create(TCSistema tCSistema);

    void edit(TCSistema tCSistema);

    void remove(TCSistema tCSistema);

    TCSistema find(Object id);

    List<TCSistema> findAll();

    List<TCSistema> findRange(int[] range);

    int count();

    public void limpiar();
    
}
