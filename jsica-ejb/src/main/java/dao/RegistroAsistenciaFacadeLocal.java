/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import com.project.jsica.ejb.entidades.Area;
import com.project.jsica.ejb.entidades.Empleado;
import com.project.jsica.ejb.entidades.RegistroAsistencia;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author RyuujiMD
 */
@Local
public interface RegistroAsistenciaFacadeLocal {

    void create(RegistroAsistencia registroAsistencia);

    void edit(RegistroAsistencia registroAsistencia);

    void remove(RegistroAsistencia registroAsistencia);

    RegistroAsistencia find(Object id);

    List<RegistroAsistencia> findAll();

    List<RegistroAsistencia> findRange(int[] range);
    
    List<RegistroAsistencia> search(String namedQuery);
    
    List<RegistroAsistencia> search(String namedQuery, Map<String, Object> parametros);
    
    List<RegistroAsistencia> search(String namedQuery, Map<String, Object> parametros, int inicio, int tamanio);

    int count();

    public void guardarLote(List<RegistroAsistencia> lista);

    public List<RegistroAsistencia> busquedaXFecha(Date fechaInicio, Date fechaFin);

    public List<RegistroAsistencia> buscarXArea(Area area, Date fechaDesde, Date fechaHasta);

    public List<RegistroAsistencia> buscarXEmpleado(Empleado empleado, Date fechaDesde, Date fechaHasta);
    
}
