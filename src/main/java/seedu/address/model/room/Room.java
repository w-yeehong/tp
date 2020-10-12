package seedu.address.model.room;

import java.util.Objects;

import seedu.address.model.patient.Patient;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskList;

/**
 * Represents Room in the app
 */
public class Room implements Comparable<Room> {

    private int roomNumber;
    private boolean isOccupied;
    private Patient patient;
    private TaskList taskList = new TaskList();

    /**
     * Creates room object where isOccupied is always false
     */
    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isOccupied = false;
        this.patient = null;
    }

    /**
     * Creates room object where roomNumber and isOccupied values are values given by user
     */
    public Room(int roomNumber, boolean isOccupied) {
        this.roomNumber = roomNumber;
        this.isOccupied = isOccupied;
        this.patient = null;
    }

    /**
     * Creates a room object containing a patient that can be found in the application.
     *
     * @param roomNumber Room Number of the room.
     * @param patient Patient to be added to the room.
     * @param taskList TaskList of tasks for the room.
     */
    public Room(int roomNumber, Patient patient, TaskList taskList) {
        this.roomNumber = roomNumber;
        this.isOccupied = true;
        this.patient = patient;
        this.taskList = taskList;
    }

    /**
     * Creates a Room object where none of the values are pre determined by app
     */
    public Room(int roomNumber, boolean isOccupied, Patient patient, TaskList taskList) {
        this.roomNumber = roomNumber;
        this.isOccupied = isOccupied;
        this.patient = patient;
        this.taskList = taskList;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public Patient getPatient() {
        return patient;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    /**
     * Adds a task to the task list of this room.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }
    /**
     * Returns true if both rooms of the same number have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two rooms.
     */
    public boolean isSameRoom(Room otherRoom) {
        if (otherRoom == this) {
            return true;
        }

        return otherRoom != null
                && Integer.valueOf(otherRoom.getRoomNumber()).equals(getRoomNumber());
    }

    /**
     * Returns true if both rooms have the same identity and data fields.
     * This defines a stronger notion of equality between two rooms.
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
                && isOccupied == room.isOccupied
                && taskList.equals(room.getTaskList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, isOccupied, taskList);
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
