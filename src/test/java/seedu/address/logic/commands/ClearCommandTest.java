package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalPatients.getTypicalPatientRecords;
import static seedu.address.testutil.command.GeneralCommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PatientRecords;
import seedu.address.model.RoomList;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.TaskList;

public class ClearCommandTest {

    @Test
    public void execute_emptyCovigentApp_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyCovigentApp_success() {
        Model model = new ModelManager(getTypicalPatientRecords(), new UserPrefs(), new RoomList(), new TaskList());
        Model expectedModel =
                new ModelManager(getTypicalPatientRecords(), new UserPrefs(), new RoomList(), new TaskList());
        expectedModel.setPatientRecords(new PatientRecords());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
