/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesiones;

import Entidades.AfActivoFijo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mpaucar
 */
@Local
public interface AfActivoFijoFacadeLocal {

    void create(AfActivoFijo afActivoFijo);

    void edit(AfActivoFijo afActivoFijo);

    void remove(AfActivoFijo afActivoFijo);

    AfActivoFijo find(Object id);

    List<AfActivoFijo> findAll();

    List<AfActivoFijo> findRange(int[] range);

    int count();
    
}
