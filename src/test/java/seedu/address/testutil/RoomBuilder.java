package seedu.address.testutil;

import seedu.address.model.patient.Patient;
import seedu.address.model.room.Room;
import seedu.address.model.tasks.Task;

public class RoomBuilder {

    public static final int DEFAULT_ROOM_NUMBER = 10;
    public static final boolean DEFAULT_IS_OCCUPIED = true;
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

    /**
     * Initializes the RoomBuilder with the data of {@code roomToCopy}.
     */
    public RoomBuilder(Room roomToCopy) {
        roomNumber = roomToCopy.getRoomNumber();
        isOccupied = roomToCopy.isOccupied();
        patient = roomToCopy.getPatient();
        task = roomToCopy.getTask();
    }

    /**
     * Sets the {@code roomNumber} of the {@code Room} that we are building.
     */
    public RoomBuilder withRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }
    /**
     * Sets the {@code isOccupied} of the {@code Room} that we are building.
     */
    public RoomBuilder withIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
        return this;
    }

    /**
     * Sets the {@code patient} of the {@code Room} that we are building.
     */
    public RoomBuilder withPatient(Patient patient) {
        this.patient = patient;
        return this;
    }
    /**
     * Sets the {@code task} of the {@code Room} that we are building.
     */
    public RoomBuilder withTask(Task task) {
        this.task = task;
        return this;
    }

    public Room build() {
        return new Room(roomNumber, isOccupied);
    }
}

