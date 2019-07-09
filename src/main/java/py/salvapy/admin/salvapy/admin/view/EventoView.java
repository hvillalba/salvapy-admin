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
import py.salvapy.admin.salvapy.admin.dao.EventoDao;
import py.salvapy.admin.salvapy.admin.dao.NoticiasDao;
import py.salvapy.admin.salvapy.admin.entities.Evento;
import py.salvapy.admin.salvapy.admin.entities.Noticia;
import py.salvapy.admin.salvapy.admin.form.EventoForm;
import py.salvapy.admin.salvapy.admin.form.NoticiasForm;
import py.salvapy.admin.salvapy.admin.util.ResourceLocator;

/**
 *
 * @author hectorvillalba
 */
public class EventoView extends CssLayout implements View  {
    public static final String VIEW_NAME = "Eventos";
    Grid<Evento> gridEvento = new Grid<>(Evento.class);
    private Button newEvento;
    EventoDao eventoDao = ResourceLocator.locate(EventoDao.class);
    Logger log =Logger.getLogger("EventoView");
    List<Evento> lista = new ArrayList<>();
    private TextField filterText = new TextField("Filtros");
    private EventoForm eventoForm = new EventoForm();
    private Button detalle = new Button("Editar");
    
    public EventoView(){
        try {
            System.out.print("Nueva instancia de EventoView");
            setSizeFull();
            addStyleName("crud-view");
            
            HorizontalLayout horizontalLayout = createTopBar();
            lista = eventoDao.getEventos();
            
            
            eventoForm.setVisible(false);
            detalle.setVisible(false);
            newEvento.addClickListener(e -> {
                gridEvento.setVisible(false);
                horizontalLayout.setVisible(false);
                gridEvento.asSingleSelect().clear();
                eventoForm.setVisible(true);
                eventoForm.setEvento(new Evento());
            });
            
            HorizontalLayout horizontalLayout2 = new HorizontalLayout();
            horizontalLayout2.addComponents(gridEvento, eventoForm);
            horizontalLayout2.setSizeFull();
            horizontalLayout2.setExpandRatio(gridEvento, 1);
            
            
            VerticalLayout barAndGridLayout = new VerticalLayout();
            barAndGridLayout.addComponent(horizontalLayout);
            barAndGridLayout.addComponent(horizontalLayout2);
            barAndGridLayout.setMargin(true);
            barAndGridLayout.setSpacing(true);
            barAndGridLayout.setSizeFull();
            barAndGridLayout.setExpandRatio(horizontalLayout2,1);
            barAndGridLayout.addStyleName("crud-main-layout");
            log.info("Se encontraron  " + lista.size() + " registros");
           
            gridEvento.setItems(lista);
            gridEvento.removeAllColumns();
            gridEvento.addColumn(Evento::getIdevento).setCaption("Codigo");
            gridEvento.addColumn(Evento::getTitulo).setCaption("Titulo");
            gridEvento.addColumn(Evento::getDescripcion).setCaption("Descripción");
            gridEvento.addColumn(Evento::getFechaDesde).setCaption("Desde");
            gridEvento.addColumn(Evento::getFechaHasta).setCaption("Hasta");
            gridEvento.setSizeFull();
            
            gridEvento.asSingleSelect().addValueChangeListener(event -> {
                if (event.getValue() == null) {
                    log.info("SingleSelect null");
                    eventoForm.setVisible(false);
                }else{
                    detalle.setVisible(true);
                    eventoForm.setEvento(event.getValue());
                    log.info("SingleSelect: " + event.getValue().getIdevento());
                }
            });
            
            detalle.addClickListener(e -> {
                eventoForm.setVisible(true);
                //noticiaForm.setNoticia(new Noticia());
                gridEvento.setVisible(false);
                horizontalLayout.setVisible(false);
            });
            
            eventoForm.setSaveListener(usuarios -> {
                    gridEvento.clearSortOrder();
                    lista = eventoDao.getEventos();
                    gridEvento.setItems(lista);
                    gridEvento.setVisible(true);
                    horizontalLayout.setVisible(true);
                    eventoForm.setVisible(false);
                    Notification.show("Nuevo Evento creada", Notification.Type.HUMANIZED_MESSAGE);
            });

            eventoForm.setCancelListener(usuarios -> {
                gridEvento.clearSortOrder();
            });

            eventoForm.setDeleteListener(usuarios -> {
                gridEvento.clearSortOrder();
                lista = eventoDao.getEventos();
                gridEvento.setItems(lista);
                gridEvento.setVisible(true);
                horizontalLayout.setVisible(true);
                eventoForm.setVisible(false);
                gridEvento.clearSortOrder();
                Notification.show("Atención", "Registro borrado correctamente", Notification.Type.HUMANIZED_MESSAGE);
            });
        addComponent(barAndGridLayout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private HorizontalLayout createTopBar() {
        newEvento = new Button("Nueva Evento");
        newEvento.addStyleName(ValoTheme.BUTTON_PRIMARY);
        newEvento.setIcon(VaadinIcons.PLUS_CIRCLE);
        
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
        topLayout.addComponent(newEvento);
//        topLayout.setComponentAlignment(cssLayout2, Alignment.MIDDLE_LEFT);
        topLayout.setComponentAlignment(cssLayout, Alignment.MIDDLE_LEFT);
        topLayout.setComponentAlignment(newEvento, Alignment.MIDDLE_RIGHT);
        
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
