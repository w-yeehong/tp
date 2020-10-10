package seedu.address.logic.commands.patient;

import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.getTypicalCovigentApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RoomList;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddPatientCommand}.
 */
public class AddPatientCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalCovigentApp(), new UserPrefs(), new RoomList());
    }

    @Test
    public void execute_newPatient_success() {
        Patient validPatient = new PatientBuilder().build();

        Model expectedModel = new ModelManager(model.getPatientRecords(), new UserPrefs(), new RoomList());
        expectedModel.addPatient(validPatient);

        assertCommandSuccess(new AddPatientCommand(validPatient), model,
                String.format(AddPatientCommand.MESSAGE_SUCCESS, validPatient), expectedModel);
    }

    @Test
    public void execute_duplicatePatient_throwsCommandException() {
        Patient patientInList = model.getPatientRecords().getPatientList().get(0);
        assertCommandFailure(new AddPatientCommand(patientInList), model, AddPatientCommand.MESSAGE_DUPLICATE_PATIENT);
    }
}
