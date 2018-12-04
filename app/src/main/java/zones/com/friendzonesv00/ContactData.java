package zones.com.friendzonesv00;

public class ContactData {
    static Contact[] contacts = new Contact[] {};
    static String[] names = new String[] {};

    public static void add(String name, String phone, int timeZone) {
        // Create new Contact
        Contact newContact = new Contact(name, phone, timeZone);

        // Update contact data
        Contact[] newContacts = new Contact[contacts.length + 1];
        for (int i = 0; i < contacts.length; i++) {
            newContacts[i] = contacts[i];
        }
        newContacts[newContacts.length - 1] = newContact;
        contacts = newContacts;
        // Update names data
        names = new String[contacts.length];
        for (int i = 0; i < contacts.length; i++) {
            names[i] = contacts[i].getName();
        }
    }
}
