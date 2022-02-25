/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesiones;

import Entidades.AfHistorico;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mpaucar
 */
@Stateless
public class AfHistoricoFacade extends AbstractFacade<AfHistorico> implements AfHistoricoFacadeLocal {
    @PersistenceContext(unitName = "EJBActivosFijosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AfHistoricoFacade() {
        super(AfHistorico.class);
    }
    
}
