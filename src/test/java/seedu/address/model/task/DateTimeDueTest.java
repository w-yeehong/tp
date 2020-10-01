package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class DateTimeDueTest {

    @Test
    public void constructor_nullString_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTimeDue((String) null));
    }

    @Test
    public void constructor_nullOptional_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTimeDue((Optional<String>) null));
    }

    @Test
    public void constructor_emptyString_throwsIllegalArgumentException() {
        String emptyString = "";
        assertThrows(IllegalArgumentException.class, () -> new DateTimeDue(emptyString));
    }

    @Test
    public void constructor_emptyStringOptional_throwsIllegalArgumentException() {
        Optional<String> emptyStringOptional = Optional.of("");
        assertThrows(IllegalArgumentException.class, () -> new DateTimeDue(emptyStringOptional));
    }

    @Test
    public void constructor_invalidDateTimeString_throwsIllegalArgumentException() {
        String invalidDateTimeString = "aaa";
        assertThrows(IllegalArgumentException.class, () -> new DateTimeDue(invalidDateTimeString));
    }

    @Test
    public void constructor_invalidDateTimeStringOptional_throwsIllegalArgumentException() {
        Optional<String> invalidDateTimeStringOptional = Optional.of("aaa");
        assertThrows(IllegalArgumentException.class, () -> new DateTimeDue(invalidDateTimeStringOptional));
    }

    @Test
    public void getValue_emptyOptional_returnEmptyOptional() {
        assertEquals(Optional.empty(), new DateTimeDue(Optional.empty()).getValue());
    }

    @Test
    public void isValidDateTimeDue() {
        // null date-time
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
