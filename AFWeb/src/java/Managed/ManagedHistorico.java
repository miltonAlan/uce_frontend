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
import org.primefaces.model.chart.PieChartModel;

@ManagedBean(name = "ManagedHistorico")
@SessionScoped
public class ManagedHistorico implements Serializable {

    private final static Logger logger = Logger.getLogger(ManagedHistorico.class);
    LoggerConfig loggerConfig = new LoggerConfig();
    HashMap<String, String> parametros = new HashMap<String, String>();
    @EJB
    private AfUsuarioFacadeLocal manejadorAfUsuario;
    private AfUsuario afUsuario;
    private List<AfUsuario> listaAfUsuarios;
    private String nombreResponsableAct;
    private String marca;
    private String modelo;
    private String usuario, clave, motivo;
    private AfActivoFijo activoTemp;
    private String descDepre, descReva;
    public double valorDepreApre;
    private PieChartModel pie;
    private Map<String, Integer> mapHistorico = new HashMap<String, Integer>();

    public Map<String, Integer> getMapHistorico() {
        return mapHistorico;
    }

    public void setMapHistorico(Map<String, Integer> mapHistorico) {
        this.mapHistorico = mapHistorico;
    }

    public PieChartModel getPie() {
        return pie;
    }

    public void pintar() {
        pie = new PieChartModel();
        for (AfHistorico afhistorico1 : manejadorAfHistorico.findAll()) {
            pie.set(afhistorico1.getAhMovimiento(), afhistorico1.getAfAhConsecutivo());
        }
    }

    public void setPie(PieChartModel pie) {
        this.pie = pie;
    }

    public double getValorDepreApre() {
        return valorDepreApre;
    }

    public void setValorDepreApre(double valorDepreApre) {
        this.valorDepreApre = valorDepreApre;
    }

    public String getDescDepre() {
        return descDepre;
    }

    public void setDescDepre(String descDepre) {
        this.descDepre = descDepre;
    }

    public String getDescReva() {
        return descReva;
    }

