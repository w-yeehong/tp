package seedu.address.logic.parser;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import javax.swing.text.html.parser.Parser;
import seedu.address.logic.commands.AddNumberOfRoomsCommand;
import seedu.address.logic.commands.FindRoomCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input and creates new AddNumberOfRoomsCommand object
 */
public class AddNumberOfRoomsParser {

    public static String NO_ARGUMENTS_GIVEN = "no arguments are given";
    public static String NUMBER_OF_ROOMS_GIVEN_IN_DIGITS = "Please give the number of digits in numbers\n Example: 200";

    /**
     * Parses the given {@code String} of arguments in the context of the AddPatientCommand
     * and returns an AddNumberOfRoomsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddNumberOfRoomsCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if(trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NO_ARGUMENTS_GIVEN));
            //if user does not give number of room
        }
        int numberOfRooms;
        try {
            numberOfRooms = Integer.parseInt(trimmedArgs);
        }catch (NumberFormatException nfe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NUMBER_OF_ROOMS_GIVEN_IN_DIGITS));
            //if user does not give number of rooms in terms of digits but gives in terms of words
        }
        return new AddNumberOfRoomsCommand(numberOfRooms);
    }
}
