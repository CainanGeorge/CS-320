package contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContactServiceTest {

    private ContactService service;

    @BeforeEach
    void setUp() {
        service = new ContactService();
    }

    @Test
    void testAddContact() {
        Contact contact = new Contact("3001", "Cainan", "Smith", "6025551234", "123 Main St");
        service.addContact(contact);
        assertNotNull(service.getContact("3001"));
        assertEquals("Cainan", service.getContact("3001").getFirstName());
    }

    @Test
    void testAddDuplicateIdFails() {
        service.addContact(new Contact("3002", "Cainan", "Smith", "6025551234", "123 Main St"));
        assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(new Contact("3002", "Alex", "Jones", "4805559876", "456 Oak Ave"));
        });
    }

    @Test
    void testAddNullContactFails() {
        assertThrows(IllegalArgumentException.class, () -> service.addContact(null));
    }

    @Test
    void testDeleteContact() {
        service.addContact(new Contact("3003", "Cainan", "Smith", "6025551234", "123 Main St"));
        service.deleteContact("3003");
        assertNull(service.getContact("3003"));
    }

    @Test
    void testDeleteMissingContactFails() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("9999"));
    }

    @Test
    void testDeleteNullIdFails() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact(null));
    }

    @Test
    void testUpdateFirstName() {
        service.addContact(new Contact("3004", "Cainan", "Smith", "6025551234", "123 Main St"));
        service.updateFirstName("3004", "Alex");
        assertEquals("Alex", service.getContact("3004").getFirstName());
    }

    @Test
    void testUpdateLastName() {
        service.addContact(new Contact("3005", "Cainan", "Smith", "6025551234", "123 Main St"));
        service.updateLastName("3005", "Jones");
        assertEquals("Jones", service.getContact("3005").getLastName());
    }

    @Test
    void testUpdatePhone() {
        service.addContact(new Contact("3006", "Cainan", "Smith", "6025551234", "123 Main St"));
        service.updatePhone("3006", "4805559876");
        assertEquals("4805559876", service.getContact("3006").getPhone());
    }

    @Test
    void testUpdateAddress() {
        service.addContact(new Contact("3007", "Cainan", "Smith", "6025551234", "123 Main St"));
        service.updateAddress("3007", "456 Oak Ave");
        assertEquals("456 Oak Ave", service.getContact("3007").getAddress());
    }

    @Test
    void testUpdateMissingContactFails() {
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("9999", "Alex"));
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("9999", "Jones"));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("9999", "4805559876"));
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("9999", "456 Oak Ave"));
    }

    // an invalid update should be rejected without wiping the stored value
    @Test
    void testUpdateWithInvalidValueFails() {
        service.addContact(new Contact("3008", "Cainan", "Smith", "6025551234", "123 Main St"));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("3008", "555"));
        assertEquals("6025551234", service.getContact("3008").getPhone());
    }

    @Test
    void testGetMissingContactReturnsNull() {
        assertNull(service.getContact("9999"));
    }

    @Test
    void testMultipleContactsStoredSeparately() {
        service.addContact(new Contact("3009", "Cainan", "Smith", "6025551234", "123 Main St"));
        service.addContact(new Contact("3010", "Alex", "Jones", "4805559876", "456 Oak Ave"));
        service.updateFirstName("3009", "Chris");
        assertEquals("Chris", service.getContact("3009").getFirstName());
        assertEquals("Alex", service.getContact("3010").getFirstName());
    }
}
