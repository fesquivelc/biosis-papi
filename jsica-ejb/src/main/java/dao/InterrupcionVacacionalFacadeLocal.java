/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.project.jsica.ejb.entidades.InterrupcionVacacional;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fesquivelc
 */
@Local
public interface InterrupcionVacacionalFacadeLocal {

    void create(InterrupcionVacacional interrupcionVacacional);

    void edit(InterrupcionVacacional interrupcionVacacional);

    void remove(InterrupcionVacacional interrupcionVacacional);

    InterrupcionVacacional find(Object id);

    List<InterrupcionVacacional> findAll();

    List<InterrupcionVacacional> findRange(int[] range);

    int count();
    
}
