/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

@ManagedBean(name = "ManagedConcepto")
@SessionScoped
public class ManagedConcepto implements Serializable {

    private final static Logger logger = Logger.getLogger(ManagedConcepto.class);
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

    public ManagedConcepto() {
    }

    public void grabarAfUsuario() {
        manejadorAfUsuario.create(afUsuario);
    }

    public double buscarUltimo(List list) {
        return 0;
    }

    public void asignarConsecutivo() {
        this.afConcepto.setAcConsecutivo(this.listaAfConceptos.get(this.listaAfConceptos.size() - 1).getAcConsecutivo() + 1);

    }

    public void grabarAfConcepto() {
        if (manejadorAfConcepto.buscarPorConcepto(afConcepto.getAcConcepto()) == null) {
            afConcepto.setAcEstado("Vigente");
            asignarConsecutivo();
            parametros.put("conceptoGuardado", afConcepto.toString());
            loggerConfig.setMensajeLog("grabarAfConcepto()", "Graba un nuevo concepto de para los Activos Fijos", parametros);
            logger.info(loggerConfig.getMensajeLog());
            parametros.clear();
            manejadorAfConcepto.create(afConcepto);
            this.setListaAfConceptos(manejadorAfConcepto.findAll());
            afConcepto = new AfConcepto();
            addMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Registro guardado exitosamente");

        } else {
            addMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Un concepto con la misma descripción ya existe");
            parametros.put("conceptoNOGuardado", afConcepto.toString());
            parametros.put("ERRORMSG", "Un concepto con la misma descripción ya existe");
            loggerConfig.setMensajeLog("grabarAfConcepto()", "Graba un nuevo concepto de para los Activos Fijos", parametros);
            logger.error(loggerConfig.getMensajeLog());
            parametros.clear();
        }
    }

    public void grabarActivoFijo() {
        this.afActivoFijo.setAuAfConsecutivo(manejadorAfUsuario.find(codCliente));
        this.afActivoFijo.setAcAfConcepto(manejadorAfConcepto.find(codConcepto));
        this.manejadorAfActivoFijo.create(afActivoFijo);

    }

//    public void grabarAfHistorico() {
//        this.afHistorico.setAfActivoFijo(manejadorAfActivoFijo.find(codActivoFijo));
//        this.manejadorAfHistorico.create(afHistorico);
//    }
    public void listarAfUsuarios() {
        setListaAfUsuarios(manejadorAfUsuario.findAll());
    }

    public void listarAfConceptos() {
        this.setListaAfConceptos(manejadorAfConcepto.findAll());
    }

    public void editarConcepto(AfConcepto concepto) {
        manejadorAfConcepto.edit(concepto);
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Registro modificado exitosamente"));
        parametros.put("conceptoEditado", concepto.toString());
        loggerConfig.setMensajeLog("editarConcepto()", "Edita la informacion de los conceptos de Activos Fijos", parametros);
        logger.info(loggerConfig.getMensajeLog());
        parametros.clear();

    }

    @PostConstruct
    private void inicio() {
        afConcepto = new AfConcepto();
//        afUsuario = new AfUsuario();
        listarAfConceptos();
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
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public List<AfConcepto> getListaAfConceptos() {
        return listaAfConceptos;
    }

    public void setListaAfConceptos(List<AfConcepto> listaAfConceptos) {
        this.listaAfConceptos = listaAfConceptos;
    }
}
