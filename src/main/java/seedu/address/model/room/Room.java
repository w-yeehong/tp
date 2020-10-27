package seedu.address.model.room;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.model.patient.Patient;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskList;
import seedu.address.model.task.exceptions.TaskNotFoundException;

/**
 * Represents Room in the app
 */
public class Room {

    private int roomNumber;
    private boolean isOccupied;
    private Optional<Patient> patient;
    private TaskList taskList = new TaskList();

    /**
     * Creates room object where isOccupied is always false
     */
    public Room(int roomNumber) {
        requireAllNonNull(roomNumber);
        this.roomNumber = roomNumber;
        this.isOccupied = false;
        this.patient = Optional.empty();
    }

    /**
     * Creates room object where roomNumber and isOccupied values are values given by user
     */
    public Room(int roomNumber, boolean isOccupied) {
        requireAllNonNull(roomNumber, isOccupied);
        this.roomNumber = roomNumber;
        this.isOccupied = isOccupied;
        this.patient = Optional.empty();
    }

    /**
     * Creates a room object containing a patient that can be found in the application.
     *
     * @param roomNumber Room Number of the room.
     * @param patient Patient to be added to the room.
     * @param taskList TaskList of tasks for the room.
     */
    public Room(int roomNumber, Optional<Patient> patient, TaskList taskList) {
        requireAllNonNull(roomNumber, patient, taskList);
        this.roomNumber = roomNumber;
        this.isOccupied = true;
        this.patient = patient;
        this.taskList = taskList;
    }

    /**
     * Creates a Room object where none of the values are pre determined by app
     */
    public Room(int roomNumber, boolean isOccupied, Optional<Patient> patient, TaskList taskList) {
        requireAllNonNull(roomNumber, isOccupied, patient, taskList);
        this.roomNumber = roomNumber;
        this.isOccupied = isOccupied;
        this.patient = patient;
        this.taskList = taskList;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public Patient getPatient() {
        if (patient.isEmpty()) {
            return null;
        } else {
            return patient.get();
        }
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

    public void setPatient(Patient patient) {
        this.patient = Optional.ofNullable(patient);
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
     * Adds tasks of TaskList to the task list of this room.
     *
     * @param tasks The task to add.
     */
    public void addTask(TaskList tasks) {
        this.taskList.add(tasks);
    }

    /**
     * Deletes a task from the task list of this room.
     * The task must be in this room.
     *
     * @param task The task to delete.
     * @throws TaskNotFoundException if task is not found in the task list of this room.
     */
    public void deleteTask(Task task) {
        try {
            taskList.remove(task);
        } catch (TaskNotFoundException e) {
            throw e;
        }
    }

    /**
     * Replaces a task from the task list of this room with {@code editedTask}.
     * The task must be in this room.
     *
     * @param target The task to replace.
     * @param editedTask The modified task to replace the original.
     * @throws TaskNotFoundException if task is not found in the task list of this room.
     */
    public void setTask(Task target, Task editedTask) {
        try {
            taskList.setTask(target, editedTask);
        } catch (TaskNotFoundException e) {
            throw e;
        }
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList.setTasks(taskList);
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
        if (taskList == null && patient.isEmpty()) {
            return room.taskList == null
                    && room.patient.isEmpty()
                    && roomNumber == room.roomNumber
                    && isOccupied == room.isOccupied;
        } else if (taskList == null) {
            return roomNumber == room.roomNumber
                    && room.taskList == null
                    && isOccupied == room.isOccupied
                    && patient.equals(room.getPatient());
        } else if (patient.isEmpty()) {
            System.out.println(roomNumber == room.roomNumber
                    && room.patient.isEmpty()
                    && isOccupied == room.isOccupied
                    && taskList.equals(room.getTaskList()));
            return roomNumber == room.roomNumber
                    && room.patient.isEmpty()
                    && isOccupied == room.isOccupied
                    && taskList.equals(room.getTaskList());
        } else {
            return roomNumber == room.roomNumber
                    && isOccupied == room.isOccupied
                    && patient.get().equals(room.getPatient())
                    && taskList.equals(room.getTaskList());
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, isOccupied, taskList);
    }


    @Override
    public String toString() {
        String patientDetails = getPatient() == null ? "-" : getPatient().toString();
        final StringBuilder builder = new StringBuilder();
        builder.append("Room Number: ")
                .append(getRoomNumber() + "\n")
                .append("Patient: ")
                .append(patientDetails + "\n")
                .append("TaskList: ")
                .append(taskList.toString() + "\n");
        return builder.toString();
    }
}