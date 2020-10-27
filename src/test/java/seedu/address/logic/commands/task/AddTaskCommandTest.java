package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.REMIND_PATIENT;
import static seedu.address.testutil.TypicalTasks.RESTOCK_SUPPLY;
import static seedu.address.testutil.command.RoomCommandTestUtil.VALID_ROOM_NUMBER_EIGHT;
import static seedu.address.testutil.command.RoomCommandTestUtil.VALID_ROOM_NUMBER_SEVEN;

import org.junit.jupiter.api.Test;

public class AddTaskCommandTest {

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null, VALID_ROOM_NUMBER_SEVEN));
    }

    @Test
    public void constructor_nullRoomIndex_throwsNullPointerException() {
        assertThrows(AssertionError.class, () -> new AddTaskCommand(REMIND_PATIENT, -1));
    }

    // TODO: set up RoomList and Model stubs for testing

    @Test
    public void equals() {
        AddTaskCommand addRemindPatientToRoomSeven = new AddTaskCommand(REMIND_PATIENT, VALID_ROOM_NUMBER_SEVEN);

        // same object -> returns true
        assertTrue(addRemindPatientToRoomSeven.equals(addRemindPatientToRoomSeven));

        // same values -> returns true
        AddTaskCommand addRemindPatientToRoomSevenCopy = new AddTaskCommand(REMIND_PATIENT, VALID_ROOM_NUMBER_SEVEN);
        assertTrue(addRemindPatientToRoomSeven.equals(addRemindPatientToRoomSevenCopy));

        // different types -> returns false
        assertFalse(addRemindPatientToRoomSeven.equals(1));

        // null -> returns false
        assertFalse(addRemindPatientToRoomSeven.equals(null));

        // different task -> returns false
        AddTaskCommand addRestockSupplyToRoomSeven = new AddTaskCommand(RESTOCK_SUPPLY, VALID_ROOM_NUMBER_SEVEN);
        assertFalse(addRemindPatientToRoomSeven.equals(addRestockSupplyToRoomSeven));

        // different room number -> returns false
        AddTaskCommand addRemindPatientToRoomEight = new AddTaskCommand(REMIND_PATIENT, VALID_ROOM_NUMBER_EIGHT);
        assertFalse(addRemindPatientToRoomSeven.equals(addRemindPatientToRoomEight));
    }
}
