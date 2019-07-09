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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Usuario.findByApellido", query = "SELECT u FROM Usuario u WHERE u.apellido = :apellido"),
    @NamedQuery(name = "Usuario.findByFechaDeNacimiento", query = "SELECT u FROM Usuario u WHERE u.fechaDeNacimiento = :fechaDeNacimiento"),
    @NamedQuery(name = "Usuario.findByDependencia", query = "SELECT u FROM Usuario u WHERE u.dependencia = :dependencia"),
    @NamedQuery(name = "Usuario.findByEstado", query = "SELECT u FROM Usuario u WHERE u.estado = :estado"),
    @NamedQuery(name = "Usuario.findByPerfilId", query = "SELECT u FROM Usuario u WHERE u.perfilId = :perfilId"),
    @NamedQuery(name = "Usuario.findByUsuarioAltaId", query = "SELECT u FROM Usuario u WHERE u.usuarioAltaId = :usuarioAltaId"),
    @NamedQuery(name = "Usuario.findByFechaAlta", query = "SELECT u FROM Usuario u WHERE u.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Usuario.findByUsuarioModificacionId", query = "SELECT u FROM Usuario u WHERE u.usuarioModificacionId = :usuarioModificacionId"),
    @NamedQuery(name = "Usuario.findByFechaModificacion", query = "SELECT u FROM Usuario u WHERE u.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "Usuario.findByUsuarioEliminacionId", query = "SELECT u FROM Usuario u WHERE u.usuarioEliminacionId = :usuarioEliminacionId"),
    @NamedQuery(name = "Usuario.findByFechaEliminacion", query = "SELECT u FROM Usuario u WHERE u.fechaEliminacion = :fechaEliminacion"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "fecha_de_nacimiento")
    @Temporal(TemporalType.TIME)
    private Date fechaDeNacimiento;
    @Column(name = "dependencia")
    private String dependencia;
    @Column(name = "estado")
    private String estado;
    @Column(name = "perfil_id")
    private BigInteger perfilId;
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
    @OneToMany(mappedBy = "usuarioAltaId", fetch = FetchType.LAZY)
    private List<PedidosSangre> pedidosSangreList;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigInteger getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(BigInteger perfilId) {
        this.perfilId = perfilId;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.salvapy.admin.salvapy.admin.entities.Usuario[ id=" + id + " ]";
    }
    
}
