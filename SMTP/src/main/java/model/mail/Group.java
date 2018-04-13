package model.mail;

import java.util.LinkedList;

public class Group {
    private LinkedList<Person> group = new LinkedList<Person>();

    public void addMember(Person person){
        group.add(person);
    }

    public LinkedList<Person> getGroup() {
        return group;
    }
}
