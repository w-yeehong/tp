package seedu.address.logic.commands.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Patient;
import seedu.address.model.room.RoomList;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeletePatientCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new RoomList());

    @Test
    public void execute_validNameUnfilteredList_success() {
        Patient patientToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        String patientToDeleteName = patientToDelete.getName().toString();
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(patientToDeleteName);

        String expectedMessage = String.format(DeletePatientCommand.MESSAGE_DELETE_PATIENT_SUCCESS, patientToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new RoomList());
        expectedModel.deletePatient(patientToDelete);

        assertCommandSuccess(deletePatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNameUnfilteredList_throwsCommandException() {
        String invalidPatientName = "Obviously invalid name";
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(invalidPatientName);
        assertCommandFailure(deletePatientCommand, model, Messages.MESSAGE_INVALID_PATIENT_NAME_INPUT);
    }

    @Test
    public void equals() {
        DeletePatientCommand deleteFirstCommand = new DeletePatientCommand("Jane Doe");
        DeletePatientCommand deleteSecondCommand = new DeletePatientCommand("John Doe");

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same names of patient -> returns true
        DeletePatientCommand deleteFirstCommandCopy = new DeletePatientCommand("jane doe");
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different patient -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPatient(Model model) {
        model.updateFilteredPatientList(p -> false);

        assertTrue(model.getFilteredPatientList().isEmpty());
    }
}
