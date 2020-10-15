package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.NewCommandTestUtil.DATETIME_DUE_DESC_ORDER_BEDSHEETS;
import static seedu.address.logic.commands.NewCommandTestUtil.DATETIME_DUE_DESC_REMIND_PATIENT;
import static seedu.address.logic.commands.NewCommandTestUtil.DESCRIPTION_DESC_ORDER_BEDSHEETS;
import static seedu.address.logic.commands.NewCommandTestUtil.DESCRIPTION_DESC_REMIND_PATIENT;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_DATETIME_DUE_FORMAT_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_DATETIME_DUE_VALUE_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_ROOM_NUMBER_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_TASK_NUMBER_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.NewCommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.NewCommandTestUtil.ROOM_NUMBER_DESC_ONE;
import static seedu.address.logic.commands.NewCommandTestUtil.ROOM_NUMBER_DESC_TWO;
import static seedu.address.logic.commands.NewCommandTestUtil.TASK_NUMBER_DESC_ONE;
import static seedu.address.logic.commands.NewCommandTestUtil.TASK_NUMBER_DESC_TWO;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_DATETIME_DUE_REMIND_PATIENT;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_DESCRIPTION_REMIND_PATIENT;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_ROOM_INDEX_ONE;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_TASK_INDEX_ONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.task.EditTaskCommand;
import seedu.address.logic.commands.task.EditTaskCommand.EditTaskDescriptor;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.task.DateTimeDue;
import seedu.address.model.task.Description;

public class EditTaskCommandParserTest {

    private EditTaskCommandParser parser = new EditTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        EditTaskDescriptor descriptor = new EditTaskDescriptor();
        descriptor.setDescription(new Description(VALID_DESCRIPTION_REMIND_PATIENT));
        descriptor.setDateTimeDue(new DateTimeDue(VALID_DATETIME_DUE_REMIND_PATIENT));

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ROOM_NUMBER_DESC_ONE + TASK_NUMBER_DESC_ONE
                + DESCRIPTION_DESC_REMIND_PATIENT + DATETIME_DUE_DESC_REMIND_PATIENT,
                new EditTaskCommand(VALID_ROOM_INDEX_ONE, VALID_TASK_INDEX_ONE, descriptor));

        // multiple room number - last room number accepted
        assertParseSuccess(parser, ROOM_NUMBER_DESC_TWO + ROOM_NUMBER_DESC_ONE + TASK_NUMBER_DESC_ONE
                + DESCRIPTION_DESC_REMIND_PATIENT + DATETIME_DUE_DESC_REMIND_PATIENT,
                new EditTaskCommand(VALID_ROOM_INDEX_ONE, VALID_TASK_INDEX_ONE, descriptor));

        // multiple task number - last task number accepted
        assertParseSuccess(parser, ROOM_NUMBER_DESC_ONE + TASK_NUMBER_DESC_TWO + TASK_NUMBER_DESC_ONE
                + DESCRIPTION_DESC_REMIND_PATIENT + DATETIME_DUE_DESC_REMIND_PATIENT,
                new EditTaskCommand(VALID_ROOM_INDEX_ONE, VALID_TASK_INDEX_ONE, descriptor));

        // multiple description - last description accepted
        assertParseSuccess(parser, ROOM_NUMBER_DESC_ONE + TASK_NUMBER_DESC_ONE + DESCRIPTION_DESC_ORDER_BEDSHEETS
                + DESCRIPTION_DESC_REMIND_PATIENT + DATETIME_DUE_DESC_REMIND_PATIENT,
                new EditTaskCommand(VALID_ROOM_INDEX_ONE, VALID_TASK_INDEX_ONE, descriptor));

        // multiple due date - last due date accepted
        assertParseSuccess(parser, ROOM_NUMBER_DESC_ONE + TASK_NUMBER_DESC_ONE + DESCRIPTION_DESC_REMIND_PATIENT
                + DATETIME_DUE_DESC_ORDER_BEDSHEETS + DATETIME_DUE_DESC_REMIND_PATIENT,
                new EditTaskCommand(VALID_ROOM_INDEX_ONE, VALID_TASK_INDEX_ONE, descriptor));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no description
        EditTaskDescriptor descriptor = new EditTaskDescriptor();
        descriptor.setDateTimeDue(new DateTimeDue(VALID_DATETIME_DUE_REMIND_PATIENT));
        assertParseSuccess(parser, ROOM_NUMBER_DESC_ONE + TASK_NUMBER_DESC_ONE + DATETIME_DUE_DESC_REMIND_PATIENT,
                new EditTaskCommand(VALID_ROOM_INDEX_ONE, VALID_ROOM_INDEX_ONE, descriptor));

        // no due date
        descriptor = new EditTaskDescriptor();
        descriptor.setDescription(new Description(VALID_DESCRIPTION_REMIND_PATIENT));
        assertParseSuccess(parser, ROOM_NUMBER_DESC_ONE + TASK_NUMBER_DESC_ONE + DESCRIPTION_DESC_REMIND_PATIENT,
                new EditTaskCommand(VALID_ROOM_INDEX_ONE, VALID_ROOM_INDEX_ONE, descriptor));
    }

    @Test
    public void parse_allOptionalFieldsMissing_failure() {
        // no description or due date
        assertParseFailure(parser, ROOM_NUMBER_DESC_ONE + TASK_NUMBER_DESC_ONE, Messages.MESSAGE_TASK_NOT_EDITED);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

        // missing room number prefix
        assertParseFailure(parser, TASK_NUMBER_DESC_ONE + DESCRIPTION_DESC_REMIND_PATIENT
                + DATETIME_DUE_DESC_REMIND_PATIENT, expectedMessage);

        // missing task number prefix
        assertParseFailure(parser, ROOM_NUMBER_DESC_ONE + DESCRIPTION_DESC_REMIND_PATIENT
                + DATETIME_DUE_DESC_REMIND_PATIENT, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid due date value
        assertParseFailure(parser, ROOM_NUMBER_DESC_ONE + TASK_NUMBER_DESC_ONE + DESCRIPTION_DESC_REMIND_PATIENT
                + INVALID_DATETIME_DUE_VALUE_DESC, DateTimeDue.MESSAGE_CONSTRAINTS);

        // invalid due date format
        assertParseFailure(parser, ROOM_NUMBER_DESC_ONE + TASK_NUMBER_DESC_ONE + DESCRIPTION_DESC_REMIND_PATIENT
                + INVALID_DATETIME_DUE_FORMAT_DESC, DateTimeDue.MESSAGE_CONSTRAINTS);

        // invalid room number
        assertParseFailure(parser, INVALID_ROOM_NUMBER_DESC + TASK_NUMBER_DESC_ONE,
                ParserUtil.MESSAGE_INVALID_INDEX);

        // invalid task number
        assertParseFailure(parser, ROOM_NUMBER_DESC_ONE + INVALID_TASK_NUMBER_DESC,
                ParserUtil.MESSAGE_INVALID_INDEX);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, ROOM_NUMBER_DESC_ONE + INVALID_TASK_NUMBER_DESC + DESCRIPTION_DESC_REMIND_PATIENT
                + INVALID_DATETIME_DUE_VALUE_DESC, ParserUtil.MESSAGE_INVALID_INDEX);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + ROOM_NUMBER_DESC_ONE + TASK_NUMBER_DESC_ONE
                + DESCRIPTION_DESC_REMIND_PATIENT + DATETIME_DUE_DESC_REMIND_PATIENT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE));
    }
}
