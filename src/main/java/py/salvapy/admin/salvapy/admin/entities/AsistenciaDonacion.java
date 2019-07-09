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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "asistencia_donacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsistenciaDonacion.findAll", query = "SELECT a FROM AsistenciaDonacion a"),
    @NamedQuery(name = "AsistenciaDonacion.findByFechaAsistencia", query = "SELECT a FROM AsistenciaDonacion a WHERE a.fechaAsistencia = :fechaAsistencia"),
    @NamedQuery(name = "AsistenciaDonacion.findByFechaConfirmacion", query = "SELECT a FROM AsistenciaDonacion a WHERE a.fechaConfirmacion = :fechaConfirmacion"),
    @NamedQuery(name = "AsistenciaDonacion.findByEstado", query = "SELECT a FROM AsistenciaDonacion a WHERE a.estado = :estado"),
    @NamedQuery(name = "AsistenciaDonacion.findByUsuarioAltaId", query = "SELECT a FROM AsistenciaDonacion a WHERE a.usuarioAltaId = :usuarioAltaId"),
    @NamedQuery(name = "AsistenciaDonacion.findByFechaAlta", query = "SELECT a FROM AsistenciaDonacion a WHERE a.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "AsistenciaDonacion.findByUsuarioModificacionId", query = "SELECT a FROM AsistenciaDonacion a WHERE a.usuarioModificacionId = :usuarioModificacionId"),
    @NamedQuery(name = "AsistenciaDonacion.findByFechaModificacion", query = "SELECT a FROM AsistenciaDonacion a WHERE a.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "AsistenciaDonacion.findByUsuarioEliminacionId", query = "SELECT a FROM AsistenciaDonacion a WHERE a.usuarioEliminacionId = :usuarioEliminacionId"),
    @NamedQuery(name = "AsistenciaDonacion.findByFechaEliminacion", query = "SELECT a FROM AsistenciaDonacion a WHERE a.fechaEliminacion = :fechaEliminacion"),
    @NamedQuery(name = "AsistenciaDonacion.findById", query = "SELECT a FROM AsistenciaDonacion a WHERE a.id = :id")})
public class AsistenciaDonacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "fecha_asistencia")
    @Temporal(TemporalType.TIME)
    private Date fechaAsistencia;
    @Column(name = "fecha_confirmacion")
    @Temporal(TemporalType.TIME)
    private Date fechaConfirmacion;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_donante", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Donantes idDonante;
    @OneToMany(mappedBy = "asistenciaDonacionId", fetch = FetchType.LAZY)
    private List<PedidosSangre> pedidosSangreList;

    public AsistenciaDonacion() {
    }

    public AsistenciaDonacion(Integer id) {
        this.id = id;
    }

    public Date getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(Date fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }

    public Date getFechaConfirmacion() {
        return fechaConfirmacion;
    }

    public void setFechaConfirmacion(Date fechaConfirmacion) {
        this.fechaConfirmacion = fechaConfirmacion;
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

    public Donantes getIdDonante() {
        return idDonante;
    }

    public void setIdDonante(Donantes idDonante) {
        this.idDonante = idDonante;
    }

    @XmlTransient
    public List<PedidosSangre> getPedidosSangreList() {
        return pedidosSangreList;
    }

    public void setPedidosSangreList(List<PedidosSangre> pedidosSangreList) {
        this.pedidosSangreList = pedidosSangreList;
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
        if (!(object instanceof AsistenciaDonacion)) {
            return false;
        }
        AsistenciaDonacion other = (AsistenciaDonacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.salvapy.admin.salvapy.admin.entities.AsistenciaDonacion[ id=" + id + " ]";
    }
    
}
