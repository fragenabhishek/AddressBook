import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }

    public List<Contact> searchContacts(String keyword) {
        List<Contact> searchResults = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                    contact.getPhoneNumber().contains(keyword)) {
                searchResults.add(contact);
            }
        }
        return searchResults;
    }

    public boolean updateContact(String name, Contact updatedContact) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equalsIgnoreCase(name)) {
                contacts.set(i, updatedContact);
                return true;
            }
        }
        return false;
    }

    public boolean deleteContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                contacts.remove(contact);
                return true;
            }
        }
        return false;
    }
    public void saveContactsToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Contact contact : contacts) {
                writer.write(contact.getName() + "," + contact.getPhoneNumber() + "," +
                        contact.getEmail() + "," + contact.getAddress() + "\n");
            }
            System.out.println("Contacts saved to " + filename + " successfully!\n");
        } catch (IOException e) {
            System.out.println("Error occurred while saving contacts to file: " + e.getMessage() + "\n");
        }
    }
}
