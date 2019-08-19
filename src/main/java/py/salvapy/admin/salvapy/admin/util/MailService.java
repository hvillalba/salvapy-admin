package py.salvapy.admin.salvapy.admin.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.mail.util.ByteArrayDataSource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.java.Log;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import py.salvapy.admin.salvapy.admin.entities.Donantes;

/**
 *
 * @author hectorvillalba
 * metodo que envia mail
 */
@Log
@Stateless
public class MailService implements Serializable{
    
    @PersistenceContext
    static EntityManager em;
    
    public static void enviarMailActivacion(Donantes donante) throws EmailException, IOException {
        try {
            //String link = Constants.PUBLIC_API_CONFIRM_URL + donante.getNombre() + " "+ donante.getApellido() + "/" + usuarioParam.getTokenConfirmacion();
            String subject = "Bienvenido a SalvaPy";
            String password = RandomString.getAlphaNumericString(4);
            String message = "Su password provisorio es: " + password;
            
            donante.setPassword(PasswordService.getInstance().encrypt(password));
            em.merge(donante);
            
            List mails = new ArrayList();
            //mails.add(usuarioParam.getEmail());

            sendeEmail(subject, message, mails);
        } catch (Exception ex) {
            Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sendeEmail(String subject,
            String mensaje,
            List<String> emails,
            byte[] content,
            String nameAttachment
    ) throws EmailException, IOException {
        EmailAttachment attachment = new EmailAttachment();
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Archivo Adjunto");
        attachment.setName(nameAttachment);

        // Create the email message
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(Constants.HOST_NAME_SMTP);
        email.setSSLOnConnect(true);
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(Constants.USER_NAME_SMTP, Constants.PASS_SMTP));
        for (String s : emails) {
            email.addTo(s);
        }
        //email.addCc(config.getMailCC());
        email.setFrom(Constants.USER_NAME_SMTP, "SalvaPy");
        email.setSubject(subject);
        email.setMsg(mensaje);

        // add the attachment
        String extension = nameAttachment.substring(nameAttachment.length() - 3);
        String nameWithoutExtension = nameAttachment.substring(0, nameAttachment.length() - 4);
        email.attach(new ByteArrayDataSource(content, "application/" + extension), nameWithoutExtension, "Archivo Adjunto", EmailAttachment.ATTACHMENT);
        // send the email
        email.send();
    }

    public static void sendeEmail(String subject,
            String mensaje,
            List<String> emails) throws EmailException, IOException {

        // Create the email message
        Email email = new SimpleEmail();
        email.setCharset("UTF-8");
        email.setHostName(Constants.HOST_NAME_SMTP);
        email.setSmtpPort(465);
        email.setSSLOnConnect(true);
        email.setAuthenticator(new DefaultAuthenticator(Constants.USER_NAME_SMTP, Constants.PASS_SMTP));
        for (String s : emails) {
            email.addTo(s);
        }
        //email.addCc(config.getMailCC());
        email.setFrom(Constants.USER_NAME_SMTP, "SalvaPy");
        email.setSubject(subject);
        email.setMsg(mensaje);
        // send the email
        email.send();
    }
}
