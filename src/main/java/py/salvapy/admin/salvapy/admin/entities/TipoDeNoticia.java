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
@Table(name = "tipo_de_noticia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDeNoticia.findAll", query = "SELECT t FROM TipoDeNoticia t"),
    @NamedQuery(name = "TipoDeNoticia.findById", query = "SELECT t FROM TipoDeNoticia t WHERE t.id = :id"),
    @NamedQuery(name = "TipoDeNoticia.findByTitulo", query = "SELECT t FROM TipoDeNoticia t WHERE t.titulo = :titulo"),
    @NamedQuery(name = "TipoDeNoticia.findByDescripcion", query = "SELECT t FROM TipoDeNoticia t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoDeNoticia.findByEstado", query = "SELECT t FROM TipoDeNoticia t WHERE t.estado = :estado"),
    @NamedQuery(name = "TipoDeNoticia.findByUsuarioAltaId", query = "SELECT t FROM TipoDeNoticia t WHERE t.usuarioAltaId = :usuarioAltaId"),
    @NamedQuery(name = "TipoDeNoticia.findByFechaAlta", query = "SELECT t FROM TipoDeNoticia t WHERE t.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "TipoDeNoticia.findByUsuarioModificacionId", query = "SELECT t FROM TipoDeNoticia t WHERE t.usuarioModificacionId = :usuarioModificacionId"),
    @NamedQuery(name = "TipoDeNoticia.findByFechaModificacion", query = "SELECT t FROM TipoDeNoticia t WHERE t.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "TipoDeNoticia.findByUsuarioEliminacionId", query = "SELECT t FROM TipoDeNoticia t WHERE t.usuarioEliminacionId = :usuarioEliminacionId"),
    @NamedQuery(name = "TipoDeNoticia.findByFechaEliminacion", query = "SELECT t FROM TipoDeNoticia t WHERE t.fechaEliminacion = :fechaEliminacion")})
public class TipoDeNoticia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estado")
    private String estado;
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
    @OneToMany(mappedBy = "tipoNoticiaId", fetch = FetchType.LAZY)
    private List<Noticia> noticiaList;

    public TipoDeNoticia() {
    }

    public TipoDeNoticia(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    @XmlTransient
    public List<Noticia> getNoticiaList() {
        return noticiaList;
    }

    public void setNoticiaList(List<Noticia> noticiaList) {
        this.noticiaList = noticiaList;
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
        if (!(object instanceof TipoDeNoticia)) {
            return false;
        }
        TipoDeNoticia other = (TipoDeNoticia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.salvapy.admin.salvapy.admin.entities.TipoDeNoticia[ id=" + id + " ]";
    }
    
}
