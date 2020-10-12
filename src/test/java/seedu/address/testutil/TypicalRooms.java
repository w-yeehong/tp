package seedu.address.testutil;

import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalTasks.REMIND_PATIENT;
import static seedu.address.testutil.TypicalTasks.RESTOCK_SUPPLY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.room.Room;
import seedu.address.model.room.RoomList;

public class TypicalRooms {

    public static final Room ROOM_NO_PATIENT_NO_TASK = new RoomBuilder()
            .withIsOccupied(false).withPatient(null).build();
    public static final Room ROOM_NO_PATIENT_TASK_RESTOCK_SUPPLY = new RoomBuilder()
            .withIsOccupied(false).withPatient(null).withTasks(RESTOCK_SUPPLY).build();
    public static final Room ROOM_PATIENT_ALICE_NO_TASK = new RoomBuilder()
            .withIsOccupied(true).withPatient(ALICE).build();
    public static final Room ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT = new RoomBuilder()
            .withIsOccupied(true).withPatient(ALICE).withTasks(REMIND_PATIENT).build();
    public static final Room ROOM_INVALID_PATIENT_PRESENT_OCCUPIED_FALSE = new RoomBuilder()
            .withIsOccupied(false).withPatient(ALICE).build();

    private TypicalRooms() {
    } // prevents instantiation

    public static RoomList getTypicalRoomList() {
        RoomList roomList = new RoomList();
        for (Room room : getTypicalRooms()) {
            roomList.addRooms(room);
        }
        return roomList;
    }

    public static List<Room> getTypicalRooms() {
        System.out.println(ROOM_PATIENT_ALICE_NO_TASK.getTaskList());
        return new ArrayList<>(Arrays.asList(ROOM_NO_PATIENT_NO_TASK, ROOM_NO_PATIENT_TASK_RESTOCK_SUPPLY,
                ROOM_PATIENT_ALICE_NO_TASK, ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT));
    }
}
