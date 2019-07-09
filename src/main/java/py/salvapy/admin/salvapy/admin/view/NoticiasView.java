/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.salvapy.admin.salvapy.admin.view;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import py.salvapy.admin.salvapy.admin.dao.NoticiasDao;
import py.salvapy.admin.salvapy.admin.entities.Noticia;
import py.salvapy.admin.salvapy.admin.form.NoticiasForm;
import py.salvapy.admin.salvapy.admin.util.ResourceLocator;

/**
 *
 * @author hectorvillalba
 */
public class NoticiasView extends CssLayout implements View {
    public static final String VIEW_NAME = "Noticias";
    Grid<Noticia> gridNoticia = new Grid<>(Noticia.class);
    private Button newNoticia;
    NoticiasDao noticiaDao = ResourceLocator.locate(NoticiasDao.class);
    Logger log =Logger.getLogger("NoticiaView");
    List<Noticia> lista = new ArrayList<>();
    private TextField filterText = new TextField("Filtros");
    private NoticiasForm noticiaForm = new NoticiasForm();
    private Button detalle = new Button("Editar");
    
    public NoticiasView(){
        try {
            System.out.print("Nueva instancia de NoticiasView");
            setSizeFull();
            addStyleName("crud-view");
            
            HorizontalLayout horizontalLayout = createTopBar();
            lista = noticiaDao.getNoticias();
            
            
            noticiaForm.setVisible(false);
            detalle.setVisible(false);
            newNoticia.addClickListener(e -> {
                gridNoticia.setVisible(false);
                horizontalLayout.setVisible(false);
                gridNoticia.asSingleSelect().clear();
                noticiaForm.setVisible(true);
                noticiaForm.setNoticia(new Noticia());
            });
            
            HorizontalLayout horizontalLayout2 = new HorizontalLayout();
            horizontalLayout2.addComponents(gridNoticia, noticiaForm);
            horizontalLayout2.setSizeFull();
            horizontalLayout2.setExpandRatio(gridNoticia, 1);
            
            
            VerticalLayout barAndGridLayout = new VerticalLayout();
            barAndGridLayout.addComponent(horizontalLayout);
            barAndGridLayout.addComponent(horizontalLayout2);
            barAndGridLayout.setMargin(true);
            barAndGridLayout.setSpacing(true);
            barAndGridLayout.setSizeFull();
            barAndGridLayout.setExpandRatio(horizontalLayout2,1);
            barAndGridLayout.addStyleName("crud-main-layout");
            log.info("Se encontraron  " + lista.size() + " registros");
           
            gridNoticia.setItems(lista);
            gridNoticia.removeAllColumns();
            gridNoticia.addColumn(Noticia::getId).setCaption("Codigo");
            gridNoticia.addColumn(Noticia::getTitulo).setCaption("Titulo");
            gridNoticia.addColumn(Noticia::getDescripcion).setCaption("Descripción");
            gridNoticia.addColumn(Noticia::getFechaDesde).setCaption("Desde");
            gridNoticia.addColumn(Noticia::getFechaHasta).setCaption("Hasta");
            gridNoticia.setSizeFull();
            
            gridNoticia.asSingleSelect().addValueChangeListener(event -> {
                if (event.getValue() == null) {
                    log.info("SingleSelect null");
                    noticiaForm.setVisible(false);
                }else{
                    detalle.setVisible(true);
                    noticiaForm.setNoticia(event.getValue());
                    log.info("SingleSelect: " + event.getValue().getId());
                }
            });
            
            detalle.addClickListener(e -> {
                noticiaForm.setVisible(true);
                //noticiaForm.setNoticia(new Noticia());
                gridNoticia.setVisible(false);
                horizontalLayout.setVisible(false);
            });
            
            noticiaForm.setSaveListener(usuarios -> {
                    gridNoticia.clearSortOrder();
                    lista = noticiaDao.getNoticias();
                    gridNoticia.setItems(lista);
                    gridNoticia.setVisible(true);
                    horizontalLayout.setVisible(true);
                    noticiaForm.setVisible(false);
                    //updateList("");
                    Notification.show("Nueva Noticia creada", Notification.Type.HUMANIZED_MESSAGE);
            });

            noticiaForm.setCancelListener(usuarios -> {
                gridNoticia.clearSortOrder();
            });

            noticiaForm.setDeleteListener(usuarios -> {
                gridNoticia.clearSortOrder();
                lista = noticiaDao.getNoticias();
                gridNoticia.setItems(lista);
                gridNoticia.setVisible(true);
                horizontalLayout.setVisible(true);
                noticiaForm.setVisible(false);
                //updateList("");
                Notification.show("Atención", "Registro borrado correctamente", Notification.Type.HUMANIZED_MESSAGE);
    //            grid.getContainerDataSource().removeItem(product);
            });
        addComponent(barAndGridLayout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
        private HorizontalLayout createTopBar() {
        newNoticia = new Button("Nueva Noticia");
        newNoticia.addStyleName(ValoTheme.BUTTON_PRIMARY);
        newNoticia.setIcon(VaadinIcons.PLUS_CIRCLE);
        
        detalle.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        detalle.setIcon(VaadinIcons.EDIT);
        
        
        CssLayout cssLayout = new CssLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponentsAndExpand(filterText,detalle);
        cssLayout.addComponent(horizontalLayout);
        
//        CssLayout cssLayout2 = new CssLayout();
//        HorizontalLayout horizontalLayout2 = new HorizontalLayout();
//        horizontalLayout2.addComponentsAndExpand(detalle);
//        cssLayout2.addComponent(horizontalLayout2);

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setSpacing(true);
        topLayout.setWidth("100%");
        topLayout.addComponent(cssLayout);
        topLayout.addComponent(newNoticia);
//        topLayout.setComponentAlignment(cssLayout2, Alignment.MIDDLE_LEFT);
        topLayout.setComponentAlignment(cssLayout, Alignment.MIDDLE_LEFT);
        topLayout.setComponentAlignment(newNoticia, Alignment.MIDDLE_RIGHT);
        
//        topLayout.setExpandRatio(cssLayout,1);
//        topLayout.setComponentAlignment(cssLayout2, Alignment.MIDDLE_LEFT);
//        topLayout.setExpandRatio(cssLayout2,1);
        topLayout.addStyleName("top-bar");
        
        filterText.setPlaceholder("filtro de busqueda");
        //filterText.addValueChangeListener(e -> updateList(e.getValue()));
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        return topLayout;
    }
    
}
