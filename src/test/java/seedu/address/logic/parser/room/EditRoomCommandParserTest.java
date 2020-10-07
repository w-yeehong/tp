package seedu.address.logic.parser.room;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.room.EditRoomCommand.MESSAGE_ROOM_NOT_EDITED;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.room.EditRoomCommand;

public class EditRoomCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditRoomCommand.MESSAGE_USAGE);

    private static final String VALID_ROOM_NUMBER = "1";
    private static final String INVALID_ROOM_NUMBER = "-1";
    private static final String INVALID_ROOM_INPUT = "a";

    private EditRoomCommandParser parser = new EditRoomCommandParser();

    @Test
    public void parse_invalidInputs_failure() {
        // No field specified
        assertParseFailure(parser, VALID_ROOM_NUMBER, MESSAGE_ROOM_NOT_EDITED);

        // Negative integer input
        assertParseFailure(parser, INVALID_ROOM_NUMBER, MESSAGE_INVALID_FORMAT);

        // Non-integer input
        assertParseFailure(parser, INVALID_ROOM_INPUT, MESSAGE_INVALID_FORMAT);

        // No index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }
}
