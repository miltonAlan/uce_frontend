/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesiones;

import Entidades.AfConcepto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mpaucar
 */
@Local
public interface AfConceptoFacadeLocal {

    void create(AfConcepto afConcepto);

    void edit(AfConcepto afConcepto);

    void remove(AfConcepto afConcepto);

    AfConcepto find(Object id);

    List<AfConcepto> findAll();

    List<AfConcepto> findRange(int[] range);

    int count();
    
   AfConcepto buscarPorConcepto(String concepto);
    
}
