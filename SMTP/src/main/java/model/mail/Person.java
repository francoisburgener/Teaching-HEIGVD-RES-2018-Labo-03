package model.mail;

/**
 * Class that represents a person by storing his email address
 * @author Burgener François, Curchod Bryan
 */
public class Person {
    private String address;

    /**
     * Constructor of a Person
     * @param address initial address
     */
    public Person(String address) {
        this.address = address;
    }

    /**
     * Get the address of a person
     * @return email address of the person
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set an address to a person
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
