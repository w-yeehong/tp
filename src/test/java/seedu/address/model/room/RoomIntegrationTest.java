package seedu.address.model.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRooms.ROOM_PATIENT_ALICE_NO_TASK;
import static seedu.address.testutil.TypicalRooms.ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT;
import static seedu.address.testutil.TypicalTasks.REMIND_PATIENT;

import org.junit.jupiter.api.Test;

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
        assertEquals(ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT, room);
    }

}
