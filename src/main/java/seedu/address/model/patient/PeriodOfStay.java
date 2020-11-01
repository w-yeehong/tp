package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

//@@author chiamyunqing
/**
 * Represents a Patient's period of stay in the facility.
 * Guarantees: immutable; is valid as declared in {@link #isValidPeriodOfStay(String)}
 */
public class PeriodOfStay {

    public static final String MESSAGE_CONSTRAINTS =
            "Period of stay should be in the format YYYYMMDD-YYYYMMDD.";

    public static final String VALIDATION_REGEX = "\\d{8}[-]\\d{8}";

    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructs a {@code PeriodOfStay}.
     *
     * @param periodOfStay A valid period of stay.
     */
    public PeriodOfStay(String periodOfStay) {
        requireNonNull(periodOfStay);
        checkArgument(isValidPeriodOfStay(periodOfStay), MESSAGE_CONSTRAINTS);
        startDate = LocalDate.parse(periodOfStay.split("-")[0], DateTimeFormatter.ofPattern("yyyyMMdd"));
        endDate = LocalDate.parse(periodOfStay.split("-")[1], DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    /**
     * Returns true if a given string is a valid period of stay.
     */
    public static boolean isValidPeriodOfStay(String test) {
        boolean isRegexCorrect = test.matches(VALIDATION_REGEX);
        if (!isRegexCorrect) {
            return false;
        }
        String[] dates = test.split("-");
        try {
            LocalDate start = LocalDate.parse(dates[0], DateTimeFormatter.ofPattern("yyyyMMdd"));
            LocalDate end = LocalDate.parse(dates[1], DateTimeFormatter.ofPattern("yyyyMMdd"));
            return start.compareTo(end) <= 0; //check start date less than or equals end date
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    /**
     * Returns a string that is more human readable (e.g. 09 Jan 2020), meant for UI.
     *
     * @return reader friendly string.
     */
    public String readerFriendlyString() {
        return startDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " to "
                + endDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /**
     * Primarily used for storage of data.
     */
    @Override
    public String toString() {
        return startDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "-"
                + endDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PeriodOfStay // instanceof handles nulls
                && startDate.compareTo(((PeriodOfStay) other).startDate) == 0
                && endDate.compareTo(((PeriodOfStay) other).endDate) == 0); // state check
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
