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
@Table(name = "donantes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Donantes.findAll", query = "SELECT d FROM Donantes d"),
    @NamedQuery(name = "Donantes.findByNombre", query = "SELECT d FROM Donantes d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "Donantes.findByApellido", query = "SELECT d FROM Donantes d WHERE d.apellido = :apellido"),
    @NamedQuery(name = "Donantes.findByCiudad", query = "SELECT d FROM Donantes d WHERE d.ciudad = :ciudad"),
    @NamedQuery(name = "Donantes.findByDescripcion", query = "SELECT d FROM Donantes d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "Donantes.findByEstado", query = "SELECT d FROM Donantes d WHERE d.estado = :estado"),
    @NamedQuery(name = "Donantes.findBySexo", query = "SELECT d FROM Donantes d WHERE d.sexo = :sexo"),
    @NamedQuery(name = "Donantes.findByProfesion", query = "SELECT d FROM Donantes d WHERE d.profesion = :profesion"),
    @NamedQuery(name = "Donantes.findByEstadoCivil", query = "SELECT d FROM Donantes d WHERE d.estadoCivil = :estadoCivil"),
    @NamedQuery(name = "Donantes.findByUsuarioAltaId", query = "SELECT d FROM Donantes d WHERE d.usuarioAltaId = :usuarioAltaId"),
    @NamedQuery(name = "Donantes.findByFechaAlta", query = "SELECT d FROM Donantes d WHERE d.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Donantes.findByUsuarioModificacionId", query = "SELECT d FROM Donantes d WHERE d.usuarioModificacionId = :usuarioModificacionId"),
    @NamedQuery(name = "Donantes.findByFechaModificacion", query = "SELECT d FROM Donantes d WHERE d.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "Donantes.findByUsuarioEliminacionId", query = "SELECT d FROM Donantes d WHERE d.usuarioEliminacionId = :usuarioEliminacionId"),
    @NamedQuery(name = "Donantes.findByFechaEliminacion", query = "SELECT d FROM Donantes d WHERE d.fechaEliminacion = :fechaEliminacion"),
    @NamedQuery(name = "Donantes.findById", query = "SELECT d FROM Donantes d WHERE d.id = :id"),
    @NamedQuery(name = "Donantes.findByUsername", query = "SELECT d FROM Donantes d WHERE d.username = :username"),
    @NamedQuery(name = "Donantes.findByPassword", query = "SELECT d FROM Donantes d WHERE d.password = :password"),
    @NamedQuery(name = "Donantes.findByNrodocumento", query = "SELECT d FROM Donantes d WHERE d.nrodocumento = :nrodocumento"),
    @NamedQuery(name = "Donantes.findByFechaNacimiento", query = "SELECT d FROM Donantes d WHERE d.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Donantes.findByTipoSangre", query = "SELECT d FROM Donantes d WHERE d.tipoSangre = :tipoSangre")})
public class Donantes implements Serializable {

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
    @Column(name = "sexo")
    private String sexo;
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
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "nrodocumento")
    private Integer nrodocumento;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "tipo_sangre")
    private String tipoSangre;
    @OneToMany(mappedBy = "idDonante", fetch = FetchType.LAZY)
    private List<AsistenciaDonacion> asistenciaDonacionList;
    @JoinColumn(name = "tipo_sangre_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoSangre tipoSangreId;
    
    @Column(name = "token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Donantes() {
    }

    public Donantes(Integer id) {
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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

    public Integer getNrodocumento() {
        return nrodocumento;
    }

    public void setNrodocumento(Integer nrodocumento) {
        this.nrodocumento = nrodocumento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    @XmlTransient
    public List<AsistenciaDonacion> getAsistenciaDonacionList() {
        return asistenciaDonacionList;
    }

    public void setAsistenciaDonacionList(List<AsistenciaDonacion> asistenciaDonacionList) {
        this.asistenciaDonacionList = asistenciaDonacionList;
    }

    public TipoSangre getTipoSangreId() {
        return tipoSangreId;
    }

    public void setTipoSangreId(TipoSangre tipoSangreId) {
        this.tipoSangreId = tipoSangreId;
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
        if (!(object instanceof Donantes)) {
            return false;
        }
        Donantes other = (Donantes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.salvapy.admin.salvapy.admin.entities.Donantes[ id=" + id + " ]";
    }
    
}
