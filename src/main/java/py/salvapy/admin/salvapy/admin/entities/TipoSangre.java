/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.salvapy.admin.salvapy.admin.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hectorvillalba
 */
@Entity
@Table(name = "tipo_sangre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoSangre.findAll", query = "SELECT t FROM TipoSangre t"),
    @NamedQuery(name = "TipoSangre.findByNombre", query = "SELECT t FROM TipoSangre t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoSangre.findByDescripcion", query = "SELECT t FROM TipoSangre t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoSangre.findByUsuarioAltaId", query = "SELECT t FROM TipoSangre t WHERE t.usuarioAltaId = :usuarioAltaId"),
    @NamedQuery(name = "TipoSangre.findByFechaAlta", query = "SELECT t FROM TipoSangre t WHERE t.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "TipoSangre.findByUsuarioModificacionId", query = "SELECT t FROM TipoSangre t WHERE t.usuarioModificacionId = :usuarioModificacionId"),
    @NamedQuery(name = "TipoSangre.findByFechaModificacion", query = "SELECT t FROM TipoSangre t WHERE t.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "TipoSangre.findByUsuarioEliminacionId", query = "SELECT t FROM TipoSangre t WHERE t.usuarioEliminacionId = :usuarioEliminacionId"),
    @NamedQuery(name = "TipoSangre.findByFechaEliminacion", query = "SELECT t FROM TipoSangre t WHERE t.fechaEliminacion = :fechaEliminacion"),
    @NamedQuery(name = "TipoSangre.findById", query = "SELECT t FROM TipoSangre t WHERE t.id = :id")})
public class TipoSangre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "usuario_alta_id")
    private BigInteger usuarioAltaId;
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIME)
    private Date fechaAlta;
    @Column(name = "usuario_modificacion_id")
    private BigInteger usuarioModificacionId;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIME)
    private Date fechaModificacion;
    @Column(name = "usuario_eliminacion_id")
    private BigInteger usuarioEliminacionId;
    @Column(name = "fecha_eliminacion")
    @Temporal(TemporalType.TIME)
    private Date fechaEliminacion;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(mappedBy = "tipoSangreId", fetch = FetchType.LAZY)
    private List<Donantes> donantesList;

    public TipoSangre() {
    }

    public TipoSangre(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigInteger getUsuarioAltaId() {
        return usuarioAltaId;
    }

    public void setUsuarioAltaId(BigInteger usuarioAltaId) {
        this.usuarioAltaId = usuarioAltaId;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public BigInteger getUsuarioModificacionId() {
        return usuarioModificacionId;
    }

    public void setUsuarioModificacionId(BigInteger usuarioModificacionId) {
        this.usuarioModificacionId = usuarioModificacionId;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public BigInteger getUsuarioEliminacionId() {
        return usuarioEliminacionId;
    }

    public void setUsuarioEliminacionId(BigInteger usuarioEliminacionId) {
        this.usuarioEliminacionId = usuarioEliminacionId;
    }

    public Date getFechaEliminacion() {
        return fechaEliminacion;
    }

    public void setFechaEliminacion(Date fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public List<Donantes> getDonantesList() {
        return donantesList;
    }

    public void setDonantesList(List<Donantes> donantesList) {
        this.donantesList = donantesList;
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
        if (!(object instanceof TipoSangre)) {
            return false;
        }
        TipoSangre other = (TipoSangre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.salvapy.admin.salvapy.admin.entities.TipoSangre[ id=" + id + " ]";
    }
    
}
