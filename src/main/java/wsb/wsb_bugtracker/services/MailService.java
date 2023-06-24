package wsb.wsb_bugtracker.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import wsb.wsb_bugtracker.models.Mail;

@Service
public class MailService {

    static private JavaMailSender javaMailSender;


    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public static void send(Mail mail) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom("ekotestowe@hotmail.com");
            mimeMessageHelper.setTo(mail.getRecipient());
            mimeMessageHelper.setSubject(mail.getSubject());
            mimeMessageHelper.setText(mail.getContent());

            System.out.println("Rozpoczęcie wysyłki");

            javaMailSender.send(mimeMessage);

            System.out.println("Mail został wysłany");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
