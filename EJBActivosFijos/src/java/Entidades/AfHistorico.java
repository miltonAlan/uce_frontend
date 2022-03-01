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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = "AfHistorico.findByAfAhConsecutivo", query = "SELECT a FROM AfHistorico a WHERE a.afAhConsecutivo = :afAhConsecutivo"),
    @NamedQuery(name = "AfHistorico.findByAhFecha", query = "SELECT a FROM AfHistorico a WHERE a.ahFecha = :ahFecha"),
    @NamedQuery(name = "AfHistorico.findByAhMovimiento", query = "SELECT a FROM AfHistorico a WHERE a.ahMovimiento = :ahMovimiento"),
    @NamedQuery(name = "AfHistorico.findByAhValor", query = "SELECT a FROM AfHistorico a WHERE a.ahValor = :ahValor"),
    @NamedQuery(name = "AfHistorico.findByAhPeriodo", query = "SELECT a FROM AfHistorico a WHERE a.ahPeriodo = :ahPeriodo"),
    @NamedQuery(name = "AfHistorico.findByAhResponsable", query = "SELECT a FROM AfHistorico a WHERE a.ahResponsable = :ahResponsable")})
public class AfHistorico implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "af_ah_consecutivo")
    private Double afAhConsecutivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ah_fecha")
    private String ahFecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ah_movimiento")
    private String ahMovimiento;
    @Column(name = "ah_valor")
    private Double ahValor;
    @Column(name = "ah_periodo")
    private Double ahPeriodo;
    @Size(max = 100)
    @Column(name = "ah_responsable")
    private String ahResponsable;
    @JoinColumn(name = "af_ah_consecutivo", referencedColumnName = "af_consecutivo", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private AfActivoFijo afActivoFijo;

    public AfHistorico() {
    }

    public AfHistorico(Double afAhConsecutivo) {
        this.afAhConsecutivo = afAhConsecutivo;
    }

    public AfHistorico(Double afAhConsecutivo, String ahFecha, String ahMovimiento) {
        this.afAhConsecutivo = afAhConsecutivo;
        this.ahFecha = ahFecha;
        this.ahMovimiento = ahMovimiento;
    }

    public Double getAfAhConsecutivo() {
        return afAhConsecutivo;
    }

    public void setAfAhConsecutivo(Double afAhConsecutivo) {
        this.afAhConsecutivo = afAhConsecutivo;
    }

    public String getAhFecha() {
        return ahFecha;
    }

    public void setAhFecha(String ahFecha) {
        this.ahFecha = ahFecha;
    }

    public String getAhMovimiento() {
        return ahMovimiento;
    }

    public void setAhMovimiento(String ahMovimiento) {
        this.ahMovimiento = ahMovimiento;
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
        hash += (afAhConsecutivo != null ? afAhConsecutivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfHistorico)) {
            return false;
        }
        AfHistorico other = (AfHistorico) object;
        if ((this.afAhConsecutivo == null && other.afAhConsecutivo != null) || (this.afAhConsecutivo != null && !this.afAhConsecutivo.equals(other.afAhConsecutivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.AfHistorico[ afAhConsecutivo=" + afAhConsecutivo + " ]";
    }
    
}
