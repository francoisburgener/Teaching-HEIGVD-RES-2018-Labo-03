package model.mail;

import java.util.LinkedList;

/**
 * Class representing an email. It stores the address of the sender and receiver(s),
 * the recipient, the subject and the body of the message
 * @author Burgener François, Curchod Bryan
 */
public class Mail {
    private String from;
    private LinkedList<String> to;
    private String body;
    private String subject;

    /**
     * constructor that set all attribute
     * @param from sender
     * @param to receiver(s)
     * @param body content of the mail
     * @param subject subject of the mail
     */
    public Mail(String from, LinkedList<String> to, String body, String subject) {
        this.from = from;
        this.to = to;
        this.body = body;
        this.subject = subject;
    }

    /**
     * Get the adress of the sender
     * @return adress of the sender
     */
    public String getFrom() {
        return from;
    }

    /**
     * Set the adress of the sender
     * @param from adress of the sender
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Get the receivers addresses of the mail
     * @return receivers addresses of the mail
     */
    public LinkedList<String> getTo() {
        return to;
    }

    /**
     * Set the receivers addresses of the mail
     * @param to receveirs addresses  of the mail
     */
    public void setTo(LinkedList<String> to) {
        this.to = to;
    }

    /**
     * Get the body of the mail
     * @return body of the mail
     */
    public String getBody() {
        return body;
    }

    /**
     * Set the body of the mail
     * @param body body of the mail
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Get the subject of the mail
     * @return subject of the mail
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Set the suject of the mail
     * @param subject subject of the mail
     */
    public void setSubject(String subject) {

        this.subject = subject;
    }
}
