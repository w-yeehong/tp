package seedu.address.logic.commands.patient;

import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientRecords;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomList;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;


/**
 * Contains integration tests (interaction with the Model) for {@code DeletePatientCommand}.
 */
public class DeletePatientCommandIntegrationTest {

    //patient records -> [ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE]
    //room list -> [room 7, Alice; room 8, Benson; room 10, null]
    private Model model = new ModelManager(getTypicalPatientRecords(), new UserPrefs(), getTypicalRoomList());

    @Test
    public void execute_deletePatientInRoom_success() {
        Patient alice = model.getFilteredPatientList().get(0);
        Name aliceName = alice.getName();
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(aliceName);
        String expectedMessage = String.format(DeletePatientCommand.MESSAGE_DELETE_PATIENT_SUCCESS, alice);

        ModelManager expectedModel = new ModelManager(model.getPatientRecords(), new UserPrefs(), getTypicalRoomList());
        expectedModel.clearRoom(aliceName);
        expectedModel.deletePatient(alice);
        assertCommandSuccess(deletePatientCommand, model, expectedMessage, expectedModel);
    }
}
