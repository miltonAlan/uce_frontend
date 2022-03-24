/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Managed;

import Entidades.AfActivoFijo;
import Entidades.AfConcepto;
import Entidades.AfHistorico;
import Entidades.AfUsuario;
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
import java.util.Timer;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "ManagedTraslado")
@SessionScoped
public class ManagedTraslado implements Serializable {

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
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                    AfHistorico historicoTemp = new AfHistorico();
                    asignarConsecutivoHistorico(historicoTemp);

                    historicoTemp.setAhFecha(sdf.format(new Date()));
                    historicoTemp.setAhResponsableAct(nombreResponsableAct);
                    historicoTemp.setAhResponsableAnt(nombreResponsableAnt);
                    historicoTemp.setAhMovimiento("Traslado de responsable-" + "Activo Fijo: "
                            + activoFijo.getAfMarca() + " " + activoFijo.getAfModelo() + " desde: " + nombreResponsableAnt + " hacia: " +nombreResponsableAct );
                    historicoTemp.setAhPeriodo(0.0);
                    historicoTemp.setAhValor(0.0);
                    System.out.println("------------");
                    System.out.println("XX:historicoTemp getAfAhConsecutivo" + historicoTemp.getAfAhConsecutivo());
                    System.out.println("XX:historicoTemp getAhFecha" + historicoTemp.getAhFecha());
                    System.out.println("XX:historicoTemp getAhMovimiento" + historicoTemp.getAhMovimiento());
                    System.out.println("XX:historicoTemp getAhPeriodo" + historicoTemp.getAhPeriodo());
                    System.out.println("XX:historicoTemp getAhValor" + historicoTemp.getAhValor());
                    System.out.println("XX:historicoTemp getAhResponsableAct" + historicoTemp.getAhResponsableAct());
                    System.out.println("XX:historicoTemp getAhResponsableAnt" + historicoTemp.getAhResponsableAnt());
                    System.out.println("------------");
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


            } else {
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "El responsable no puede ser el mismo"));
            }
        } else {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "El responsable no tiene Activos Fijos asignados"));
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
