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
import py.salvapy.admin.salvapy.admin.dao.DonacionDao;
import py.salvapy.admin.salvapy.admin.dao.NoticiasDao;
import py.salvapy.admin.salvapy.admin.entities.Donacion;
import py.salvapy.admin.salvapy.admin.entities.Noticia;
import py.salvapy.admin.salvapy.admin.form.DonacionForm;
import py.salvapy.admin.salvapy.admin.form.NoticiasForm;
import py.salvapy.admin.salvapy.admin.util.ResourceLocator;

/**
 *
 * @author hectorvillalba
 */
public class DonacionView extends CssLayout implements View {
    public static final String VIEW_NAME = "Donacion";
    Grid<Donacion> gridDonacion = new Grid<>(Donacion.class);
    private Button newDonacion;
    DonacionDao donacionDao = ResourceLocator.locate(DonacionDao.class);
    Logger log =Logger.getLogger("NoticiaView");
    List<Donacion> lista = new ArrayList<>();
    private TextField filterText = new TextField("Filtros");
    private DonacionForm donacionForm = new DonacionForm();
    private Button detalle = new Button("Editar");
    
    public DonacionView(){
        try {
            System.out.print("Nueva instancia de DonacionView");
            setSizeFull();
            addStyleName("crud-view");
            
            HorizontalLayout horizontalLayout = createTopBar();
            lista = donacionDao.getEventos();
            
            
            donacionForm.setVisible(false);
            detalle.setVisible(false);
            newDonacion.addClickListener(e -> {
                gridDonacion.setVisible(false);
                horizontalLayout.setVisible(false);
                gridDonacion.asSingleSelect().clear();
                donacionForm.setVisible(true);
                donacionForm.setNoticia(new Donacion());
            });
            
            HorizontalLayout horizontalLayout2 = new HorizontalLayout();
            horizontalLayout2.addComponents(gridDonacion, donacionForm);
            horizontalLayout2.setSizeFull();
            horizontalLayout2.setExpandRatio(gridDonacion, 1);
            
            
            VerticalLayout barAndGridLayout = new VerticalLayout();
            barAndGridLayout.addComponent(horizontalLayout);
            barAndGridLayout.addComponent(horizontalLayout2);
            barAndGridLayout.setMargin(true);
            barAndGridLayout.setSpacing(true);
            barAndGridLayout.setSizeFull();
            barAndGridLayout.setExpandRatio(horizontalLayout2,1);
            barAndGridLayout.addStyleName("crud-main-layout");
            log.info("Se encontraron  " + lista.size() + " registros");
           
            gridDonacion.setItems(lista);
            gridDonacion.removeAllColumns();
            gridDonacion.addColumn(Donacion::getIddonacion).setCaption("Codigo");
            gridDonacion.addColumn(Donacion::getPaciente).setCaption("Paciente");
            gridDonacion.addColumn(Donacion::getDescripcion).setCaption("Descripción");
            gridDonacion.addColumn(Donacion::getSexo).setCaption("Sexo");
            gridDonacion.addColumn(Donacion::getTipoSangre).setCaption("Tipo de Sangre");
            gridDonacion.addColumn(Donacion::getCantDonantes).setCaption("Cant. Donantes");
            gridDonacion.addColumn(Donacion::getFechaDesde).setCaption("Desde");
            gridDonacion.addColumn(Donacion::getFechaHasta).setCaption("Hasta");
            gridDonacion.setSizeFull();
            
            gridDonacion.asSingleSelect().addValueChangeListener(event -> {
                if (event.getValue() == null) {
                    log.info("SingleSelect null");
                    donacionForm.setVisible(false);
                }else{
                    detalle.setVisible(true);
                    donacionForm.setNoticia(event.getValue());
                    log.info("SingleSelect: " + event.getValue().getIddonacion());
                }
            });
            
            detalle.addClickListener(e -> {
                donacionForm.setVisible(true);
                //noticiaForm.setNoticia(new Noticia());
                gridDonacion.setVisible(false);
                horizontalLayout.setVisible(false);
            });
            
            donacionForm.setSaveListener(usuarios -> {
                    gridDonacion.clearSortOrder();
                    gridDonacion.setVisible(true);
                    lista = donacionDao.getEventos();
                    gridDonacion.setItems(lista);
                    donacionForm.setVisible(true);
                    horizontalLayout.setVisible(true);
                    donacionForm.setVisible(false);
                    Notification.show("Nueva Noticia creada", Notification.Type.HUMANIZED_MESSAGE);
            });

            donacionForm.setCancelListener(usuarios -> {
                gridDonacion.clearSortOrder();
            });

            donacionForm.setDeleteListener(usuarios -> {
                    gridDonacion.clearSortOrder();
                    gridDonacion.setVisible(true);
                    lista = donacionDao.getEventos();
                    gridDonacion.setItems(lista);
                    donacionForm.setVisible(true);
                    horizontalLayout.setVisible(true);
                    donacionForm.setVisible(false);
                    Notification.show("Atención", "Registro borrado correctamente", Notification.Type.HUMANIZED_MESSAGE);
            });
        addComponent(barAndGridLayout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private HorizontalLayout createTopBar() {
        newDonacion = new Button("Nueva Donacion");
        newDonacion.addStyleName(ValoTheme.BUTTON_PRIMARY);
        newDonacion.setIcon(VaadinIcons.PLUS_CIRCLE);
        
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
        topLayout.addComponent(newDonacion);
//        topLayout.setComponentAlignment(cssLayout2, Alignment.MIDDLE_LEFT);
        topLayout.setComponentAlignment(cssLayout, Alignment.MIDDLE_LEFT);
        topLayout.setComponentAlignment(newDonacion, Alignment.MIDDLE_RIGHT);
        
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
