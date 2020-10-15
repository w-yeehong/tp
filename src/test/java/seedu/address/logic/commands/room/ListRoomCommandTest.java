package seedu.address.logic.commands.room;

import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientRecords;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListRoomCommand.
 */
public class ListRoomCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        //note: can't have empty patient records since the room list contains the patients
        model = new ModelManager(getTypicalPatientRecords(), new UserPrefs(), getTypicalRoomList());
        expectedModel = new ModelManager(model.getPatientRecords(), new UserPrefs(), model.getModifiableRoomList());
    }

    @Test
    public void execute_list_showsEverything() {
        assertCommandSuccess(new ListRoomCommand(), model, ListRoomCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
