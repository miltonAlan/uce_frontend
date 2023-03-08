package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "af_activo_fijo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AfActivoFijo.findAll", query = "SELECT a FROM AfActivoFijo a"),
    @NamedQuery(name = "AfActivoFijo.findByAfConsecutivo", query = "SELECT a FROM AfActivoFijo a WHERE a.afConsecutivo = :afConsecutivo"),
    @NamedQuery(name = "AfActivoFijo.findByAfEstado", query = "SELECT a FROM AfActivoFijo a WHERE a.afEstado = :afEstado"),
    @NamedQuery(name = "AfActivoFijo.findByAfMarca", query = "SELECT a FROM AfActivoFijo a WHERE a.afMarca = :afMarca"),
    @NamedQuery(name = "AfActivoFijo.findByAfModelo", query = "SELECT a FROM AfActivoFijo a WHERE a.afModelo = :afModelo"),
    @NamedQuery(name = "AfActivoFijo.findByAfValor", query = "SELECT a FROM AfActivoFijo a WHERE a.afValor = :afValor"),
    @NamedQuery(name = "AfActivoFijo.findByAfDepAcum", query = "SELECT a FROM AfActivoFijo a WHERE a.afDepAcum = :afDepAcum"),
    @NamedQuery(name = "AfActivoFijo.findByAfCodigoBarras", query = "SELECT a FROM AfActivoFijo a WHERE a.afCodigoBarras = :afCodigoBarras"),
    @NamedQuery(name = "AfActivoFijo.findByAfPeriodoDep", query = "SELECT a FROM AfActivoFijo a WHERE a.afPeriodoDep = :afPeriodoDep"),
    @NamedQuery(name = "AfActivoFijo.findByau_af_consecutivo", query = "SELECT a FROM AfActivoFijo a WHERE a.auAfConsecutivo = :au_af_consecutivo")})
public class AfActivoFijo implements Serializable {
 
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "af_consecutivo")
    private Integer afConsecutivo;
    @Size(max = 100)
    @Column(name = "af_estado")
    private String afEstado;
    @Size(max = 100)
    @Column(name = "af_marca")
    private String afMarca;
    @Size(max = 100)
    @Column(name = "af_modelo")
    private String afModelo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "af_valor")
    private Double afValor;
    @Column(name = "af_dep_acum")
    private Double afDepAcum;
    @Size(max = 100)
    @Column(name = "af_codigo_barras")
    private String afCodigoBarras;
    @Column(name = "af_periodo_dep")
    private Integer afPeriodoDep;
    @Size(max = 100)
    @Column(name = "af_fecha_creacion")
    private String afFechaCreacion;
    @JoinColumn(name = "au_af_consecutivo", referencedColumnName = "au_consecutivo")
    @ManyToOne
    private AfUsuario auAfConsecutivo;
    @JoinColumn(name = "ac_af_concepto", referencedColumnName = "ac_consecutivo")
    @ManyToOne
    private AfConcepto acAfConcepto;

    public AfActivoFijo() {
    }

    public AfActivoFijo(Integer afConsecutivo) {
        this.afConsecutivo = afConsecutivo;
    }

    public Integer getAfConsecutivo() {
        return afConsecutivo;
    }

    public void setAfConsecutivo(Integer afConsecutivo) {
        this.afConsecutivo = afConsecutivo;
    }

    public String getAfEstado() {
        return afEstado;
    }

    public void setAfEstado(String afEstado) {
        this.afEstado = afEstado;
    }

    public String getAfMarca() {
        return afMarca;
    }

    public void setAfMarca(String afMarca) {
        this.afMarca = afMarca;
    }

    public String getAfModelo() {
        return afModelo;
    }

    public void setAfModelo(String afModelo) {
        this.afModelo = afModelo;
    }

    public Double getAfValor() {
        return afValor;
    }

    public void setAfValor(Double afValor) {
        this.afValor = afValor;
    }

    public Double getAfDepAcum() {
        return afDepAcum;
    }

    public void setAfDepAcum(Double afDepAcum) {
        this.afDepAcum = afDepAcum;
    }

    public String getAfCodigoBarras() {
        return afCodigoBarras;
    }

    public void setAfCodigoBarras(String afCodigoBarras) {
        this.afCodigoBarras = afCodigoBarras;
    }

    public Integer getAfPeriodoDep() {
        return afPeriodoDep;
    }

    public void setAfPeriodoDep(Integer afPeriodoDep) {
        this.afPeriodoDep = afPeriodoDep;
    }

    public String getAfFechaCreacion() {
        return afFechaCreacion;
    }

    public void setAfFechaCreacion(String afFechaCreacion) {
        this.afFechaCreacion = afFechaCreacion;
    }

    public AfUsuario getAuAfConsecutivo() {
        return auAfConsecutivo;
    }

    public void setAuAfConsecutivo(AfUsuario auAfConsecutivo) {
        this.auAfConsecutivo = auAfConsecutivo;
    }

    public AfConcepto getAcAfConcepto() {
        return acAfConcepto;
    }

    public void setAcAfConcepto(AfConcepto acAfConcepto) {
        this.acAfConcepto = acAfConcepto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (afConsecutivo != null ? afConsecutivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfActivoFijo)) {
            return false;
        }
        AfActivoFijo other = (AfActivoFijo) object;
        if ((this.afConsecutivo == null && other.afConsecutivo != null) || (this.afConsecutivo != null && !this.afConsecutivo.equals(other.afConsecutivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AfActivoFijo{" + "afConsecutivo=" + afConsecutivo + ", afEstado=" + afEstado + ", afMarca=" + afMarca + ", afModelo=" + afModelo + ", afValor=" + afValor + ", afDepAcum=" + afDepAcum + ", afCodigoBarras=" + afCodigoBarras + ", afPeriodoDep=" + afPeriodoDep + ", afFechaCreacion=" + afFechaCreacion + ", auAfConsecutivo=" + auAfConsecutivo + ", acAfConcepto=" + acAfConcepto + '}';
    }
}
