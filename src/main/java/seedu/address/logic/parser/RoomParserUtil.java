package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class RoomParserUtil {
    public static final String MESSAGE_INVALID_NUMBER = "Please only enter positive numbers";

    /**
     * Parses {@code roomNumber} into an {@code Integer} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified roomNumber is invalid (not non-zero unsigned integer).
     */
    public static Integer parseRoomNumber(String roomNumber) throws ParseException {
        requireNonNull(roomNumber);
        String trimmedRoomNumber = roomNumber.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedRoomNumber)) {
            throw new ParseException(MESSAGE_INVALID_NUMBER);
        }
        return Integer.parseInt(trimmedRoomNumber);
    }
}
