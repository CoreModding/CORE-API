package info.coremodding.api.net.smtp.email;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @author James The mail util
 */
public class EmailUtil {

    /**
     * @param path       The location of the file
     * @param desc       The file description
     * @param name       The file name
     * @param urlnotpath Is the path a URL not a path?
     * @return The email attachment generated
     * @throws MalformedURLException You gave a bad URL!
     */
    public static EmailAttachment createAttachment(String path, String desc,
                                                   String name, boolean urlnotpath) throws MalformedURLException {
        EmailAttachment attachment = new EmailAttachment();
        if (urlnotpath) {
            attachment.setURL(new URL(path));
        } else {
            attachment.setPath(path);
        }
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription(desc);
        attachment.setName(name);
        return attachment;
    }

    /**
     * Send an email
     *
     * @param host        The email server to use
     * @param tos         Who to send it to
     * @param from        The email you use
     * @param namefrom    The username you'll send as
     * @param subject     The subject
     * @param message     The message
     * @param attachments All attachments
     * @throws EmailException Something went wrong!
     */
    public static void sendEmail(String host, List<String> tos, String from,
                                 String namefrom, String subject, String message,
                                 List<EmailAttachment> attachments) throws EmailException {

        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(host);
        for (String to : tos) {
            email.addTo(to, to);
        }
        email.setFrom(from, namefrom);
        email.setSubject(subject);
        email.setMsg(message);
        for (EmailAttachment attach : attachments) {
            email.attach(attach);
        }
        email.send();
    }
}
