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
import py.salvapy.admin.salvapy.admin.entities.Acercade;
import py.salvapy.admin.salvapy.admin.entities.Donacion;

/**
 *
 * @author hectorvillalba
 */

@Stateless
public class AcercaDeDao implements Serializable{
    
    @PersistenceContext
    EntityManager em;   
    
    public List<Acercade> getAcercaDe(){
        return em.createQuery("select a from Acercade a ").getResultList();
    }
    
    public void save(Acercade a){
        try {
            em.merge(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   public boolean delete(Acercade donacion){
        try {
            em.remove(em.contains(donacion) ? donacion : em.merge(donacion));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
        
}
