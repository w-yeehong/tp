package seedu.address.logic.commands.room;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ROOM_NOT_FOUND;
import static seedu.address.logic.commands.NewCommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientRecords;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomList;

/**
 * Contains unit tests for SearchRoomCommand.
 */
public class SearchRoomCommandTest {
    private Model model = new ModelManager(getTypicalPatientRecords(), new UserPrefs(), getTypicalRoomList());

    @Test
    public void constructor_nullRoomNumber_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SearchRoomCommand(null));
    }

    @Test
    public void execute_findInvalidRoomNumber_failure() {
        SearchRoomCommand searchRoomCommand = new SearchRoomCommand(9999);
        String expectedMessage = MESSAGE_INVALID_ROOM_NOT_FOUND;
        assertCommandFailure(searchRoomCommand, model, expectedMessage);
    }

    //TODO success test cases once we finalise how rooms UI works
}
