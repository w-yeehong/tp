package seedu.address.logic.commands;

import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.getTypicalCovigentApp;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PatientRecords;
import seedu.address.model.RoomList;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyCovigentApp_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyCovigentApp_success() {
        Model model = new ModelManager(getTypicalCovigentApp(), new UserPrefs(), new RoomList());
        Model expectedModel = new ModelManager(getTypicalCovigentApp(), new UserPrefs(), new RoomList());
        expectedModel.setPatientRecords(new PatientRecords());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
