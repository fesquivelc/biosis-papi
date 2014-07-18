/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.Biometrico;
import java.util.List;
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

    int count();
    
}
