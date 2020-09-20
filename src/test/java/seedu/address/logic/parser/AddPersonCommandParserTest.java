package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.testutil.PersonBuilder;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_AGE_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_PERIOD_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.INVALID_TEMP_DESC;
import static seedu.address.logic.commands.NewCommandTestUtil.AGE_DESC_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.AGE_DESC_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.PERIOD_DESC_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.PERIOD_DESC_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.NewCommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.NewCommandTestUtil.TEMP_DESC_AMY;
import static seedu.address.logic.commands.NewCommandTestUtil.TEMP_DESC_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_AGE_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_PERIOD_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_TEMP_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

public class AddPersonCommandParserTest {

    private AddPersonCommandParser parser = new AddPersonCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + TEMP_DESC_BOB
                + PERIOD_DESC_BOB + PHONE_DESC_BOB + AGE_DESC_BOB, new AddPersonCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + TEMP_DESC_BOB
                + PERIOD_DESC_BOB + PHONE_DESC_BOB + AGE_DESC_BOB, new AddPersonCommand(expectedPerson));
/*
        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddPersonCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddPersonCommand(expectedPerson));


 */
    }

/* for remark TODO
    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY ,
                new AddPersonCommand(expectedPerson));
    }

 */

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + TEMP_DESC_BOB + PERIOD_DESC_BOB
                        + PHONE_DESC_BOB + AGE_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + TEMP_DESC_BOB + PERIOD_DESC_BOB
                        + VALID_PHONE_BOB + AGE_DESC_BOB, expectedMessage);


        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_TEMP_BOB + VALID_PERIOD_BOB
                        + VALID_PHONE_BOB + VALID_AGE_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + TEMP_DESC_BOB + PERIOD_DESC_BOB
                + PHONE_DESC_BOB + AGE_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + TEMP_DESC_BOB + PERIOD_DESC_BOB
                + INVALID_PHONE_DESC + AGE_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_TEMP_DESC + PERIOD_DESC_BOB
                + PHONE_DESC_BOB + AGE_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + TEMP_DESC_BOB + PERIOD_DESC_BOB
                + PHONE_DESC_BOB + AGE_DESC_BOB, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE));
    }
}
