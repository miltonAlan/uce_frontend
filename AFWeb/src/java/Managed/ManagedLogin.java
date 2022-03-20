/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Managed;

import Entidades.AfConcepto;
import Entidades.AfUsuario;
import Sesiones.AfUsuarioFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author alejo
 */
@ManagedBean(name = "ManagedLogin")
@SessionScoped
public class ManagedLogin implements Serializable {

    /**
     * Creates a new instance of ManagedLogin
     */
    public ManagedLogin() {
    }
    
    @EJB
    private AfUsuarioFacadeLocal manejadorAfUsuario;
    private AfUsuario afUsuario;
    private List<AfUsuario> listaAfUsuarios;
    
    @PostConstruct
    private void inicio() {
     
    afUsuario = new AfUsuario();
        
    }

    public AfUsuario getAfUsuario() {
        return afUsuario;
    }

    public void setAfUsuario(AfUsuario afUsuario) {
        this.afUsuario = afUsuario;
    }

    public List<AfUsuario> getListaAfUsuarios() {
        return listaAfUsuarios;
    }

    public void setListaAfUsuarios(List<AfUsuario> listaAfUsuarios) {
        this.listaAfUsuarios = listaAfUsuarios;
    }
    
    public void login (){
        AfUsuario us;
        String redireccion=null;
        System.out.println("xxxxxxxxxantes: " + afUsuario.getAuClave() + "separador " + afUsuario.getAuLogin());
        try {
            us = manejadorAfUsuario.iniciarSesion(this.afUsuario);
            System.out.println("xxxxxxxxxxxxdespues: " + us);
        } catch (Exception e) {
        }
    }
}
