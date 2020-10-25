package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.task.EditTaskCommand.EditTaskDescriptor;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.command.RoomCommandTestUtil.VALID_ROOM_NUMBER_EIGHT;
import static seedu.address.testutil.command.RoomCommandTestUtil.VALID_ROOM_NUMBER_SEVEN;
import static seedu.address.testutil.command.TaskCommandTestUtil.VALID_DESCRIPTION_REMIND_PATIENT;
import static seedu.address.testutil.command.TaskCommandTestUtil.VALID_TASK_INDEX_ONE;
import static seedu.address.testutil.command.TaskCommandTestUtil.VALID_TASK_INDEX_TWO;

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
    public void constructor_nullTaskIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditTaskCommand(VALID_ROOM_NUMBER_SEVEN, null, descriptor));
    }

    @Test
    public void constructor_nullEditTaskDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditTaskCommand(VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE, null));
    }

    @Test
    public void equals() {
        EditTaskCommand editTaskOneFromRoomSeven = new EditTaskCommand(
                VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE, descriptor);
        EditTaskCommand editTaskOneFromRoomEight = new EditTaskCommand(
                VALID_ROOM_NUMBER_EIGHT, VALID_TASK_INDEX_ONE, descriptor);
        EditTaskCommand editTaskTwoFromRoomSeven = new EditTaskCommand(
                VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_TWO, descriptor);

        // same object -> returns true
        assertTrue(editTaskOneFromRoomSeven.equals(editTaskOneFromRoomSeven));

        // same values -> returns true
        EditTaskCommand editTaskOneFromRoomSevenCopy = new EditTaskCommand(
                VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE, descriptor);
        assertTrue(editTaskOneFromRoomSeven.equals(editTaskOneFromRoomSevenCopy));

        // different types -> returns false
        assertFalse(editTaskOneFromRoomSeven.equals(1));

        // null -> returns false
        assertFalse(editTaskOneFromRoomSeven.equals(null));

        // different room number -> returns false
        assertFalse(editTaskOneFromRoomSeven.equals(editTaskOneFromRoomEight));

        // different task index -> returns false
        assertFalse(editTaskOneFromRoomSeven.equals(editTaskTwoFromRoomSeven));

        // different edit task descriptor -> returns false
        EditTaskDescriptor modifiedDescriptor = new EditTaskDescriptor();
        modifiedDescriptor.setDescription(new Description(VALID_DESCRIPTION_REMIND_PATIENT));
        EditTaskCommand editTaskOneFromRoomSevenModifiedDescriptor = new EditTaskCommand(
                VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE, modifiedDescriptor);
        assertFalse(editTaskOneFromRoomSeven.equals(editTaskOneFromRoomSevenModifiedDescriptor));
    }
}
