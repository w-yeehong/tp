package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.commons.util.DateTimeUtil;

/**
 * Represents the date-time when a Task is due.
 * Guarantees: immutable; is an optional attribute of task;
 * is valid as declared in {@link #isValidDateTimeDue(String)}
 */
public class DateTimeDue {

    private static final DateTimeFormatter[] ALLOWED_DATETIME_FORMATS = {
        DateTimeUtil.DATETIME_FORMAT_YEAR_MONTH_DAY_OPTIONAL_TIME,
        DateTimeUtil.DATETIME_FORMAT_DAY_MONTH_YEAR_SLASH_DELIMITED_OPTIONAL_TIME
    };

    public static final String MESSAGE_CONSTRAINTS = "Due dates should adhere to one of the "
            + "following formats:\n"
            + "1. yyyyMMdd (e.g. 20201230)\n"
            + "2. d/M/yyyy (e.g. 30/12/2020)\n"
            + "Format 2 allows a single digit for day and month (e.g. 1/1/2020); \n"
            + "Format 1 does not.";

    public final LocalDateTime value;

    /**
     * Constructs a {@code DateTimeDue}.
     *
     * @param dueAt A valid date-time.
     */
    public DateTimeDue(String dueAt) {
        requireNonNull(dueAt);
        checkArgument(isValidDateTimeDue(dueAt), MESSAGE_CONSTRAINTS);
        value = DateTimeUtil.parseFirstMatching(dueAt, LocalDateTime::from, ALLOWED_DATETIME_FORMATS);
    }

    public LocalDateTime getValue() {
        return value;
    }

    /**
     * Returns if a given string is a valid date-time.
     */
    public static boolean isValidDateTimeDue(String test) {
        try {
            DateTimeUtil.parseFirstMatching(test, LocalDateTime::from, ALLOWED_DATETIME_FORMATS);
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return value.format(DateTimeUtil.DATETIME_FORMAT_DAY_MONTH_YEAR_LONG_SPACE_DELIMITED_OPTIONAL_TIME);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTimeDue // instanceof handles nulls
                && value.equals(((DateTimeDue) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
