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
import com.vaadin.ui.Upload.ProgressListener;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.StartedListener;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.vaadin.easyuploads.UploadField;
import py.salvapy.admin.salvapy.admin.dao.NoticiasDao;
import py.salvapy.admin.salvapy.admin.entities.Noticia;
import py.salvapy.admin.salvapy.admin.util.Constants;
import py.salvapy.admin.salvapy.admin.util.ResourceLocator;
import py.salvapy.admin.salvapy.admin.util.Util;

/**
 *
 * @author hectorvillalba
 */
public class NoticiasForm extends CssLayout implements View {
    TextField titulo = new TextField("Titulo", "");
    TextArea descripcion = new TextArea("Descripción", "");
    DateField desde = new DateField("Desde");
    DateField hasta = new DateField("Hasta");
    String url = "";
    String fileName = "";
    String ubicacion = "";
    //final Image image = new Image("Uploaded image");
    //final UploadField upload = new UploadField();
   
    
    // Implement both receiver that saves upload in a file and
        // BEGIN-EXAMPLE: component.upload.basic
        // Show uploaded file in this placeholder
    final Image image = new Image("Imagen");

    private void borrarArchivo(String fileName, String ubicacion) {
        Util.deleteFileFromDisk(ubicacion,fileName);
        System.out.println("Borrar archivo borrado...!!!");
    }
        

        // Implement both receiver that saves upload in a file and
        // listener for successful upload
        class ImageReceiver implements Receiver, SucceededListener {
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
                    ubicacion= Constants.PUBLIC_SERVER_URL + "/salvapy/";
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

            public void uploadSucceeded(SucceededEvent event) {
                // Show the uploaded file in the image viewer
                image.setVisible(true);
                image.setSource(new FileResource(file));
            }
        };
    
    Button save = new Button("Guardar");
    Button cancel = new Button("Cancelar");
    Button delete = new Button("Borrar");
   
    private NoticiasDao categoriaDao = ResourceLocator.locate(NoticiasDao.class);
    private Binder<Noticia> binder = new Binder<>(Noticia.class);
    private Noticia noticia;
    private Consumer<Noticia> saveListener;
    private Consumer<Noticia> deleteListener;
    private Consumer<Noticia> cancelListener;
    ImageReceiver receiver = new ImageReceiver();
    Upload upload =upload = new Upload("Subir imagen", receiver);
    FormLayout formLayout = new FormLayout();
    
    
    public NoticiasForm(){
        try {
            System.out.print("Nueva instancia de NoticiasForm");
            setSizeFull();
            addStyleName("crud-view");
            
            VerticalLayout mapContent = new VerticalLayout();
            mapContent.setSizeFull();
            
            HorizontalLayout botones = new HorizontalLayout();
            botones.addComponents(save, cancel, delete);
            save.addStyleName(ValoTheme.BUTTON_PRIMARY);
            delete.addStyleName(ValoTheme.BUTTON_DANGER);
//            VerticalLayout mapContent = new VerticalLayout();
            //mapContent.setExpandRatio(googleMap, 1.0f);
            
            
            formLayout.addComponents(titulo, descripcion, desde,hasta, botones, upload, image);
            //VerticalLayout verticalLayout = createVerticalLayout();
            VerticalLayout mapAndForm = new VerticalLayout();
            mapAndForm.addComponent(formLayout);
            Panel panel = new Panel();
            panel.setContent(mapAndForm);
            panel.setSizeFull();
            panel.setScrollTop(1540);
            
            binder.bind(descripcion, Noticia::getDescripcion,Noticia::setDescripcion);
            binder.bind(titulo, Noticia::getTitulo,Noticia::setTitulo);
            binder.bind(desde, No.getter(),No.setter());
            binder.bind(hasta, No.getter(),No.setter());
            binder.bindInstanceFields(this);

            image.setVisible(false);
                     

            // Create the upload with a caption and set receiver later
            
            upload.setButtonCaption("Start Upload");
            upload.addSucceededListener(receiver);

            // Prevent too big downloads
            final long UPLOAD_LIMIT = 1000000l;
            upload.addStartedListener(new StartedListener() {
                private static final long serialVersionUID = 4728847902678459488L;

                @Override
                public void uploadStarted(StartedEvent event) {
                    if (event.getContentLength() > UPLOAD_LIMIT) {
                        Notification.show("Too big file",
                            Notification.Type.ERROR_MESSAGE);
                        upload.interruptUpload();
                    }
                }
            });
            
                    // Check the size also during progress 
            upload.addProgressListener(new ProgressListener() {
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
            
//            upload.addListener(new Listener(){
//                @Override
//                public void componentEvent(Event event)
//                {
//                    showUploadedImage(upload);
//                }
//            });
            
            save.addClickListener(e ->{
                save();
            });
            cancel.addClickListener(e -> {
                setVisible(false);
                cancelListener.accept(noticia);
            });
            delete.addClickListener(e -> {
                setVisible(false);
                borrarArchivo(noticia.getFileName(), noticia.getUbicacion());
                delete();
                deleteListener.accept(noticia);
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
    public void setNoticia(Noticia noticia){
        this.noticia= noticia;
        binder.setBean(noticia);  
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (noticia.getFechaDesde() != null) {
            LocalDate dt = LocalDate.parse(noticia.getFechaDesde().toString(), formatter);
            //Instant instant = Instant.ofEpochMilli(noticia.getFechaDesde().getTime());
            //LocalDate ldt = LocalDate.from(instant);
            desde.setValue(dt); 
        }
        if (noticia.getFechaHasta() != null) {
            //Instant instant1 = Instant.ofEpochMilli(noticia.getFechaHasta().getTime());
            //LocalDate ldt1 = LocalDate.from(instant1);
            LocalDate dt1 = LocalDate.parse(noticia.getFechaHasta().toString(), formatter);
            hasta.setValue(dt1);            
        }
        if (noticia.getImagen()!= null) {
            image.setSource(new ExternalResource(noticia.getImagen()));
            image.setVisible(true);
        }           
        delete.setVisible(((noticia.getId() != null)));
        setVisible(true);
        descripcion.selectAll();
    }
    
    private void delete(){
        try {
            if (categoriaDao.delete(noticia)) {
                setVisible(false);
            }else{
                Notification.show("Atención", "Ocurio un error al intentar borrar el registro", Notification.Type.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            Logger.getLogger(NoticiasForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void save(){
        try {
            java.util.Date d = new SimpleDateFormat("yyyy-MM-dd").parse(desde.getValue().toString());
            java.util.Date h = new SimpleDateFormat("yyyy-MM-dd").parse(hasta.getValue().toString());
            if (!url.equals("")) {
                noticia.setImagen(url);
                noticia.setUbicacion(ubicacion);
                noticia.setFileName(fileName);
            }
            
            noticia.setFechaDesde(d);
            noticia.setFechaHasta(h);
            categoriaDao.save(noticia);
            setVisible(false);
            saveListener.accept(noticia);
        } catch (Exception ex) {
            Notification.show("Atención", "Ocurio un error al intentar borrar el registro", Notification.Type.ERROR_MESSAGE);
            saveListener.accept(noticia);
            Logger.getLogger(NoticiasForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setSaveListener(Consumer<Noticia> saveListener) {
        this.saveListener = saveListener;
    }

    public void setDeleteListener(Consumer<Noticia> deleteListener) {
        this.deleteListener = deleteListener;
    }

    public void setCancelListener(Consumer<Noticia> cancelListener) {
        this.cancelListener = cancelListener;
    }
    
    
   
}
