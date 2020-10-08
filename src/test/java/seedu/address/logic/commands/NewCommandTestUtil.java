package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_COMMENTS;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_PERIOD_OF_STAY;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_TEMP;
import static seedu.address.logic.parser.task.TaskCliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.task.TaskCliSyntax.PREFIX_DUE_DATE;
import static seedu.address.logic.parser.task.TaskCliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.patient.EditPatientCommand.EditPatientDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.patient.NameContainsKeywordsPredicate;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.EditPatientDescriptorBuilder;

public class NewCommandTestUtil {

    // General
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    // Patients

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_TEMP_AMY = "36.7";
    public static final String VALID_TEMP_BOB = "37.0";
    public static final String VALID_PERIOD_AMY = "20200912-20200918";
    public static final String VALID_PERIOD_BOB = "20201001-20201014";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_AGE_AMY = "22";
    public static final String VALID_AGE_BOB = "45";
    public static final String COMMENT_AMY = "Down with flu";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String TEMP_DESC_AMY = " " + PREFIX_TEMP + VALID_TEMP_AMY;
    public static final String TEMP_DESC_BOB = " " + PREFIX_TEMP + VALID_TEMP_BOB;
    public static final String PERIOD_DESC_AMY = " " + PREFIX_PERIOD_OF_STAY + VALID_PERIOD_AMY;
    public static final String PERIOD_DESC_BOB = " " + PREFIX_PERIOD_OF_STAY + VALID_PERIOD_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String AGE_DESC_AMY = " " + PREFIX_AGE + VALID_AGE_AMY;
    public static final String AGE_DESC_BOB = " " + PREFIX_AGE + VALID_AGE_BOB;
    public static final String COMMENT_DESC_AMY = " " + PREFIX_COMMENTS + COMMENT_AMY;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_TEMP_DESC = " " + PREFIX_TEMP + "36.P"; //non-numeric not allowed in temperature
    public static final String INVALID_PERIOD_DESC = " " + PREFIX_PERIOD_OF_STAY + "20200913-20201838"; //invalid date
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_AGE_DESC = " " + PREFIX_AGE + "999"; //cannot be so old

    public static final String NO_COMMENT = "-";
    public static final String COMMENT = "History of heart attack";

    public static final EditPatientDescriptor DESC_AMY;
    public static final EditPatientDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPatientDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withTemperature(VALID_TEMP_AMY).withPeriodOfStay(VALID_PERIOD_AMY)
                .withAge(VALID_AGE_AMY).withComment(COMMENT).build();
        DESC_BOB = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTemperature(VALID_TEMP_BOB).withPeriodOfStay(VALID_PERIOD_BOB)
                .withAge(VALID_AGE_BOB).withComment(NO_COMMENT).build();
    }

    // Tasks

    public static final String VALID_DESCRIPTION_REMIND_PATIENT = "Remind Alice to change bedsheets.";
    public static final String VALID_DESCRIPTION_ORDER_BEDSHEET = "Order new bedsheets for Room #3.";
    public static final String VALID_DATETIME_DUE_REMIND_PATIENT = "20201230 2359";
    public static final String VALID_DATETIME_DUE_ORDER_BEDSHEET = "20201023 1930";
    public static final String VALID_ROOM_NUMBER_ONE = "1";
    public static final String VALID_ROOM_NUMBER_TWO = "2";
    public static final Index VALID_ROOM_INDEX_ONE = Index.fromOneBased(1);
    public static final Index VALID_ROOM_INDEX_TWO = Index.fromOneBased(2);

    public static final String INVALID_DATETIME_DUE_VALUE = "aaa";
    public static final String INVALID_DATETIME_DUE_FORMAT = "2020-12-31";
    public static final String INVALID_ROOM_NUMBER = "-1";

    public static final String DESCRIPTION_DESC_REMIND_PATIENT = " " + PREFIX_DESCRIPTION
            + VALID_DESCRIPTION_REMIND_PATIENT;
    public static final String DESCRIPTION_DESC_ORDER_BEDSHEET = " " + PREFIX_DESCRIPTION
            + VALID_DATETIME_DUE_ORDER_BEDSHEET;
    public static final String DATETIME_DUE_DESC_REMIND_PATIENT = " " + PREFIX_DUE_DATE
            + VALID_DATETIME_DUE_REMIND_PATIENT;
    public static final String DATETIME_DUE_DESC_ORDER_BEDSHEET = " " + PREFIX_DUE_DATE
            + VALID_DATETIME_DUE_ORDER_BEDSHEET;
    public static final String ROOM_NUMBER_DESC_ONE = " " + PREFIX_ROOM_NUMBER + VALID_ROOM_NUMBER_ONE;
    public static final String ROOM_NUMBER_DESC_TWO = " " + PREFIX_ROOM_NUMBER + VALID_ROOM_NUMBER_TWO;

    public static final String INVALID_DATETIME_DUE_VALUE_DESC = " " + PREFIX_DUE_DATE
            + INVALID_DATETIME_DUE_VALUE;
    public static final String INVALID_DATETIME_DUE_FORMAT_DESC = " " + PREFIX_DUE_DATE
            + INVALID_DATETIME_DUE_FORMAT;
    public static final String INVALID_ROOM_NUMBER_DESC = " " + PREFIX_ROOM_NUMBER
            + INVALID_ROOM_NUMBER;

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered patient list and selected patient in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Patient> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPatientList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPatientList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the patient at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPatientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPatientList().size());

        Patient patient = model.getFilteredPatientList().get(targetIndex.getZeroBased());
        final String[] splitName = patient.getName().fullName.split("\\s+");
        model.updateFilteredPatientList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPatientList().size());
    }
}
