package contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ContactTest {

    @Test
    void testContactCreatedWithValidValues() {
        Contact contact = new Contact("1234567890", "Cainan", "Smith", "6025551234", "123 Main St Phoenix AZ");
        assertEquals("1234567890", contact.getContactID());
        assertEquals("Cainan", contact.getFirstName());
        assertEquals("Smith", contact.getLastName());
        assertEquals("6025551234", contact.getPhone());
        assertEquals("123 Main St Phoenix AZ", contact.getAddress());
    }

    @Test
    void testContactIdTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345678901", "Cainan", "Smith", "6025551234", "123 Main St");
        });
    }

    @Test
    void testContactIdNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, "Cainan", "Smith", "6025551234", "123 Main St");
        });
    }

    @Test
    void testFirstNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1001", "Christopher", "Smith", "6025551234", "123 Main St");
        });
    }

    @Test
    void testFirstNameNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1002", null, "Smith", "6025551234", "123 Main St");
        });
    }

    @Test
    void testLastNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1003", "Cainan", "Fitzgeralds", "6025551234", "123 Main St");
        });
    }

    @Test
    void testLastNameNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1004", "Cainan", null, "6025551234", "123 Main St");
        });
    }

    @Test
    void testPhoneTooShort() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1005", "Cainan", "Smith", "602555123", "123 Main St");
        });
    }

    @Test
    void testPhoneTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1006", "Cainan", "Smith", "60255512345", "123 Main St");
        });
    }

    // letters and dashes should fail even when the length is right
    @Test
    void testPhoneNotAllDigits() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1007", "Cainan", "Smith", "602-555-12", "123 Main St");
        });
    }

    @Test
    void testPhoneNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1008", "Cainan", "Smith", null, "123 Main St");
        });
    }

    @Test
    void testAddressTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1009", "Cainan", "Smith", "6025551234", "1234 Really Long Street Name Phoenix AZ");
        });
    }

    @Test
    void testAddressNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1010", "Cainan", "Smith", "6025551234", null);
        });
    }

    // boundary check, 10 characters is still valid
    @Test
    void testFieldsAtMaxLength() {
        Contact contact = new Contact("0123456789", "AAAAAAAAAA", "BBBBBBBBBB", "1112223333",
                "123456789012345678901234567890");
        assertEquals("AAAAAAAAAA", contact.getFirstName());
        assertEquals("BBBBBBBBBB", contact.getLastName());
        assertEquals(30, contact.getAddress().length());
    }

    @Test
    void testSettersUpdateValues() {
        Contact contact = new Contact("2001", "Cainan", "Smith", "6025551234", "123 Main St");
        contact.setFirstName("Alex");
        contact.setLastName("Jones");
        contact.setPhone("4805559876");
        contact.setAddress("456 Oak Ave");
        assertEquals("Alex", contact.getFirstName());
        assertEquals("Jones", contact.getLastName());
        assertEquals("4805559876", contact.getPhone());
        assertEquals("456 Oak Ave", contact.getAddress());
    }

    @Test
    void testSetterRejectsBadValue() {
        Contact contact = new Contact("2002", "Cainan", "Smith", "6025551234", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("12345"));
        // original value should survive the failed update
        assertEquals("6025551234", contact.getPhone());
    }
}
