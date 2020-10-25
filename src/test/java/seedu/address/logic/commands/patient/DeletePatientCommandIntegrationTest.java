package seedu.address.logic.commands.patient;

import static seedu.address.testutil.TypicalPatients.getTypicalPatientRecords;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomList;
import static seedu.address.testutil.command.GeneralCommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.task.TaskList;

//@@author chiamyunqing
/**
 * Contains integration tests (interaction with both PatientRecords and RoomList inthe Model)
 * for {@code DeletePatientCommand}.
 */

//TODO -> to test rooms with tasklist once the NewCommandParserUtil is abstracted
public class DeletePatientCommandIntegrationTest {

    //patient records -> [ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE]
    //room list -> [room 7, Alice; room 8, Benson; room 10, null]
    private Model model =
            new ModelManager(getTypicalPatientRecords(), new UserPrefs(), getTypicalRoomList(), new TaskList());

    @Test
    public void execute_deletePatientInRoom_success() {
        Patient alice = model.getFilteredPatientList().get(0);
        Name aliceName = alice.getName();
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(aliceName);
        String expectedMessage = String.format(DeletePatientCommand.MESSAGE_DELETE_PATIENT_SUCCESS, alice);

        ModelManager expectedModel =
                new ModelManager(model.getPatientRecords(), new UserPrefs(), getTypicalRoomList(), new TaskList());
        expectedModel.removePatientFromRoom(aliceName);
        expectedModel.deletePatient(alice);
        assertCommandSuccess(deletePatientCommand, model, expectedMessage, expectedModel);
    }
}
