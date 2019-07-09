/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.salvapy.admin.salvapy.admin.form;

import com.vaadin.data.Binder;
import com.vaadin.data.ValueProvider;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.navigator.View;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Setter;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
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
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.vaadin.easyuploads.UploadField;
import py.salvapy.admin.salvapy.admin.dao.DonacionDao;
import py.salvapy.admin.salvapy.admin.dao.NoticiasDao;
import py.salvapy.admin.salvapy.admin.entities.Donacion;
import py.salvapy.admin.salvapy.admin.entities.Donantes;
import py.salvapy.admin.salvapy.admin.entities.Noticia;
import py.salvapy.admin.salvapy.admin.util.Constants;
import py.salvapy.admin.salvapy.admin.util.ResourceLocator;
import py.salvapy.admin.salvapy.admin.util.Util;

/**
 *
 * @author hectorvillalba
 */
public class DonacionForm extends CssLayout implements View {
    TextField nombrePaciente = new TextField("Nombre Paciente", "");
    ComboBox<String> tipoSangre = new ComboBox<>();
    TextField edad = new TextField("Edad", "");
    TextField cantDonantes = new TextField("Cant. de Donantes", "");
    ComboBox<String> sexo = new ComboBox<String>() ;
    TextArea descripcion = new TextArea("Descripción", "");
    DateField desde = new DateField("Desde");
    DateField hasta = new DateField("Hasta");
    String url = "";
    String ubicacion = "";
    String fileName = "";
    private static final Logger LOG = Logger.getLogger("MarcacionFacadeREST");
    //final Image image = new Image("Uploaded image");
    //final UploadField upload = new UploadField();
   
    
    // Implement both receiver that saves upload in a file and
        // BEGIN-EXAMPLE: component.upload.basic
        // Show uploaded file in this placeholder
    final Image image = new Image("Imagen");

