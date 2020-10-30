package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

//@@author chiamyunqing
/**
 * Represents a Patient's age recorded in the app.
 * A valid patient age is between 0 (inclusive) and 120 (exclusive),
 * as declared in {@link #isValidAge(String)}.
 * Guarantees: immutable.
 */
public class Age {

    public static final String MESSAGE_CONSTRAINTS =
            "Age should only contain numbers.";

    public static final String VALIDATION_REGEX = "\\d{1,3}";
    private final int value;

    /**
     * Constructs a {@code Age}.
     *
     * @param age A valid age.
     */
    public Age(String age) {
        requireNonNull(age);
        checkArgument(isValidAge(age), MESSAGE_CONSTRAINTS);
        value = Integer.parseInt(age);
    }

    /**
     * Returns true if a given string is a valid age.
     */
    public static boolean isValidAge(String test) {
        return test.matches(VALIDATION_REGEX)
                && (Integer.parseInt(test) >= 0)
                && (Integer.parseInt(test) < 120);
    }

    /**
     * Returns the value of the age.
     */
    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Age // instanceof handles nulls
                && value == ((Age) other).getValue()); // state check
    }

    @Override
    public int hashCode() {
        return Integer.toString(value).hashCode();
    }
}
