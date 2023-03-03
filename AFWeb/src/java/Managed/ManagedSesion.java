package Managed;

import Entidades.AfUsuario;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "ManagedSesion")
@SessionScoped
public class ManagedSesion implements Serializable {
    
    private static String paginaAccesoDenegado = "accesodenegado.xhtml";
    
    public void validarSesion() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            AfUsuario afUsuario = (AfUsuario) facesContext.getExternalContext().getSessionMap().get(ManagedLogin.usuarioSesion);
            if (afUsuario == null) {
                facesContext.getExternalContext().redirect(paginaAccesoDenegado);
            }
        } catch (Exception e) {
        }
    }
}
