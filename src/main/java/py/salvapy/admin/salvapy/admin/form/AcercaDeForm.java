/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.salvapy.admin.salvapy.admin.form;

import com.vaadin.data.Binder;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import py.salvapy.admin.salvapy.admin.dao.AcercaDeDao;
import py.salvapy.admin.salvapy.admin.entities.Acercade;
import py.salvapy.admin.salvapy.admin.util.ResourceLocator;

/**
 *
 * @author hectorvillalba
 */
public class AcercaDeForm  extends FormLayout{
    TextArea descripcion = new TextArea("Descripción", "");
    
    //ExpandingTextArea expandingTextArea;
    Button save = new Button("Guardar");
    Button cancel = new Button("Cancelar");
    Button delete = new Button("Borrar");
   
    private AcercaDeDao acercaDeDao = ResourceLocator.locate(AcercaDeDao.class);
    private Binder<Acercade> binder = new Binder<>(Acercade.class);
    private Acercade acercaDe;
    private Consumer<Acercade> saveListener;
    private Consumer<Acercade> deleteListener;
    private Consumer<Acercade> cancelListener;

    public Consumer<Acercade> getCancelListener() {
        return cancelListener;
    }

    public void setCancelListener(Consumer<Acercade> cancelListener) {
        this.cancelListener = cancelListener;
    }

    public Consumer<Acercade> getSaveListener() {
        return saveListener;
    }

    public void setSaveListener(Consumer<Acercade> saveListener) {
        this.saveListener = saveListener;
    }

    public Consumer<Acercade> getDeleteListener() {
        return deleteListener;
    }

    public void setDeleteListener(Consumer<Acercade> deleteListener) {
        this.deleteListener = deleteListener;
    }
    
    
    public void setCiudad(Acercade ciudad){
        this.acercaDe = ciudad;
        binder.setBean(ciudad);  
        delete.setVisible(((ciudad.getId() != null)));
        setVisible(true);
        descripcion.selectAll();
    }
        
    public AcercaDeForm(){
        try {
            addStyleName("product-form-wrapper");
            addStyleName("product-form");
            setSizeUndefined();
            descripcion.setRows(20);
            descripcion.setSizeFull();
            //VerticalLayout verticalLayout = createVerticalLayout();
            HorizontalLayout botones = new HorizontalLayout();
            botones.addComponents(save, cancel, delete);
            save.addStyleName(ValoTheme.BUTTON_PRIMARY);
            delete.addStyleName(ValoTheme.BUTTON_DANGER);
            addComponents(descripcion,botones);
            
            
            binder.bind(descripcion, Acercade::getDescripcion,Acercade::setDescripcion);
            //password.addBlurListener(blurEvent -> {validarPassword();});
            //binder.bind(apellido, Usuarios::getApellido, Usuarios::setApellido);
            binder.forField(descripcion)
                    .withNullRepresentation ( "" )
                    //.withConverter(new StringToIntegerConverter ( Integer.valueOf ( 0 ), "integers only" ) )
                    .bind(Acercade::getDescripcion, Acercade::setDescripcion);
            //binder.bind(telefono, Usuarios::getTelefono, Usuarios::setTelefono);
            binder.bindInstanceFields(this);
            
            save.addClickListener(e ->{
                save();
            });
            cancel.addClickListener(e -> {
                setVisible(false);
                cancelListener.accept(acercaDe);
            });
            delete.addClickListener(e -> {
                setVisible(false);
                delete();
                deleteListener.accept(acercaDe);
            });
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void delete(){
        try {
            
            if (acercaDeDao.delete(acercaDe)) {
                setVisible(false);
            }else{
                Notification.show("Atención", "Ocurio un error al intentar borrar el registro", Notification.Type.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            Logger.getLogger(AcercaDeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void save(){
        try {
            acercaDeDao.save(acercaDe);
            setVisible(false);
            saveListener.accept(acercaDe);
        } catch (Exception ex) {
            Notification.show("Atención", "Ocurio un error al intentar borrar el registro", Notification.Type.ERROR_MESSAGE);
            saveListener.accept(acercaDe);
            Logger.getLogger(AcercaDeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
