/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.jsica.ejb.dao;

import com.project.jsica.ejb.entidades.Usuario;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);
    
    List<Usuario> search(String namedQuery);
    
    List<Usuario> search(String namedQuery, Map<String, Object> parametros);
    
    List<Usuario> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();
    
}
