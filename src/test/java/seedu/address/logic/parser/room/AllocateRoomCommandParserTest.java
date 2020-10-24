package seedu.address.logic.parser.room;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_INTEGER_ROOM_NUMBER_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_NAME_JAMES_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_NON_NUMBER_ROOM_NUMBER;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_NON_NUMBER_ROOM_NUMBER_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_ROOM_NUMBER;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_ROOM_NUMBER_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.ROOM_NUMBER_DESC_ONE;
import static seedu.address.logic.commands.NewCommandTestUtil.ROOM_NUMBER_DESC_TWO;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_NAME_AMY_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_NAME_JAMES_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_PATIENT_NAME_JAMES;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_ROOM_NUMBER_ONE;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_ROOM_NUMBER_TWO;
import static seedu.address.logic.commands.room.AllocateRoomCommand.MESSAGE_ROOM_NOT_EDITED;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_NUMBER;
import static seedu.address.model.patient.Name.MESSAGE_CONSTRAINTS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.room.AllocateRoomCommand;
import seedu.address.model.patient.Name;
import seedu.address.testutil.EditRoomDescriptorBuilder;

public class AllocateRoomCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, AllocateRoomCommand.MESSAGE_USAGE);

    private AllocateRoomCommandParser parser = new AllocateRoomCommandParser();

    @Test
    public void parse_invalidInput_failure() {
        // No field specified
        assertParseFailure(parser, VALID_ROOM_NUMBER_ONE, MESSAGE_ROOM_NOT_EDITED);

        // Negative integer input
        assertParseFailure(parser, INVALID_ROOM_NUMBER, MESSAGE_INVALID_FORMAT);

        // Non-integer input
        assertParseFailure(parser, INVALID_NON_NUMBER_ROOM_NUMBER, MESSAGE_INVALID_FORMAT);
        // Non-number input
        assertParseFailure(parser, INVALID_NON_NUMBER_ROOM_NUMBER, MESSAGE_INVALID_FORMAT);

        // No index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidRoomNumber_failure() {
        assertParseFailure(parser, VALID_ROOM_NUMBER_ONE + INVALID_ROOM_NUMBER_DESC,
                MESSAGE_INVALID_NUMBER); // negative room number

        assertParseFailure(parser, VALID_ROOM_NUMBER_ONE + INVALID_INTEGER_ROOM_NUMBER_DESC,
                MESSAGE_INVALID_NUMBER); // non integer room number

        assertParseFailure(parser, VALID_ROOM_NUMBER_ONE + INVALID_NON_NUMBER_ROOM_NUMBER_DESC,
                MESSAGE_INVALID_NUMBER); // room number is not number
    }

    @Test
    public void parse_invalidPatientName_failure() {
        assertParseFailure(parser, VALID_ROOM_NUMBER_ONE + INVALID_NAME_JAMES_DESC,
                MESSAGE_CONSTRAINTS); // invalid patient name
    }

    @Test
    public void parse_invalidFieldsValue_failure() {
        // Invalid room number followed by valid patient name
        assertParseFailure(parser, VALID_ROOM_NUMBER_ONE + INVALID_ROOM_NUMBER_DESC
                + VALID_NAME_JAMES_DESC, MESSAGE_INVALID_NUMBER);

        // Valid room number followed by invalid patient name
        assertParseFailure(parser, VALID_ROOM_NUMBER_ONE + ROOM_NUMBER_DESC_ONE
                + INVALID_NAME_JAMES_DESC, MESSAGE_CONSTRAINTS);

        // Multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, VALID_ROOM_NUMBER_ONE + INVALID_ROOM_NUMBER_DESC
                + INVALID_NAME_JAMES_DESC, MESSAGE_INVALID_NUMBER);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = VALID_ROOM_NUMBER_ONE + ROOM_NUMBER_DESC_TWO + VALID_NAME_JAMES_DESC;

        AllocateRoomCommand.AllocateRoomDescriptor descriptor = new EditRoomDescriptorBuilder()
                .withRoomNumber(Integer.valueOf(VALID_ROOM_NUMBER_TWO))
                .withPatient(new Name(VALID_PATIENT_NAME_JAMES))
                .build();
        AllocateRoomCommand expectedCommand = new AllocateRoomCommand(Integer.valueOf(VALID_ROOM_NUMBER_ONE),
            descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        // only valid room number
        String userInput = VALID_ROOM_NUMBER_ONE + ROOM_NUMBER_DESC_TWO;

        AllocateRoomCommand.AllocateRoomDescriptor descriptor = new EditRoomDescriptorBuilder()
                .withRoomNumber(Integer.valueOf(VALID_ROOM_NUMBER_TWO)).build();
        AllocateRoomCommand expectedCommand = new AllocateRoomCommand(Integer.valueOf(VALID_ROOM_NUMBER_ONE),
            descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
        // only valid patient
        userInput = VALID_ROOM_NUMBER_ONE + VALID_NAME_JAMES_DESC;

        descriptor = new EditRoomDescriptorBuilder()
            .withPatient(new Name(VALID_PATIENT_NAME_JAMES)).build();
        expectedCommand = new AllocateRoomCommand(Integer.valueOf(VALID_ROOM_NUMBER_ONE), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = VALID_ROOM_NUMBER_ONE + VALID_NAME_JAMES_DESC + ROOM_NUMBER_DESC_ONE
            + VALID_NAME_AMY_DESC + ROOM_NUMBER_DESC_TWO;

        AllocateRoomCommand.AllocateRoomDescriptor descriptor = new EditRoomDescriptorBuilder()
            .withRoomNumber(Integer.valueOf(VALID_ROOM_NUMBER_TWO))
            .withPatient(new Name(VALID_NAME_AMY))
            .build();
        AllocateRoomCommand expectedCommand = new AllocateRoomCommand(Integer.valueOf(VALID_ROOM_NUMBER_ONE),
            descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // No other valid values specified
        String userInput = VALID_ROOM_NUMBER_TWO + INVALID_ROOM_NUMBER_DESC + ROOM_NUMBER_DESC_ONE;
        AllocateRoomCommand.AllocateRoomDescriptor descriptor = new EditRoomDescriptorBuilder()
            .withRoomNumber(Integer.valueOf(VALID_ROOM_NUMBER_ONE))
            .build();
        AllocateRoomCommand expectedCommand = new AllocateRoomCommand(Integer.valueOf(VALID_ROOM_NUMBER_TWO),
            descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // Other valid values specified
        userInput = VALID_ROOM_NUMBER_TWO + INVALID_ROOM_NUMBER_DESC + VALID_NAME_JAMES_DESC + ROOM_NUMBER_DESC_ONE;
        descriptor = new EditRoomDescriptorBuilder()
            .withRoomNumber(Integer.valueOf(VALID_ROOM_NUMBER_ONE))
            .withPatient(new Name(VALID_PATIENT_NAME_JAMES)).build();
        expectedCommand = new AllocateRoomCommand(Integer.valueOf(VALID_ROOM_NUMBER_TWO), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