    public void setDescReva(String descReva) {
        this.descReva = descReva;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

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

    public void test() {
//        System.out.println("XX: nombreResponsableAct" + temp);
//        System.out.println("XX: nombreResponsableAnt" + nombreResponsableAnt);
    }

    public void revalorizar() {
        System.out.println("XX : activoTemp" + this.activoTemp);
        System.out.println("XX:descReva  " + this.descReva);
        System.out.println("XX:  valorDepreApre" + this.valorDepreApre);
        AfUsuario userTemp = manejadorAfUsuario.iniciarSesion(clave, usuario);
        if (userTemp != null) {
            System.out.println("XX: " + userTemp.getAuNombre());
            System.out.println("XX: " + userTemp.getAuApellido());
            System.out.println("XX:activoTemp " + activoTemp);
//            activoTemp.setAfDepAcum(valorDepreApre + activoTemp.getAfDepAcum());
            if (filtro == 0) // Depreciacion
            {
                activoTemp.setAfDepAcum(activoTemp.getAfDepAcum() - valorDepreApre);
                activoTemp.setAfValor(activoTemp.getAfValor() - valorDepreApre);
            }
            if (filtro == 1) // Apreciación
            {
                activoTemp.setAfDepAcum(activoTemp.getAfDepAcum() + valorDepreApre);
                activoTemp.setAfValor(activoTemp.getAfValor() + valorDepreApre);
            }
            System.out.println("XX: " + activoTemp.getAfDepAcum());
            manejadorAfActivoFijo.edit(activoTemp);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            AfHistorico historicoTemp = new AfHistorico();
            asignarConsecutivoHistorico(historicoTemp);

            historicoTemp.setAhFecha(sdf.format(new Date()));
            if (filtro == 0) // Depreciacion
            {
                historicoTemp.setAhMovimiento("Depreciación-User: " + usuario + "Activo Fijo: "
                        + activoTemp.getAfMarca() + " " + activoTemp.getAfModelo());
            }
            if (filtro == 1) // Apreciación
            {
                historicoTemp.setAhMovimiento("Revalorización-User: " + usuario + "Activo Fijo: "
                        + activoTemp.getAfMarca() + " " + activoTemp.getAfModelo());
            }

            historicoTemp.setAhPeriodo(0.0);
            historicoTemp.setAhValor(valorDepreApre);
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
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Credenciales Correctas!\n Depreciación/Revalorización del Activo Fijo correcta"));

            parametros.put("clave", clave);
            parametros.put("usuario", usuario);
            parametros.put("valor Depreciacion o Apreciacion", String.valueOf(valorDepreApre));
            parametros.put("Actifo Fijo Apreciado Depreciado", activoTemp.toString());
            parametros.put("Aprecia (1) - Deprecia(0)", String.valueOf(filtro));
            loggerConfig.setMensajeLog("revalorizar()", "Apreciacion o depreciacion del Activo Fijo", parametros);
            logger.info(loggerConfig.getMensajeLog());

        } else {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informacion", "Credenciales incorrectas \n Usted no tiene privilegios para depreciar o revalorizar un Activo Fijo"));
        }
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
    private List<AfHistorico> listaHistoricos;

    public List<AfHistorico> getListaHistoricos() {
        loggerConfig.setMensajeLog("getListaHistoricos()", "Lista los movimientos hechos sobre los activos fijos", parametros);
        logger.info(loggerConfig.getMensajeLog());
        return manejadorAfHistorico.findAll();
    }

    public void setListaHistoricos(List<AfHistorico> listaHistoricos) {
        this.listaHistoricos = listaHistoricos;
    }

    public AfHistorico getAfHistorico() {
        return afHistorico;
    }

    public void setAfHistorico(AfHistorico afHistorico) {
        this.afHistorico = afHistorico;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
        nombreResponsableAct = temp;
    }
    private double codActivoFijo;
    private String temp;

    public ManagedHistorico() {
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
        this.setListaActivosFijos(manejadorAfActivoFijo.findAll());
    }

    public void buscarResponsable() {
//        nombreResponsableAnt = temp;
        System.out.println("XX: " + temp);
//        int tempuser = mapUsuarios.get(nombreResponsableAnt);
//        System.out.println("XX:tempuser " + tempuser);
        System.out.println("XX:nombreResponsableAnt " + nombreResponsableAnt);
//        System.out.println("XX:nombreResponsableAct " + nombreResponsableAct);
//        System.out.println("XX: " + this.listaAfUsuarios);
//        List<AfActivoFijo> listaTemp = manejadorAfActivoFijo.findAll();
//        listaAntResponsables = new ArrayList<AfActivoFijo>();
//        for (AfActivoFijo afActivoFijo1 : listaTemp) {
//            if (afActivoFijo1.getAuAfConsecutivo().getAuConsecutivo() == tempuser) {
//                listaAntResponsables.add(afActivoFijo1);
//            }
//        }
//        this.listaActivosFijos = listaAntResponsables;
        System.out.println("XX:listaActivosFijos " + listaActivosFijos);
        System.out.println("XX:listaAntResponsables " + listaAntResponsables);
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
                            + activoFijo.getAfMarca() + " " + activoFijo.getAfModelo());
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

    public void listarHistorico() {
        this.setListaHistoricos(manejadorAfHistorico.findAll());
    }

    @PostConstruct
    private void inicio() {
        afActivoFijo = new AfActivoFijo();
        afHistorico = new AfHistorico();
        activoTemp = new AfActivoFijo();
        listarActivosFijos();
        listarConceptos();
        listarUsuarios();
        listarHistorico();
//        this.setListaHistoricos(manejadorAfHistorico.findAll());
        System.out.println("");
//        mapHistorico.put("sfd", 123);
//        setNombresConceptos();
        setNombresUsuarios();
//        System.out.println("XX: " + this.listaActivosFijos);

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

    public void validaBaja() {
        AfUsuario userTemp = manejadorAfUsuario.iniciarSesion(clave, usuario);
        if (userTemp != null) {
            System.out.println("XX: " + userTemp.getAuNombre());
            System.out.println("XX: " + userTemp.getAuApellido());
            System.out.println("XX:activoTemp " + activoTemp);
//            activoTemp.setAfEstado("Dado de Baja por: " + userTemp.getAuNombre() + " " + userTemp.getAuApellido());
            if (activoTemp.getAfEstado().equalsIgnoreCase("Dado de Baja")) {
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informacion", "No puede dar de baja un Activo Fijo nuevamente"));
            } else {
                activoTemp.setAfEstado("Dado de Baja");
                manejadorAfActivoFijo.edit(activoTemp);
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Credenciales Correctas!"));
            }

        } else {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informacion", "Credenciales incorrectas \n Usted no tiene privilegios para realizar la baja de un Activo Fijo"));
        }
    }
    private String textoBoton;
    private int filtro;

    public int getFiltro() {
        return filtro;
    }

    public void setFiltro(int filtro) {
        this.filtro = filtro;
    }

    public String getTextoBoton() {
        return textoBoton;
    }

    public void setTextoBoton(String textoBoton) {
        this.textoBoton = textoBoton;
    }

    public void setActivoTemp(AfActivoFijo activo, int filtro) {
        this.activoTemp = activo;
        this.setFiltro(filtro);
        System.out.println("XX: filtro: " + filtro);
        if (filtro == 0) {
            this.textoBoton = "Depreciar";
        }
        if (filtro == 1) {
            this.textoBoton = "Revalorizar";
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
