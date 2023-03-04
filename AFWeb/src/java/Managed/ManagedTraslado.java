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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

@ManagedBean(name = "ManagedTraslado")
@SessionScoped
public class ManagedTraslado implements Serializable {

    private final static Logger logger = Logger.getLogger(ManagedTraslado.class);
    LoggerConfig loggerConfig = new LoggerConfig();
    HashMap<String, String> parametros = new HashMap<String, String>();
    @EJB
    private AfUsuarioFacadeLocal manejadorAfUsuario;
    private AfUsuario afUsuario;
    private List<AfUsuario> listaAfUsuarios;
    private String nombreResponsableAct;

    public String getNombreResponsableAct() {
        return nombreResponsableAct;
    }

    public void setNombreResponsableAct(String nombreResponsableAct) {
        this.nombreResponsableAct = nombreResponsableAct;
    }

    public String getNombreResponsableAnt() {
        return nombreResponsableAnt;
    }

    public void setNombreResponsableAnt(String nombreResponsableAnt) {
        this.nombreResponsableAnt = nombreResponsableAnt;
    }
    private String nombreResponsableAnt;
    private List<String> nombresUsuarios = new ArrayList<String>();
    private Map<String, Integer> mapUsuarios = new HashMap<String, Integer>();

    public List<String> getNombresUsuarios() {
        return nombresUsuarios;
    }

    public void setNombresUsuarios() {

        for (AfUsuario us : this.listaAfUsuarios) {
            nombresUsuarios.add(us.getAuNombre() + " " + us.getAuApellido());
            mapUsuarios.put((us.getAuNombre() + " " + us.getAuApellido()), us.getAuConsecutivo());
        }
    }
    @EJB
    private AfConceptoFacadeLocal manejadorAfConcepto;
    private AfConcepto afConcepto;
    private List<AfConcepto> listaAfConceptos;
    private List<String> nombresConceptos = new ArrayList<String>();
    private String nombreConcepto;
    private Map<String, Integer> mapConceptos = new HashMap<String, Integer>();
    private List<AfActivoFijo> listaAntResponsables;
    private List<AfActivoFijo> listaActResponsables;

    public String getNombreConcepto() {
        return nombreConcepto;
    }

    public void setNombreConcepto(String nombreConcepto) {
        this.nombreConcepto = nombreConcepto;
    }

    public List<String> getNombresConceptos() {
        return nombresConceptos;
    }

    public void setNombresConceptos() {
        for (AfConcepto afConcepto : this.listaAfConceptos) {
            nombresConceptos.add(afConcepto.getAcConcepto());
            mapConceptos.put(afConcepto.getAcConcepto(), afConcepto.getAcConsecutivo());
        }
    }

    public void setCodigoConcepto() {
    }

    public void setListaAfConceptos(List<AfConcepto> listaAfConceptos) {
        this.listaAfConceptos = listaAfConceptos;
    }
    @EJB
    private AfActivoFijoFacadeLocal manejadorAfActivoFijo;
    private AfActivoFijo afActivoFijo;
    private double codCliente;
    private double codConcepto;
    private List<AfActivoFijo> listaActivosFijos = new ArrayList<AfActivoFijo>();

    public List<AfActivoFijo> getListaActivosFijos() {
        return listaActivosFijos;
    }

    public void setListaActivosFijos(List<AfActivoFijo> listaActivosFijos) {
        this.listaActivosFijos = listaActivosFijos;
    }
    @EJB
    private AfHistoricoFacadeLocal manejadorAfHistorico;
    private AfHistorico afHistorico;
    private double codActivoFijo;

    public ManagedTraslado() {
    }

    public void grabarAfUsuario() {
        manejadorAfUsuario.create(afUsuario);
    }

