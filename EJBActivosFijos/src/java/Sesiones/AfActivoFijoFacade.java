/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesiones;

import Entidades.AfActivoFijo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


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
    
    public List<AfActivoFijo> listar(){
        
        Query q=em.createNativeQuery("select ac_af_concepto,af_estado from af_activo_fijo;", AfActivoFijo.class);
        
        
        return q.getResultList();
    
    
    }
    
}
