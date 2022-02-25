/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesiones;

import Entidades.AfEjemplo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alejo
 */
@Stateless
public class AfEjemploFacade extends AbstractFacade<AfEjemplo> implements AfEjemploFacadeLocal {
    @PersistenceContext(unitName = "EJBActivosFijosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AfEjemploFacade() {
        super(AfEjemplo.class);
    }
    
}
