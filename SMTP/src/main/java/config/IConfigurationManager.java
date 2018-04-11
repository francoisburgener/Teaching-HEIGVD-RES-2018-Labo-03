package config;

import model.mail.Person;

import java.util.LinkedList;

public interface IConfigurationManager {
    public LinkedList<Person> getVictims();
    public LinkedList<String> getMessages();
}
