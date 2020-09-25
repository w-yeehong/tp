package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;



/**
 * Represents a Person's temperature recorded in the app.
 * Guarantees: immutable; is valid as declared in {@link #isValidTemperature(String)}
 */

public class TemperatureRange {
    public static final String MESSAGE_CONSTRAINTS_TEMPERATURERANGE =
            "Temperature range should contain temperature-temperature";
    public static final String MESSAGE_CONSTRAINTS_TEMPERATURE =
            "Temperature should only contain numbers, and it should be to one decimal place. (E.g. 36.0)";
    public static final String VALIDATION_REGEX_TEMPERATURE = "\\d\\d[.]\\d";
    public static final String VALIDATION_REGEX_TEMPERATURERANGE = VALIDATION_REGEX_TEMPERATURE + "[-]" + VALIDATION_REGEX_TEMPERATURE;
    public final double startingTemperature;
    public final double endingTemperature;

    /**
     * Constructs a {@code Temperature}.
     * @param temperatureRange A valid temperature.
     */
    public TemperatureRange (String temperatureRange) {
        requireNonNull(temperatureRange);
        checkArgument(isValidTemperatureRange(temperatureRange), MESSAGE_CONSTRAINTS_TEMPERATURERANGE);
        String[] temperatures = temperatureRange.split("-");
        checkArgument(isValidTemperature(temperatures[0]), MESSAGE_CONSTRAINTS_TEMPERATURE); //ensures temp is always to 1 d.p.
        checkArgument(isValidTemperature(temperatures[0]), MESSAGE_CONSTRAINTS_TEMPERATURE); //ensures temp is always to 1 d.p.
        startingTemperature = Double.parseDouble(temperatures[0]);
        endingTemperature = Double.parseDouble(temperatures[1]);
    }

    public double getStartingTemperature () {
        return this.startingTemperature;
    }

    public double getEndingTemperature () {
        return this.endingTemperature;
    }


    /**
     * Returns true if a given string is a valid temperature.
     */
    public static boolean isValidTemperature(String test) {
        return test.matches(VALIDATION_REGEX_TEMPERATURE);
    }

    public static boolean isValidTemperatureRange(String test) {return test.matches(VALIDATION_REGEX_TEMPERATURERANGE); }

    @Override
    public String toString() {
        return startingTemperature + " to " + endingTemperature;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TemperatureRange // instanceof handles nulls
                && startingTemperature == ((TemperatureRange) other).startingTemperature
                && endingTemperature == ((TemperatureRange) other).endingTemperature); // state check
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}

