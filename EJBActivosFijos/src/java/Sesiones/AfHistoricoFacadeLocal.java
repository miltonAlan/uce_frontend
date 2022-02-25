/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesiones;

import Entidades.AfHistorico;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mpaucar
 */
@Local
public interface AfHistoricoFacadeLocal {

    void create(AfHistorico afHistorico);

    void edit(AfHistorico afHistorico);

    void remove(AfHistorico afHistorico);

    AfHistorico find(Object id);

    List<AfHistorico> findAll();

    List<AfHistorico> findRange(int[] range);

    int count();
    
}
