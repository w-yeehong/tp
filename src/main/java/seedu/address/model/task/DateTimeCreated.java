package seedu.address.model.task;

import java.time.LocalDateTime;

import seedu.address.commons.util.DateTimeUtil;

/**
 * Represents the date-time when a Task is created.
 * Guarantees: immutable; its value is generated upon creation of a task.
 */
public class DateTimeCreated {

    public final LocalDateTime value;

    /**
     * Constructs a {@code DateTimeCreated}.
     *
     * The date-time is set based on the current date-time value of the system clock.
     */
    public DateTimeCreated() {
        value = LocalDateTime.now();
    }

    public LocalDateTime getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.format(DateTimeUtil.DATETIME_FORMAT_DAY_MONTH_YEAR_LONG_SPACE_DELIMITED_OPTIONAL_TIME);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTimeCreated // instanceof handles nulls
                && value.equals(((DateTimeCreated) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
