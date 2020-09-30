package seedu.address.logic.parser;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.NO_ARGUMENTS_GIVEN;
import static seedu.address.commons.core.Messages.NUMBER_OF_ROOMS_GIVEN_IN_DIGITS;

import seedu.address.logic.commands.AddRoomsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input and creates new AddRoomsCommand object
 */
public class AddRoomsParser implements Parser<AddRoomsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPatientCommand
     * and returns an AddRoomsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddRoomsCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NO_ARGUMENTS_GIVEN));
            //if user does not give number of room
        }
        int numberOfRooms = -1; //used so that it can be used to test for NumberFormatException
        try {
            numberOfRooms = Integer.parseInt(trimmedArgs);
        } catch (NumberFormatException nfe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NUMBER_OF_ROOMS_GIVEN_IN_DIGITS));
            //if user does not give number of rooms in terms of digits but gives in terms of words
        }
        return new AddRoomsCommand(numberOfRooms);
    }

}
