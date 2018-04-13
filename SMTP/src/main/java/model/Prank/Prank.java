package model.Prank;

import model.mail.Mail;
import model.mail.Person;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Prank {
    private Person sender;
    private LinkedList<Person> RCPT = new LinkedList<Person>();
    private String message;

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public LinkedList<Person> getRCPT() {
        return RCPT;
    }

    public void setRCPT(LinkedList<Person> RCPT) {
        this.RCPT = RCPT;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Mail generateMail(){
        Mail mail = new Mail();
        mail.setFrom(sender.getAddress());
        String[] to = new String[2];
        int i = 0;
        for(Person p : RCPT){
            to[i] = p.getAddress();
            ++i;
        }
        mail.setTo(to);
        int index = message.indexOf("\r\n");
        String subject = message.substring(0,index);
        String body = message.substring(index+1);
        mail.setSubject(subject);
        mail.setBody(body);

        return mail;
    }

}
