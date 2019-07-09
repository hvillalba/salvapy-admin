/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.salvapy.admin.salvapy.admin.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import py.salvapy.admin.salvapy.admin.entities.Noticia;

/**
 *
 * @author hectorvillalba
 */

@Stateless
public class NoticiasDao implements Serializable{
    
    @PersistenceContext
    EntityManager em;
    
    
    public List<Noticia> getNoticias(){
        List<Noticia> lista = new ArrayList<>();
        try {
            lista = em.createQuery("select n from Noticia n ").getResultList();          
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public void save(Noticia noticia){
        try {
            em.merge(noticia);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean delete(Noticia noticia){
        try {
            em.remove(em.contains(noticia) ? noticia : em.merge(noticia));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
}
