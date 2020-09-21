package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.EditPatientCommand.MESSAGE_NOT_EDITED;
import static seedu.address.logic.commands.NewCommandTestUtil.AGE_DESC_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.AGE_DESC_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.COMMENT_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.COMMENT_DESC_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_AGE_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_PERIOD_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_TEMP_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.PERIOD_DESC_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.PERIOD_DESC_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.TEMP_DESC_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.TEMP_DESC_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_AGE_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_AGE_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_PERIOD_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_PERIOD_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_TEMP_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_TEMP_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditPatientCommand;
import seedu.address.model.person.Age;
import seedu.address.model.person.Name;
import seedu.address.model.person.PeriodOfStay;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Temperature;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditPatientCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPatientCommand.MESSAGE_USAGE);

    private EditPatientCommandParser parser = new EditPatientCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no field specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, VALID_NAME_AMY + INVALID_NAME_DESC,
                Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, VALID_NAME_AMY + INVALID_PHONE_DESC,
                Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, VALID_NAME_AMY + INVALID_AGE_DESC,
                Age.MESSAGE_CONSTRAINTS); // invalid age
        assertParseFailure(parser, VALID_NAME_AMY + INVALID_PERIOD_DESC,
                PeriodOfStay.MESSAGE_CONSTRAINTS); // invalid period
        assertParseFailure(parser, VALID_NAME_AMY + INVALID_TEMP_DESC,
                Temperature.MESSAGE_CONSTRAINTS); // invalid temperature

        // invalid phone followed by valid age
        assertParseFailure(parser, VALID_NAME_AMY + INVALID_PHONE_DESC + AGE_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_AGE_DESC + VALID_PERIOD_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = VALID_NAME_AMY + PHONE_DESC_BOB + AGE_DESC_AMY
                + TEMP_DESC_AMY + PERIOD_DESC_AMY + NAME_DESC_AMY;

        EditPatientCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withName(VALID_NAME_AMY).withPhone(VALID_PHONE_BOB).withAge(VALID_AGE_AMY)
                .withTemperature(VALID_TEMP_AMY).withPeriodOfStay(VALID_PERIOD_AMY).build();
        EditPatientCommand expectedCommand = new EditPatientCommand(VALID_NAME_AMY, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = VALID_NAME_AMY + PHONE_DESC_BOB + AGE_DESC_AMY;

        EditPatientCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withPhone(VALID_PHONE_BOB).withAge(VALID_AGE_AMY).build();
        EditPatientCommand expectedCommand = new EditPatientCommand(VALID_NAME_AMY, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        String userInput = VALID_NAME_AMY + NAME_DESC_AMY;
        EditPatientCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withName(VALID_NAME_AMY).build();
        EditPatientCommand expectedCommand = new EditPatientCommand(VALID_NAME_AMY, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = VALID_NAME_AMY + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditPatientCommand(VALID_NAME_AMY, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // age
        userInput = VALID_NAME_AMY + AGE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withAge(VALID_AGE_AMY).build();
        expectedCommand = new EditPatientCommand(VALID_NAME_AMY, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // period
        userInput = VALID_NAME_AMY + PERIOD_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPeriodOfStay(VALID_PERIOD_AMY).build();
        expectedCommand = new EditPatientCommand(VALID_NAME_AMY, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // temperature
        userInput = VALID_NAME_AMY + TEMP_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withTemperature(VALID_TEMP_AMY).build();
        expectedCommand = new EditPatientCommand(VALID_NAME_AMY, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // comment
        userInput = VALID_NAME_AMY + COMMENT_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withComment(COMMENT_AMY).build();
        expectedCommand = new EditPatientCommand(VALID_NAME_AMY, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = VALID_NAME_AMY + PHONE_DESC_AMY + AGE_DESC_AMY + TEMP_DESC_AMY
                + PERIOD_DESC_AMY + PHONE_DESC_AMY + AGE_DESC_AMY + TEMP_DESC_AMY + PERIOD_DESC_AMY
                + PHONE_DESC_BOB + AGE_DESC_BOB + TEMP_DESC_BOB + PERIOD_DESC_BOB;

        EditPatientCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withPhone(VALID_PHONE_BOB).withAge(VALID_AGE_BOB).withTemperature(VALID_TEMP_BOB)
                .withPeriodOfStay(VALID_PERIOD_BOB)
                .build();
        EditPatientCommand expectedCommand = new EditPatientCommand(VALID_NAME_AMY, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        String userInput = VALID_NAME_AMY + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditPatientCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withPhone(VALID_PHONE_BOB).build();
        EditPatientCommand expectedCommand = new EditPatientCommand(VALID_NAME_AMY, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = VALID_NAME_AMY + TEMP_DESC_BOB + INVALID_PHONE_DESC + PERIOD_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).withTemperature(VALID_TEMP_BOB)
                .withPeriodOfStay(VALID_PERIOD_BOB).build();
        expectedCommand = new EditPatientCommand(VALID_NAME_AMY, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
