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
import py.salvapy.admin.salvapy.admin.entities.Usuario;
/**
 *
 * @author hectorvillalba
 */
@Stateless
public class AutorizationResolver implements Serializable {

    @PersistenceContext
    EntityManager em;
    private Usuario usuarios;

    public Usuario authorizedUser(String user, String pass){
        try {
            String sql = "select u from Usuario u where u.username = :username and u.password = :pass";
            List<Usuario> lista = em.createQuery(sql)
                    .setParameter("username", user)
                    .setParameter("pass", pass)
                    .getResultList();
            if (!lista.isEmpty()){
                usuarios = lista.get(0);
                return usuarios;
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}