/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesiones;

import Entidades.AfUsuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mpaucar
 */
@Stateless
public class AfUsuarioFacade extends AbstractFacade<AfUsuario> implements AfUsuarioFacadeLocal {
    @PersistenceContext(unitName = "EJBActivosFijosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AfUsuarioFacade() {
        super(AfUsuario.class);
    }
    
}
