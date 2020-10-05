package seedu.address.testutil;

import seedu.address.model.patient.Age;
import seedu.address.model.patient.Comment;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.PeriodOfStay;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Temperature;
import seedu.address.model.room.Room;
import seedu.address.model.tasks.Task;

public class RoomBuilder {

    public static final int DEFAULT_ROOM_NUMBER = 10;
    public static boolean DEFAULT_IS_OCCUPIED = true;
    public static final Patient DEFAULT_PATIENT = TypicalPatients.ALICE;
    public static final Task DEFAULT_TASK = null;

    private int roomNumber;
    private boolean isOccupied;
    private Patient patient;
    private Task task;

    /**
     * Creates a {@code PatientBuilder} with the default details.
     */
    public RoomBuilder() {
        roomNumber = DEFAULT_ROOM_NUMBER;
        isOccupied = DEFAULT_IS_OCCUPIED;
        patient = DEFAULT_PATIENT;
        task = DEFAULT_TASK;
    }

    public RoomBuilder(Room roomToCopy) {
        roomNumber = roomToCopy.getRoomNumber();
        isOccupied = roomToCopy.isOccupied();
        patient = roomToCopy.getPatient();
        task = roomToCopy.getTask();
    }

    public RoomBuilder withRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }

    public RoomBuilder withIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
        return this;
    }

    public RoomBuilder withPatient(Patient patient) {
        this.patient = patient;
        return this;
    }

    public RoomBuilder withTask(Task task) {
        this.task = task;
        return this;
    }

    public Room build() {
        return new Room(roomNumber, isOccupied);
    }
}

