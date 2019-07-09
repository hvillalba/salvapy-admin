package py.salvapy.admin.salvapy.admin.ui;

import com.vaadin.server.VaadinSession;

import java.util.*;
import py.salvapy.admin.salvapy.admin.entities.Usuario;

public class UserHolder {

    public static Usuario get() {
        Usuario user = (Usuario) VaadinSession.getCurrent().getAttribute(Usuario.class.getName());
        return user;
    }
    public static void setUsuarios(Usuario user) {
        VaadinSession.getCurrent().setAttribute(Usuario.class.getName(), user);
        //poblarFormularios();
    }

//    public static boolean viewAccesibleToUser(String viewName) {
//        return get().isInAnyRole(viewNames_roles.get(viewName));
//    }
//
//    public static final Map<String, List<Boolean>> viewNames_roles = new HashMap<>();
//
//    public static void poblarFormularios() {
//        viewNames_roles.put(UserViews.VIEW_NAME, Arrays.asList(get().getAdministrador());
//        viewNames_roles.put(UsersView.VIEW_NAME, Arrays.asList(get().getAdministrador(), get().getHelpdesk()));
//        viewNames_roles.put(ClientesView.VIEW_NAME, Arrays.asList(get().getAdministrador(), get().getHelpdesk()));
//        viewNames_roles.put(ProyectosView.VIEW_NAME, Arrays.asList(get().getAdministrador(), get().getHelpdesk()));
//        viewNames_roles.put(TipoReclamoView.VIEW_NAME, Arrays.asList(get().getAdministrador(), get().getHelpdesk()));
//        viewNames_roles.put(ReportesReclamos.VIEWNAME, Arrays.asList(get().getAdministrador(), get().getHelpdesk()));
//    }
//    private void setRolesAllowedInView(String viewName, String ... roles) {
//
//   }
}
