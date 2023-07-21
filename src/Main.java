import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("Address Book Application");
            System.out.println("1. Add a new contact");
            System.out.println("2. View all contacts");
            System.out.println("3. Search contact");
            System.out.println("4. Update contact");
            System.out.println("5. Delete contact");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter contact name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();

                    Contact newContact = new Contact(name, phoneNumber, email, address);
                    addressBook.addContact(newContact);
                    System.out.println("Contact added successfully!\n");
                    break;
                case 2:
                    System.out.println("Contacts in the address book:");
                    for (Contact contact : addressBook.getAllContacts()) {
                        System.out.println(contact.getName() + " (" + contact.getPhoneNumber() + ", " +
                                contact.getEmail() + ", " + contact.getAddress() + ")");
                    }
                    System.out.println();
                    break;
                case 3:
                    System.out.print("Enter search query: ");
                    String searchQuery = scanner.nextLine();
                    List<Contact> searchResults = addressBook.searchContacts(searchQuery);
                    if (searchResults.isEmpty()) {
                        System.out.println("No matching contacts found.\n");
                    } else {
                        System.out.println("Search results:");
                        for (Contact contact : searchResults) {
                            System.out.println(contact.getName() + " (" + contact.getPhoneNumber() + ", " +
                                    contact.getEmail() + ", " + contact.getAddress() + ")");
                        }
                        System.out.println();
                    }
                    break;

                case 4:
                    System.out.print("Enter contact name to update: ");
                    String nameToUpdate = scanner.nextLine();
                    List<Contact> contactsToUpdate = addressBook.searchContacts(nameToUpdate);
                    if (contactsToUpdate.isEmpty()) {
                        System.out.println("Contact not found.\n");
                    } else {
                        Contact contactToUpdate = contactsToUpdate.get(0);
                        System.out.print("Enter new phone number: ");
                        String newPhoneNumber = scanner.nextLine();
                        System.out.print("Enter new email: ");
                        String newEmail = scanner.nextLine();
                        System.out.print("Enter new address: ");
                        String newAddress = scanner.nextLine();

                        // Create a new updated contact with the provided information
                        Contact updatedContact = new Contact(contactToUpdate.getName(), newPhoneNumber, newEmail, newAddress);
                        if (addressBook.updateContact(nameToUpdate, updatedContact)) {
                            System.out.println("Contact updated successfully!\n");
                        } else {
                            System.out.println("Contact not found.\n");
                        }
                    }
                    break;

                case 5:
                    System.out.print("Enter contact name to delete: ");
                    String nameToDelete = scanner.nextLine();
                    if (addressBook.deleteContact(nameToDelete)) {
                        System.out.println("Contact deleted successfully!\n");
                    } else {
                        System.out.println("Contact not found.\n");
                    }
                    break;
                case 6:
                    System.out.println("Exiting the address book application.");
                    break;
                case 7:
                    System.out.print("Enter filename to load contacts from: ");
                    String filename = scanner.nextLine();
                    try (Scanner fileScanner = new Scanner(new File(filename))) {
                        while (fileScanner.hasNextLine()) {
                            String line = fileScanner.nextLine();
                            String[] parts = line.split(",");
                            if (parts.length == 4) {
                                Contact contact = new Contact(parts[0], parts[1], parts[2], parts[3]);
                                addressBook.addContact(contact);
                            }
                        }
                        System.out.println("Contacts loaded from " + filename + " successfully!\n");
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found. Please check the filename and try again.\n");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        } while (choice != 6);

        scanner.close();
    }
}
