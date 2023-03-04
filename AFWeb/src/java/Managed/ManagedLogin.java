package Managed;

import Entidades.AfUsuario;
import Parameters.LoggerConfig;
import Sesiones.AfUsuarioFacadeLocal;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

@ManagedBean(name = "ManagedLogin")
@SessionScoped
public class ManagedLogin implements Serializable {

    public static String usuarioSesion = "usuarioSesion";
    private final static Logger logger = Logger.getLogger(ManagedLogin.class);
    LoggerConfig loggerConfig = new LoggerConfig();
    HashMap<String, String> parametros = new HashMap<String, String>();

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
        String claveEncriptada = "";
        for (int i = 0; i < clave.length(); i++) {
            claveEncriptada += "*";
        }

        parametros.put("clave", claveEncriptada);
        if (userTemp != null) {

            // almacenamiento sesion usuario
            // este valor se usara para llenar los archivos de log
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(usuarioSesion, userTemp);

            loggerConfig.setMensajeLog("login()", "Log hacia el sistema", parametros);

            logger.info(loggerConfig.getMensajeLog());

            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Credenciales Correctas"));
            return "Menu.xhtml";

        } else {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informacion", "Las credenciales son inCorrectas"));
            loggerConfig.setMensajeLog("login()", "Falla al tratar de loguear al sistema", parametros);

            logger.error(loggerConfig.getMensajeLog());
        }
        return null;
    }

    public String login2() {
        loggerConfig.setMensajeLog("login2()", "Log hacia el sistema como invitado", parametros);

        logger.info(loggerConfig.getMensajeLog());
        
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
