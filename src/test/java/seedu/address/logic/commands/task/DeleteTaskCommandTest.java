package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_ROOM_INDEX_ONE;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_ROOM_INDEX_TWO;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_TASK_INDEX_ONE;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_TASK_INDEX_TWO;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeleteTaskCommandTest {

    @Test
    public void constructor_nullRoomIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTaskCommand(null, VALID_TASK_INDEX_ONE));
    }

    @Test
    public void constructor_nullTaskIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTaskCommand(VALID_ROOM_INDEX_ONE, null));
    }

    @Test
    public void equals() {
        DeleteTaskCommand deleteTaskOneFromRoomOne = new DeleteTaskCommand(
                VALID_ROOM_INDEX_ONE, VALID_TASK_INDEX_ONE);

        // same object -> returns true
        assertTrue(deleteTaskOneFromRoomOne.equals(deleteTaskOneFromRoomOne));

        // same values -> returns true
        DeleteTaskCommand deleteTaskOneFromRoomOneCopy = new DeleteTaskCommand(
                VALID_ROOM_INDEX_ONE, VALID_TASK_INDEX_ONE);
        assertTrue(deleteTaskOneFromRoomOne.equals(deleteTaskOneFromRoomOneCopy));

        // different types -> returns false
        assertFalse(deleteTaskOneFromRoomOne.equals(1));

        // null -> returns false
        assertFalse(deleteTaskOneFromRoomOne.equals(null));

        // different room index -> returns false
        DeleteTaskCommand deleteTaskOneFromRoomTwo = new DeleteTaskCommand(
                VALID_ROOM_INDEX_TWO, VALID_TASK_INDEX_ONE);
        assertFalse(deleteTaskOneFromRoomOne.equals(deleteTaskOneFromRoomTwo));

        // different task index -> returns false
        DeleteTaskCommand deleteTaskTwoFromRoomOne = new DeleteTaskCommand(
                VALID_ROOM_INDEX_ONE, VALID_TASK_INDEX_TWO);
        assertFalse(deleteTaskOneFromRoomOne.equals(deleteTaskTwoFromRoomOne));
    }
}
