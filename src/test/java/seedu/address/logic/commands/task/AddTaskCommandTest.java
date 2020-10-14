package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_ROOM_INDEX_ONE;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_ROOM_INDEX_TWO;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.REMIND_PATIENT;
import static seedu.address.testutil.TypicalTasks.RESTOCK_SUPPLY;

import org.junit.jupiter.api.Test;

public class AddTaskCommandTest {

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null, VALID_ROOM_INDEX_ONE));
    }

    @Test
    public void constructor_nullRoomIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(REMIND_PATIENT, null));
    }

    // TODO: set up RoomList and Model stubs for testing

    @Test
    public void equals() {
        AddTaskCommand addRemindPatientTaskRoomOne = new AddTaskCommand(REMIND_PATIENT, VALID_ROOM_INDEX_ONE);

        // same object -> returns true
        assertTrue(addRemindPatientTaskRoomOne.equals(addRemindPatientTaskRoomOne));

        // same values -> returns true
        AddTaskCommand addRemindPatientTaskRoomOneCopy = new AddTaskCommand(REMIND_PATIENT, VALID_ROOM_INDEX_ONE);
        assertTrue(addRemindPatientTaskRoomOne.equals(addRemindPatientTaskRoomOneCopy));

        // different types -> returns false
        assertFalse(addRemindPatientTaskRoomOne.equals(1));

        // null -> returns false
        assertFalse(addRemindPatientTaskRoomOne.equals(null));

        // different task -> returns false
        AddTaskCommand addRestockSupplyTaskRoomOne = new AddTaskCommand(RESTOCK_SUPPLY, VALID_ROOM_INDEX_ONE);
        assertFalse(addRemindPatientTaskRoomOne.equals(addRestockSupplyTaskRoomOne));

        // different room index -> returns false
        AddTaskCommand addRemindPatientTaskRoomTwo = new AddTaskCommand(REMIND_PATIENT, VALID_ROOM_INDEX_TWO);
        assertFalse(addRemindPatientTaskRoomOne.equals(addRemindPatientTaskRoomTwo));
    }
}
