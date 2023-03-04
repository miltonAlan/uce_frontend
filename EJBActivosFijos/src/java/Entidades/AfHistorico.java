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
    @NamedQuery(name = "AfHistorico.findByAhResponsableAnt", query = "SELECT a FROM AfHistorico a WHERE a.ahResponsableAnt = :ahResponsableAnt"),
    @NamedQuery(name = "AfHistorico.findByAhResponsableAct", query = "SELECT a FROM AfHistorico a WHERE a.ahResponsableAct = :ahResponsableAct")})
public class AfHistorico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "af_ah_consecutivo")
    private Integer afAhConsecutivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ah_fecha")
    private String ahFecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "ah_movimiento")
    private String ahMovimiento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ah_valor")
    private Double ahValor;
    @Column(name = "ah_periodo")
    private Double ahPeriodo;
    @Size(max = 100)
    @Column(name = "ah_responsable_ant")
    private String ahResponsableAnt;
    @Size(max = 100)
    @Column(name = "ah_responsable_act")
    private String ahResponsableAct;

    public AfHistorico() {
    }

    public AfHistorico(Integer afAhConsecutivo) {
        this.afAhConsecutivo = afAhConsecutivo;
    }

    public AfHistorico(Integer afAhConsecutivo, String ahFecha, String ahMovimiento) {
        this.afAhConsecutivo = afAhConsecutivo;
        this.ahFecha = ahFecha;
        this.ahMovimiento = ahMovimiento;
    }

    public Integer getAfAhConsecutivo() {
        return afAhConsecutivo;
    }

    public void setAfAhConsecutivo(Integer afAhConsecutivo) {
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

    public String getAhResponsableAnt() {
        return ahResponsableAnt;
    }

    public void setAhResponsableAnt(String ahResponsableAnt) {
        this.ahResponsableAnt = ahResponsableAnt;
    }

    public String getAhResponsableAct() {
        return ahResponsableAct;
    }

    public void setAhResponsableAct(String ahResponsableAct) {
        this.ahResponsableAct = ahResponsableAct;
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
        return "AfHistorico{" + "afAhConsecutivo=" + afAhConsecutivo + ", ahFecha=" + ahFecha + ", ahMovimiento=" + ahMovimiento + ", ahValor=" + ahValor + ", ahPeriodo=" + ahPeriodo + ", ahResponsableAnt=" + ahResponsableAnt + ", ahResponsableAct=" + ahResponsableAct + '}';
    }
}
