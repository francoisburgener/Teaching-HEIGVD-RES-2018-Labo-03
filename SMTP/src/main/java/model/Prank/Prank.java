package model.Prank;

import model.mail.Mail;
import model.mail.Person;

import java.util.LinkedList;

/**
 * Represent a prank mail
 * @author Burgener François, Curchod Bryan
 */
public class Prank {
    private Person sender;
    private LinkedList<Person> RCPT;
    private String message;

    /**
     * constructor for a prank
     * @param sender person chosen to send the mail
     * @param RCPT list of personne to send the mail to
     * @param message content of the prank
     */
    public Prank(Person sender, LinkedList<Person> RCPT, String message){
        this.sender = sender;
        this.RCPT = RCPT;
        this.message = message;
    }

    /**
     * forge a mail
     * @return prank mail to send
     */
    public Mail createMail(){
        LinkedList<String> to = new LinkedList<String>();
        for(Person p : RCPT){
            to.add(p.getAddress());
        }
        int index = message.indexOf("\r\n");
        String subject = message.substring(0,index);
        String body = message.substring(index+1);
        return new Mail(sender.getAddress(),to,body,subject);
    }

}
