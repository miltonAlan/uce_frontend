/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Managed;

import Entidades.AfUsuario;
import Sesiones.AfUsuarioFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "ManagedLogin")
@SessionScoped
public class ManagedLogin implements Serializable {

    public static String usuarioSesion = "usuarioSesion";

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
    private String clave, usuario;

    public String login() {
        AfUsuario userTemp = manejadorAfUsuario.iniciarSesion(clave, usuario);
        if (userTemp != null) {

            // almacenamiento sesion usuario
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(usuarioSesion, userTemp);
            
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Credenciales Correctas"));
            return "Menu.xhtml";

        } else {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informacion", "Las credenciales son inCorrectas"));
        }
        return null;
    }

    public String login2() {
        return "MenuUsuario.xhtml";
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
