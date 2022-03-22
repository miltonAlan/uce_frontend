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
@Table(name = "af_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AfUsuario.findAll", query = "SELECT a FROM AfUsuario a"),
    @NamedQuery(name = "AfUsuario.findByAuConsecutivo", query = "SELECT a FROM AfUsuario a WHERE a.auConsecutivo = :auConsecutivo"),
    @NamedQuery(name = "AfUsuario.findByAuNombre", query = "SELECT a FROM AfUsuario a WHERE a.auNombre = :auNombre"),
    @NamedQuery(name = "AfUsuario.findByAuApellido", query = "SELECT a FROM AfUsuario a WHERE a.auApellido = :auApellido"),
    @NamedQuery(name = "AfUsuario.findByAuFechaNacimiento", query = "SELECT a FROM AfUsuario a WHERE a.auFechaNacimiento = :auFechaNacimiento"),
    @NamedQuery(name = "AfUsuario.findByAuClave", query = "SELECT a FROM AfUsuario a WHERE a.auClave = :auClave"),
    @NamedQuery(name = "AfUsuario.findByAuLogin", query = "SELECT a FROM AfUsuario a WHERE a.auLogin = :auLogin"),
    @NamedQuery(name = "AfUsuario.findByAuCedula", query = "SELECT a FROM AfUsuario a WHERE a.auCedula = :auCedula"),
    @NamedQuery(name = "AfUsuario.findByAuCargo", query = "SELECT a FROM AfUsuario a WHERE a.auCargo = :auCargo")})
public class AfUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "au_consecutivo")
    private Integer auConsecutivo;
    @Size(max = 100)
    @Column(name = "au_nombre")
    private String auNombre;
    @Size(max = 100)
    @Column(name = "au_apellido")
    private String auApellido;
    @Size(max = 100)
    @Column(name = "au_fecha_nacimiento")
    private String auFechaNacimiento;
    @Size(max = 100)
    @Column(name = "au_clave")
    private String auClave;
    @Size(max = 100)
    @Column(name = "au_login")
    private String auLogin;
    @Size(max = 50)
    @Column(name = "au_cedula")
    private String auCedula;
    @Size(max = 100)
    @Column(name = "au_cargo")
    private String auCargo;
    @OneToMany(mappedBy = "auAfConsecutivo")
    private Collection<AfActivoFijo> afActivoFijoCollection;

    public AfUsuario() {
    }

    public AfUsuario(Integer auConsecutivo) {
        this.auConsecutivo = auConsecutivo;
    }

    public Integer getAuConsecutivo() {
        return auConsecutivo;
    }

    public void setAuConsecutivo(Integer auConsecutivo) {
        this.auConsecutivo = auConsecutivo;
    }

    public String getAuNombre() {
        return auNombre;
    }

    public void setAuNombre(String auNombre) {
        this.auNombre = auNombre;
    }

    public String getAuApellido() {
        return auApellido;
    }

    public void setAuApellido(String auApellido) {
        this.auApellido = auApellido;
    }

    public String getAuFechaNacimiento() {
        return auFechaNacimiento;
    }

    public void setAuFechaNacimiento(String auFechaNacimiento) {
        this.auFechaNacimiento = auFechaNacimiento;
    }

    public String getAuClave() {
        return auClave;
    }

    public void setAuClave(String auClave) {
        this.auClave = auClave;
    }

    public String getAuLogin() {
        return auLogin;
    }

    public void setAuLogin(String auLogin) {
        this.auLogin = auLogin;
    }

    public String getAuCedula() {
        return auCedula;
    }

    public void setAuCedula(String auCedula) {
        this.auCedula = auCedula;
    }

    public String getAuCargo() {
        return auCargo;
    }

    public void setAuCargo(String auCargo) {
        this.auCargo = auCargo;
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
        hash += (auConsecutivo != null ? auConsecutivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfUsuario)) {
            return false;
        }
        AfUsuario other = (AfUsuario) object;
        if ((this.auConsecutivo == null && other.auConsecutivo != null) || (this.auConsecutivo != null && !this.auConsecutivo.equals(other.auConsecutivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.AfUsuario[ auConsecutivo=" + auConsecutivo + " ]";
    }
    
}
