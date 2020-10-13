package seedu.address.model.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRooms.ROOM_PATIENT_ALICE_NO_TASK;
import static seedu.address.testutil.TypicalRooms.ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT;
import static seedu.address.testutil.TypicalTasks.REMIND_PATIENT;
import static seedu.address.testutil.TypicalTasks.RESTOCK_SUPPLY;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.RoomList;
import seedu.address.model.room.exceptions.RoomNotFoundException;
import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.testutil.RoomBuilder;

/**
 * Contains integration tests for the interactions between {@code RoomList}
 * and its dependencies ({@code Room} and {@code Task}).
 */
public class RoomListIntegrationTest {

    private RoomList roomList; // note that this is mutable

    @BeforeEach
    public void setUp() {
        roomList = new RoomList();
    }

    @Test
    public void addTaskToRoom_validTaskNullRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roomList.addTaskToRoom(REMIND_PATIENT, null));
    }

    @Test
    public void addTaskToRoom_nullTaskValidRoom_throwsNullPointerException() {
        Room room = new RoomBuilder().build();
        assertThrows(NullPointerException.class, () -> roomList.addTaskToRoom(null, room));
    }

    @Test
    public void addTaskToRoom_validTaskRoomNotInRoomList_throwsRoomNotFoundException() {
        Room room = new RoomBuilder().build();
        assertThrows(RoomNotFoundException.class, () ->
                roomList.addTaskToRoom(RESTOCK_SUPPLY, room));
    }

    @Test
    public void addTaskToRoom_validTaskValidRoom_success() {
        Room room = new RoomBuilder(ROOM_PATIENT_ALICE_NO_TASK).build();
        roomList.addRooms(room);
        roomList.addTaskToRoom(REMIND_PATIENT, room);

        RoomList expectedRoomList = new RoomList();
        expectedRoomList.addRooms(ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT);

        assertEquals(expectedRoomList, roomList);
    }

    @Test
    public void deleteTaskFromRoom_validTaskNullRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roomList.deleteTaskFromRoom(REMIND_PATIENT, null));
    }

    @Test
    public void deleteTaskFromRoom_nullTaskValidRoom_throwsNullPointerException() {
        Room room = new RoomBuilder().build();
        assertThrows(NullPointerException.class, () -> roomList.deleteTaskFromRoom(null, room));
    }

    @Test
    public void deleteTaskFromRoom_validTaskRoomNotInRoomList_throwsRoomNotFoundException() {
        Room room = new RoomBuilder(ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT).build();
        assertThrows(RoomNotFoundException.class, () ->
                roomList.deleteTaskFromRoom(REMIND_PATIENT, room));
    }

    @Test
    public void deleteTaskFromRoom_validRoomTaskNotInTaskList_throwsRoomNotFoundException() {
        Room room = new RoomBuilder(ROOM_PATIENT_ALICE_NO_TASK).build();
        roomList.addRooms(room);
        assertThrows(TaskNotFoundException.class, () ->
                roomList.deleteTaskFromRoom(REMIND_PATIENT, room));
    }

    @Test
    public void deleteTaskFromRoom_validTaskValidRoom_success() {
        Room room = new RoomBuilder(ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT).build();
        roomList.addRooms(room);
        roomList.deleteTaskFromRoom(REMIND_PATIENT, room);

        RoomList expectedRoomList = new RoomList();
        expectedRoomList.addRooms(ROOM_PATIENT_ALICE_NO_TASK);

        assertEquals(expectedRoomList, roomList);
    }

}
