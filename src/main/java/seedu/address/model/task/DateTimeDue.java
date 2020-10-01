package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import seedu.address.commons.util.DateTimeUtil;

/**
 * Represents the date-time when a Task is due.
 * Guarantees: immutable; is an optional attribute of task;
 * is valid as declared in {@link #isValidDateTimeDue(String)}
 */
public class DateTimeDue {

    public static final String MESSAGE_CONSTRAINTS = "Due dates should adhere to one of the "
            + "following formats:\n"
            + "1. yyyyMMdd (e.g. 20201230)\n"
            + "2. d/M/yyyy (e.g. 30/12/2020)\n"
            + "Format 2 allows a single digit for day and month (e.g. 1/1/2020); \n"
            + "Format 1 does not.";

    private static final DateTimeFormatter[] ALLOWED_DATETIME_FORMATS = {
        DateTimeUtil.DATETIME_FORMAT_YEAR_MONTH_DAY_OPTIONAL_TIME,
        DateTimeUtil.DATETIME_FORMAT_DAY_MONTH_YEAR_SLASH_DELIMITED_OPTIONAL_TIME
    };

    private static final String NULL_VALUE_TO_STRING = "-";

    public final Optional<LocalDateTime> value;

    /**
     * Constructs a {@code DateTimeDue}.
     * {@code optionalDueAt} must be non-null (but can be empty).
     *
     * If the date-time string is empty, the value defaults to {@code Optional.empty()}.
     * If the date-time string is present and valid, the value parsed into is a {@code LocalDateTime}
     * and wrapped in an {@code Optional}.
     *
     * @param optionalDueAt A valid optional date-time string.
     */
    public DateTimeDue(Optional<String> optionalDueAt) {
        requireNonNull(optionalDueAt);
        value = optionalDueAt
                .map((dueAt) -> {
                    checkArgument(isValidDateTimeDue(dueAt), MESSAGE_CONSTRAINTS);
                    return Optional.of(
                        DateTimeUtil.parseFirstMatching(dueAt, LocalDateTime::from, ALLOWED_DATETIME_FORMATS));
                })
                .orElse(Optional.empty());
    }

    /**
     * Constructs a {@code DateTimeDue}.
     * {@code dueAt} must be non-null.
     *
     * @param dueAt A valid date-time string.
     */
    public DateTimeDue(String dueAt) {
        requireNonNull(dueAt);
        checkArgument(isValidDateTimeDue(dueAt), MESSAGE_CONSTRAINTS);
        value = Optional.of(
                DateTimeUtil.parseFirstMatching(dueAt, LocalDateTime::from, ALLOWED_DATETIME_FORMATS));
    }

    public Optional<LocalDateTime> getValue() {
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
        return value
                .map((dueAt) ->
                    dueAt.format(DateTimeUtil.DATETIME_FORMAT_DAY_MONTH_YEAR_LONG_SPACE_DELIMITED_OPTIONAL_TIME))
                .orElse(NULL_VALUE_TO_STRING);
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
