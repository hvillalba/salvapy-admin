package py.salvapy.admin.salvapy.admin.ui;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.*;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.HashMap;
import java.util.Map;
import py.salvapy.admin.salvapy.admin.view.AcercaDeSalvapyView;
import py.salvapy.admin.salvapy.admin.view.AcercaDeView;
import py.salvapy.admin.salvapy.admin.view.DonacionView;
import py.salvapy.admin.salvapy.admin.view.EventoView;
import py.salvapy.admin.salvapy.admin.view.NoticiasView;

public class MainScreen extends HorizontalLayout {
    private Menu menu;

    public MainScreen () {
        setStyleName("main-screen");

        CssLayout viewContainer = new CssLayout();
        viewContainer.addStyleName("valo-content");
        viewContainer.setSizeFull();

        Navigator navigator = new Navigator(UI.getCurrent(), viewContainer);
        navigator.setErrorView(ErrorView.class);
        menu = new Menu(navigator);

        menu.addView(AboutView.class, AboutView.VIEW_NAME, AboutView.VIEW_NAME,VaadinIcons.CHECK);
        menu.addView(NoticiasView.class, NoticiasView.VIEW_NAME, NoticiasView.VIEW_NAME, VaadinIcons.ABACUS);
        menu.addView(EventoView.class, EventoView.VIEW_NAME, EventoView.VIEW_NAME, VaadinIcons.ENVELOPE);
        menu.addView(DonacionView.class, DonacionView.VIEW_NAME, DonacionView.VIEW_NAME, VaadinIcons.ACCESSIBILITY);
        menu.addView(AcercaDeView.class, AcercaDeView.VIEW_NAME, AcercaDeView.VIEW_NAME, VaadinIcons.INFO);
        menu.addView(AcercaDeSalvapyView.class, AcercaDeSalvapyView.VIEW_NAME, AcercaDeSalvapyView.VIEW_NAME, VaadinIcons.ABSOLUTE_POSITION);
//        menu.addView(CategoriaView.class, CategoriaView.VIEW_NAME, CategoriaView.VIEW_NAME, VaadinIcons.LEVEL_UP);
//        menu.addView(RutaView.class, RutaView.VIEW_NAME, RutaView.VIEW_NAME, VaadinIcons.ARROWS);
//        menu.addView(FrequenciaView.class, FrequenciaView.VIEW_NAME, FrequenciaView.VIEW_NAME, VaadinIcons.CALENDAR);
//        menu.addView(ClienteView.class, ClienteView.VIEW_NAME, ClienteView.VIEW_NAME, VaadinIcons.USERS);
//        menu.addView(GeoZonaView.class, GeoZonaView.VIEW_NAME, GeoZonaView.VIEW_NAME, VaadinIcons.USER_CHECK);
//        menu.addView(MarcacionView.class, MarcacionView.VIEW_NAME, MarcacionView.VIEW_NAME, VaadinIcons.ALARM);
//        menu.addView(AsignarGeoZonaView.class, AsignarGeoZonaView.VIEW_NAME, AsignarGeoZonaView.VIEW_NAME, VaadinIcons.CHART_TIMELINE);
//        menu.addView(VisitasView.class, VisitasView.VIEW_NAME, VisitasView.VIEW_NAME, VaadinIcons.TIME_FORWARD);
//        menu.addView(VisitasResumenView.class, VisitasResumenView.VIEW_NAME, VisitasResumenView.VIEW_NAME, VaadinIcons.TIME_FORWARD);
//        menu.addView(ResumenMarcacionView.class, ResumenMarcacionView.VIEW_NAME, ResumenMarcacionView.VIEW_NAME, VaadinIcons.TIME_FORWARD);
//        menu.addView(ResumenImpulsadoresView.class, ResumenImpulsadoresView.VIEW_NAME, ResumenImpulsadoresView.VIEW_NAME, VaadinIcons.TIME_FORWARD);
//        menu.addView(ResumenSupervisoresView.class, ResumenSupervisoresView.VIEW_NAME, ResumenSupervisoresView.VIEW_NAME, VaadinIcons.TIME_FORWARD);


        addComponent(menu);
        addComponent(viewContainer);
        setExpandRatio(viewContainer, 1);
        setSizeFull();

        // notify the view menu about view changes so that it can display which view is currently active
        navigator.addViewChangeListener(new ViewChangeListener() {
            public boolean beforeViewChange(ViewChangeEvent event) {
                return true;
            }
            public void afterViewChange(ViewChangeEvent event) {
                menu.styleMenuItemOfActiveView(event.getViewName());
            }
        });
    }

