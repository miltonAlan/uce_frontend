package Managed;

import Entidades.AfActivoFijo;
import Entidades.AfConcepto;
import Entidades.AfHistorico;
import Entidades.AfUsuario;
import Parameters.LoggerConfig;
import Sesiones.AfActivoFijoFacadeLocal;
import Sesiones.AfConceptoFacadeLocal;
import Sesiones.AfHistoricoFacadeLocal;
import Sesiones.AfUsuarioFacadeLocal;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

@ManagedBean(name = "JSFManaged")
@SessionScoped
public class JSFManaged implements Serializable {

    private final static Logger logger = Logger.getLogger(JSFManaged.class);
    LoggerConfig loggerConfig = new LoggerConfig();
    HashMap<String, String> parametros = new HashMap<String, String>();
    @EJB
    private AfUsuarioFacadeLocal manejadorAfUsuario;
    private AfUsuario afUsuario;
    private List<AfUsuario> listaAfUsuarios;
    @EJB
    private AfConceptoFacadeLocal manejadorAfConcepto;
    private AfConcepto afConcepto;
    private List<AfConcepto> listaAfConceptos;
    @EJB
    private AfActivoFijoFacadeLocal manejadorAfActivoFijo;
    private AfActivoFijo afActivoFijo;
    private double codCliente;
    private double codConcepto;
    @EJB
    private AfHistoricoFacadeLocal manejadorAfHistorico;
    private AfHistorico afHistorico;
    private double codActivoFijo;
    private Date fecha;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public JSFManaged() {
    }

    public void grabarAfUsuario() {
        try {
            if (manejadorAfUsuario.buscarPorUsuario(afUsuario.getAuCedula()) == null) {

                asignarConsecutivo();
                manejadorAfUsuario.create(afUsuario);
                this.setListaAfUsuarios(manejadorAfUsuario.findAll());
                parametros.put("usuarioRegistrado", afUsuario.toString());
                loggerConfig.setMensajeLog("grabarAfUsuario()", "Ingresa un usuario/responsable nuevo al sistema", parametros);
                logger.info(loggerConfig.getMensajeLog());
                parametros.clear();
                afUsuario = new AfUsuario();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Registro guardado exitosamente"));

            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia!", "El registro ya existe"));
                parametros.put("usuarioRegistrado", afUsuario.toString());
                parametros.put("ERRORMSG", "El registro ya existe");
                loggerConfig.setMensajeLog("grabarAfUsuario()", "Ingresa un usuario/responsable nuevo al sistema", parametros);
                logger.error(loggerConfig.getMensajeLog());
                parametros.clear();
            }

        } catch (Exception e) {
        }

    }

    public void grabarAfConcepto() {
        if (manejadorAfConcepto.buscarPorConcepto(afConcepto.getAcConcepto()) == null) {
            afConcepto.setAcEstado("Vigente");
            manejadorAfConcepto.create(afConcepto);
            if (manejadorAfConcepto.buscarPorConcepto(afConcepto.getAcConcepto()) != null) {
                addMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Registro guardado exitosamente");
            }
        } else {
            addMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Un concepto con la misma descripción ya existe");
        }
    }

    public void grabarActivoFijo() {
        this.afActivoFijo.setAuAfConsecutivo(manejadorAfUsuario.find(codCliente));
        this.afActivoFijo.setAcAfConcepto(manejadorAfConcepto.find(codConcepto));
        this.manejadorAfActivoFijo.create(afActivoFijo);

    }

    public void grabarAfHistorico() {
        this.manejadorAfHistorico.create(afHistorico);
    }

    public void listarAfUsuarios() {
        setListaAfUsuarios(manejadorAfUsuario.findAll());
    }

    public void listarAfConceptos() {
        this.setListaAfConceptos(manejadorAfConcepto.findAll());
    }

    public void editarConcepto(AfConcepto concepto) {
        manejadorAfConcepto.edit(concepto);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Registro modificado exitosamente"));

    }

    @PostConstruct
    private void inicio() {
        afUsuario = new AfUsuario();
        afConcepto = new AfConcepto();
        afActivoFijo = new AfActivoFijo();
        afHistorico = new AfHistorico();
        listarAfUsuarios();
        listarAfConceptos();
    }

    public void asignarConsecutivo() {

        this.afUsuario
                .setAuConsecutivo(this.listaAfUsuarios.get(this.listaAfUsuarios.size() - 1).getAuConsecutivo() + 1);

    }

    public AfUsuario getAfUsuario() {
        return afUsuario;
    }

    public void setAfUsuario(AfUsuario afUsuario) {
        this.afUsuario = afUsuario;
    }

    public AfConcepto getAfConcepto() {
        return afConcepto;
    }

    public void setAfConcepto(AfConcepto afConcepto) {
        this.afConcepto = afConcepto;
    }

    public List<AfUsuario> getListaAfUsuarios() {

        parametros.put("responsablesActivosFijos", listaAfUsuarios.toString());
        parametros.put("cantidadResponsables", String.valueOf(listaAfUsuarios.size()));
        loggerConfig.setMensajeLog("getListaAfUsuarios()", "Obtiene todos los responsables de los Activos Fijos", parametros);
        logger.info(loggerConfig.getMensajeLog());
        parametros.clear();
        return listaAfUsuarios;
    }

    public void setListaAfUsuarios(List<AfUsuario> listaAfUsuarios) {
        this.listaAfUsuarios = listaAfUsuarios;
    }

    public AfActivoFijo getAfActivoFijo() {
        return afActivoFijo;
    }

    public void setAfActivoFijo(AfActivoFijo afActivoFijo) {
        this.afActivoFijo = afActivoFijo;
    }

    public double getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(double codCliente) {
        this.codCliente = codCliente;
    }

    public double getCodConcepto() {
        return codConcepto;
    }

    public void setCodConcepto(double codConcepto) {
        this.codConcepto = codConcepto;
    }

    public AfHistorico getAfHistorico() {
        return afHistorico;
    }

    public void setAfHistorico(AfHistorico afHistorico) {
        this.afHistorico = afHistorico;
    }

    public double getCodActivoFijo() {
        return codActivoFijo;
    }

    public void setCodActivoFijo(double codActivoFijo) {
        this.codActivoFijo = codActivoFijo;
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public List<AfConcepto> getListaAfConceptos() {
        return listaAfConceptos;
    }

    public void setListaAfConceptos(List<AfConcepto> listaAfConceptos) {
        this.listaAfConceptos = listaAfConceptos;
    }
}
