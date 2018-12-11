package zones.com.friendzonesv00;

public class ContactData {
    static Contact[] contacts = new Contact[] {};
    static String[] names = new String[] {};

    public static void add(String name, String phone, int timeZone, Time bedtime, Time getUpTime) {
        // Create new Contact
        Contact newContact = new Contact(name, phone, timeZone, bedtime, getUpTime);

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

    public static void remove(int position) {
        Contact[] newContacts = new Contact[contacts.length - 1];
        String[] newNames = new String[names.length - 1];
        int oldIndex = 0;
        for (int i = 0; i < newContacts.length; i++) {
            if (oldIndex == position) {
                oldIndex++;
            }
            newContacts[i] = contacts[oldIndex];
            newNames[i] = names[oldIndex];
            oldIndex++;
        }
        contacts = newContacts;
        names = newNames;
    }
}
