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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "noticia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Noticia.findAll", query = "SELECT n FROM Noticia n"),
    @NamedQuery(name = "Noticia.findByTitulo", query = "SELECT n FROM Noticia n WHERE n.titulo = :titulo"),
    @NamedQuery(name = "Noticia.findByDescripcion", query = "SELECT n FROM Noticia n WHERE n.descripcion = :descripcion"),
    @NamedQuery(name = "Noticia.findByImagen", query = "SELECT n FROM Noticia n WHERE n.imagen = :imagen"),
    @NamedQuery(name = "Noticia.findByFechaDesde", query = "SELECT n FROM Noticia n WHERE n.fechaDesde = :fechaDesde"),
    @NamedQuery(name = "Noticia.findByFechaHasta", query = "SELECT n FROM Noticia n WHERE n.fechaHasta = :fechaHasta"),
    @NamedQuery(name = "Noticia.findByEstado", query = "SELECT n FROM Noticia n WHERE n.estado = :estado"),
    @NamedQuery(name = "Noticia.findByUsuarioAltaId", query = "SELECT n FROM Noticia n WHERE n.usuarioAltaId = :usuarioAltaId"),
    @NamedQuery(name = "Noticia.findByFechaAlta", query = "SELECT n FROM Noticia n WHERE n.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Noticia.findByUsuarioModificacionId", query = "SELECT n FROM Noticia n WHERE n.usuarioModificacionId = :usuarioModificacionId"),
    @NamedQuery(name = "Noticia.findByFechaModificacion", query = "SELECT n FROM Noticia n WHERE n.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "Noticia.findByUsuarioEliminacionId", query = "SELECT n FROM Noticia n WHERE n.usuarioEliminacionId = :usuarioEliminacionId"),
    @NamedQuery(name = "Noticia.findByFechaEliminacion", query = "SELECT n FROM Noticia n WHERE n.fechaEliminacion = :fechaEliminacion"),
    @NamedQuery(name = "Noticia.findById", query = "SELECT n FROM Noticia n WHERE n.id = :id")})
public class Noticia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "imagen")
    private String imagen;
    @Column(name = "fecha_desde")
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;
    @Column(name = "fecha_hasta")
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
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
    @JoinColumn(name = "tipo_noticia_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoDeNoticia tipoNoticiaId;
    @Column(name = "ubicacion")
    private String ubicacion;
    @Column(name = "file_name")
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    
    public Noticia() {
    }

    public Noticia(Integer id) {
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoDeNoticia getTipoNoticiaId() {
        return tipoNoticiaId;
    }

    public void setTipoNoticiaId(TipoDeNoticia tipoNoticiaId) {
        this.tipoNoticiaId = tipoNoticiaId;
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
        if (!(object instanceof Noticia)) {
            return false;
        }
        Noticia other = (Noticia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.salvapy.admin.salvapy.admin.entities.Noticia[ id=" + id + " ]";
    }
    
}
