package seedu.address.logic.parser.room;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.room.SearchRoomCommand;

public class SearchRoomCommandParserTest {

    private SearchRoomCommandParser parser = new SearchRoomCommandParser();

    @Test
    public void parse_validArgs_returnsSearchRoomCommand() {
        assertParseSuccess(parser, "7", new SearchRoomCommand(7));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SearchRoomCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsNonNumber_throwsParseException() {
        assertParseFailure(parser, "twenty", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SearchRoomCommand.MESSAGE_USAGE));
    }
}
