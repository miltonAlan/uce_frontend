/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mpaucar
 */
@Entity
@Table(name = "af_historico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AfHistorico.findAll", query = "SELECT a FROM AfHistorico a"),
    @NamedQuery(name = "AfHistorico.findByAfAhConsecutivo", query = "SELECT a FROM AfHistorico a WHERE a.afHistoricoPK.afAhConsecutivo = :afAhConsecutivo"),
    @NamedQuery(name = "AfHistorico.findByAhFecha", query = "SELECT a FROM AfHistorico a WHERE a.afHistoricoPK.ahFecha = :ahFecha"),
    @NamedQuery(name = "AfHistorico.findByAhMovimiento", query = "SELECT a FROM AfHistorico a WHERE a.afHistoricoPK.ahMovimiento = :ahMovimiento"),
    @NamedQuery(name = "AfHistorico.findByAhValor", query = "SELECT a FROM AfHistorico a WHERE a.ahValor = :ahValor"),
    @NamedQuery(name = "AfHistorico.findByAhPeriodo", query = "SELECT a FROM AfHistorico a WHERE a.ahPeriodo = :ahPeriodo"),
    @NamedQuery(name = "AfHistorico.findByAhResponsable", query = "SELECT a FROM AfHistorico a WHERE a.ahResponsable = :ahResponsable")})
public class AfHistorico implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AfHistoricoPK afHistoricoPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ah_valor")
    private Double ahValor;
    @Column(name = "ah_periodo")
    private Double ahPeriodo;
    @Size(max = 100)
    @Column(name = "ah_responsable")
    private String ahResponsable;
    @JoinColumn(name = "af_ah_consecutivo", referencedColumnName = "af_consecutivo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AfActivoFijo afActivoFijo;

    public AfHistorico() {
    }

    public AfHistorico(AfHistoricoPK afHistoricoPK) {
        this.afHistoricoPK = afHistoricoPK;
    }

    public AfHistorico(double afAhConsecutivo, String ahFecha, String ahMovimiento) {
        this.afHistoricoPK = new AfHistoricoPK(afAhConsecutivo, ahFecha, ahMovimiento);
    }

    public AfHistoricoPK getAfHistoricoPK() {
        return afHistoricoPK;
    }

    public void setAfHistoricoPK(AfHistoricoPK afHistoricoPK) {
        this.afHistoricoPK = afHistoricoPK;
    }

    public Double getAhValor() {
        return ahValor;
    }

    public void setAhValor(Double ahValor) {
        this.ahValor = ahValor;
    }

    public Double getAhPeriodo() {
        return ahPeriodo;
    }

    public void setAhPeriodo(Double ahPeriodo) {
        this.ahPeriodo = ahPeriodo;
    }

    public String getAhResponsable() {
        return ahResponsable;
    }

    public void setAhResponsable(String ahResponsable) {
        this.ahResponsable = ahResponsable;
    }

    public AfActivoFijo getAfActivoFijo() {
        return afActivoFijo;
    }

    public void setAfActivoFijo(AfActivoFijo afActivoFijo) {
        this.afActivoFijo = afActivoFijo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (afHistoricoPK != null ? afHistoricoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfHistorico)) {
            return false;
        }
        AfHistorico other = (AfHistorico) object;
        if ((this.afHistoricoPK == null && other.afHistoricoPK != null) || (this.afHistoricoPK != null && !this.afHistoricoPK.equals(other.afHistoricoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.AfHistorico[ afHistoricoPK=" + afHistoricoPK + " ]";
    }
    
}
