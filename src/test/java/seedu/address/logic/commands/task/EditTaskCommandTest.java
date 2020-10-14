package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_DESCRIPTION_REMIND_PATIENT;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_ROOM_INDEX_ONE;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_ROOM_INDEX_TWO;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_TASK_INDEX_ONE;
import static seedu.address.logic.commands.NewCommandTestUtil.VALID_TASK_INDEX_TWO;
import static seedu.address.logic.commands.task.EditTaskCommand.EditTaskDescriptor;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.task.Description;

public class EditTaskCommandTest {

    private EditTaskDescriptor descriptor;

    @BeforeEach
    public void setUp() {
        descriptor = new EditTaskDescriptor();
    }

    @Test
    public void constructor_nullRoomIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditTaskCommand(null, VALID_TASK_INDEX_ONE, descriptor));
    }

    @Test
    public void constructor_nullTaskIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditTaskCommand(VALID_ROOM_INDEX_ONE, null, descriptor));
    }

    @Test
    public void constructor_nullEditTaskDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditTaskCommand(VALID_ROOM_INDEX_ONE, VALID_TASK_INDEX_ONE, null));
    }

    @Test
    public void equals() {
        EditTaskCommand editTaskOneFromRoomOne = new EditTaskCommand(
                VALID_ROOM_INDEX_ONE, VALID_TASK_INDEX_ONE, descriptor);
        EditTaskCommand editTaskOneFromRoomTwo = new EditTaskCommand(
                VALID_ROOM_INDEX_TWO, VALID_TASK_INDEX_ONE, descriptor);
        EditTaskCommand editTaskTwoFromRoomOne = new EditTaskCommand(
                VALID_ROOM_INDEX_ONE, VALID_TASK_INDEX_TWO, descriptor);

        // same object -> returns true
        assertTrue(editTaskOneFromRoomOne.equals(editTaskOneFromRoomOne));

        // same values -> returns true
        EditTaskCommand editTaskOneFromRoomOneCopy = new EditTaskCommand(
                VALID_ROOM_INDEX_ONE, VALID_TASK_INDEX_ONE, descriptor);
        assertTrue(editTaskOneFromRoomOne.equals(editTaskOneFromRoomOneCopy));

        // different types -> returns false
        assertFalse(editTaskOneFromRoomOne.equals(1));

        // null -> returns false
        assertFalse(editTaskOneFromRoomOne.equals(null));

        // different room index -> returns false
        assertFalse(editTaskOneFromRoomOne.equals(editTaskOneFromRoomTwo));

        // different task index -> returns false
        assertFalse(editTaskOneFromRoomOne.equals(editTaskTwoFromRoomOne));

        // different edit task descriptor -> returns false
        EditTaskDescriptor modifiedDescriptor = new EditTaskDescriptor();
        modifiedDescriptor.setDescription(new Description(VALID_DESCRIPTION_REMIND_PATIENT));
        EditTaskCommand editTaskOneFromRoomOneModifiedDescriptor = new EditTaskCommand(
                VALID_ROOM_INDEX_ONE, VALID_TASK_INDEX_TWO, descriptor);
        assertFalse(editTaskOneFromRoomOne.equals(editTaskOneFromRoomOneModifiedDescriptor));
    }
}
