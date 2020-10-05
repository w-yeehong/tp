package seedu.address.model.room;

import java.util.Objects;

import seedu.address.logic.parser.Parser;
import seedu.address.model.patient.Patient;
import seedu.address.model.tasks.Task;

/**
 * Represents Room in the app
 */
public class Room implements Comparable<Room> {
    private int roomNumber;
    private boolean isOccupied;
    private Patient patient;
    private Task task;

    /**
     * Creates room object where roomNumber and isOccupied values are values given by user
     */
    public Room(int roomNumber, boolean isOccupied) {
        this.roomNumber = roomNumber;
        this.isOccupied = isOccupied;
        this.patient = null;
        this.task = null;
    }

    /**
     * Creates room object where isOccupied is always false
     */
    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isOccupied = false;
        this.patient = null;
        this.task = null;
    }

    public Room(int roomNumber, boolean isOccupied, Patient patient, Task task) {
        this.roomNumber = roomNumber;
        this.isOccupied = isOccupied;
        this.patient = patient;
        this.task = task;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public Patient getPatient() {
        return patient;
    }

    public Task getTask() {
        return task;
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    /**
     * Returns true if both rooms have the same identity and data fields.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room = (Room) o;
        return roomNumber == room.roomNumber
                && isOccupied == room.isOccupied;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, isOccupied);
    }

    @Override
    public int compareTo(Room room) {
        if (room.isOccupied == this.isOccupied) {
            if (room.roomNumber < this.roomNumber) {
                return 1;
            } else {
                return -1;
            }
        } else {
            if (room.isOccupied) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    public String toString() {
        return "Room number " + this.roomNumber;
    }
}
