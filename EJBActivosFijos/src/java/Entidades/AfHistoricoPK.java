/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author mpaucar
 */
@Embeddable
public class AfHistoricoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "af_ah_consecutivo")
    private double afAhConsecutivo;
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

    public AfHistoricoPK() {
    }

    public AfHistoricoPK(double afAhConsecutivo, String ahFecha, String ahMovimiento) {
        this.afAhConsecutivo = afAhConsecutivo;
        this.ahFecha = ahFecha;
        this.ahMovimiento = ahMovimiento;
    }

    public double getAfAhConsecutivo() {
        return afAhConsecutivo;
    }

    public void setAfAhConsecutivo(double afAhConsecutivo) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) afAhConsecutivo;
        hash += (ahFecha != null ? ahFecha.hashCode() : 0);
        hash += (ahMovimiento != null ? ahMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfHistoricoPK)) {
            return false;
        }
        AfHistoricoPK other = (AfHistoricoPK) object;
        if (this.afAhConsecutivo != other.afAhConsecutivo) {
            return false;
        }
        if ((this.ahFecha == null && other.ahFecha != null) || (this.ahFecha != null && !this.ahFecha.equals(other.ahFecha))) {
            return false;
        }
        if ((this.ahMovimiento == null && other.ahMovimiento != null) || (this.ahMovimiento != null && !this.ahMovimiento.equals(other.ahMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.AfHistoricoPK[ afAhConsecutivo=" + afAhConsecutivo + ", ahFecha=" + ahFecha + ", ahMovimiento=" + ahMovimiento + " ]";
    }
    
}
