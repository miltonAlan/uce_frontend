/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesiones;

import Entidades.AfVariables;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mpaucar
 */
@Local
public interface AfVariablesFacadeLocal {

    void create(AfVariables afVariables);

    void edit(AfVariables afVariables);

    void remove(AfVariables afVariables);

    AfVariables find(Object id);

    List<AfVariables> findAll();

    List<AfVariables> findRange(int[] range);

    int count();
    
}
