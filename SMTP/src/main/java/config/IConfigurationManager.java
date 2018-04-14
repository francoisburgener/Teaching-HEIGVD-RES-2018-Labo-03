package config;

import model.mail.Person;

import java.util.LinkedList;

public interface IConfigurationManager {
    public String getStmpServerAdress();
    public int getSmtpServerPort();
    public int getNumberOfGroups();
    public LinkedList<Person> getVictims();
    public LinkedList<String> getMessages();

}
