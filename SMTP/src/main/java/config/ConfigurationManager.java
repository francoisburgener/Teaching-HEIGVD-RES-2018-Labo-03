package config;

import model.mail.Person;

import java.io.*;
import java.util.LinkedList;
import java.util.Properties;

/**
 * Class representing the configuration of the of the server
 */
public class ConfigurationManager implements IConfigurationManager {
    private String stmpServerAdress;
    private int smtpServerPort;
    private final LinkedList<Person> victims;
    private final LinkedList<String> messages;
    private int numberOfGroups;

    /**
     * Constructeur of the class
     * @throws IOException
     */
    public ConfigurationManager() throws IOException {
        victims = loadAddressFromFile("./config/victims.txt");
        messages = loadMessageFromFile("./config/message.txt");
        loadProperties("./config/config.properties");
    }

    /**
     * Get the address of the smtp server on file config.properties
     * @return the address of the smtp server
     */
    @Override
    public String getStmpServerAdress() {
        return stmpServerAdress;
    }

    /**
     * Get the port of the smtp server on file config.properties
     * @return the port of the smtp server
     */
    @Override
    public int getSmtpServerPort() {
        return smtpServerPort;
    }

    /**
     * Get the number of group we want to create
     * @return number of group
     */
    @Override
    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    /**
     * Get the list of victims ( person)
     * @return the list of victims
     */
    @Override
    public LinkedList<Person> getVictims() {
        return victims;
    }

    /**
     * Get the list of message
     * @return
     */
    @Override
    public LinkedList<String> getMessages() {
        return messages;
    }

    /**
     * Load the properties
     * @param fileName  path of the file config.properties
     * @throws IOException
     */
    private void loadProperties(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties properties = new Properties();
        properties.load(fis);

        stmpServerAdress = properties.getProperty("smtpServerAdress");
        smtpServerPort = Integer.parseInt(properties.getProperty("smtpServerPort"));
        numberOfGroups = Integer.parseInt(properties.getProperty("numberOfGroups"));
    }

    /**
     * Load addresses of victims from file
     * @param fileName path of the file victims.txt
     * @return
     * @throws IOException
     */
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

    /**
     * Load messages from file
     * @param fileName path of the file message.txt
     * @return
     * @throws IOException
     */
    private LinkedList<String> loadMessageFromFile(String fileName) throws IOException {
        LinkedList<String> messages;
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        messages = new LinkedList<String>();
        String line;
        while((line = reader.readLine()) != null){
            String message = line;
            while((line = reader.readLine()) != null && !line.equals("@@")) {
                message += line + "\r\n";
            }
            messages.add(message);
        }
        return messages;
    }
}
