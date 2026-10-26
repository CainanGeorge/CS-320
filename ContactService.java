package contact;

import java.util.HashMap;
import java.util.Map;

public class ContactService {

    // contact ID maps to the contact itself so lookups stay constant time
    private final Map<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null");
        }
        if (contacts.containsKey(contact.getContactID())) {
            throw new IllegalArgumentException("Contact ID already exists");
        }
        contacts.put(contact.getContactID(), contact);
    }

    public void deleteContact(String contactID) {
        if (contactID == null || !contacts.containsKey(contactID)) {
            throw new IllegalArgumentException("Contact ID not found");
        }
        contacts.remove(contactID);
    }

    public void updateFirstName(String contactID, String firstName) {
        getExisting(contactID).setFirstName(firstName);
    }

    public void updateLastName(String contactID, String lastName) {
        getExisting(contactID).setLastName(lastName);
    }

    public void updatePhone(String contactID, String phone) {
        getExisting(contactID).setPhone(phone);
    }

    public void updateAddress(String contactID, String address) {
        getExisting(contactID).setAddress(address);
    }

    public Contact getContact(String contactID) {
        return contacts.get(contactID);
    }

    // shared lookup so each update method does not repeat the same check
    private Contact getExisting(String contactID) {
        Contact contact = contacts.get(contactID);
        if (contact == null) {
            throw new IllegalArgumentException("Contact ID not found");
        }
        return contact;
    }
}
