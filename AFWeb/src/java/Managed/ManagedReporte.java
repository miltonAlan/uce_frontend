/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Managed;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import java.io.File;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author AREVALO
 */
@ManagedBean
@RequestScoped
public class ManagedReporte {

    /**
     * Creates a new instance of ManagedReporte
     */
public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
Document pdf = (Document) document;
pdf.addHeader("Prueba", "Intento");
pdf.open();
pdf.setPageSize(PageSize.A4);
ServletContext servletContext = (ServletContext)
FacesContext.getCurrentInstance().getExternalContext().getContext();
String logo = servletContext.getRealPath("") + File.separator + "images" +
File.separator + "logo.png";
pdf.add(Image.getInstance(logo));

}

    
}
