/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.salvapy.admin.salvapy.admin.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hectorvillalba
 */
@Entity
@Table(name = "acercadesalvapy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acercadesalvapy.findAll", query = "SELECT a FROM Acercadesalvapy a"),
    @NamedQuery(name = "Acercadesalvapy.findById", query = "SELECT a FROM Acercadesalvapy a WHERE a.id = :id"),
    @NamedQuery(name = "Acercadesalvapy.findByDescripcion", query = "SELECT a FROM Acercadesalvapy a WHERE a.descripcion = :descripcion")})
public class Acercadesalvapy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "descripcion")
    private String descripcion;

    public Acercadesalvapy() {
    }

    public Acercadesalvapy(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acercadesalvapy)) {
            return false;
        }
        Acercadesalvapy other = (Acercadesalvapy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.salvapy.admin.salvapy.admin.entities.Acercadesalvapy[ id=" + id + " ]";
    }
    
}
