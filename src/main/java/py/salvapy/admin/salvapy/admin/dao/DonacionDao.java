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
import py.salvapy.admin.salvapy.admin.entities.Donacion;
import py.salvapy.admin.salvapy.admin.entities.Donantes;
import py.salvapy.admin.salvapy.admin.entities.Evento;

/**
 *
 * @author hectorvillalba
 */
@Stateless
public class DonacionDao implements Serializable{
    @PersistenceContext
    EntityManager em;   
    
    public List<Donacion> getEventos(){
        return em.createQuery("select e from Donacion e ").getResultList();
    } 
    
    
    public List<Donantes> getDonantes(){
        return em.createQuery("select e from Donantes e ").getResultList();
    } 
     
    public void save(Donacion donacion){
        try {
            em.merge(donacion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean delete(Donacion donacion){
        try {
            em.remove(em.contains(donacion) ? donacion : em.merge(donacion));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
