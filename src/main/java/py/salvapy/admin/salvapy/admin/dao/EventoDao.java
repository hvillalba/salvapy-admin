/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.salvapy.admin.salvapy.admin.dao;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import py.salvapy.admin.salvapy.admin.entities.Evento;
import py.salvapy.admin.salvapy.admin.entities.Noticia;

/**
 *
 * @author hectorvillalba
 */


@Stateless
public class EventoDao  implements Serializable {
    
    @PersistenceContext
    EntityManager em;
    
    
    public List<Evento> getEventos(){
        return em.createQuery("select e from Evento e ").getResultList();
    } 
     
    public void save(Evento evento){
        try {
            em.merge(evento);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean delete(Evento evento){
        try {
            em.remove(em.contains(evento) ? evento : em.merge(evento));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
