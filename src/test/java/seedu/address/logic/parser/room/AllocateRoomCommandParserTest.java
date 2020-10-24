package seedu.address.logic.parser.room;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_NAME_JAMES_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_NON_NUMBER_ROOM_NUMBER;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_ROOM_NUMBER;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_NAME_AMY_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_NAME_JAMES_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_PATIENT_NAME_JAMES;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_ROOM_NUMBER_ONE;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_ROOM_NUMBER_TWO;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.patient.Name.MESSAGE_CONSTRAINTS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.room.AllocateRoomCommand;
import seedu.address.model.patient.Name;
import seedu.address.testutil.AllocateRoomDescriptorBuilder;

public class AllocateRoomCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, AllocateRoomCommand.MESSAGE_USAGE);

    private AllocateRoomCommandParser parser = new AllocateRoomCommandParser();

    @Test
    public void parse_invalidInput_failure() {
        // No field specified
        assertParseFailure(parser, VALID_ROOM_NUMBER_ONE, AllocateRoomCommand.MESSAGE_USAGE);

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
    public void parse_invalidPatientName_failure() {
        assertParseFailure(parser, VALID_ROOM_NUMBER_ONE + INVALID_NAME_JAMES_DESC,
                MESSAGE_CONSTRAINTS); // invalid patient name
    }

    @Test
    public void parse_invalidFieldsValue_failure() {
        // Invalid patient name
        assertParseFailure(parser, VALID_ROOM_NUMBER_ONE + INVALID_NAME_JAMES_DESC, MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = VALID_ROOM_NUMBER_ONE + VALID_NAME_JAMES_DESC;

        AllocateRoomCommand.AllocateRoomDescriptor descriptor = new AllocateRoomDescriptorBuilder()
                .withRoomNumber(Integer.valueOf(VALID_ROOM_NUMBER_TWO))
                .withPatient(new Name(VALID_PATIENT_NAME_JAMES))
                .build();
        AllocateRoomCommand expectedCommand = new AllocateRoomCommand(Integer.valueOf(VALID_ROOM_NUMBER_ONE),
            descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedValue_acceptsLast() {
        String userInput = VALID_ROOM_NUMBER_ONE + VALID_NAME_JAMES_DESC
            + VALID_NAME_AMY_DESC;

        AllocateRoomCommand.AllocateRoomDescriptor descriptor = new AllocateRoomDescriptorBuilder()
            .withRoomNumber(Integer.valueOf(VALID_ROOM_NUMBER_ONE))
            .withPatient(new Name(VALID_NAME_AMY))
            .build();
        AllocateRoomCommand expectedCommand = new AllocateRoomCommand(Integer.valueOf(VALID_ROOM_NUMBER_ONE),
            descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        String userInput = VALID_ROOM_NUMBER_TWO + INVALID_NAME_JAMES_DESC + VALID_NAME_JAMES_DESC;
        AllocateRoomCommand.AllocateRoomDescriptor descriptor = new AllocateRoomDescriptorBuilder()
            .withRoomNumber(Integer.valueOf(VALID_ROOM_NUMBER_ONE))
            .withPatient(new Name(VALID_PATIENT_NAME_JAMES)).build();
        AllocateRoomCommand expectedCommand = new AllocateRoomCommand(Integer.valueOf(VALID_ROOM_NUMBER_TWO),
            descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void equals() {
        AllocateRoomCommand.AllocateRoomDescriptor descriptorOne = new AllocateRoomDescriptorBuilder()
            .withRoomNumber(Integer.valueOf(VALID_ROOM_NUMBER_ONE))
            .withPatient(new Name(VALID_PATIENT_NAME_JAMES)).build();

        AllocateRoomCommand.AllocateRoomDescriptor descriptorTwo = new AllocateRoomDescriptorBuilder()
            .withRoomNumber(Integer.valueOf(VALID_ROOM_NUMBER_TWO))
            .withPatient(new Name(VALID_PATIENT_NAME_JAMES)).build();

        AllocateRoomCommand allocateFirstRoomCommand = new AllocateRoomCommand(
            Integer.valueOf(VALID_ROOM_NUMBER_ONE), descriptorOne);
        AllocateRoomCommand allocateSecondRoomCommand = new AllocateRoomCommand(
            Integer.valueOf(VALID_ROOM_NUMBER_TWO), descriptorTwo);

        // same object -> returns true
        assertTrue(allocateFirstRoomCommand.equals(allocateFirstRoomCommand));

        // different types -> returns false
        assertFalse(allocateFirstRoomCommand.equals(1));

        // null -> returns false
        assertFalse(allocateFirstRoomCommand.equals(null));

        // different patient -> returns false
        assertFalse(allocateFirstRoomCommand.equals(allocateSecondRoomCommand));
    }
}
