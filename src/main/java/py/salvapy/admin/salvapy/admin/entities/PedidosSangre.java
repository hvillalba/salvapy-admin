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
@Table(name = "pedidos_sangre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PedidosSangre.findAll", query = "SELECT p FROM PedidosSangre p"),
    @NamedQuery(name = "PedidosSangre.findByDescripcion", query = "SELECT p FROM PedidosSangre p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "PedidosSangre.findByEstado", query = "SELECT p FROM PedidosSangre p WHERE p.estado = :estado"),
    @NamedQuery(name = "PedidosSangre.findByFechaAlta", query = "SELECT p FROM PedidosSangre p WHERE p.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "PedidosSangre.findByUsuarioModificacionId", query = "SELECT p FROM PedidosSangre p WHERE p.usuarioModificacionId = :usuarioModificacionId"),
    @NamedQuery(name = "PedidosSangre.findByFechaModificacion", query = "SELECT p FROM PedidosSangre p WHERE p.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "PedidosSangre.findByUsuarioEliminacionId", query = "SELECT p FROM PedidosSangre p WHERE p.usuarioEliminacionId = :usuarioEliminacionId"),
    @NamedQuery(name = "PedidosSangre.findByFechaEliminacion", query = "SELECT p FROM PedidosSangre p WHERE p.fechaEliminacion = :fechaEliminacion"),
    @NamedQuery(name = "PedidosSangre.findById", query = "SELECT p FROM PedidosSangre p WHERE p.id = :id")})
public class PedidosSangre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estado")
    private String estado;
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
    @JoinColumn(name = "asistencia_donacion_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AsistenciaDonacion asistenciaDonacionId;
    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Paciente pacienteId;
    @JoinColumn(name = "usuario_alta_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuarioAltaId;

    public PedidosSangre() {
    }

    public PedidosSangre(Integer id) {
        this.id = id;
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

    public AsistenciaDonacion getAsistenciaDonacionId() {
        return asistenciaDonacionId;
    }

    public void setAsistenciaDonacionId(AsistenciaDonacion asistenciaDonacionId) {
        this.asistenciaDonacionId = asistenciaDonacionId;
    }

    public Paciente getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Paciente pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Usuario getUsuarioAltaId() {
        return usuarioAltaId;
    }

    public void setUsuarioAltaId(Usuario usuarioAltaId) {
        this.usuarioAltaId = usuarioAltaId;
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
        if (!(object instanceof PedidosSangre)) {
            return false;
        }
        PedidosSangre other = (PedidosSangre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.salvapy.admin.salvapy.admin.entities.PedidosSangre[ id=" + id + " ]";
    }
    
}
