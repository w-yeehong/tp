package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_ROOM_NUMBER_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_TASK_NUMBER_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.NewCommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.NewCommandTestUtil.ROOM_NUMBER_DESC_ONE;
import static seedu.address.logic.commands.NewCommandTestUtil.ROOM_NUMBER_EIGHT_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.ROOM_NUMBER_SEVEN_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.TASK_NUMBER_DESC_ONE;
import static seedu.address.logic.commands.NewCommandTestUtil.TASK_NUMBER_DESC_TWO;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_ROOM_NUMBER_SEVEN;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_TASK_INDEX_ONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.task.DeleteTaskCommand;
import seedu.address.logic.parser.ParserUtil;

public class DeleteTaskCommandParserTest {

    private DeleteTaskCommandParser parser = new DeleteTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ROOM_NUMBER_SEVEN_DESC + TASK_NUMBER_DESC_ONE,
                new DeleteTaskCommand(VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE));

        // multiple room numbers - last room number accepted
        assertParseSuccess(parser, ROOM_NUMBER_EIGHT_DESC + ROOM_NUMBER_SEVEN_DESC + TASK_NUMBER_DESC_ONE,
                new DeleteTaskCommand(VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE));

        // multiple task numbers - last task number accepted
        assertParseSuccess(parser, ROOM_NUMBER_SEVEN_DESC + TASK_NUMBER_DESC_TWO + TASK_NUMBER_DESC_ONE,
                new DeleteTaskCommand(VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE);

        // missing room number prefix
        assertParseFailure(parser, TASK_NUMBER_DESC_ONE, expectedMessage);

        // missing task number prefix
        assertParseFailure(parser, ROOM_NUMBER_SEVEN_DESC, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid room number
        assertParseFailure(parser, INVALID_ROOM_NUMBER_DESC + TASK_NUMBER_DESC_ONE,
                ParserUtil.MESSAGE_INVALID_NUMBER);

        // invalid task number
        assertParseFailure(parser, ROOM_NUMBER_SEVEN_DESC + INVALID_TASK_NUMBER_DESC,
                ParserUtil.MESSAGE_INVALID_INDEX);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + ROOM_NUMBER_SEVEN_DESC + TASK_NUMBER_DESC_ONE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE));
    }
}