    public void asignarConsecutivo() {
        int next = this.listaActivosFijos.size();
        this.afActivoFijo.setAfConsecutivo(next + 1);
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void listarUsuarios() {
        this.setListaAfUsuarios(manejadorAfUsuario.findAll());
    }

    public void listarActivosFijos() {
    }

    public void buscarResponsable() {
        int tempuser = mapUsuarios.get(nombreResponsableAnt);
        List<AfActivoFijo> listaTemp = manejadorAfActivoFijo.findAll();
        listaAntResponsables = new ArrayList<AfActivoFijo>();
        for (AfActivoFijo afActivoFijo1 : listaTemp) {
            if (afActivoFijo1.getAuAfConsecutivo().getAuConsecutivo() == tempuser) {
                listaAntResponsables.add(afActivoFijo1);
            }
        }
        parametros.put("nombreResponsableActual", nombreResponsableAnt);
//        parametros.put("cantidadActivosFijosACargo",String.valueOf(listaAntResponsables.size()));
        loggerConfig.setMensajeLog("buscarResponsable()", "Consulta de Activos Fijos asignados a cierto responsable", parametros);
        logger.info(loggerConfig.getMensajeLog());

        this.listaActivosFijos = listaAntResponsables;
    }

    public void asignarConsecutivoHistorico(AfHistorico historico) {
        int tempSize = manejadorAfHistorico.findAll().size();
        historico.setAfAhConsecutivo(tempSize + 1);
    }

    public void trasladarResponsable() {
        if (listaAntResponsables != null) {
            if (!nombreResponsableAct.equalsIgnoreCase(nombreResponsableAnt)) {
                int tempuser = mapUsuarios.get(nombreResponsableAct);
                AfUsuario nuevoResponsable = manejadorAfUsuario.find(tempuser);
                List<AfActivoFijo> listaTemp = manejadorAfActivoFijo.findAll();
                for (AfActivoFijo activoFijo : listaAntResponsables) {
                    activoFijo.setAuAfConsecutivo(nuevoResponsable);
                    SimpleDateFormat sdf = new SimpleDateFormat(LoggerConfig.dateFormat);

                    AfHistorico historicoTemp = new AfHistorico();
                    asignarConsecutivoHistorico(historicoTemp);

                    historicoTemp.setAhFecha(sdf.format(new Date()));
                    historicoTemp.setAhResponsableAct(nombreResponsableAct);
                    historicoTemp.setAhResponsableAnt(nombreResponsableAnt);
                    historicoTemp.setAhMovimiento("Traslado de responsable-" + "Activo Fijo: "
                            + activoFijo.getAfMarca() + " " + activoFijo.getAfModelo() + " desde: " + nombreResponsableAnt + " hacia: " + nombreResponsableAct);
                    historicoTemp.setAhPeriodo(0.0);
                    historicoTemp.setAhValor(0.0);
                    manejadorAfHistorico.create(historicoTemp);
                    manejadorAfActivoFijo.edit(activoFijo);

                }
                int tempuser2 = mapUsuarios.get(nombreResponsableAct);
                List<AfActivoFijo> listaTemp2 = manejadorAfActivoFijo.findAll();
                listaAntResponsables = new ArrayList<AfActivoFijo>();
                for (AfActivoFijo afActivoFijo1 : listaTemp2) {
                    if (afActivoFijo1.getAuAfConsecutivo().getAuConsecutivo() == tempuser2) {
                        listaAntResponsables.add(afActivoFijo1);
                    }
                }
                this.listaActivosFijos = listaAntResponsables;
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Trasladando Activos fijos de " + nombreResponsableAnt
                        + " hacia  " + nombreResponsableAct + "\n Por favor espere un momento..."));
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Traslado realizado exitosamente"));

                parametros.put("nombreResponsableActual", nombreResponsableAnt);
                parametros.put("nombreResponsableNuevo", nombreResponsableAct);
                loggerConfig.setMensajeLog("trasladarResponsable()", "Traslada los activos fijos a cargo de una persona hacia otra", parametros);
                logger.info(loggerConfig.getMensajeLog());
                parametros.clear();
            } else {
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "El responsable no puede ser el mismo"));
                parametros.put("ERRORMSG", "El responsable no puede ser el mismo");
                parametros.put("nombreResponsableActual", nombreResponsableAnt);
                parametros.put("nombreResponsableNuevo", nombreResponsableAct);
                loggerConfig.setMensajeLog("trasladarResponsable()", "Traslada los activos fijos a cargo de una persona hacia otra", parametros);
                logger.error(loggerConfig.getMensajeLog());
                parametros.clear();
            }
        } else {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "El responsable no tiene Activos Fijos asignados"));
            parametros.put("ERRORMSG", "El responsable no tiene Activos Fijos asignados");
            parametros.put("nombreResponsableActual", nombreResponsableAnt);
            loggerConfig.setMensajeLog("trasladarResponsable()", "Traslada los activos fijos a cargo de una persona hacia otra", parametros);
            logger.error(loggerConfig.getMensajeLog());
            parametros.clear();
        }

    }

    public void listarConceptos() {
        this.setListaAfConceptos(manejadorAfConcepto.findAll());
    }

    @PostConstruct
    private void inicio() {
        afActivoFijo = new AfActivoFijo();
        afHistorico = new AfHistorico();
        listarActivosFijos();
        listarConceptos();
        listarUsuarios();
        setNombresConceptos();
        setNombresUsuarios();

    }

    public String buscarConcepto(AfConcepto concepto) {
        String retult = null;
        retult = manejadorAfConcepto.find(concepto.getAcConsecutivo()).getAcConcepto();
        return retult;
    }

    public void editarActivo(AfActivoFijo activo) {
        try {
            activo.setAcAfConcepto(manejadorAfConcepto.buscarPorConcepto(nombreConcepto));
            manejadorAfActivoFijo.edit(activo);
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Registro modificado exitosamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informacion", "Falló el la edición del Activo Fijo \n Consulte con el administrador"));
        }


    }

    public String buscarUsuario(AfUsuario usuario) {
        String retult = null;
        AfUsuario temp = manejadorAfUsuario.find(usuario.getAuConsecutivo());
        retult = temp.getAuNombre() + " " + temp.getAuApellido();
        return retult;
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

    public double getCodActivoFijo() {
        return codActivoFijo;
    }

    public void setCodActivoFijo(double codActivoFijo) {
        this.codActivoFijo = codActivoFijo;
    }
}
