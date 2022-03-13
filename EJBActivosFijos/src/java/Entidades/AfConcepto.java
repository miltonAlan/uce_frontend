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
 * @author User
 */
@Entity
@Table(name = "af_concepto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AfConcepto.findAll", query = "SELECT a FROM AfConcepto a"),
    @NamedQuery(name = "AfConcepto.findByAcConsecutivo", query = "SELECT a FROM AfConcepto a WHERE a.acConsecutivo = :acConsecutivo"),
    @NamedQuery(name = "AfConcepto.findByAcConcepto", query = "SELECT a FROM AfConcepto a WHERE a.acConcepto = :acConcepto"),
    @NamedQuery(name = "AfConcepto.findByAcDepreciable", query = "SELECT a FROM AfConcepto a WHERE a.acDepreciable = :acDepreciable"),
    @NamedQuery(name = "AfConcepto.findByAcEstado", query = "SELECT a FROM AfConcepto a WHERE a.acEstado = :acEstado")})
public class AfConcepto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ac_consecutivo")
    private Integer acConsecutivo;
    @Size(max = 255)
    @Column(name = "ac_concepto")
    private String acConcepto;
    @Size(max = 255)
    @Column(name = "ac_depreciable")
    private String acDepreciable;
    @Size(max = 255)
    @Column(name = "ac_estado")
    private String acEstado;

    public AfConcepto() {
    }

    public AfConcepto(Integer acConsecutivo) {
        this.acConsecutivo = acConsecutivo;
    }

    public Integer getAcConsecutivo() {
        return acConsecutivo;
    }

    public void setAcConsecutivo(Integer acConsecutivo) {
        this.acConsecutivo = acConsecutivo;
    }

    public String getAcConcepto() {
        return acConcepto;
    }

    public void setAcConcepto(String acConcepto) {
        this.acConcepto = acConcepto;
    }

    public String getAcDepreciable() {
        return acDepreciable;
    }

    public void setAcDepreciable(String acDepreciable) {
        this.acDepreciable = acDepreciable;
    }

    public String getAcEstado() {
        return acEstado;
    }

    public void setAcEstado(String acEstado) {
        this.acEstado = acEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (acConsecutivo != null ? acConsecutivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfConcepto)) {
            return false;
        }
        AfConcepto other = (AfConcepto) object;
        if ((this.acConsecutivo == null && other.acConsecutivo != null) || (this.acConsecutivo != null && !this.acConsecutivo.equals(other.acConsecutivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.AfConcepto[ acConsecutivo=" + acConsecutivo + " ]";
    }
    
}
