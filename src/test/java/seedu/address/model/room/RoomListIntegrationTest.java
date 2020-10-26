package seedu.address.model.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRooms.ROOM_PATIENT_ALICE_NO_TASK;
import static seedu.address.testutil.TypicalRooms.ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT;
import static seedu.address.testutil.TypicalTasks.REMIND_PATIENT;
import static seedu.address.testutil.TypicalTasks.RESTOCK_SUPPLY;
import static seedu.address.testutil.command.TaskCommandTestUtil.VALID_TASK_INDEX_ONE;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.RoomList;
import seedu.address.model.room.exceptions.RoomNotFoundException;
import seedu.address.model.task.Task;
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
    public void getTaskFromRoomWithTaskIndex_nullTaskIndex_throwsNullPointerException() {
        Room room = new RoomBuilder().build();
        assertThrows(NullPointerException.class, () ->
                roomList.getTaskFromRoomWithTaskIndex(null, room));
    }

    @Test
    public void getTaskFromRoomWithTaskIndex_nullRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                roomList.getTaskFromRoomWithTaskIndex(VALID_TASK_INDEX_ONE, null));
    }

    @Test
    public void getTaskFromRoomWithTaskIndex_taskNotInRoom_returnsEmptyOptional() {
        Room room = new RoomBuilder(ROOM_PATIENT_ALICE_NO_TASK).build();
        Optional<Task> optionalTask = roomList.getTaskFromRoomWithTaskIndex(VALID_TASK_INDEX_ONE, room);
        assertTrue(optionalTask.isEmpty());
    }

    @Test
    public void getTaskFromRoomWithTaskIndex_taskInRoom_returnsTask() {
        Room room = new RoomBuilder(ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT).build();
        Optional<Task> optionalTask = roomList.getTaskFromRoomWithTaskIndex(VALID_TASK_INDEX_ONE, room);
        assertTrue(optionalTask.map(task -> task.equals(REMIND_PATIENT)).orElse(false));
    }

    @Test
    public void addTaskToRoom_nullRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roomList.addTaskToRoom(REMIND_PATIENT, null));
    }

    @Test
    public void addTaskToRoom_nullTask_throwsNullPointerException() {
        Room room = new RoomBuilder().build();
        assertThrows(NullPointerException.class, () -> roomList.addTaskToRoom(null, room));
    }

    @Test
    public void addTaskToRoom_roomNotInRoomList_throwsRoomNotFoundException() {
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
    public void deleteTaskFromRoom_nullRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roomList.deleteTaskFromRoom(REMIND_PATIENT, null));
    }

    @Test
    public void deleteTaskFromRoom_nullTask_throwsNullPointerException() {
        Room room = new RoomBuilder().build();
        assertThrows(NullPointerException.class, () -> roomList.deleteTaskFromRoom(null, room));
    }

    @Test
    public void deleteTaskFromRoom_roomNotInRoomList_throwsRoomNotFoundException() {
        Room room = new RoomBuilder(ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT).build();
        assertThrows(RoomNotFoundException.class, () ->
                roomList.deleteTaskFromRoom(REMIND_PATIENT, room));
    }

    @Test
    public void deleteTaskFromRoom_taskNotInTaskList_throwsTaskNotFoundException() {
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

    @Test
    public void setTaskToRoom_nullRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roomList.setTaskToRoom(REMIND_PATIENT, RESTOCK_SUPPLY, null));
    }

    @Test
    public void setTaskToRoom_nullEditedTask_throwsNullPointerException() {
        Room room = new RoomBuilder().build();
        assertThrows(NullPointerException.class, () -> roomList.setTaskToRoom(REMIND_PATIENT, null, room));
    }

    @Test
    public void setTaskToRoom_nullTarget_throwsNullPointerException() {
        Room room = new RoomBuilder().build();
        assertThrows(NullPointerException.class, () -> roomList.setTaskToRoom(null, RESTOCK_SUPPLY, room));
    }

    @Test
    public void setTaskToRoom_roomNotInRoomList_throwsRoomNotFoundException() {
        Room room = new RoomBuilder(ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT).build();
        assertThrows(RoomNotFoundException.class, () ->
                roomList.setTaskToRoom(REMIND_PATIENT, RESTOCK_SUPPLY, room));
    }

    @Test
    public void setTaskToRoom_taskNotInTaskList_throwsTaskNotFoundException() {
        Room room = new RoomBuilder(ROOM_PATIENT_ALICE_NO_TASK).build();
        roomList.addRooms(room);
        assertThrows(TaskNotFoundException.class, () ->
                roomList.setTaskToRoom(REMIND_PATIENT, RESTOCK_SUPPLY, room));
    }

    @Test
    public void setTaskToRoom_validTaskValidEditedTaskValidRoom_success() {
        Room room = new RoomBuilder(ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT).build();
        roomList.addRooms(room);
        roomList.setTaskToRoom(REMIND_PATIENT, RESTOCK_SUPPLY, room);

        RoomList expectedRoomList = new RoomList();
        Room expectedRoom = new RoomBuilder(ROOM_PATIENT_ALICE_NO_TASK).withTasks(RESTOCK_SUPPLY).build();
        expectedRoomList.addRooms(expectedRoom);

        assertEquals(expectedRoomList, roomList);
    }

}
