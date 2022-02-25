/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesiones;

import Entidades.AfConcepto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mpaucar
 */
@Stateless
public class AfConceptoFacade extends AbstractFacade<AfConcepto> implements AfConceptoFacadeLocal {
    @PersistenceContext(unitName = "EJBActivosFijosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AfConceptoFacade() {
        super(AfConcepto.class);
    }
    
}
