package model.Prank;

import SMTP.ISmtpClient;
import SMTP.SmtpClient;
import config.IConfigurationManager;
import model.mail.Group;
import model.mail.Person;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

/**
 * This class, gather information from the config in order
 * to forge emails and send them.
 * @author Burgener François, Curchod Bryan
 */
public class PrankGenerator {

    private final static int MAX_GROUP_SIZE = 3;
    IConfigurationManager config;
    ISmtpClient smtpClient;

    /**
     * Constructeur of PrankGenerator, set the configManager, and create the SMTP client
     * @param cm
     * @throws IOException
     */
    public PrankGenerator(IConfigurationManager cm) throws IOException {
        this.config = cm;
        smtpClient = new SmtpClient(config.getStmpServerAdress(), config.getSmtpServerPort());
    }

    /**
     * Use a list of person to create a certain number of groups
     * @param victims list of victims to put in a group
     * @param numberOfGroups number of group to create
     * @return list of group
     */
    Group[] createGroups(LinkedList<Person> victims, int numberOfGroups){


        // we check if we have enough person to create the wanted amount of groups
        if(victims.size()/MAX_GROUP_SIZE < numberOfGroups) {
            numberOfGroups = victims.size()/MAX_GROUP_SIZE;
        }
        Group[] groups = new Group[numberOfGroups];
        int i = 0;

        for(Person p : victims){
            if(groups[i] == null){
                groups[i] = new Group();
            }
            groups[i].addMember(p);
            i = (i+1) % numberOfGroups;
        }

        return groups;
    }

    /**
     * Method to create prank
     * @return return list of pranks
     */
    public LinkedList<Prank> createPranks(){
        LinkedList<Prank> pranks = new LinkedList<Prank>();
        LinkedList<Person> victims = config.getVictims();
        LinkedList<String> messages = config.getMessages();
        int numberOfGroups = config.getNumberOfGroups();
        Group[] groups = createGroups(victims,numberOfGroups);

        int cnt = 0;
        Random rdm = new Random();
        for(Group group : groups){
            LinkedList<Person> groupMembers = group.getGroup();
            Person sender = groupMembers.removeFirst();
            pranks.add(new Prank(sender,groupMembers,messages.get(rdm.nextInt(messages.size()))));
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
