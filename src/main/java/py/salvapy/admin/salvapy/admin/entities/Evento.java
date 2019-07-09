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
@Table(name = "evento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e"),
    @NamedQuery(name = "Evento.findByIdevento", query = "SELECT e FROM Evento e WHERE e.idevento = :idevento"),
    @NamedQuery(name = "Evento.findByFechaDesde", query = "SELECT e FROM Evento e WHERE e.fechaDesde = :fechaDesde"),
    @NamedQuery(name = "Evento.findByFechaHasta", query = "SELECT e FROM Evento e WHERE e.fechaHasta = :fechaHasta"),
    @NamedQuery(name = "Evento.findByDescripcion", query = "SELECT e FROM Evento e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "Evento.findByTitulo", query = "SELECT e FROM Evento e WHERE e.titulo = :titulo"),
    @NamedQuery(name = "Evento.findByUrl", query = "SELECT e FROM Evento e WHERE e.url = :url")})
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idevento")
    private Integer idevento;
    @Column(name = "fecha_desde")
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;
    @Column(name = "fecha_hasta")
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
    @Size(max = 800)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 150)
    @Column(name = "titulo")
    private String titulo;
    @Size(max = 200)
    @Column(name = "url")
    private String url;
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

    public Evento() {
    }

    public Evento(Integer idevento) {
        this.idevento = idevento;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    

    public Integer getIdevento() {
        return idevento;
    }

    public void setIdevento(Integer idevento) {
        this.idevento = idevento;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idevento != null ? idevento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.idevento == null && other.idevento != null) || (this.idevento != null && !this.idevento.equals(other.idevento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.salvapy.admin.salvapy.admin.entities.Evento[ idevento=" + idevento + " ]";
    }
    
}
