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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alejo
 */
@Entity
@Table(name = "af_ejemplo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AfEjemplo.findAll", query = "SELECT a FROM AfEjemplo a"),
    @NamedQuery(name = "AfEjemplo.findByAfEjem", query = "SELECT a FROM AfEjemplo a WHERE a.afEjem = :afEjem")})
public class AfEjemplo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "af_ejem")
    private Character afEjem;

    public AfEjemplo() {
    }

    public AfEjemplo(Character afEjem) {
        this.afEjem = afEjem;
    }

    public Character getAfEjem() {
        return afEjem;
    }

    public void setAfEjem(Character afEjem) {
        this.afEjem = afEjem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (afEjem != null ? afEjem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfEjemplo)) {
            return false;
        }
        AfEjemplo other = (AfEjemplo) object;
        if ((this.afEjem == null && other.afEjem != null) || (this.afEjem != null && !this.afEjem.equals(other.afEjem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.AfEjemplo[ afEjem=" + afEjem + " ]";
    }
    
}