    private static class Menu extends CssLayout {

        private static final String VALO_MENUITEMS = "valo-menuitems";
        private static final String VALO_MENU_TOGGLE = "valo-menu-toggle";
        private static final String VALO_MENU_VISIBLE = "valo-menu-visible";
        private Navigator navigator;
        private Map<String, Button> viewButtons = new HashMap<>();

        private CssLayout menuItemsLayout;
        private CssLayout menuPart;

        public Menu(Navigator navigator) {
            this.navigator = navigator;
            setPrimaryStyleName(ValoTheme.MENU_ROOT);
            menuPart = new CssLayout();
            menuPart.addStyleName(ValoTheme.MENU_PART);

            // header of the menu
            final HorizontalLayout top = new HorizontalLayout();
            top.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
            top.addStyleName(ValoTheme.MENU_TITLE);
            top.setSpacing(true);
            Label title = new Label("Operaciones");
            title.addStyleName(ValoTheme.LABEL_H3);
            title.setSizeUndefined();
            //Image image = new Image(null, new ThemeResource("img/table-logo.png"));
            //image.setStyleName("logo");
            //top.addComponent(image);
            top.addComponent(title);
            menuPart.addComponent(top);

            // logout menu item
            MenuBar logoutMenu = new MenuBar();
            logoutMenu.addItem("Logout", FontAwesome.SIGN_OUT, i -> {
                for (UI ui: VaadinSession.getCurrent().getUIs())
                    ui.access(() -> ui.getPage().setLocation("/")); // FIXME, has to be the contextPath of the app server

                getSession().close();
                Page.getCurrent().reload();
            });

            logoutMenu.addStyleName("user-menu");
            menuPart.addComponent(logoutMenu);

            // button for toggling the visibility of the menu when on a small screen
            final Button showMenu = new Button("Menu", cl -> {
                if (menuPart.getStyleName().contains(VALO_MENU_VISIBLE))
                    menuPart.removeStyleName(VALO_MENU_VISIBLE);
                else
                    menuPart.addStyleName(VALO_MENU_VISIBLE);
            });

            showMenu.addStyleName(ValoTheme.BUTTON_PRIMARY);
            showMenu.addStyleName(ValoTheme.BUTTON_SMALL);
            showMenu.addStyleName(VALO_MENU_TOGGLE);
            showMenu.setIcon(VaadinIcons.MENU);
            menuPart.addComponent(showMenu);

            // container for the navigation buttons, which are added by addView()
            menuItemsLayout = new CssLayout();
            menuItemsLayout.setPrimaryStyleName(VALO_MENUITEMS);
            menuPart.addComponent(menuItemsLayout);

            addComponent(menuPart);
        }

        public void addView(Class<? extends View> viewClass, final String name, String caption, Resource icon) {
            ///if (UserHolder.viewAccesibleToUser(name)) {
                createViewButton(name, caption, icon);
                navigator.addView(name, viewClass);
            ///}
        }

        private void createViewButton(final String name, String caption, Resource icon) {
            Button button = new Button(caption, cl->  navigator.navigateTo(name));
            button.setPrimaryStyleName(ValoTheme.MENU_ITEM);
            button.setIcon(icon);
            menuItemsLayout.addComponent(button);
            viewButtons.put(name, button);
        }

        public void styleMenuItemOfActiveView(String viewName) {
            for (Button button : viewButtons.values())
                button.removeStyleName("selected");

            Button selected = viewButtons.get(viewName);
            if (selected != null)
                selected.addStyleName("selected");

            menuPart.removeStyleName(VALO_MENU_VISIBLE);
        }
    }
}
