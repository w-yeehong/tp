package seedu.address.logic.parser.room;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.room.SearchRoomCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.RoomParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new SearchRoomCommand object
 */
public class SearchRoomCommandParser implements Parser<SearchRoomCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SearchRoomCommand
     * and returns a SearchRoomCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    @Override
    public SearchRoomCommand parse(String userInput) throws ParseException {
        try {
            Integer roomNumber = RoomParserUtil.parseRoomNumber(userInput.trim());
            return new SearchRoomCommand(roomNumber);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SearchRoomCommand.MESSAGE_USAGE));
        }
    }
}
