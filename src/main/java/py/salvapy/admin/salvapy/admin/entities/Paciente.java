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
@Table(name = "paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p"),
    @NamedQuery(name = "Paciente.findByNombre", query = "SELECT p FROM Paciente p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Paciente.findByApellido", query = "SELECT p FROM Paciente p WHERE p.apellido = :apellido"),
    @NamedQuery(name = "Paciente.findByCiudad", query = "SELECT p FROM Paciente p WHERE p.ciudad = :ciudad"),
    @NamedQuery(name = "Paciente.findByDescripcion", query = "SELECT p FROM Paciente p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Paciente.findByEstado", query = "SELECT p FROM Paciente p WHERE p.estado = :estado"),
    @NamedQuery(name = "Paciente.findByFechaNacimiento", query = "SELECT p FROM Paciente p WHERE p.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Paciente.findBySexo", query = "SELECT p FROM Paciente p WHERE p.sexo = :sexo"),
    @NamedQuery(name = "Paciente.findByTipoSangreId", query = "SELECT p FROM Paciente p WHERE p.tipoSangreId = :tipoSangreId"),
    @NamedQuery(name = "Paciente.findByProfesion", query = "SELECT p FROM Paciente p WHERE p.profesion = :profesion"),
    @NamedQuery(name = "Paciente.findByEstadoCivil", query = "SELECT p FROM Paciente p WHERE p.estadoCivil = :estadoCivil"),
    @NamedQuery(name = "Paciente.findByUsuarioAltaId", query = "SELECT p FROM Paciente p WHERE p.usuarioAltaId = :usuarioAltaId"),
    @NamedQuery(name = "Paciente.findByFechaAlta", query = "SELECT p FROM Paciente p WHERE p.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Paciente.findByUsuarioModificacionId", query = "SELECT p FROM Paciente p WHERE p.usuarioModificacionId = :usuarioModificacionId"),
    @NamedQuery(name = "Paciente.findByFechaModificacion", query = "SELECT p FROM Paciente p WHERE p.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "Paciente.findByUsuarioEliminacionId", query = "SELECT p FROM Paciente p WHERE p.usuarioEliminacionId = :usuarioEliminacionId"),
    @NamedQuery(name = "Paciente.findByFechaEliminacion", query = "SELECT p FROM Paciente p WHERE p.fechaEliminacion = :fechaEliminacion"),
    @NamedQuery(name = "Paciente.findById", query = "SELECT p FROM Paciente p WHERE p.id = :id")})
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.TIME)
    private Date fechaNacimiento;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "tipo_sangre_id")
    private Serializable tipoSangreId;
    @Column(name = "profesion")
    private String profesion;
    @Column(name = "estado_civil")
    private String estadoCivil;
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
    @OneToMany(mappedBy = "pacienteId", fetch = FetchType.LAZY)
    private List<PedidosSangre> pedidosSangreList;

    public Paciente() {
    }

    public Paciente(Integer id) {
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Serializable getTipoSangreId() {
        return tipoSangreId;
    }

    public void setTipoSangreId(Serializable tipoSangreId) {
        this.tipoSangreId = tipoSangreId;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
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
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.salvapy.admin.salvapy.admin.entities.Paciente[ id=" + id + " ]";
    }
    
}
