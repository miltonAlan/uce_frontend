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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "JSFManaged")
@SessionScoped
public class JSFManaged implements Serializable {

    @EJB
    private AfUsuarioFacadeLocal manejadorAfUsuario;
    private AfUsuario afUsuario;
    private List<AfUsuario> listaAfUsuarios;
    @EJB
    private AfConceptoFacadeLocal manejadorAfConcepto;
    private AfConcepto afConcepto;
    @EJB
    private AfActivoFijoFacadeLocal manejadorAfActivoFijo;
    private AfActivoFijo afActivoFijo;
    private double codCliente;
    private double codConcepto;
    @EJB
    private AfHistoricoFacadeLocal manejadorAfHistorico;
    private AfHistorico afHistorico;
    private double codActivoFijo;

    public JSFManaged() {
    }

    public void grabarAfUsuario() {
        manejadorAfUsuario.create(afUsuario);
    }

    public void grabarAfConcepto() {
        manejadorAfConcepto.create(afConcepto);
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

    @PostConstruct
    private void inicio() {
        afUsuario = new AfUsuario();
        afConcepto = new AfConcepto();
        afActivoFijo = new AfActivoFijo();
        afHistorico = new AfHistorico();
        listarAfUsuarios();
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
}
