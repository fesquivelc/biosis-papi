/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.Biometrico;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author RyuujiMD
 */
@Stateless
public class BiometricoFacade extends AbstractFacade<Biometrico> implements BiometricoFacadeLocal {

    @PersistenceContext(unitName = jsica_PU)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BiometricoFacade() {
        super(Biometrico.class);
    }

    @Override
    public Biometrico searchByIp(String ip) {
        String jpql = "SELECT b FROM Biometrico b WHERE b.ipv4 = :ip";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("ip", ip);

        List<Biometrico> lista = this.search(jpql, parametros, -1, 1);

        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }
    }

}
