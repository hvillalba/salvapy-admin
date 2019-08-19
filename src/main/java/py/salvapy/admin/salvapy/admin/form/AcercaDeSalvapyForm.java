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
import py.salvapy.admin.salvapy.admin.dao.AcercaDeSalvaPyDao;
import py.salvapy.admin.salvapy.admin.entities.Acercade;
import py.salvapy.admin.salvapy.admin.entities.Acercadesalvapy;
import py.salvapy.admin.salvapy.admin.util.ResourceLocator;

/**
 *
 * @author hectorvillalba
 */
public class AcercaDeSalvapyForm extends FormLayout {
    TextArea descripcion = new TextArea("Descripción", "");
    
    Button save = new Button("Guardar");
    Button cancel = new Button("Cancelar");
    Button delete = new Button("Borrar");
   
    private AcercaDeSalvaPyDao acercaDeDao = ResourceLocator.locate(AcercaDeSalvaPyDao.class);
    private Binder<Acercadesalvapy> binder = new Binder<>(Acercadesalvapy.class);
    private Acercadesalvapy acercaDe;
    private Consumer<Acercadesalvapy> saveListener;
    private Consumer<Acercadesalvapy> deleteListener;
    private Consumer<Acercadesalvapy> cancelListener;

    public Consumer<Acercadesalvapy> getCancelListener() {
        return cancelListener;
    }

    public void setCancelListener(Consumer<Acercadesalvapy> cancelListener) {
        this.cancelListener = cancelListener;
    }

    public Consumer<Acercadesalvapy> getSaveListener() {
        return saveListener;
    }

    public void setSaveListener(Consumer<Acercadesalvapy> saveListener) {
        this.saveListener = saveListener;
    }

    public Consumer<Acercadesalvapy> getDeleteListener() {
        return deleteListener;
    }

    public void setDeleteListener(Consumer<Acercadesalvapy> deleteListener) {
        this.deleteListener = deleteListener;
    }
    
    
    public void setCiudad(Acercadesalvapy ciudad){
        this.acercaDe = ciudad;
        binder.setBean(ciudad);  
        delete.setVisible(((ciudad.getId() != null)));
        setVisible(true);
        descripcion.selectAll();
    }
        
    public AcercaDeSalvapyForm(){
        try {
            addStyleName("product-form-wrapper");
            addStyleName("product-form");
            setSizeUndefined();
            //VerticalLayout verticalLayout = createVerticalLayout();
            HorizontalLayout botones = new HorizontalLayout();
            descripcion.setWordWrap(false);
            botones.addComponents(save, cancel, delete);
            save.addStyleName(ValoTheme.BUTTON_PRIMARY);
            delete.addStyleName(ValoTheme.BUTTON_DANGER);
            addComponents(descripcion,botones);
            
            
            binder.bind(descripcion, Acercadesalvapy::getDescripcion,Acercadesalvapy::setDescripcion);
            //password.addBlurListener(blurEvent -> {validarPassword();});
            //binder.bind(apellido, Usuarios::getApellido, Usuarios::setApellido);
            binder.forField(descripcion)
                    .withNullRepresentation ( "" )
                    //.withConverter(new StringToIntegerConverter ( Integer.valueOf ( 0 ), "integers only" ) )
                    .bind(Acercadesalvapy::getDescripcion, Acercadesalvapy::setDescripcion);
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
