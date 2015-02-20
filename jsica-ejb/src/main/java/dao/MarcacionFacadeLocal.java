/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.project.jsica.ejb.entidades.Marcacion;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fesquivelc
 */
@Local
public interface MarcacionFacadeLocal {

    void create(Marcacion marcacion);

    void edit(Marcacion marcacion);

    void remove(Marcacion marcacion);

    Marcacion find(Object id);

    List<Marcacion> findAll();

    List<Marcacion> findRange(int[] range);

    int count();

    public List<Marcacion> buscarXFecha(String dni, Date fechaInicio, Date fechaFin);

    public List<Marcacion> buscarXFecha(String dni, Date fechaInicio, Date fechaFin, int desde, int tamanio);

    public List<Marcacion> buscarXFecha(Date fechaInicio, Date fechaFin);

    public List<Marcacion> buscarXFecha(Date fecha);

    public List<Marcacion> buscarXFecha(String dni, Date fecha);

    public List<Marcacion> buscarXFecha(Date fechaInicio, Date fechaFin, int desde, int tamanio);

    public List<Marcacion> buscarXFechaXHora(Date fechaInicio, Date horaInicio, Date horaFin, int desde, int tamanio);

    public int totalXEmpleadoXFecha(String dni, Date fechaInicio, Date fechaFin);

    public int totalXFecha(Date fechaInicio, Date fechaFin);

    public Marcacion buscarXFechaXhora(String dni, Date fecha, Date horaI, Date horaF);

    public List<Object[]> buscarEmpleadosXEvento(int evento, boolean dentro);

    public List<Marcacion> buscarXFechaXHora(String dni, Date fechaInicio, Date horaInicio, Date horaFin, int desde, int tamanio);

    public List<Marcacion> buscarXFechaXHora(List<Integer> dni, Date fechaInicio, Date horaInicio, Date horaFin, int desde, int tamanio);

    public List<Marcacion> buscarXFecha(List<Integer> empleados, Date fechaInicio, Date fechaFin, int desde, int tamanio);

    public int totalXEmpleadoXFecha(List<Integer> empleados, Date fechaInicio, Date fechaFin);

    public int totalXFechaXHora(Date fechaInicio, Date horaInicio, Date horaFin);

    public int totalXFecha(List<Integer> empleados, Date fechaInicio, Date fechaFin);

    public int totalXFechaXHora(List<Integer> dni, Date fechaInicio, Date horaInicio, Date horaFin);
    
}
