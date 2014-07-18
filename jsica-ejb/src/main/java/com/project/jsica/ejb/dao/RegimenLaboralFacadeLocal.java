/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.RegimenLaboral;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface RegimenLaboralFacadeLocal {

    void create(RegimenLaboral regimenLaboral);

    void edit(RegimenLaboral regimenLaboral);

    void remove(RegimenLaboral regimenLaboral);

    RegimenLaboral find(Object id);

    List<RegimenLaboral> findAll();

    List<RegimenLaboral> findRange(int[] range);

    int count();
    
}
