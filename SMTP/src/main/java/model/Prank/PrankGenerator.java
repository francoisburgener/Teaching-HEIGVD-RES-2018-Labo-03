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
    public PrankGenerator(IConfigurationManager configurationManager) throws IOException {
        this.configurationManager = configurationManager;
        String serverAdress  = configurationManager.getStmpServerAdress();
        int serverPort = configurationManager.getSmtpServerPort();
        smtpClient = new SmtpClient(serverAdress,serverPort);
    }

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

    public LinkedList<Prank> createPranks(){
        LinkedList<Prank> pranks = new LinkedList<Prank>();
        LinkedList<Person> victims = configurationManager.getVictims();
        LinkedList<String> messages = configurationManager.getMessages();
        int numberOfGroups = configurationManager.getNumberOfGroups();

        LinkedList<Group> groups = createGroups(victims,numberOfGroups);

        int cnt = 0;
        for(Group group : groups){
            Prank prank = new Prank();

            LinkedList<Person> groupMembers = group.getGroup();
            Person sender = groupMembers.removeFirst();
            prank.setSender(sender);
            prank.setRCPT(groupMembers);
            prank.setMessage(messages.get(cnt));
            pranks.add(prank);
            cnt++;
        }
        return pranks;
    }

    public void send() throws IOException {
        LinkedList<Prank> pranks = createPranks();
        for(Prank prank : pranks){
            smtpClient.sendMail(prank.generateMail());
        }
    }

}
