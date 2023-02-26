package wsb.wsb_bugtracker.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mail {

    String recipient;
    String subject;
    String content;

    public Mail (String recipient, String subject, String content) {
        this.recipient = recipient;
        this.subject = subject;
        this.content = content;
    }
}
