package seedu.address.logic.commands.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.NameContainsKeywordsPredicate;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.TemperatureRangePredicate;
import seedu.address.model.room.RoomList;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.SearchPatientDescriptorBuilder;

import java.util.Arrays;

/**
 * Contains unit tests for SearchPatientCommand.
 */
public class SearchPatientCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new RoomList());

    @Test
    public void execute_searchPatientName_success() {
        Patient patient = new PatientBuilder().withName("Joe Khan").build();
        NameContainsKeywordsPredicate predicate = preparePredicate("Joe Khan");
        SearchPatientCommand.SearchPatientDescriptor descriptor =
                new SearchPatientDescriptorBuilder().withName("Joe Khan").build();
        SearchPatientCommand searchPatientCommand = new SearchPatientCommand(descriptor);
        model.setPatient(model.getFilteredPatientList().get(0), patient);
        model.updateFilteredPatientList(predicate);
        String expectedMessage = String.format(SearchPatientCommand.MESSAGE_SEARCH_PATIENT_SUCCESS, patient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                new RoomList());
        expectedModel.setPatient(model.getFilteredPatientList().get(0), patient);

        expectedModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(searchPatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_searchPatientTemperature_success() {
        Patient patient = new PatientBuilder().withTemperature("40.0").build();
        TemperatureRangePredicate predicate = new TemperatureRangePredicate(39.9, 40.0);
        SearchPatientCommand.SearchPatientDescriptor descriptor =
                new SearchPatientDescriptorBuilder().withTemperatureRange("39.9-40.0").build();
        SearchPatientCommand searchPatientCommand = new SearchPatientCommand(descriptor);
        model.setPatient(model.getFilteredPatientList().get(0), patient);
        model.updateFilteredPatientList(predicate);
        String specifiOutput = String.format("%d.%s\n", 1, patient);

        String expectedMessage =
                String.format(SearchPatientCommand.MESSAGE_SEARCH_PATIENT_LIST_SUCCESS + specifiOutput);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                new RoomList());
        expectedModel.setPatient(model.getFilteredPatientList().get(0), patient);
        expectedModel.updateFilteredPatientList(predicate);

        assertCommandSuccess(searchPatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_searchPatientInvalidSearchCriteria_throwsCommandException() {
        SearchPatientCommand.SearchPatientDescriptor descriptor =
                new SearchPatientDescriptorBuilder().build();
        SearchPatientCommand searchPatientCommand = new SearchPatientCommand(descriptor);

        String expectedMessage = String.format(SearchPatientCommand.MESSAGE_NOT_FOUND);

        assertCommandFailure(searchPatientCommand, model, expectedMessage);
    }

    @Test
    void execute_searchPatientTemperatureRange_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new RoomList());
        TemperatureRangePredicate predicate = new TemperatureRangePredicate(36.9, 37.0);
        Patient patient = new PatientBuilder().withTemperature("36.0").build();
        SearchPatientCommand.SearchPatientDescriptor descriptor =
                new SearchPatientDescriptorBuilder().withTemperatureRange("36.9-37.0").build();
        SearchPatientCommand searchPatientCommand = new SearchPatientCommand(descriptor);
        model.setPatient(model.getFilteredPatientList().get(0), patient);
        model.updateFilteredPatientList(predicate);

        String expectedMessage = String.format(SearchPatientCommand.MESSAGE_PATIENT_NOT_FOUND);

        assertCommandFailure(searchPatientCommand, model, expectedMessage);
    }

    @Test
    void execute_searchPatientName_throwsCommandException() {
        Patient patient = new PatientBuilder().withName("Joe").build();
        NameContainsKeywordsPredicate predicate = preparePredicate("koe");
        SearchPatientCommand.SearchPatientDescriptor descriptor =
                new SearchPatientDescriptorBuilder().withName("koe").build();
        SearchPatientCommand searchPatientCommand = new SearchPatientCommand(descriptor);
        model.setPatient(model.getFilteredPatientList().get(0), patient);
        model.updateFilteredPatientList(predicate);

        String expectedMessage = String.format(SearchPatientCommand.MESSAGE_PATIENT_NOT_FOUND);

        assertCommandFailure(searchPatientCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        SearchPatientCommand.SearchPatientDescriptor joeDescriptor =
                new SearchPatientDescriptorBuilder().withName("Joe").build();
        final SearchPatientCommand searchCommand = new SearchPatientCommand(joeDescriptor);

        SearchPatientCommand.SearchPatientDescriptor amyDescriptor =
                new SearchPatientDescriptorBuilder().withName("Amy").build();
        final SearchPatientCommand newSearchCommand = new SearchPatientCommand(amyDescriptor);

        // Same object -> returns true
        assertTrue(searchCommand.equals(searchCommand));

        // null -> returns false
        assertFalse(searchCommand.equals(null));

        // Different types -> returns false
        assertFalse(searchCommand.equals(new ClearCommand()));

        // Different descriptor -> returns false
        assertNotEquals(newSearchCommand, searchCommand);
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
