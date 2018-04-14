package model.mail;

import java.util.LinkedList;

/**
 *Class representing a group of people
 */
public class Group {
    //Group of person
    private LinkedList<Person> group = new LinkedList<Person>();

    /**
     *Add a member to the group
     * @param person member to add to the group
     */
    public void addMember(Person person){
        group.add(person);
    }

    /**
     * getter of the group
     * @return Returns the list of members of the group
     */
    public LinkedList<Person> getGroup() {
        return group;
    }
}
