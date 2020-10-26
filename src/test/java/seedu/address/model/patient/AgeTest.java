package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

//@@author chiamyunqing
/**
 * Test cases for the attribute age of patient.
 */
public class AgeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Age(null));
    }

    @Test
    public void constructor_invalidAge_throwsIllegalArgumentException() {
        String emptyString = "";
        assertThrows(IllegalArgumentException.class, () -> new Age(emptyString));

        String nonIntegerAge = "twenty";
        assertThrows(IllegalArgumentException.class, () -> new Age(nonIntegerAge));

        String invalidAge = "1000";
        assertThrows(IllegalArgumentException.class, () -> new Age(invalidAge));
    }

    @Test
    public void isValidAge() {
        // null age
        assertThrows(NullPointerException.class, () -> Age.isValidAge(null));

        // invalid age
        assertFalse(Age.isValidAge("")); // empty string
        assertFalse(Age.isValidAge(" ")); // spaces only
        assertFalse(Age.isValidAge("twenty")); //non-numeric
        assertFalse(Age.isValidAge("-1")); //negative age
        assertFalse(Age.isValidAge("120")); // >= 120 years old

        //valid age
        assertTrue(Age.isValidAge("0"));
        assertTrue(Age.isValidAge("30"));
        assertTrue(Age.isValidAge("119")); //still possible
    }
}
