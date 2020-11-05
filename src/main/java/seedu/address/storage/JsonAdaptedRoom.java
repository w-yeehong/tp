package seedu.address.storage;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomTasks;
import seedu.address.model.task.Task;

//@@author itssodium
public class JsonAdaptedRoom {

    public static final String PATIENT_PRESENT_IS_OCCUPIED_FALSE = "When patient is present isOccupied cannot be false";
    public static final String PATIENT_ABSENT_IS_OCCUPIED_TRUE = "When patient is absent isOccupied cannot be true";
    public static final String DATE_WRONG_FORMAT_IN_TASKS = "The date is given in the wrong format in tasks "
            + "or the task room number does not correspond to room number.";
    public static final String PATIENT_WRONG_FORMAT = "The patient is given in the wrong format";

    private int roomNumber;
    private boolean isOccupied;
    private JsonAdaptedPatient patient;
    private JsonSerializableTaskList tasks;

    /**
     * Creates JsonAdaptedRoom based on the inputs given by the user of roomNumber and isOccupied
     */
    @JsonCreator
    public JsonAdaptedRoom(@JsonProperty("roomNumber") int roomNumber,
                           @JsonProperty("isOccupied") boolean isOccupied,
                           @JsonProperty("patient") JsonAdaptedPatient patient,
                           @JsonProperty("tasks") JsonSerializableTaskList tasks) throws IllegalValueException {
        this.roomNumber = roomNumber;
        this.isOccupied = isOccupied;
        if (patient != null) {
            this.patient = patient;
        }
        if (tasks != null) {
            this.tasks = tasks;
        }

    }

    /**
     * Creates JsonAdaptedRoom from Room object given
     */
    public JsonAdaptedRoom(Room source) {
        this.roomNumber = source.getRoomNumber();
        this.isOccupied = source.isOccupied();
        if (source.getPatient().isPresent()) {
            this.patient = new JsonAdaptedPatient(source.getPatient().get());
        }
        this.tasks = new JsonSerializableTaskList(source.getReadOnlyList());
    }

    /**
     * Converts this Jackson-friendly adapted Room object into the model's {@code Room} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted room.
     */
    public Room toModelType() throws IllegalValueException {
        if (this.patient != null && !isOccupied) {
            throw new IllegalValueException(PATIENT_PRESENT_IS_OCCUPIED_FALSE);
        }
        if (this.patient == null && isOccupied) {
            throw new IllegalValueException(PATIENT_ABSENT_IS_OCCUPIED_TRUE);
        }
        if (patient != null && isPatientInWrongFormat()) {
            throw new IllegalValueException(PATIENT_WRONG_FORMAT);
        }
        if (isTaskNotInCorrectFormat()) {
            throw new IllegalValueException(DATE_WRONG_FORMAT_IN_TASKS);
        }
        if (this.patient == null) {
            return new Room(roomNumber, isOccupied, Optional.empty(), tasks.toModelType());
        }
        return new Room(roomNumber, isOccupied, Optional.of(patient.toModelType()), tasks.toModelType());
    }

    private boolean isPatientInWrongFormat() {
        try {
            patient.toModelType();
            return false;
        } catch (IllegalValueException i) {
            return true;
        }
    }


    private boolean isTaskNotInCorrectFormat() {
        try {
            RoomTasks roomTasks = tasks.toModelType();
            ObservableList<Task> taskLists = roomTasks.getReadOnlyList();
            //check if task room number is same as room number
            for (Task task : taskLists) {
                if (task.getTaskRoomNumber() != this.roomNumber) {
                    return true;
                }
            }
            return false;
        } catch (IllegalValueException i) {
            return true;
        }
    }
}
