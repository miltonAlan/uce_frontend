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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean(name = "ManagedActivoFijo")
@SessionScoped
public class ManagedActivoFijo implements Serializable {

    @EJB
    private AfUsuarioFacadeLocal manejadorAfUsuario;
    private AfUsuario afUsuario;
    private List<AfUsuario> listaAfUsuarios;
    private String nombreResponsable;
    private List<String> nombresUsuarios = new ArrayList<String>();
    private Map<String, Integer> mapUsuarios = new HashMap<String, Integer>();
    private PieChartModel torta;

    public List<String> getNombresUsuarios() {
        return nombresUsuarios;
    }

    public void setNombresUsuarios() {

        for (AfUsuario us : this.listaAfUsuarios) {
            nombresUsuarios.add(us.getAuNombre() + " " + us.getAuApellido());
            mapUsuarios.put((us.getAuNombre() + " " + us.getAuApellido()), us.getAuConsecutivo());
        }
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }
    
    public void graficar(){
    torta=new PieChartModel();
    
        // for(AfActivoFijo afa: afFacade.listar()){
           for(AfActivoFijo afa: manejadorAfActivoFijo.findAll()){
         torta.set(afa.getAfMarca(), afa.getAfConsecutivo());
               System.out.println("xxxxx :" + afa);
           }
        
        //torta.set("Estadistica de concepto");
    }

    public PieChartModel getTorta() {
        return torta;
    }

    public void setTorta(PieChartModel torta) {
        this.torta = torta;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }
    @EJB
    private AfConceptoFacadeLocal manejadorAfConcepto;
    private AfConcepto afConcepto;
    private List<AfConcepto> listaAfConceptos;
    private List<String> nombresConceptos = new ArrayList<String>();
    private String nombreConcepto;
    private Map<String, Integer> mapConceptos = new HashMap<String, Integer>();

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
         this.setListaAfConceptos(manejadorAfConcepto.findAll());
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
    private List<AfActivoFijo> listaActivosFijos;

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

    public ManagedActivoFijo() {
    }

    public void grabarAfUsuario() {
        manejadorAfUsuario.create(afUsuario);
    }

    public void asignarConsecutivo() {
        int next = this.listaActivosFijos.size();
        this.afActivoFijo.setAfConsecutivo(next + 1);
    }

    public void grabarActivoFijo() {
        try {
            asignarConsecutivo();
            this.afActivoFijo.setAuAfConsecutivo(manejadorAfUsuario.find(mapUsuarios.get(nombreResponsable)));
            this.afActivoFijo.setAcAfConcepto(manejadorAfConcepto.buscarPorConcepto(nombreConcepto));
            afActivoFijo.setAfEstado("Vigente");
            afActivoFijo.setAfDepAcum(0.0);
            afActivoFijo.setAfPeriodoDep(0);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            afActivoFijo.setAfFechaCreacion(sdf.format(new Date()));
            System.out.println("afActivoFijo.getAfCodigoBarras " + afActivoFijo.getAfCodigoBarras());
            System.out.println("afActivoFijo.getAfEstado" + afActivoFijo.getAfEstado());
            System.out.println("afActivoFijo.getAfFechaCreacion" + afActivoFijo.getAfFechaCreacion());
            System.out.println("afActivoFijo.getAfMarca" + afActivoFijo.getAfMarca());
            System.out.println("afActivoFijo.getAfModelo" + afActivoFijo.getAfModelo());
            System.out.println("afActivoFijo.getAcAfConcepto" + afActivoFijo.getAcAfConcepto());
            System.out.println("afActivoFijo.getAfConsecutivo" + afActivoFijo.getAfConsecutivo());
            System.out.println("afActivoFijo.getAfDepAcum" + afActivoFijo.getAfDepAcum());
            System.out.println("afActivoFijo.getAfPeriodoDep" + afActivoFijo.getAfPeriodoDep());
            System.out.println("afActivoFijo.getAfValor" + afActivoFijo.getAfValor());
            System.out.println("afActivoFijo.getAuAfConsecutivo" + afActivoFijo.getAuAfConsecutivo());
            this.manejadorAfActivoFijo.create(afActivoFijo);
            this.setListaActivosFijos(manejadorAfActivoFijo.findAll());
            addMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Registro guardado exitosamente");
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Informacion", "Falló el ingreso del Activo Fijo \n Consulte con el administrador");
        }
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void listarUsuarios() {
        this.setListaAfUsuarios(manejadorAfUsuario.findAll());
    }

    public void listarActivosFijos() {
        this.setListaActivosFijos(manejadorAfActivoFijo.findAll());

    }

    public void listarConceptos() {
        this.setListaAfConceptos(manejadorAfConcepto.findAll());
    }

    @PostConstruct
    private void inicio() {
        afActivoFijo = new AfActivoFijo();
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
//            activo.setAcAfConcepto(manejadorAfConcepto.buscarPorConcepto(nombreConcepto));
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
