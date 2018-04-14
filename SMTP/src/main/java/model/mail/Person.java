package model.mail;

/**
 *Class representing a person and who stores his email address
 */
public class Person {
    private String address;

    /**
     * Constructor of a Person
     * @param address
     */
    public Person(String address) {
        this.address = address;
    }

    /**
     * Get the address of a person
     * @return
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
