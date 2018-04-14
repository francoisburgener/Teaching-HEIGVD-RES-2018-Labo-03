package model.Prank;

import SMTP.ISmtpClient;
import SMTP.SmtpClient;
import config.ConfigurationManager;
import config.IConfigurationManager;
import model.mail.Group;
import model.mail.Person;

import java.io.IOException;
import java.util.LinkedList;

public class PrankGenerator {

    IConfigurationManager configurationManager;
    ISmtpClient smtpClient;

    /**
     * Constructeur of PrankGenerator
     * @param configurationManager
     * @throws IOException
     */
    public PrankGenerator(IConfigurationManager configurationManager) throws IOException {
        this.configurationManager = configurationManager;
        String serverAdress  = configurationManager.getStmpServerAdress();
        int serverPort = configurationManager.getSmtpServerPort();
        smtpClient = new SmtpClient(serverAdress,serverPort);
    }

    /**
     * Methode who create all groups
     * @param victims list of victims to put in a group
     * @param numberOfGroups number of group to create
     * @return list of group
     */
    LinkedList<Group> createGroups(LinkedList<Person> victims, int numberOfGroups){
        LinkedList<Group> groups = new LinkedList<Group>();

        for(int i = 0; i < numberOfGroups; ++i){
            Group group = new Group();
            for(int j = 0; j < numberOfGroups; ++j){
                group.addMember(victims.removeFirst());
            }
            groups.add(group);
        }

        return groups;
    }

    /**
     * Method to create prank
     * @return return list of pranks
     */
    public LinkedList<Prank> createPranks(){
        LinkedList<Prank> pranks = new LinkedList<Prank>();
        LinkedList<Person> victims = configurationManager.getVictims();
        LinkedList<String> messages = configurationManager.getMessages();
        int numberOfGroups = configurationManager.getNumberOfGroups();
        LinkedList<Group> groups = createGroups(victims,numberOfGroups);

        int cnt = 0;
        for(Group group : groups){
            LinkedList<Person> groupMembers = group.getGroup();
            Person sender = groupMembers.removeFirst();
            pranks.add(new Prank(sender,groupMembers,messages.get(cnt)));
            cnt++;
        }
        return pranks;
    }

    /**
     * Method to send a mail
     * @throws IOException
     */
    public void send() throws IOException {
        LinkedList<Prank> pranks = createPranks();
        for(Prank prank : pranks){
            smtpClient.sendMail(prank.createMail());
        }
    }

}
