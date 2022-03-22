/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesiones;

import Entidades.AfUsuario;
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

    @Override
    public AfUsuario buscarPorUsuario(String cedula) {
        AfUsuario afUsuario = null;
        try {
            afUsuario = (AfUsuario) em.createNamedQuery("AfUsuario.findByAuCedula", AfUsuario.class)
                    .setParameter("auCedula", cedula).getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
        }
        return afUsuario;
    }

    @Override
    public AfUsuario iniciarSesion(String clave,String login) {
        AfUsuario cli = null;

        try {
            Query sql = em.createNamedQuery("AfUsuario.validarUsuario")
                    .setParameter("auLogin", login)
                    .setParameter("auClave", clave);
            List<AfUsuario> listaClientes = sql.getResultList();

            if (!listaClientes.isEmpty()) {
                cli = listaClientes.get(0);
            }
        } catch (Exception e) {
//            throw e;
        }
        return cli;

    }
}
