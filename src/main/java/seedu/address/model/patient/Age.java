package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's age recorded in the app.
 * Guarantees: immutable; is valid as declared in {@link #isValidAge(String)}
 */

public class Age {

    public static final String MESSAGE_CONSTRAINTS =
            "Age should only contain numbers.";

    public static final String VALIDATION_REGEX = "\\d{1,3}";
    public final int value;

    /**
     * Constructs a {@code Age}.
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

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Age // instanceof handles nulls
                && value == ((Age) other).value); // state check
    }

    @Override
    public int hashCode() {
        return Integer.toString(value).hashCode();
    }
}
