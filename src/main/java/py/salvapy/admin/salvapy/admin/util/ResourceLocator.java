/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.salvapy.admin.salvapy.admin.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author hectorvillalba
 */
public class ResourceLocator {

    public static <T> T locate(Class<T> clazz) {
        try {
            InitialContext ic = new InitialContext();
            Object resource = ic.lookup("java:module/" + clazz.getSimpleName());
            ic.close();
            return clazz.cast(resource);
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}