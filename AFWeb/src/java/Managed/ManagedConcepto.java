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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

@ManagedBean(name = "ManagedConcepto")
@SessionScoped
public class ManagedConcepto implements Serializable {

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
//        this.inicio();
//        System.out.println("XXXXXXXX: " + afConcepto.getAcConcepto());
        if (manejadorAfConcepto.buscarPorConcepto(afConcepto.getAcConcepto()) == null) {
            afConcepto.setAcEstado("Vigente");
            asignarConsecutivo();
//            System.out.println("XXXXXXXX: " + afConcepto.getAcConcepto());
//            System.out.println("XXXXXXXX: " + afConcepto.getAcDepreciable());
            manejadorAfConcepto.create(afConcepto);
            this.setListaAfConceptos(manejadorAfConcepto.findAll());
            addMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Registro guardado exitosamente");

        } else {
            addMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Un concepto con la misma descripción ya existe mamaverga");
        }
    }

    public void grabarActivoFijo() {
        this.afActivoFijo.setAuAfConsecutivo(manejadorAfUsuario.find(codCliente));
        this.afActivoFijo.setAcAfConcepto(manejadorAfConcepto.find(codConcepto));
        this.manejadorAfActivoFijo.create(afActivoFijo);

    }

    public void grabarAfHistorico() {
        this.afHistorico.setAfActivoFijo(manejadorAfActivoFijo.find(codActivoFijo));
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
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Registro modificado exitosamente"));

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
