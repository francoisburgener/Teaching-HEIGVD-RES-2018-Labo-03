package config;

import model.mail.Person;

import java.io.*;
import java.util.LinkedList;
import java.util.Properties;

public class ConfigurationManager implements IConfigurationManager {
    private String stmpServerAdress;
    private int smtpServerPort;
    private final LinkedList<Person> victims;
    private final LinkedList<String> messages;
    private int numberOfGroups;

    public ConfigurationManager() throws IOException {
        victims = loadAddressFromFile("./config/victims.txt");
        messages = loadMessageFromFile("./config/message.txt");
        loadProperties("./config/config.properties");
    }

    private void loadProperties(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties properties = new Properties();
        properties.load(fis);

        stmpServerAdress = properties.getProperty("smtpServerAdress");
        smtpServerPort = Integer.parseInt(properties.getProperty("smtpServerPort"));
        numberOfGroups = Integer.parseInt(properties.getProperty("numberOfGroups"));
    }

    private LinkedList<Person> loadAddressFromFile(String fileName) throws IOException {
        LinkedList<Person> people;
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        people = new LinkedList<Person>();
        String line;
        while((line = reader.readLine()) != null){
            people.add(new Person(line));
        }
        return people;
    }

    private LinkedList<String> loadMessageFromFile(String fileName) throws IOException {
        LinkedList<String> messages;
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        messages = new LinkedList<String>();
        String line;
        while((line = reader.readLine()) != null){
            String message = line;
            while((line = reader.readLine()) != null && !line.equals("==")) {
                message += line + "\r\n";
            }
            messages.add(message);
        }
        return messages;
    }

    public String getStmpServerAdress() {
        return stmpServerAdress;
    }

    public int getSmtpServerPort() {
        return smtpServerPort;
    }

    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    public LinkedList<Person> getVictims() {
        return victims;
    }

    public LinkedList<String> getMessages() {
        return messages;
    }
}
