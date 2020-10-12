package seedu.address.model.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRooms.*;
import static seedu.address.testutil.TypicalTasks.REMIND_PATIENT;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.Task;
import seedu.address.testutil.RoomBuilder;

/**
 * Contains integration tests for the interactions between {@code Room} and {@code Task}.
 */
public class RoomIntegrationTest {

    @Test
    public void addTask_nullTask_throwsNullPointerException() {
        Room room = new RoomBuilder().build();
        assertThrows(NullPointerException.class, () -> room.addTask(null));
    }

    @Test
    public void addTask_validTask_success() {
        Room room = new RoomBuilder(ROOM_PATIENT_ALICE_NO_TASK).build();
        room.addTask(REMIND_PATIENT);
        System.out.println(room.getTaskList().getInternalList().size());
        System.out.println(ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT.getTaskList().getInternalList().size());
        System.out.println(room.getTaskList().equals(ROOM_PATIENT_ALICE_NO_TASK.getTaskList()));
        assertEquals(ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT, room);
    }

}
