/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mpaucar
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ac_consecutivo")
    private Double acConsecutivo;
    @Size(max = 255)
    @Column(name = "ac_concepto")
    private String acConcepto;
    @Size(max = 1)
    @Column(name = "ac_depreciable")
    private String acDepreciable;
    @Column(name = "ac_estado")
    private Character acEstado;
    @OneToMany(mappedBy = "acAfConcepto")
    private Collection<AfActivoFijo> afActivoFijoCollection;

    public AfConcepto() {
    }

    public AfConcepto(Double acConsecutivo) {
        this.acConsecutivo = acConsecutivo;
    }

    public Double getAcConsecutivo() {
        return acConsecutivo;
    }

    public void setAcConsecutivo(Double acConsecutivo) {
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

    public Character getAcEstado() {
        return acEstado;
    }

    public void setAcEstado(Character acEstado) {
        this.acEstado = acEstado;
    }

    @XmlTransient
    public Collection<AfActivoFijo> getAfActivoFijoCollection() {
        return afActivoFijoCollection;
    }

    public void setAfActivoFijoCollection(Collection<AfActivoFijo> afActivoFijoCollection) {
        this.afActivoFijoCollection = afActivoFijoCollection;
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
