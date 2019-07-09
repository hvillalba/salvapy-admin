/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.salvapy.admin.salvapy.admin.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hectorvillalba
 */
@Entity
@Table(name = "perfiles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perfiles.findAll", query = "SELECT p FROM Perfiles p"),
    @NamedQuery(name = "Perfiles.findByTipoPerfil", query = "SELECT p FROM Perfiles p WHERE p.tipoPerfil = :tipoPerfil"),
    @NamedQuery(name = "Perfiles.findByDescripcion", query = "SELECT p FROM Perfiles p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Perfiles.findByEstado", query = "SELECT p FROM Perfiles p WHERE p.estado = :estado"),
    @NamedQuery(name = "Perfiles.findByUsuarioAltaId", query = "SELECT p FROM Perfiles p WHERE p.usuarioAltaId = :usuarioAltaId"),
    @NamedQuery(name = "Perfiles.findByFechaAlta", query = "SELECT p FROM Perfiles p WHERE p.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Perfiles.findByUsuarioModificacionId", query = "SELECT p FROM Perfiles p WHERE p.usuarioModificacionId = :usuarioModificacionId"),
    @NamedQuery(name = "Perfiles.findByFechaModificacion", query = "SELECT p FROM Perfiles p WHERE p.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "Perfiles.findByUsuarioEliminacionId", query = "SELECT p FROM Perfiles p WHERE p.usuarioEliminacionId = :usuarioEliminacionId"),
    @NamedQuery(name = "Perfiles.findByFechaEliminacion", query = "SELECT p FROM Perfiles p WHERE p.fechaEliminacion = :fechaEliminacion"),
    @NamedQuery(name = "Perfiles.findById", query = "SELECT p FROM Perfiles p WHERE p.id = :id")})
public class Perfiles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "tipo_perfil")
    private String tipoPerfil;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estado")
    private String estado;
    @Column(name = "usuario_alta_id")
    private Serializable usuarioAltaId;
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

    public Perfiles() {
    }

    public Perfiles(Integer id) {
        this.id = id;
    }

    public String getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(String tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Serializable getUsuarioAltaId() {
        return usuarioAltaId;
    }

    public void setUsuarioAltaId(Serializable usuarioAltaId) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perfiles)) {
            return false;
        }
        Perfiles other = (Perfiles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.salvapy.admin.salvapy.admin.entities.Perfiles[ id=" + id + " ]";
    }
    
}
