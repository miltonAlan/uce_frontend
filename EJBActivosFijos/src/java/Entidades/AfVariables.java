/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mpaucar
 */
@Entity
@Table(name = "af_variables")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AfVariables.findAll", query = "SELECT a FROM AfVariables a"),
    @NamedQuery(name = "AfVariables.findByAvConsecutivo", query = "SELECT a FROM AfVariables a WHERE a.avConsecutivo = :avConsecutivo"),
    @NamedQuery(name = "AfVariables.findByAvVariable", query = "SELECT a FROM AfVariables a WHERE a.avVariable = :avVariable"),
    @NamedQuery(name = "AfVariables.findByAvDescripcion", query = "SELECT a FROM AfVariables a WHERE a.avDescripcion = :avDescripcion"),
    @NamedQuery(name = "AfVariables.findByAvValor", query = "SELECT a FROM AfVariables a WHERE a.avValor = :avValor")})
public class AfVariables implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "av_consecutivo")
    private Double avConsecutivo;
    @Size(max = 255)
    @Column(name = "av_variable")
    private String avVariable;
    @Size(max = 255)
    @Column(name = "av_descripcion")
    private String avDescripcion;
    @Size(max = 255)
    @Column(name = "av_valor")
    private String avValor;

    public AfVariables() {
    }

    public AfVariables(Double avConsecutivo) {
        this.avConsecutivo = avConsecutivo;
    }

    public Double getAvConsecutivo() {
        return avConsecutivo;
    }

    public void setAvConsecutivo(Double avConsecutivo) {
        this.avConsecutivo = avConsecutivo;
    }

    public String getAvVariable() {
        return avVariable;
    }

    public void setAvVariable(String avVariable) {
        this.avVariable = avVariable;
    }

    public String getAvDescripcion() {
        return avDescripcion;
    }

    public void setAvDescripcion(String avDescripcion) {
        this.avDescripcion = avDescripcion;
    }

    public String getAvValor() {
        return avValor;
    }

    public void setAvValor(String avValor) {
        this.avValor = avValor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (avConsecutivo != null ? avConsecutivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfVariables)) {
            return false;
        }
        AfVariables other = (AfVariables) object;
        if ((this.avConsecutivo == null && other.avConsecutivo != null) || (this.avConsecutivo != null && !this.avConsecutivo.equals(other.avConsecutivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.AfVariables[ avConsecutivo=" + avConsecutivo + " ]";
    }
    
}
