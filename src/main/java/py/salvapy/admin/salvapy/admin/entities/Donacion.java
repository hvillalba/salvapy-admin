/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.salvapy.admin.salvapy.admin.entities;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hectorvillalba
 */
@Entity
@Table(name = "donacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Donacion.findAll", query = "SELECT d FROM Donacion d"),
    @NamedQuery(name = "Donacion.findByIddonacion", query = "SELECT d FROM Donacion d WHERE d.iddonacion = :iddonacion"),
    @NamedQuery(name = "Donacion.findByPaciente", query = "SELECT d FROM Donacion d WHERE d.paciente = :paciente"),
    @NamedQuery(name = "Donacion.findByTipoSangre", query = "SELECT d FROM Donacion d WHERE d.tipoSangre = :tipoSangre"),
    @NamedQuery(name = "Donacion.findByEdad", query = "SELECT d FROM Donacion d WHERE d.edad = :edad"),
    @NamedQuery(name = "Donacion.findByCantDonantes", query = "SELECT d FROM Donacion d WHERE d.cantDonantes = :cantDonantes"),
    @NamedQuery(name = "Donacion.findBySexo", query = "SELECT d FROM Donacion d WHERE d.sexo = :sexo"),
    @NamedQuery(name = "Donacion.findByDescripcion", query = "SELECT d FROM Donacion d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "Donacion.findByUrl", query = "SELECT d FROM Donacion d WHERE d.url = :url"),
    @NamedQuery(name = "Donacion.findByFechaDesde", query = "SELECT d FROM Donacion d WHERE d.fechaDesde = :fechaDesde"),
    @NamedQuery(name = "Donacion.findByFechaHasta", query = "SELECT d FROM Donacion d WHERE d.fechaHasta = :fechaHasta")})
public class Donacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddonacion")
    private Integer iddonacion;
    @Size(max = 250)
    @Column(name = "paciente")
    private String paciente;
    @Size(max = 50)
    @Column(name = "tipo_sangre")
    private String tipoSangre;
    @Column(name = "edad")
    private Integer edad;
    @Column(name = "cant_donantes")
    private Integer cantDonantes;
    @Size(max = 10)
    @Column(name = "sexo")
    private String sexo;
    @Size(max = 800)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 800)
    @Column(name = "url")
    private String url;
    @Column(name = "fecha_desde")
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;
    @Column(name = "fecha_hasta")
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
    
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
    

    public Donacion() {
    }

    public Donacion(Integer iddonacion) {
        this.iddonacion = iddonacion;
    }

    public Integer getIddonacion() {
        return iddonacion;
    }

    public void setIddonacion(Integer iddonacion) {
        this.iddonacion = iddonacion;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getCantDonantes() {
        return cantDonantes;
    }

    public void setCantDonantes(Integer cantDonantes) {
        this.cantDonantes = cantDonantes;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddonacion != null ? iddonacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Donacion)) {
            return false;
        }
        Donacion other = (Donacion) object;
        if ((this.iddonacion == null && other.iddonacion != null) || (this.iddonacion != null && !this.iddonacion.equals(other.iddonacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.salvapy.admin.salvapy.admin.entities.Donacion[ iddonacion=" + iddonacion + " ]";
    }
    
}
