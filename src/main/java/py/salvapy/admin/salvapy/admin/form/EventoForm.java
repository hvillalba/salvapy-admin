/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.salvapy.admin.salvapy.admin.form;

import com.vaadin.data.Binder;
import com.vaadin.data.ValueProvider;
import com.vaadin.navigator.View;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Setter;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.vaadin.easyuploads.UploadField;
import py.salvapy.admin.salvapy.admin.dao.EventoDao;
import py.salvapy.admin.salvapy.admin.dao.NoticiasDao;
import py.salvapy.admin.salvapy.admin.entities.Evento;
import py.salvapy.admin.salvapy.admin.entities.Noticia;
import py.salvapy.admin.salvapy.admin.util.Constants;
import py.salvapy.admin.salvapy.admin.util.ResourceLocator;
import py.salvapy.admin.salvapy.admin.util.Util;

/**
 *
 * @author hectorvillalba
 */
public class EventoForm extends CssLayout implements View {
    TextField titulo = new TextField("Titulo", "");
    TextArea descripcion = new TextArea("Descripción", "");
    DateField desde = new DateField("Desde");
    DateField hasta = new DateField("Hasta");
    String url = "";
    final Image image = new Image("Imagen");
    String fileName = "";
    String ubicacion = "";

    private void borrarArchivo(String ubicacion, String fileName) {
        Util.deleteFileFromDisk(ubicacion,fileName);
        System.out.println("Borrar archivo borrado...!!!");
    }
        

    // Implement both receiver that saves upload in a file and
    // listener for successful upload
    class ImageReceiver implements Upload.Receiver, Upload.SucceededListener {
            private static final long serialVersionUID = -1276759102490466761L;

            public File file;
            
            public OutputStream receiveUpload(String filename,
                                              String mimeType) {
                // Create upload stream
                FileOutputStream fos = null; // Stream to write to
                try {
                    // Open the file for writing.
                    file = new File(Constants.UPLOAD_DIR + "/salvapy/" +  filename);
                    url = Constants.PUBLIC_SERVER_URL + "/salvapy/" +  filename;
                    fileName = filename;
                    ubicacion = Constants.PUBLIC_SERVER_URL + "/salvapy/";
                    fos = new FileOutputStream(file);
                } catch (final java.io.FileNotFoundException e) {
                    new Notification("Could not open file<br/>",
                                     e.getMessage(),
                                     Notification.Type.ERROR_MESSAGE)
                        .show(Page.getCurrent());
                    return null;
                }
                return fos; // Return the output stream to write to
            }

            public void uploadSucceeded(Upload.SucceededEvent event) {
                // Show the uploaded file in the image viewer
                image.setVisible(true);
                image.setSource(new FileResource(file));
            }
        };
    
    Button save = new Button("Guardar");
    Button cancel = new Button("Cancelar");
    Button delete = new Button("Borrar");
   