    private void sendNoti() {
        List<Donantes> lista = donacionDao.getDonantes();
        for (Donantes donantes : lista) {
            if (donantes.getTipoSangre().equalsIgnoreCase(donacion.getTipoSangre())) {
                sendPushEntroGeoCerca(donantes.getToken());
            }
        }
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
                    ubicacion = Constants.PUBLIC_SERVER_URL + "/salvapy/";
                    fileName = filename;
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
   
    private DonacionDao donacionDao = ResourceLocator.locate(DonacionDao.class);
    private Binder<Donacion> binder = new Binder<>(Donacion.class);
    private Donacion donacion;
    private Consumer<Donacion> saveListener;
    private Consumer<Donacion> deleteListener;
    private Consumer<Donacion> cancelListener;
    ImageReceiver receiver = new ImageReceiver();
    Upload upload =upload = new Upload("Subir imagen", receiver);
    FormLayout formLayout = new FormLayout();
    
    
    public DonacionForm(){
        try {
            System.out.print("Nueva instancia de DonacionForm");
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
            
            
            formLayout.addComponents( descripcion, nombrePaciente,edad,cantDonantes,sexo, tipoSangre, desde,hasta, botones, upload, image);
            //VerticalLayout verticalLayout = createVerticalLayout();
            VerticalLayout mapAndForm = new VerticalLayout();
            mapAndForm.addComponent(formLayout);
            Panel panel = new Panel();
            panel.setContent(mapAndForm);
            panel.setSizeFull();
            panel.setScrollTop(1540);
                 
            List<String> listaSexo = Arrays.asList("M", "F");
            //Llenamos el combo
            sexo.setItems(listaSexo);
            
            List<String> listaTipoSangre = Arrays.asList("A+", "A-", "0+", "0-", "B+", "B-", "AB+", "AB-");
            //Llenamos el combo
            tipoSangre.setItems(listaTipoSangre);
            
            binder.bind(descripcion, Donacion::getDescripcion,Donacion::setDescripcion);
            binder.bind(nombrePaciente, Donacion::getPaciente,Donacion::setPaciente);
            binder.bind(sexo, Donacion::getSexo,Donacion::setSexo);
            binder.bind(tipoSangre, Donacion::getTipoSangre,Donacion::setTipoSangre);
            binder.forField(cantDonantes)
                    .withNullRepresentation ( "" )
                    .withConverter(new StringToIntegerConverter ( Integer.valueOf ( 0 ), "integers only" ) )
                    .bind(Donacion::getCantDonantes,Donacion::setCantDonantes);
            binder.forField(edad)
                    .withNullRepresentation ( "" )
                    .withConverter(new StringToIntegerConverter ( Integer.valueOf ( 0 ), "integers only" ) )
                    .bind(Donacion::getEdad,Donacion::setEdad);            
            //binder.bind(cantDonantes, Donacion::getCantDonantes,Donacion::setCantDonantes);
            binder.bind(desde, No.getter(),No.setter());
            binder.bind(hasta, No.getter(),No.setter());
            binder.bindInstanceFields(this);

            image.setVisible(false);
                     

            // Create the upload with a caption and set receiver later
            
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
                cancelListener.accept(donacion);
            });
            delete.addClickListener(e -> {
                setVisible(false);
                borrarArchivo(fileName, ubicacion);
                delete();
                deleteListener.accept(donacion);
            });    
            addComponent(panel); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void borrarArchivo(String fileName, String ubicacion) {
        Util.deleteFileFromDisk(ubicacion,fileName);
        System.out.println("Borrar archivo borrado...!!!");
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
    public void setNoticia(Donacion donacion){
        this.donacion= donacion;
        binder.setBean(donacion);  
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (donacion.getFechaDesde() != null) {
            LocalDate dt = LocalDate.parse(donacion.getFechaDesde().toString(), formatter);
            desde.setValue(dt); 
        }
        if (donacion.getFechaHasta() != null) {
            LocalDate dt1 = LocalDate.parse(donacion.getFechaHasta().toString(), formatter);
            hasta.setValue(dt1);
        }
        if (donacion.getUrl()!= null) {
            image.setSource(new ExternalResource(donacion.getUrl()));
            image.setVisible(true);
        }           
        delete.setVisible(((donacion.getIddonacion() != null)));
        setVisible(true);
        descripcion.selectAll();
    }
    
    private void delete(){
        try {
            if (donacionDao.delete(donacion)) {
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
                donacion.setUrl(url);
                donacion.setUbicacion(ubicacion);
                donacion.setFileName(fileName);
            }
            donacion.setFechaDesde(d);
            donacion.setFechaHasta(h);
            donacionDao.save(donacion);
            setVisible(false);
           
            saveListener.accept(donacion);
            sendNoti();
        } catch (Exception ex) {
            Notification.show("Atención", "Ocurio un error al intentar borrar el registro", Notification.Type.ERROR_MESSAGE);
            saveListener.accept(donacion);
            Logger.getLogger(NoticiasForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setSaveListener(Consumer<Donacion> saveListener) {
        this.saveListener = saveListener;
    }

    public void setDeleteListener(Consumer<Donacion> deleteListener) {
        this.deleteListener = deleteListener;
    }

    public void setCancelListener(Consumer<Donacion> cancelListener) {
        this.cancelListener = cancelListener;
    }
    
    
    private void sendPushEntroGeoCerca(String token) {
        try {
            //String token = superior.getFcmToken();
            System.out.println("token: " + token);
            HttpURLConnection httpcon = (HttpURLConnection) ((new URL("https://fcm.googleapis.com/fcm/send").openConnection()));
            httpcon.setDoOutput(true);
            httpcon.setRequestProperty("Content-Type", "application/json");
            httpcon.setRequestProperty("Authorization", "key=AIzaSyCvba6FGO5uOIk7yMQeXafljleodVHiKW4");
            httpcon.setRequestMethod("POST");
            httpcon.connect();
            System.out.println("Connected!");
            //Type listType = new TypeToken<Request<OrdersDto>>(){}.getType();
            //String json = gson.toJson(order, listType);
            String message = "{\"notification\":{\"title\": \"Pedido de Donacion\" , \"text\": \"El paciente "+donacion.getPaciente()+" necesita sangre del tipo "+donacion.getTipoSangre()+" \" }, \"data\":{\"action\": \"Pedido de Donacion\", \"title\": \"Pedido de Donacion\" , \"text\": \"El paciente "+donacion.getPaciente()+" necesita sangre del tipo "+donacion.getTipoSangre()+" \" }, \"to\": \"" + token + "\"}";
                
            // 
            System.out.println("Message: " + message);
            byte[] outputBytes = message.getBytes("UTF-8");
            OutputStream os = httpcon.getOutputStream();
            os.write(outputBytes);
            os.close();

            // Reading response
            InputStream input = httpcon.getInputStream();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
                for (String line; (line = reader.readLine()) != null;) {
                    System.out.println(line);
                }
            }

            System.out.println("Http POST request sent!");
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    
}
