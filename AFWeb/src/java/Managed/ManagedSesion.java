package Managed;

import Entidades.AfUsuario;
import Parameters.LoggerConfig;
import java.io.Serializable;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

@ManagedBean(name = "ManagedSesion")
@SessionScoped
public class ManagedSesion implements Serializable {

    private static String paginaAccesoDenegado = "accesodenegado.xhtml";
    private final static Logger logger = Logger.getLogger(ManagedSesion.class);
    LoggerConfig loggerConfig = new LoggerConfig();
    HashMap<String, String> parametros = new HashMap<String, String>();

    public void validarSesion() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            AfUsuario afUsuario = (AfUsuario) facesContext.getExternalContext().getSessionMap().get(ManagedLogin.usuarioSesion);

            parametros.put("tieneAcceso", afUsuario == null ? "NO" : "SI");
            loggerConfig.setMensajeLog("validarSesion()", "Validacion que los usuarios accedan unicamente a los recursos del sistema si han iniciado sesion", parametros);
            logger.info(loggerConfig.getMensajeLog());

            if (afUsuario == null) {
                facesContext.getExternalContext().redirect(paginaAccesoDenegado);
            }
        } catch (Exception e) {
        }
    }
}