    private EventoDao eventoDao = ResourceLocator.locate(EventoDao.class);
    private Binder<Evento> binder = new Binder<>(Evento.class);
    private Evento evento;
    private Consumer<Evento> saveListener;
    private Consumer<Evento> deleteListener;
    private Consumer<Evento> cancelListener;
    ImageReceiver receiver = new ImageReceiver();
    Upload upload =upload = new Upload("Subir imagen", receiver);
    FormLayout formLayout = new FormLayout();
    
    
    public EventoForm(){
        try {
            System.out.print("Nueva instancia de EventoForm");
            setSizeFull();
            addStyleName("crud-view");
            
            VerticalLayout mapContent = new VerticalLayout();
            mapContent.setSizeFull();
            
            HorizontalLayout botones = new HorizontalLayout();
            botones.addComponents(save, cancel, delete);
            save.addStyleName(ValoTheme.BUTTON_PRIMARY);
            delete.addStyleName(ValoTheme.BUTTON_DANGER);

            formLayout.addComponents(titulo, descripcion, desde,hasta, botones, upload, image);
            //VerticalLayout verticalLayout = createVerticalLayout();
            VerticalLayout mapAndForm = new VerticalLayout();
            mapAndForm.addComponent(formLayout);
            Panel panel = new Panel();
            panel.setContent(mapAndForm);
            panel.setSizeFull();
            panel.setScrollTop(1540);

            binder.bind(descripcion, Evento::getDescripcion,Evento::setDescripcion);
            binder.bind(titulo, Evento::getTitulo,Evento::setTitulo);
            binder.bind(desde, No.getter(),No.setter());
            binder.bind(hasta, No.getter(),No.setter());
            binder.bindInstanceFields(this);

            image.setVisible(false);
            upload.setButtonCaption("Start Upload");
            upload.addSucceededListener(receiver);

            // Prevent too big downloads
            final long UPLOAD_LIMIT = 1000000l;
            upload.addStartedListener(new Upload.StartedListener() {
                private static final long serialVersionUID = 4728847902678459488L;

                @Override
                public void uploadStarted(Upload.StartedEvent event) {
                    if (event.getContentLength() > UPLOAD_LIMIT) {
                        Notification.show("Too big file",
                            Notification.Type.ERROR_MESSAGE);
                        upload.interruptUpload();
                    }
                }
            });
            
                    // Check the size also during progress 
            upload.addProgressListener(new Upload.ProgressListener() {
                private static final long serialVersionUID = 8587352676703174995L;

                @Override
                public void updateProgress(long readBytes, long contentLength) {
                    if (readBytes > UPLOAD_LIMIT) {
                        Notification.show("Too big file",
                            Notification.Type.ERROR_MESSAGE);
                        upload.interruptUpload();
                    }
                } 
            });
                    // Create uploads directory
            File uploads = new File("/tmp/uploads");
            if (!uploads.exists() && !uploads.mkdir())
            addComponent(new Label("ERROR: Could not create upload dir"));
            
            save.addClickListener(e ->{
                save();
            });
            cancel.addClickListener(e -> {
                setVisible(false);
                cancelListener.accept(evento);
            });
            delete.addClickListener(e -> {
                setVisible(false);
                borrarArchivo(ubicacion,fileName);
                delete();
                deleteListener.accept(evento);
            });    
            addComponent(panel); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void showUploadedImage(UploadField upload) {
        Object value = upload.getValue();
        final byte[] data = (byte[]) value;

        StreamResource resource = new StreamResource(
                new StreamResource.StreamSource() {
                    @Override
                    public InputStream getStream() {
                        return new ByteArrayInputStream(data);
                    }
                }, "filename.png");

        image.setSource(resource);
    }
    
      // convenience empty getter and setter implementation for better readability
    public static class No {
        public static <SOURCE, TARGET> ValueProvider<SOURCE, TARGET> getter() {
            return source -> null;
        }
 
        public static <BEAN, FIELDVALUE> Setter<BEAN, FIELDVALUE> setter() {
            return (bean, fieldValue) -> {
                //no op
            };
        }
    }
    
    public void setEvento(Evento evento){
        this.evento= evento;
        binder.setBean(evento);  
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (evento.getFechaDesde() != null) {
            LocalDate dt = LocalDate.parse(evento.getFechaDesde().toString(), formatter);
            desde.setValue(dt);
        }
        if (evento.getFechaHasta() != null) {
            LocalDate dt1 = LocalDate.parse(evento.getFechaHasta().toString(), formatter);
            hasta.setValue(dt1);
        }
        if (evento.getUrl() != null) {
            image.setSource(new ExternalResource(evento.getUrl()));
            image.setVisible(true);
        }           
        delete.setVisible(((evento.getIdevento() != null)));
        setVisible(true);
        descripcion.selectAll();
    }
    
    private void delete(){
        try {
            if (eventoDao.delete(evento)) {
                setVisible(false);
            }else{
                Notification.show("Atención", "Ocurio un error al intentar borrar el registro", Notification.Type.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(NoticiasForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void save(){
        try {
            java.util.Date d = new SimpleDateFormat("yyyy-MM-dd").parse(desde.getValue().toString());
            java.util.Date h = new SimpleDateFormat("yyyy-MM-dd").parse(hasta.getValue().toString());
            if (!url.equals("")) {
                evento.setUrl(url);
                evento.setUbicacion(ubicacion);
                evento.setFileName(fileName);
            }
           
            evento.setFechaDesde(d);
            evento.setFechaHasta(h);
            eventoDao.save(evento);
            setVisible(false);
            saveListener.accept(evento);
        } catch (Exception ex) {
            Notification.show("Atención", "Ocurio un error al intentar borrar el registro", Notification.Type.ERROR_MESSAGE);
            saveListener.accept(evento);
            Logger.getLogger(NoticiasForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setSaveListener(Consumer<Evento> saveListener) {
        this.saveListener = saveListener;
    }

    public void setDeleteListener(Consumer<Evento> deleteListener) {
        this.deleteListener = deleteListener;
    }

    public void setCancelListener(Consumer<Evento> cancelListener) {
        this.cancelListener = cancelListener;
    }
    
    
    
}
