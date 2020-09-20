package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TemperatureTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Temperature(null));
    }

    @Test
    public void constructor_invalidTemperature_throwsIllegalArgumentException() {
        String invalidTemperature = "";
        assertThrows(IllegalArgumentException.class, () -> new Temperature(invalidTemperature));
    }

    @Test
    public void isValidTemperature() {
        // null temperature
        assertThrows(NullPointerException.class, () -> Temperature.isValidTemperature(null));

        // invalid temperature
        assertFalse(Temperature.isValidTemperature("")); //empty string
        assertFalse(Temperature.isValidTemperature(" ")); // spaces only
        assertFalse(Temperature.isValidTemperature("37")); //not 1 decimal place
        assertFalse(Temperature.isValidTemperature("temp")); //non-numeric
        assertFalse(Temperature.isValidTemperature("37.a")); //alphabets within digits
        assertFalse(Temperature.isValidTemperature("37.")); //no digit after decimal
        assertFalse(Temperature.isValidTemperature("3 7.6")); //spaces within digits

        // valid phone numbers
        assertTrue(Temperature.isValidTemperature("36.7"));
        assertTrue(Temperature.isValidTemperature("36.0"));
    }
}
