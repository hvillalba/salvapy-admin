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
import py.salvapy.admin.salvapy.admin.dao.AcercaDeDao;
import py.salvapy.admin.salvapy.admin.dao.AcercaDeSalvaPyDao;
import py.salvapy.admin.salvapy.admin.entities.Acercade;
import py.salvapy.admin.salvapy.admin.entities.Acercadesalvapy;
import py.salvapy.admin.salvapy.admin.form.AcercaDeForm;
import py.salvapy.admin.salvapy.admin.form.AcercaDeSalvapyForm;
import py.salvapy.admin.salvapy.admin.util.ResourceLocator;

/**
 *
 * @author hectorvillalba
 */
public class AcercaDeSalvapyView extends CssLayout implements View {
    public static final String VIEW_NAME = "Acerca de SalvaPy";
    Grid<Acercadesalvapy> gridAcercaDe = new Grid<>(Acercadesalvapy.class);
    private Button newCiudad;
    AcercaDeSalvaPyDao acercaDeDao = ResourceLocator.locate(AcercaDeSalvaPyDao.class);
    Logger log =Logger.getLogger("Acerca de SalvaPy");
    List<Acercadesalvapy> lista = new ArrayList<>();
    private TextField filterText = new TextField("Filtros");
    private AcercaDeSalvapyForm acercaDeForm = new AcercaDeSalvapyForm();
    
    public AcercaDeSalvapyView(){
        try {
            System.out.print("Nueva instancia de Acerca de SalvaPy");
            setSizeFull();
            addStyleName("crud-view");
            
            HorizontalLayout horizontalLayout = createTopBar();
            lista = acercaDeDao.getAcercaSalvaPy();
            
            newCiudad.addClickListener(e -> {
                gridAcercaDe.asSingleSelect().clear();
                acercaDeForm.setCiudad(new Acercadesalvapy());
            });
            
            acercaDeForm.setVisible(false);
            HorizontalLayout horizontalLayout2 = new HorizontalLayout();
            horizontalLayout2.addComponents(gridAcercaDe, acercaDeForm);
            horizontalLayout2.setSizeFull();
            horizontalLayout2.setExpandRatio(gridAcercaDe, 1);
            
            
            VerticalLayout barAndGridLayout = new VerticalLayout();
            barAndGridLayout.addComponent(horizontalLayout);
            barAndGridLayout.addComponent(horizontalLayout2);
            barAndGridLayout.setMargin(true);
            barAndGridLayout.setSpacing(true);
            barAndGridLayout.setSizeFull();
            barAndGridLayout.setExpandRatio(horizontalLayout2,1);
            barAndGridLayout.addStyleName("crud-main-layout");
            log.info("Se encontraron  " + lista.size() + " registros");
           
            gridAcercaDe.setItems(lista);
            gridAcercaDe.removeAllColumns();
            gridAcercaDe.addColumn(Acercadesalvapy::getId).setCaption("Id");
            gridAcercaDe.addColumn(Acercadesalvapy::getDescripcion).setCaption("Descripción");
            gridAcercaDe.setSizeFull();
            
            gridAcercaDe.asSingleSelect().addValueChangeListener(event -> {
                if (event.getValue() == null) {
                    log.info("SingleSelect null");
                    acercaDeForm.setVisible(false);
                }else{
                    acercaDeForm.setCiudad(event.getValue());
                    log.info("SingleSelect: " + event.getValue().getId());
                }
            });
            
        acercaDeForm.setSaveListener(usuarios -> {
                gridAcercaDe.clearSortOrder();
                lista = acercaDeDao.getAcercaSalvaPy();
                gridAcercaDe.setItems(lista);
                Notification.show("Nueva registro creada", Notification.Type.HUMANIZED_MESSAGE);
        });

        acercaDeForm.setCancelListener(usuarios -> {
            gridAcercaDe.clearSortOrder();
            lista = acercaDeDao.getAcercaSalvaPy();
            gridAcercaDe.setItems(lista);
        });

        acercaDeForm.setDeleteListener(usuarios -> {
            gridAcercaDe.clearSortOrder();  
            lista = acercaDeDao.getAcercaSalvaPy();
            gridAcercaDe.setItems(lista);
            Notification.show("Atención", "Registro borrado correctamente", Notification.Type.HUMANIZED_MESSAGE);
//            grid.getContainerDataSource().removeItem(product);
        });
                    
        addComponent(barAndGridLayout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private HorizontalLayout createTopBar() {
        newCiudad = new Button("Nueva Registro");
        newCiudad.addStyleName(ValoTheme.BUTTON_PRIMARY);
        newCiudad.setIcon(VaadinIcons.PLUS_CIRCLE);
        
        
        CssLayout cssLayout = new CssLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponentsAndExpand(filterText);
        cssLayout.addComponent(horizontalLayout);

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setSpacing(true);
        topLayout.setWidth("100%");
        topLayout.addComponent(cssLayout);
        topLayout.addComponent(newCiudad);
        topLayout.setComponentAlignment(cssLayout, Alignment.MIDDLE_LEFT);
        topLayout.setComponentAlignment(newCiudad, Alignment.MIDDLE_RIGHT);
//        topLayout.setExpandRatio(filterText,1);
//        topLayout.setComponentAlignment(filterCombo, Alignment.MIDDLE_LEFT);
//        topLayout.setExpandRatio(filterCombo,1);
        topLayout.addStyleName("top-bar");
        
        filterText.setPlaceholder("filtro de busqueda");
//        filterText.addValueChangeListener(e -> updateList(e.getValue()));
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        return topLayout;
    }
    
//    private void updateList(String param) {
//        try {
//            lista = acercaDeDao.getListCiudad(param);
//            gridCiudad.setSizeFull();
//            gridCiudad.setItems(lista);
//            gridCiudad.clearSortOrder();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }    
    
}
