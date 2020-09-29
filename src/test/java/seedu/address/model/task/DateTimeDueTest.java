package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTimeDueTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTimeDue(null));
    }

    @Test
    public void constructor_emptyString_throwsIllegalArgumentException() {
        String emptyString = "";
        assertThrows(IllegalArgumentException.class, () -> new DateTimeDue(emptyString));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidDateTime = "aaa";
        assertThrows(IllegalArgumentException.class, () -> new DateTimeDue(invalidDateTime));
    }

    @Test
    public void isValidDateTimeDue() {
        // null email
        assertThrows(NullPointerException.class, () -> DateTimeDue.isValidDateTimeDue(null));

        // blank date-time
        assertFalse(DateTimeDue.isValidDateTimeDue("")); // empty string
        assertFalse(DateTimeDue.isValidDateTimeDue(" ")); // spaces only

        // invalid date-time
        assertFalse(DateTimeDue.isValidDateTimeDue("//")); // only hyphens
        assertFalse(DateTimeDue.isValidDateTimeDue("2345 Oct 2020")); // invalid day
        assertFalse(DateTimeDue.isValidDateTimeDue("aa")); // gibberish text

        // non-conforming date-time (i.e. not in allowed formats)
        assertFalse(DateTimeDue.isValidDateTimeDue("5-1-2020")); // d-M-yyyy
        assertFalse(DateTimeDue.isValidDateTimeDue("05-01-2020 2359")); // dd-MM-yyyy HHmm

        // valid and conforming date-time (i.e. in allowed formats)
        assertTrue(DateTimeDue.isValidDateTimeDue("20200105")); // yyyyMMdd
        assertTrue(DateTimeDue.isValidDateTimeDue("20200105")); // yyyyMMdd HHmm
        assertTrue(DateTimeDue.isValidDateTimeDue("5/1/2020")); // d/M/yyyy
        assertTrue(DateTimeDue.isValidDateTimeDue("05/01/2020")); // dd/MM/yyyy HHmm
        assertTrue(DateTimeDue.isValidDateTimeDue("5/1/2020 2359")); // d/M/yyyy HHmm
        assertTrue(DateTimeDue.isValidDateTimeDue("05/01/2020 2359")); // dd/MM/yyyy HHmm
    }
}
