package config;

import model.mail.Person;

import java.io.*;
import java.util.LinkedList;
import java.util.Properties;

/**
 * Class representing the configuration of the prank generator.
 * Gather the information about the SMTP server address and port, the number of
 * groups to form, and the location of the files for the list of the victims and
 * the messages to send.
 * @author Burgener François, Curchod Bryan
 */
public class ConfigurationManager implements IConfigurationManager {
    private String stmpServerAdress;
    private int smtpServerPort;
    private LinkedList<Person> victims;
    private LinkedList<String> messages;
    private int numberOfGroups;

    /**
     * Default constructor, take a file by default
     * @throws IOException
     */
    /*public ConfigurationManager() throws IOException {
        loadProperties("./config/config.properties");
    }*/

    /**
     * Construct an instance based on a customized configuration file
     * @param file
     * @throws IOException
     */
    public ConfigurationManager(String file) throws  IOException{
        loadProperties(file);
    }

    /**
     * Get the address of the smtp server
     * @return the address of the smtp server
     */
    @Override
    public String getStmpServerAdress() {
        return stmpServerAdress;
    }

    /**
     * Get the port of the smtp server
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

    public String getVictimsFile() {
        return null;
    }
    /**
     * Get the list of victims (email addresses to use)
     * @return list of email addresses to use for the prank
     */
    @Override
    public LinkedList<Person> getVictims() {
        return victims;
    }

    /**
     * Get the list of message
     * @return list of messages to use for the prank
     */
    @Override
    public LinkedList<String> getMessages() {
        return messages;
    }

    /**
     * Load the properties from a configuration file
     * @param fileName  path of the configuration file
     * @throws IOException
     */
    private void loadProperties(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties properties = new Properties();
        properties.load(fis);

        stmpServerAdress = properties.getProperty("smtpServerAdress");
        smtpServerPort = Integer.parseInt(properties.getProperty("smtpServerPort"));
        numberOfGroups = Integer.parseInt(properties.getProperty("numberOfGroups"));
        victims = loadAddressFromFile(properties.getProperty("victimListeFile"));
        messages = loadMessageFromFile(properties.getProperty("messagesListeFile"));
    }

    /**
     * Load addresses of victims from file
     * @param fileName path to the file containing a list of email
     * @return the list extracted from the file
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
     * @param fileName path to the file containing a list of messages
     * @return a list of usable messages
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
