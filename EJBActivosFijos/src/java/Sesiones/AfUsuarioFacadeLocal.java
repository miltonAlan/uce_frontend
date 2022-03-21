/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesiones;

import Entidades.AfUsuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mpaucar
 */
@Local
public interface AfUsuarioFacadeLocal {

    void create(AfUsuario afUsuario);

    void edit(AfUsuario afUsuario);

    void remove(AfUsuario afUsuario);

    AfUsuario find(Object id);

    List<AfUsuario> findAll();

    List<AfUsuario> findRange(int[] range);

    int count();
    
     AfUsuario buscarPorUsuario(String cedula);
     
     AfUsuario iniciarSesion(String clave, String login);

}
