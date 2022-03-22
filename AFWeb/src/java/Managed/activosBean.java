/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Managed;

import Entidades.AfActivoFijo;
import Sesiones.AfActivoFijoFacade;
import Sesiones.AfActivoFijoFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author alejo
 */
@ManagedBean(name = "activosBean")
@ViewScoped
public class activosBean implements Serializable{

    @EJB
    private AfActivoFijoFacade afFacade;
    @EJB
    private AfActivoFijoFacadeLocal manejadorAfActivoFijo;
    
    private List<AfActivoFijo> listado;
    
    private PieChartModel torta;
    
    public activosBean() {
    }
    
    
    public void listar(){
    //listado=afFacade.listar();
    //graficar();
    }
    
    public void graficar(){
    torta=new PieChartModel();
    
        // for(AfActivoFijo afa: afFacade.listar()){
           for(AfActivoFijo afa: manejadorAfActivoFijo.findAll()){
            // torta.set(afa.getAfMarca(), afa.getAfConsecutivo());
               System.out.println("xxxxx :" + afa);
           }
        
        
    }

    public List<AfActivoFijo> getListado() {
        return listado;
    }

    public void setListado(List<AfActivoFijo> listado) {
        this.listado = listado;
    }

    public PieChartModel getTorta() {
        return torta;
    }

    public void setTorta(PieChartModel torta) {
        this.torta = torta;
    }

   
}
