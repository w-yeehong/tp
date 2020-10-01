package seedu.address.logic.parser.room;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.NO_ARGUMENTS_GIVEN;
import static seedu.address.commons.core.Messages.NUMBER_OF_ROOMS_GIVEN_IN_DIGITS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.room.AddRoomsCommand;

class AddRoomsParserTest {
    private AddRoomsParser parser = new AddRoomsParser();
    @Test
    void parse() {
        String input = "123";
        assertParseSuccess(parser, input, new AddRoomsCommand(123));
    }

    @Test
    void parseError_empty_input() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, NO_ARGUMENTS_GIVEN));
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, NO_ARGUMENTS_GIVEN));
    }

    @Test
    void parse_error() {
        assertParseFailure(parser, "two hundred",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NUMBER_OF_ROOMS_GIVEN_IN_DIGITS));
        assertParseFailure(parser, "twelve",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NUMBER_OF_ROOMS_GIVEN_IN_DIGITS));
    }
}
