package Parameters;

import Entidades.AfUsuario;
import Managed.ManagedLogin;
import java.io.File;
import java.util.HashMap;
import javax.faces.context.FacesContext;
import org.apache.log4j.PropertyConfigurator;

public class LoggerConfig {

    private final static String archivoPropertiesLog4j = "log4j.properties";
    private String mensajeLog;

    private void configLogger() {
        File log4jfile = new File(archivoPropertiesLog4j);
        PropertyConfigurator.configure(log4jfile.getAbsolutePath());
    }

    public void setMensajeLog(String metodo, String descMetodo, HashMap<String, String> parametros) {
        AfUsuario afUsuario = (AfUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(ManagedLogin.usuarioSesion);
        String login = afUsuario == null ? "Usuario o Clave Incorrectos o es un Invitado" : afUsuario.getAuLogin();
        this.mensajeLog = "Metodo:" + metodo + " -Descripcion Metodo:" + descMetodo + " -UsuarioLogin:" + login + " - Parametros:" + parametros;
    }

    public LoggerConfig() {
        configLogger();
    }

    public String getMensajeLog() {
        return mensajeLog;
    }
}
