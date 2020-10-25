package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.command.RoomCommandTestUtil.VALID_ROOM_NUMBER_EIGHT;
import static seedu.address.testutil.command.RoomCommandTestUtil.VALID_ROOM_NUMBER_SEVEN;
import static seedu.address.testutil.command.TaskCommandTestUtil.VALID_TASK_INDEX_ONE;
import static seedu.address.testutil.command.TaskCommandTestUtil.VALID_TASK_INDEX_TWO;

import org.junit.jupiter.api.Test;

public class DeleteTaskCommandTest {

    @Test
    public void constructor_nullTaskIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTaskCommand(VALID_ROOM_NUMBER_SEVEN, null));
    }

    @Test
    public void equals() {
        DeleteTaskCommand deleteTaskOneFromRoomSeven = new DeleteTaskCommand(
                VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE);

        // same object -> returns true
        assertTrue(deleteTaskOneFromRoomSeven.equals(deleteTaskOneFromRoomSeven));

        // same values -> returns true
        DeleteTaskCommand deleteTaskOneFromRoomSevenCopy = new DeleteTaskCommand(
                VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE);
        assertTrue(deleteTaskOneFromRoomSeven.equals(deleteTaskOneFromRoomSevenCopy));

        // different types -> returns false
        assertFalse(deleteTaskOneFromRoomSeven.equals(1));

        // null -> returns false
        assertFalse(deleteTaskOneFromRoomSeven.equals(null));

        // different room index -> returns false
        DeleteTaskCommand deleteTaskOneFromRoomEight = new DeleteTaskCommand(
                VALID_ROOM_NUMBER_EIGHT, VALID_TASK_INDEX_ONE);
        assertFalse(deleteTaskOneFromRoomSeven.equals(deleteTaskOneFromRoomEight));

        // different task index -> returns false
        DeleteTaskCommand deleteTaskTwoFromRoomSeven = new DeleteTaskCommand(
                VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_TWO);
        assertFalse(deleteTaskOneFromRoomSeven.equals(deleteTaskTwoFromRoomSeven));
    }
}
