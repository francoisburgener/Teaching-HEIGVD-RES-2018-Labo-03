package model.Prank;

import model.mail.Mail;
import model.mail.Person;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Prank {
    private Person sender;
    private LinkedList<Person> RCPT;
    private String message;

    public Prank(Person sender, LinkedList<Person> RCPT, String message){
        this.sender = sender;
        this.RCPT = RCPT;
        this.message = message;
    }

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
