/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesiones;

import Entidades.AfActivoFijo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mpaucar
 */
@Stateless
public class AfActivoFijoFacade extends AbstractFacade<AfActivoFijo> implements AfActivoFijoFacadeLocal {
    @PersistenceContext(unitName = "EJBActivosFijosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AfActivoFijoFacade() {
        super(AfActivoFijo.class);
    }
    
}
