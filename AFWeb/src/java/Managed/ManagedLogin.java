package Managed;

import Entidades.AfUsuario;
import Sesiones.AfUsuarioFacadeLocal;
import java.io.File;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@ManagedBean(name = "ManagedLogin")
@SessionScoped
public class ManagedLogin implements Serializable {

    public static String usuarioSesion = "usuarioSesion";
    private final static String archivoPropertiesLog4j = "log4j.properties";
    private final static Logger logger = Logger.getLogger(ManagedLogin.class);

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

    private void configLogger() {
        File log4jfile = new File(archivoPropertiesLog4j);
        PropertyConfigurator.configure(log4jfile.getAbsolutePath());
    }

    public String login() {
        configLogger();
        AfUsuario userTemp = manejadorAfUsuario.iniciarSesion(clave, usuario);
        String mensajeLog = "";
        String claveEncriptada = "";
        if (userTemp != null) {

            // almacenamiento sesion usuario
            // este valor se usara para llenar los archivos de log
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(usuarioSesion, userTemp);
            for (int i = 0; i < clave.length(); i++) {
                claveEncriptada += "*";
            }
            mensajeLog = "Metodo:login()" + ", Parametros: usuario:" + usuario + " clave:" + claveEncriptada;

            logger.info(mensajeLog);
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Credenciales Correctas"));
            return "Menu.xhtml";

        } else {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informacion", "Las credenciales son inCorrectas"));
            logger.error("Las credenciales son inCorrectas - Metodo:login()");
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
