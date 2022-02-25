/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesiones;

import Entidades.AfEjemplo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author alejo
 */
@Local
public interface AfEjemploFacadeLocal {

    void create(AfEjemplo afEjemplo);

    void edit(AfEjemplo afEjemplo);

    void remove(AfEjemplo afEjemplo);

    AfEjemplo find(Object id);

    List<AfEjemplo> findAll();

    List<AfEjemplo> findRange(int[] range);

    int count();
    
}
