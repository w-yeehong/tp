package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's temperature recorded in the app.
 * Guarantees: immutable; is valid as declared in {@link #isValidTemperature(String)}
 */

public class Temperature {
    public static final String MESSAGE_CONSTRAINTS =
            "Temperature should only contain numbers, and it should be to one decimal place. (E.g. 36.0)";
    public static final String VALIDATION_REGEX = "\\d\\d[.]\\d";
    public final double value;

    /**
     * Constructs a {@code Temperature}.
     * @param temperature A valid temperature.
     */
    public Temperature(String temperature) {
        requireNonNull(temperature);
        checkArgument(isValidTemperature(temperature), MESSAGE_CONSTRAINTS); //ensures temp is always to 1 d.p.
        value = Double.parseDouble(temperature);
    }

    /**
     * Returns the float value of the temperature reading.
     */
    public double getValue () {
        return this.value;
    }


    /**
     * Returns true if a given string is a valid temperature.
     */
    public static boolean isValidTemperature(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Temperature // instanceof handles nulls
                && value == ((Temperature) other).value); // state check
    }

    @Override
    public int hashCode() {
        return Double.toString(value).hashCode();
    }
}
